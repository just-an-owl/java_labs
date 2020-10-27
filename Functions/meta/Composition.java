package functions.meta;

import functions.Function;
import functions.InappropriateFunctionPointException;

public class Composition implements Function {
    Function f1;
    Function f2;

    public Composition(Function F1, Function F2){
        f1=F1;
        f2=F2;
    }

    @Override
    public double getLeftDomainBorder() {
        return f1.getLeftDomainBorder();
    }

    @Override
    public double getRightDomainBorder() {
        return f1.getRightDomainBorder();
    }

    @Override
    public double getFunctionValue(double x) throws InappropriateFunctionPointException {
        return f2.getFunctionValue(f1.getFunctionValue(x));
    }
}
