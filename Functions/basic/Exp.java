package functions.basic;

import functions.Function;
import functions.InappropriateFunctionPointException;

public class Exp implements Function {
    public Exp(){}

    @Override
    public double getLeftDomainBorder() {
        return Double.NEGATIVE_INFINITY;
    }

    @Override
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public double getFunctionValue(double x) throws InappropriateFunctionPointException {
        return Math.exp(x);
    }
}
