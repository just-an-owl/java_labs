package functions;

import functions.FunctionPoint;

import java.util.Arrays;
import java.io.Serializable;

public class ArrayTabulatedFunction implements TabulatedFunction, Serializable {
    private FunctionPoint[] arrayPoint;
    private final double leftX;
    private final double rightX;
    private final double leftY = 0;
    private final double rightY = 0;
    private final double step;
    private int fullnessArrayPoint;//кол-во заполненных ячеек

    public ArrayTabulatedFunction(final double newLeftX, final double newRightX, final int pointsCount) throws InappropriateFunctionPointException{
        if (pointsCount<0)
            throw new InappropriateFunctionPointException("invalid counts of point, count must be >0");
        leftX = newLeftX;
        rightX = newRightX;
        fullnessArrayPoint = 0;
        step = (rightX - leftX) / (pointsCount + 2);
        arrayPoint = new FunctionPoint[pointsCount];
        for (int i = 0; i < arrayPoint.length; ++i) {            
            arrayPoint[i] = new FunctionPoint(leftX + step + i * step, Double.NaN);
        }
    }

    public ArrayTabulatedFunction(final double newLeftX, final double newRightX, final double[] values){
        arrayPoint = new FunctionPoint[values.length];
        rightX=newRightX;
        leftX=newLeftX;
        step = (rightX-leftX)/(arrayPoint.length+2);
        int iter;
        for(iter=0;iter<arrayPoint.length;iter++){
            arrayPoint[iter] = new FunctionPoint(leftX + step + iter * step, values[iter]);
        }
        fullnessArrayPoint=arrayPoint.length;
    }
    public ArrayTabulatedFunction(final double newLeftX, final double newRightX,FunctionPoint[] points) throws IllegalArgumentException{
        leftX = newLeftX;
        rightX = newRightX;
        step = (rightX-leftX)/(arrayPoint.length+2);
        arrayPoint = new FunctionPoint[points.length];
        if (points.length<2) throw new IllegalArgumentException("counts of points must be >=2");
        for(int i = 0; i<points.length-1;++i){
            if (points[i].getX()>points[i+1].getX()) throw new IllegalArgumentException("points must be sorted");
        }
        for (int i = 0; i<points.length;++i){
            arrayPoint[i] = new FunctionPoint(points[i]);
        }
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        if(point.getX()>leftX && point.getX()<rightX)
            {if(arrayPoint.length == fullnessArrayPoint){
                FunctionPoint[] n_mass = new FunctionPoint[arrayPoint.length+20];
                int i=0;
                while(point.getX()>arrayPoint[i].getX()){
                    ++i;
                }
                System.arraycopy(arrayPoint, 0, n_mass, 0, i);
                n_mass[i].set(point);
                System.arraycopy(arrayPoint, i, n_mass, i+1, arrayPoint.length-i);
                arrayPoint=n_mass;            
            }
            else{
                FunctionPoint[] n_mass = new FunctionPoint[arrayPoint.length];
                int i=0;
                while(point.getX()>arrayPoint[i].getX()){
                    ++i;
                }
                System.arraycopy(arrayPoint, 0, n_mass, 0, i);
                n_mass[i] = new FunctionPoint(point);
                System.arraycopy(arrayPoint, i, n_mass, i+1, fullnessArrayPoint-i);
                arrayPoint=n_mass;      
            }
        }
        else throw new InappropriateFunctionPointException("invalid value X of point");
    }

    public FunctionPoint getPoint(int n) throws FunctionPointIndexOutOfBoundsException{
        if(n>=0 & n<fullnessArrayPoint)
        {
            return (FunctionPoint)arrayPoint[n];
        }
        else throw new FunctionPointIndexOutOfBoundsException("invalid number");

    }

    public double getLeftDomainBorder() {
        return leftX;
    }

    public double getRightDomainBorder() {
        return rightX;
    }

    public double getFunctionValue(final double x) throws InappropriateFunctionPointException{
        int iter = 0;
        double k;
        double b;

        if(x>leftX && x<rightX){

            while(iter<arrayPoint.length && x>arrayPoint[iter].getX()) 
            {
                ++iter;
            }
            if (iter==0) {
                double y1 = 0;
                double x1 = leftX;
                double y2 = arrayPoint[arrayPoint.length].getY();
                double x2 = arrayPoint[arrayPoint.length].getX();
                b=(y2-(y1*x1/x2))/(((-1)*x2/x1)+1);
                k = (y1-b)/x1;
                return k*x+b;
            }
            if(iter==fullnessArrayPoint){
                double y1 = 0;
                double x1 = rightX;
                double y2 = arrayPoint[arrayPoint.length-1].getY();
                double x2 = arrayPoint[arrayPoint.length-1].getX();
                b=(y2-(y1*x1/x2))/(((-1)*x2/x1)+1);
                k = (y1-b)/x1;
                return k*x+b;
            }
            else
            {
                double y1 = arrayPoint[iter].getY();
                double x1 = arrayPoint[iter].getX();
                double y2 = arrayPoint[iter-1].getY();
                double x2 = arrayPoint[iter-1].getX();

                b=(y2-(y1*x1/x2))/(((-1)*x2/x1)+1);

                k = (y1-b)/x1;

                return k*x+b;
            }
        }
        else{
            throw new InappropriateFunctionPointException("invalid value");
        }
    }

    public int getPointCount() {
        return fullnessArrayPoint;
    }

    public void setPoint(final int index, final FunctionPoint point){
        if(leftX<point.getX() & rightX>point.getX()){
            if((index==0||arrayPoint[index-1].getX()<point.getX()) & (index+1==arrayPoint.length||arrayPoint[index+1].getX()>point.getX())){
                arrayPoint[index].set(point);
                ++fullnessArrayPoint;
            }
        }
    }

    public double getPointX(final int index) throws FunctionPointIndexOutOfBoundsException {
        if(index>=0 & index<fullnessArrayPoint){
            return arrayPoint[index].getX();
        }
        else throw new FunctionPointIndexOutOfBoundsException("invalid number");
    }

    public void setPointX(final int index, final double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException{
        if(leftX<x & rightX>x){
            if((index==0||arrayPoint[index-1].getX()<x) & (index+1==arrayPoint.length||arrayPoint[index+1].getX()>x)){
                arrayPoint[index].setX(x);
            }
            else throw new FunctionPointIndexOutOfBoundsException("invalid number");
        }
        else throw new InappropriateFunctionPointException("invalid value");
    }

    public double getPointY(final int index) throws FunctionPointIndexOutOfBoundsException{
        if(index>=0 && index<fullnessArrayPoint){
            return arrayPoint[index].getY();
        }
        else throw new FunctionPointIndexOutOfBoundsException("invalid number");
    }

    public void setPointY(final int index, final double Y) throws FunctionPointIndexOutOfBoundsException{
        if (index>=0 && index<fullnessArrayPoint) {
            arrayPoint[index].setY(Y);
            ++fullnessArrayPoint;
        }
        else throw new FunctionPointIndexOutOfBoundsException("invalid number");
    }

    public void deletePoint(final int index) throws FunctionPointIndexOutOfBoundsException{
        if (fullnessArrayPoint-1<3)
            throw  new IllegalStateException("points must be more 3");
        if (index>=0 && index<fullnessArrayPoint)
        {
            for(int i = index; i<arrayPoint.length-1;++i)
            {
                arrayPoint[i].set(arrayPoint[i+1]);
            }
            arrayPoint[arrayPoint.length-1].setY(Double.NaN);
            fullnessArrayPoint--;
        }
        else throw new FunctionPointIndexOutOfBoundsException("invalid index");
    }    
}