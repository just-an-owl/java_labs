package functions;

public interface Function {
    double getLeftDomainBorder();
    double getRightDomainBorder();
    double getFunctionValue(final double x) throws InappropriateFunctionPointException;
}
