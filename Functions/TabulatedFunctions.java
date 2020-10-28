package functions;

public abstract class TabulatedFunctions {
    public static TabulatedFunction tabule(Function f, double leftX, double rightX, int pointsCount) throws InappropriateFunctionPointException {
        if (f.getRightDomainBorder()<rightX || f.getLeftDomainBorder()>leftX) throw new IllegalArgumentException("invalid border");
        FunctionPoint[] points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount+2);
        for (int i=0; i<pointsCount;++i){
            points[i].set(leftX+step+step*i, f.getFunctionValue(leftX+step+step*i));
        }
        return new LinkedListTabulatedFunction(leftX,rightX, points);
    }
}
