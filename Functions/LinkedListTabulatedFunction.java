package Functions;



public class LinkedListTabulatedFunction implements TabulatedFunction{

    private PointList list;
    private double leftX;
    private double rightX;


    public LinkedListTabulatedFunction(final double newLeftX, final double newRightX, final int pointsCount){
        leftX = newLeftX;
        rightX = newRightX;
        list = new PointList();
        for (int i=0; i<pointsCount; ++i){
            list.add(new FunctionPoint());
        }
    }

    @Override
    public void addPoint(FunctionPoint point) {
        if(point.getX()>leftX && point.getX()<rightX)
        {

            list.add(point);
        }
        else
        {}
    }

    @Override
    public FunctionPoint getPoint(int n) {
        return list.getPoint(n);
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
        return 0;
    }

    @Override
    public int getPointCount() {
        return list.getCount();
    }

    @Override
    public void setPoint(int index, FunctionPoint point) {
        list.getPoint(index).set(point);
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
}

class PointList{
    private class Node{
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
    void add(FunctionPoint point){
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
