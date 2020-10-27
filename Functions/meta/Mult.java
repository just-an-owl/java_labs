package functions.meta;

import functions.Function;
import functions.InappropriateFunctionPointException;

public class Mult implements Function {
    double leftX;
    double rightX;

    Function f1;
    Function f2;

    public Mult(Function F1, Function F2){
        leftX = Math.max(F1.getLeftDomainBorder(), F2.getLeftDomainBorder());
        rightX = Math.min(F1.getRightDomainBorder(), F2.getRightDomainBorder());
        f1=F1;
        f2=F2;
    }

    @Override
    public double getLeftDomainBorder() {
        return leftX;
    }

    @Override
    public double getRightDomainBorder() {
        return rightX;
    }

    @Override
    public double getFunctionValue(double x) throws InappropriateFunctionPointException {
        if (rightX<x || leftX>x) throw new InappropriateFunctionPointException("invalid number");
        return (f1.getFunctionValue(x) * f2.getFunctionValue(x));
    }
}
