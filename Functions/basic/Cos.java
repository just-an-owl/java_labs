package functions.basic;

import functions.InappropriateFunctionPointException;

public class Cos extends TrigonometricFunction{
    @Override
    public double getFunctionValue(double x) throws InappropriateFunctionPointException {
        return Math.cos(x);
    }
}
