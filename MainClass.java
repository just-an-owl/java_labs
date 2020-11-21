import functions.*;

public class MainClass{
    public static void main(String[] arg) throws InappropriateFunctionPointException, CloneNotSupportedException {
        double[] arr = {-1, 0, 1};
        TabulatedFunction tab1, tab2, tab1a, tab2a;
        System.out.println("Массив");
        tab1 = new ArrayTabulatedFunction(0, 2, arr);
        tab1a = new ArrayTabulatedFunction(0, 2, arr);
        for (int i = 0; i < tab1.getPointCount(); i++) {
            System.out.println("Значение в точке x=" + i + ": " + tab1.getFunctionValue(i));
        }
        System.out.println(tab1.toString());
        System.out.println("Список");
        tab2 = new LinkedListTabulatedFunction(0, 2, arr);
        tab2a = new LinkedListTabulatedFunction(0, 2, arr);
        for (int i = 0; i < tab1.getPointCount(); i++) {
            System.out.println("Значение в точке x=" + i + ": " + tab2.getFunctionValue(i));
        }
        System.out.println(tab2.toString());
        if (tab2a.equals(tab2)) {
            System.out.println("Равны");
        } else {
            System.out.println("Не равны");
        }
        System.out.println(tab1.hashCode());
        System.out.println(tab1a.hashCode());
        System.out.println(tab2.hashCode());
        System.out.println(tab2a.hashCode());
        tab1.setPointX(1, 0.99);
        System.out.println(tab1.hashCode());
        System.out.println("Клонирование объектов");
        TabulatedFunction tab1c, tab2c;
        System.out.println(tab1.toString());
        System.out.println(tab2.toString());
        tab2c = (LinkedListTabulatedFunction) tab2.clone();
        tab1c = (ArrayTabulatedFunction) tab1.clone();
        tab1.setPointX(1, 0.5);
        System.out.println(tab1.toString());
        System.out.println(tab1c.toString());
        System.out.println(tab2.toString());
        tab2.setPointX(1, 0.5);
        System.out.println(tab2.toString());
        System.out.println(tab2c.toString());
    }
}
