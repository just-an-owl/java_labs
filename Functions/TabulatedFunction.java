package functions;


public interface TabulatedFunction extends Function{
    void addPoint(FunctionPoint point) throws InappropriateFunctionPointException;
    FunctionPoint getPoint(int n);

    int getPointCount();
    void setPoint(final int index, final FunctionPoint point);
    double getPointX(final int index);
    void setPointX(final int index, final double x) throws InappropriateFunctionPointException;
    double getPointY(final int index);
    void setPointY(final int index, final double Y) throws InappropriateFunctionPointException;
    void deletePoint(final int index);
}