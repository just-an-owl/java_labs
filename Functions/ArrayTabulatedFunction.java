package Functions;

import Functions.FunctionPoint;

import java.util.Arrays;

public class ArrayTabulatedFunction implements TabulatedFunction{
    private FunctionPoint[] arrayPoint;
    private final double leftX;
    private final double rightX;
    private final double leftY = 0;
    private final double rightY = 0;
    private final double step;
    private int fullnessArrayPoint;//кол-во заполненных ячеек

    public ArrayTabulatedFunction(final double newLeftX, final double newRightX, final int pointsCount) {
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

    public void addPoint(FunctionPoint point) {
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
    }

    public FunctionPoint getPoint(int n){
        if(n>=0 & n<fullnessArrayPoint)
        {
            return (FunctionPoint)arrayPoint[n];
        }
        return null;
    }

    public double getLeftDomainBorder() {
        return leftX;
    }

    public double getRightDomainBorder() {
        return rightX;
    }

    public double getFunctionValue(final double x){
        int iter = 0;
        double k;
        double b;
        if(x>leftX & x<rightX){
            while(iter<arrayPoint.length && x>arrayPoint[iter].getX()) 
            {
                ++iter;
            }            
            if(iter==fullnessArrayPoint){
                k=(rightY-arrayPoint[iter-1].getY())/(rightX-arrayPoint[iter-1].getX());
                b=rightY - k*rightX;
                return k*x+b;
            }
            else
            {
                k=(arrayPoint[iter-1].getY()-arrayPoint[iter].getY())/(arrayPoint[iter-1].getX()-arrayPoint[iter].getX());
                b=rightY - k*rightX;
                return k*x+b;
            }
        }
        else{
            return Double.NaN;
        }
    }

    public int getPointCount() {
        return fullnessArrayPoint;
    }

    public void setPoint(final int index, final FunctionPoint point){
        if(leftX<point.getX() & rightX>point.getX()){
            if((arrayPoint[index-1].getX()<point.getX()||index==0) & (arrayPoint[index+1].getX()>point.getX()||index+1==arrayPoint.length)){
                arrayPoint[index].set(point);
                ++fullnessArrayPoint;
            }
        }
    }

    public double getPointX(final int index) {
        if (arrayPoint.length > index){
            return arrayPoint[index].getX();
        }
        return Double.NaN;
    }

    public void setPointX(final int index, final double x){
        if(leftX<x & rightX>x){
            if(arrayPoint[index-1].getX()<x & arrayPoint[index+1].getX()>x){
                arrayPoint[index].setX(x);
            }
        }
    }

    public double getPointY(final int index){
        if(index<arrayPoint.length){
            return arrayPoint[index].getY();
        }
        return Double.NaN;
    }

    public void setPointY(final int index, final double Y) {
        if (index < arrayPoint.length) {
            arrayPoint[index].setY(Y);
            ++fullnessArrayPoint;
        }
    }

    public void deletePoint(final int index) {
        for(int i = index; i<arrayPoint.length-1;++i){
            arrayPoint[i].set(arrayPoint[i+1]);
        }        
        arrayPoint[arrayPoint.length-1].setY(Double.NaN);
        fullnessArrayPoint--;
    }    
}