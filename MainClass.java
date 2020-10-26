import Functions.*;

public class MainClass{
    public static void main(String[] arg) throws InappropriateFunctionPointException {
        ArrayTabulatedFunction arrayTabulatedFunction = null;
        try {
            arrayTabulatedFunction = new ArrayTabulatedFunction(1, 15, -5);
        }
        catch (InappropriateFunctionPointException fail){
            System.out.println(fail.getMessage());
        }
        try {
            arrayTabulatedFunction = new ArrayTabulatedFunction(1, 15, 5);
        }
        catch (InappropriateFunctionPointException fail){
            System.out.println(fail.getMessage());
        }
        TabulatedFunction tabulatedFunction = arrayTabulatedFunction;
        TabulatedFunction[] tabulatedFunctions = new TabulatedFunction[2];
        tabulatedFunctions[0] = tabulatedFunction;
        tabulatedFunctions[1] = new LinkedListTabulatedFunction(1,15,5);
        FunctionPoint point = new FunctionPoint(2, 3);
        tabulatedFunctions[0].setPoint(0, point);
        tabulatedFunctions[1].setPoint(0, point);
        point.set(5, 7);
        tabulatedFunctions[0].setPoint(1, point);
        tabulatedFunctions[1].setPoint(1, point);
        for(int i = 0; i<2; ++i){
            System.out.println(tabulatedFunctions[i].getFunctionValue(4));
        }
    }
}
