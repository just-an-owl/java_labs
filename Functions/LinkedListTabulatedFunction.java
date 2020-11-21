package functions;


import java.io.Serializable;
import java.util.Arrays;


public class LinkedListTabulatedFunction implements TabulatedFunction, Serializable{

    private PointList list;
    private double leftX;
    private double rightX;



    public LinkedListTabulatedFunction(final double newLeftX, final double newRightX, final int pointsCount){
        leftX = newLeftX;
        rightX = newRightX;
        double step = (rightX - leftX) / (pointsCount + 2);
        list = new PointList();
        for (int i=0; i<pointsCount; ++i){
            list.addNodeToTail(new FunctionPoint(step+step*i, 0));
        }
    }

    public LinkedListTabulatedFunction(final double newLeftX, final double newRightX,FunctionPoint[] points) throws IllegalArgumentException{
        leftX = newLeftX;
        rightX = newRightX;
        list = new PointList();
        if (points.length<2) throw new IllegalArgumentException("counts of points must be >=2");
        for(int i = 0; i<points.length-1;++i){
            if (points[i].getX()>points[i+1].getX()) throw new IllegalArgumentException("points must be sorted");
        }
        for (int i = 0; i<points.length;++i){
            list.addNodeToTail(new FunctionPoint(points[i]));
        }
    }

    public LinkedListTabulatedFunction(final double newLeftX, final double newRightX,double[] values){
        leftX = newLeftX;
        rightX = newRightX;
        list = new PointList();
        if (values.length<2) throw new IllegalArgumentException("counts of points must be >=2");
        double step;
        step = (rightX-leftX)/(list.getCount()+2);
        int iter;
        for(iter=0;iter< list.getCount();iter++){
            list.addNodeToTail(new FunctionPoint(leftX + step + iter * step, values[iter]));
        }
    }

    @Override
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException{
        if(point.getX()>leftX && point.getX()<rightX)
        {
            int i=0;
            while(point.getX()>list.getPoint(i).getX()){
                ++i;
            }
            list.addByIndex(point, i);
        }
        else
        {
            throw new InappropriateFunctionPointException("illegal point");
        }
    }

    @Override
    public FunctionPoint getPoint(int n) throws FunctionPointIndexOutOfBoundsException {
        if(n>=0)
        {
            return list.getPoint(n);
        }
        else throw new FunctionPointIndexOutOfBoundsException("Illegal index");

    }

    @Override
    public double getLeftDomainBorder() {
        return leftX;
    }

    @Override
    public double getRightDomainBorder() {
        return rightX;
    }

    @Override
    public double getFunctionValue(double x) {
        int iter = 0;
        double k;
        double b;
        if(x>leftX & x<rightX){
            while(iter<list.getCount() && x>list.getPoint(iter).getX())
            {
                ++iter;
            }
            if (iter == 0){

                double y1 = 0;
                double x1 = leftX;
                double y2 = list.getPoint(list.getCount()).getY();
                double x2 = list.getPoint(list.getCount()).getX();
                b=(y2-(y1*x1/x2))/(((-1)*x2/x1)+1);
                k = (y1-b)/x1;
                return k*x+b;
            }
            if(iter==list.getCount()){

                double y1 = 0;
                double x1 = rightX;
                double y2 = list.getPoint(list.getCount()).getY();
                double x2 = list.getPoint(list.getCount()).getX();
                b=(y2-(y1*x1/x2))/(((-1)*x2/x1)+1);
                k = (y1-b)/x1;
                return k*x+b;
            }
            else
            {

                double y1 = list.getPoint(iter).getY();
                double x1 = list.getPoint(iter).getX();
                double y2 = list.getPoint(iter-1).getY();
                double x2 = list.getPoint(iter-1).getX();
                b=(y2-(y1*x1/x2))/(((-1)*x2/x1)+1);
                k = (y1-b)/x1;
                return k*x+b;
            }

        }
        else{
            return Double.NaN;
        }
    }

    @Override
    public int getPointCount() {
        return list.getCount();
    }

    @Override
    public void setPoint(int index, FunctionPoint point) {
        if(leftX<point.getX() & rightX>point.getX()){
            if((index==0||list.getPoint(index-1).getX()<point.getX()) & (index+1== list.getCount()||list.getPoint(index+1).getX()>point.getX())){
                list.getPoint(index).set(point);
            }
        }
    }

    @Override
    public double getPointX(int index) {
        return list.getPoint(index).getX();
    }

