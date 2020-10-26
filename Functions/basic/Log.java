package functions.basic;

import functions.Function;
import functions.InappropriateFunctionPointException;

public class Log implements Function {
    double base;

    Log(double newBase){
        base = newBase;
    }

    @Override
    public double getLeftDomainBorder() {
        return 0;
    }

    @Override
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public double getFunctionValue(double x) throws InappropriateFunctionPointException {
        return Math.log(x)/Math.log(base);
    }
}
