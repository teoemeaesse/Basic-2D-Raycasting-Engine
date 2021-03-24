package lighting;

import geom.Utils;

import java.awt.geom.Line2D;

/**
 * Created by tomas on 14/07/2017.
 */
public class Ray implements Cloneable {
    private Line2D line;

    public Ray(int x1, int y1, int x2, int y2){
        line = new Line2D.Double(x1, y1, x2, y2);
    }
    public Ray(Line2D line) {
        this.line = line;
    }


    public Ray increaseLength(double increase){
        double ndx = (increase * (line.getX2() - line.getX1())) / Utils.getLineLength(line),
                ndy = (increase * (line.getY2() - line.getY1())) / Utils.getLineLength(line);
        line = new Line2D.Double((int) line.getX1(), (int) line.getY1(), (int) (line.getX1() + ndx), (int) (line.getY1() + ndy));
        return this;
    }

    public Ray rotate(double angle){
        return new Ray(new Line2D.Double(
                line.getX1(),
                line.getY1(),
                line.getX1() + Utils.getLineLength(line) * Math.cos(Math.toRadians(angle + getAngle())),
                line.getY1() + Utils.getLineLength(line) * Math.sin(Math.toRadians(angle + getAngle()))));
    }

    public double getAngle(){
        double xd = line.getX2() - line.getX1(),
                yd = line.getY2() - line.getY1();
        return Math.toDegrees(Math.atan2(yd, xd));
    }

    public Line2D getLine(){
        return line;
    }
}