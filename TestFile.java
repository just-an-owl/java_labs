import Functions.*;

public class TestFile {
    public static void main(String[] arg){
        LinkedListTabulatedFunction testTabFun = new LinkedListTabulatedFunction(1,15,2);
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(1, 15, 3);
        FunctionPoint point = new FunctionPoint(1, 3);
        testTabFun.setPoint(0, point);
        arrayTabulatedFunction.setPoint(1, point);
        arrayTabulatedFunction.setPoint(0, point);
        point.set(5, 7);
        testTabFun.setPoint(1, point);
        arrayTabulatedFunction.setPoint(2, point);
        System.out.println(testTabFun.getFunctionValue(4));
        System.out.println(arrayTabulatedFunction.getFunctionValue(4));
    }
}
