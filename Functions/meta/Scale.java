package functions.meta;

import functions.Function;
import functions.InappropriateFunctionPointException;

public class Scale implements Function {
    Function f;
    double kX;
    double kY;
    public Scale(Function function, double new_kX, double new_kY){
        kX= new_kX;
        kY = new_kY;
        f = function;
    }
    @Override
    public double getLeftDomainBorder() {
        return f.getLeftDomainBorder()*kX;
    }

    @Override
    public double getRightDomainBorder() {
        return f.getRightDomainBorder()*kX;
    }

    @Override
    public double getFunctionValue(double x) throws InappropriateFunctionPointException {
        return f.getFunctionValue(x)*kY;
    }
}
