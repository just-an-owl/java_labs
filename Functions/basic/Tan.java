package functions.basic;


import functions.InappropriateFunctionPointException;

public class Tan extends TrigonometricFunction {
    @Override
    public double getFunctionValue(double x) throws InappropriateFunctionPointException {
        return Math.tan(x);
    }
}
