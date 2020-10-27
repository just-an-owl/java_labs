package functions.meta;

import functions.Function;
import functions.InappropriateFunctionPointException;

public class Shift implements Function {
    Function f;
    double kX;
    double kY;

    public Shift(Function F, double k_X, double k_Y){
        f=F;
        kX=k_X;
        kY=k_Y;
    }
    @Override
    public double getLeftDomainBorder() {
        return f.getLeftDomainBorder()+kX;
    }

    @Override
    public double getRightDomainBorder() {
        return f.getRightDomainBorder()+kX;
    }

    @Override
    public double getFunctionValue(double x) throws InappropriateFunctionPointException {
        return f.getFunctionValue(x-kX)+kY;//x-kX, тк нам поступает х в сдвинутой области определения,
        // а сама функция работает в другой области определения
        //следовательно для получения корректных результатов мы сдвигаем х в первичную область определения
    }
}