    @Override
    public void setPointX(int index, double x) {
        list.getPoint(index).setX(x);
    }

    @Override
    public double getPointY(int index) {
        return list.getPoint(index).getY();
    }

    @Override
    public void setPointY(int index, double Y) {
        list.getPoint(index).setY(Y);
    }

    @Override
    public void deletePoint(int index) {
        list.deletePointByIndex(index);
    }

    public String toString(){
        StringBuilder string = new StringBuilder();
        for (int i = 0; i<list.getCount(); ++i) {
            FunctionPoint n = list.getPoint(i);
            string.append("(" + n.getX() + ";" + n.getY() + "),");
        }
        return string.toString();
    }

    public boolean equals(Object o){
        try{
            if (o instanceof TabulatedFunction){
                if (list.getCount() == ((TabulatedFunction) o).getPointCount()){
                    for (int i = 0; i<((TabulatedFunction) o).getPointCount(); ++i){
                        if (list.getPoint(i).getX()!=((TabulatedFunction) o).getPoint(i).getX() &&
                                list.getPoint(i).getY()!=((TabulatedFunction) o).getPoint(i).getY())
                            return false;
                    }
                    return true;
                }
                else return false;
            }
            else return false;
        }
        catch (Exception ex){
            return false;
        }

    }

    public int hashCode(){
        int hash = 5;
        FunctionPoint[] array = new FunctionPoint[list.getCount()];
        for (int i = 0; i<list.getCount();++i)
            array[i] = list.getPoint(i);
        hash = 31 * hash + Arrays.deepHashCode(array);
        hash = 31 * hash + this.getPointCount();
        return hash;
    }

    public LinkedListTabulatedFunction clone(){
        FunctionPoint[] array = new FunctionPoint[list.getCount()];
        for (int i = 0; i<list.getCount();++i)
            array[i] = list.getPoint(i);
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(rightX, leftX, array);
        return linkedListTabulatedFunction;
    }
}

class PointList{
    private class Node{
        //это узел списка, поля у него публичные т.к доступ к ним может получить только класс PointList, который реализует
        //все необходимые процедуры работы с поступающими данными
        //доступ непоседственно к узлам извне получить невозможно
       FunctionPoint data;
       Node linkUp;
       Node linkDown; 
       Node(){            
            data = new FunctionPoint();
            linkDown = this;
            linkUp = this;
       }
       Node(FunctionPoint point){
            data= new FunctionPoint(point);
            linkDown = null;
            linkUp = null;
       }
    }
    private Node head;
    private int count;
    PointList(){
        head = new Node();
        count = 0;
        head.data = null;
    }
    void addNodeToTail(FunctionPoint point){
        count++;
        if(head!=null) {
            Node head1 = head;
            while(head1.linkDown!=head){
                head1=head1.linkDown;
            }            
            head1.linkDown = new Node(point);
            head1.linkDown.linkUp = head1;
            head1.linkDown.linkDown = head;
            head.linkUp=head1.linkDown;
        }
        else{                        
            head = new Node();
            head.data=null;
            head.linkDown = new Node(point);
            head.linkDown.linkUp = head;
            head.linkDown.linkDown = head;
            head.linkUp=head.linkDown;
        }
    }
    FunctionPoint getPoint(int i){
        FunctionPoint result = null;
        Node head_cloud=head.linkDown;
        for(int iter=0; iter<i;++iter){
            head_cloud=head_cloud.linkDown;            
        }
        if(head_cloud == head){
            head_cloud= head_cloud.linkDown;
        }
        result = head_cloud.data;        
        return result;
    }
    void addByIndex(FunctionPoint point, int index){
        count++; 
        Node head_cloud = head.linkDown;
        for(int i=0; i<index; ++i){
            head_cloud=head_cloud.linkDown;
        }
        if(head_cloud==head){
            head_cloud = head_cloud.linkDown;
        }
        Node cloud = head_cloud.linkDown;
        head_cloud.linkDown = new Node(point);
        head_cloud.linkDown.linkUp = head_cloud;
        head_cloud.linkDown.linkDown = cloud;        
    }
    int getCount(){return count;}
    void deletePointByIndex(int index){
        Node head_cloud = head.linkDown;
        for(int i=0; i<index; ++i){
            head_cloud=head_cloud.linkDown;
        }
        if(head_cloud==head){
            head_cloud = head_cloud.linkDown;
        }
        Node up = head_cloud.linkUp;
        Node down = head_cloud.linkDown;
        up.linkDown=down;
        down.linkUp = up;
        count--;
    }


}
