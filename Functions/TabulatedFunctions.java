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
        return new LinkedListTabulatedFunction(leftX,rightX, points);
    }
    public static void writeTabulatedFunction(TabulatedFunction function, Writer out){
        PrintWriter writer = new PrintWriter(out);
        int pointCount = function.getPointCount();

        writer.println(pointCount);
        writer.println(function.getLeftDomainBorder());
        writer.println(function.getRightDomainBorder());

        for (int i = 0; i < pointCount; i++) {
            writer.println(function.getPointX(i));
            writer.println(function.getPointY(i));
        }
    }

    public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();

        int pointCount = (int) tokenizer.nval;
        tokenizer.nextToken();
        double left = (double) tokenizer.nval;
        tokenizer.nextToken();
        double right = (double) tokenizer.nval;

        FunctionPoint points[] = new FunctionPoint[pointCount];
        double x, y;
        for (int i = 0; i < pointCount; i++) {
            tokenizer.nextToken();
            x = tokenizer.nval;
            tokenizer.nextToken();
            y = tokenizer.nval;

            points[i] = new FunctionPoint(x, y);
        }

        return new ArrayTabulatedFunction(left, right, points);
    }



}
