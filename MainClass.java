import Functions.*;

public class MainClass{
    public static void main(String[] arg) throws InappropriateFunctionPointException {
        try {
            ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(1, 15, -5);
        }
        catch (InappropriateFunctionPointException fail){
            System.out.println(fail.getMessage());
        }
        try {
            ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(1, 15, 5);
        }
        catch (InappropriateFunctionPointException fail){
            System.out.println(fail.getMessage());
        }


    }
}
