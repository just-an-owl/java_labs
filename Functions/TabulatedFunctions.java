package functions;

import java.io.*;

public abstract class TabulatedFunctions {
    public static TabulatedFunction tabule(Function f, double leftX, double rightX, int pointsCount) throws InappropriateFunctionPointException {
        if (f.getRightDomainBorder()<rightX || f.getLeftDomainBorder()>leftX) throw new IllegalArgumentException("invalid border");
        FunctionPoint[] points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount+2);
        for (int i=0; i<pointsCount;++i){
            points[i].set(leftX+step+step*i, f.getFunctionValue(leftX+step+step*i));
        }
        return new LinkedListTabulatedFunction(leftX,rightX, points);
    }

    public static void outputTabulatedFunction(TabulatedFunction f, OutputStream out) throws IOException {
        DataOutputStream stream = new DataOutputStream(out);

        stream.write(f.getPointCount());
        stream.writeDouble(f.getLeftDomainBorder());

        stream.writeDouble(f.getRightDomainBorder());

        for (int i = 0; i<f.getPointCount(); ++i){
            stream.writeDouble(f.getPointX(i));
            stream.writeDouble(f.getPointY(i));
        }
    }

    public static TabulatedFunction inputTabulatedStream(InputStream in) throws IOException {
        DataInputStream inputStream = new DataInputStream(in);
        int count;
        count = inputStream.readInt();
        FunctionPoint[] points = new FunctionPoint[count];
        double x;
        double y;
        double leftX = inputStream.readDouble();
        double rightX = inputStream.readDouble();
        for (int i = 0; i<count; ++i){
            x=inputStream.readDouble();
            y=inputStream.readDouble();
            points[i]=new FunctionPoint(x,y);
        }
//jdkdk
        return new LinkedListTabulatedFunction(leftX,rightX, points);
    }
}
