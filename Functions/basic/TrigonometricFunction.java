package functions.basic;

import functions.Function;
import functions.InappropriateFunctionPointException;

public abstract class TrigonometricFunction implements Function {

    @Override
    public double getLeftDomainBorder() {
        return -1;
    }

    @Override
    public double getRightDomainBorder() {
        return 1;
    }

    @Override
    public abstract double getFunctionValue(double x) throws InappropriateFunctionPointException;
}
