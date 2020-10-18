package Functions;


interface TabulatedFunction{
    void addPoint(FunctionPoint point);
    FunctionPoint getPoint(int n);
    double getLeftDomainBorder();
    double getRightDomainBorder();
    double getFunctionValue(final double x);
    int getPointCount();
    void setPoint(final int index, final FunctionPoint point);
    double getPointX(final int index);
    void setPointX(final int index, final double x);
    double getPointY(final int index);
    void setPointY(final int index, final double Y);
    void deletePoint(final int index);
}