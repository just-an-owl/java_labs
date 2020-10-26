package functions.basic;

import functions.InappropriateFunctionPointException;

public class Sin extends TrigonometricFunction{
    @Override
    public double getFunctionValue(double x) throws InappropriateFunctionPointException {
        return Math.sin(x);
    }
}
