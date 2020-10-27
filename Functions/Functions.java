package functions;

import functions.meta.*;


public abstract class Functions { //абстрактный тк абстрактный не позволяет создать экземпляр класса
    public static Function Composition(Function F1, Function F2){return new Composition(F1, F2);}
    public static Function Mult(Function F1, Function F2){return new Mult(F1, F2);}
    public static Function Power(Function F1, double power){return new Power(F1, power);}
    public static Function Scale(Function F1, double kX, double kY){return new Scale(F1, kX, kY);}
    public static Function Shift(Function F1, double kX, double kY){return new Shift(F1, kX, kY);}
    public static Function Sum(Function F1, Function F2){return new Sum(F1, F2);}

}
