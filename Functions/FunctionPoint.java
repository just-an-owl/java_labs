package Functions; 

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
}