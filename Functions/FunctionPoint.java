package functions;

public class FunctionPoint{
    private double x, y;
    public FunctionPoint(){
        x=0;
        y=0;
    }
    public FunctionPoint(double X, double Y){
        x=X;
        y=Y;
    }
    public FunctionPoint(FunctionPoint Point){
        x=Point.getX();
        y=Point.getY();
    }
    public void setX(double X){x=X;}
    public void setY(double Y){y=Y;}
    public void set(FunctionPoint point){
        x=point.getX();
        y=point.getY();
    }
    public void set(double X, double Y){
        x=X;
        y=Y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public String toString(){
        return new String(x + " " + y);
    }
    public int hashCode(){
        return Double.hashCode(x)*Double.hashCode(y);
    }
    public boolean equals(Object o){
        if(o instanceof FunctionPoint){
            if(((FunctionPoint) o).x==x && ((FunctionPoint) o).y==y)
                return true;
            return false;
        }
        return false;
    }
    public Object clone(){
        return new FunctionPoint(this);
    }
}