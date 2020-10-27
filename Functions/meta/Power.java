package functions.meta;

import functions.Function;
import functions.InappropriateFunctionPointException;

public class Power implements Function {
    Function f;
    double power;


    public Power(Function function, double newPower){
        f=function;
        power=newPower;
    }
    @Override
    public double getLeftDomainBorder() {
        return f.getLeftDomainBorder();
    }

    @Override
    public double getRightDomainBorder() {
        return f.getRightDomainBorder();
    }

    @Override
    public double getFunctionValue(double x) throws InappropriateFunctionPointException {
        return Math.pow(f.getFunctionValue(x), power);
    }
}
