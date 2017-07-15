package geom;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by tomas on 14/07/2017.
 */
public class Shape {
    private Color color;
    private Point2D[] points;
    private static ArrayList<Shape> instances = new ArrayList<>();

    public Shape(Color color, Point2D ... points){
        this.color = color;
        this.points = points;
        instances.add(this);
    }

    public static void renderInstances(Graphics g){
        for(Shape i : instances)
            i.render(g);
    }

    private void render(Graphics g){
        for(int i = 0; i < points.length; i++){
            g.setColor(color);
            if(i + 1 < points.length)
                g.drawLine((int) points[i].getX(), (int) points[i].getY(), (int) points[i + 1].getX(), (int) points[i + 1].getY());
            else
                g.drawLine((int) points[i].getX(), (int) points[i].getY(), (int) points[0].getX(), (int) points[0].getY());
        }
    }


    public ArrayList<Line2D> getLines(){
        ArrayList<Line2D> lines = new ArrayList<>();
        for(int i = 0; i < points.length; i++){
            if(i + 1 < points.length)
                lines.add(new Line2D.Float((int) points[i].getX(), (int) points[i].getY(), (int) points[i + 1].getX(), (int) points[i + 1].getY()));
            else
                lines.add(new Line2D.Float((int) points[i].getX(), (int) points[i].getY(), (int) points[0].getX(), (int) points[0].getY()));
        }
        return lines;
    }
    public static ArrayList<Shape> getInstances() {
        return instances;
    }

    public Point2D[] getPoints() {
        return points;
    }
}
