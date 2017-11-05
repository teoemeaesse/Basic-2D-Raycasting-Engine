package lighting;

import geom.Shape;
import geom.Utils;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tomas on 14/07/2017.
 */
public class RayCaster {
    private int x, y;

    public RayCaster(int x, int y) {
        updatePosition(x, y);
    }

    public void updatePosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void renderComposite(Graphics2D g2d, Color c, int width, int height){
        Area visible = getVisibleArea(Shape.getInstances(), g2d),
            total = new Area(new Polygon(new int[]{0, width, width, 0}, new int[]{0, 0, height, height}, 4));
        total.subtract(visible);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g2d.setColor(c);
        g2d.fill(total);
    }
    public Area getVisibleArea(ArrayList<Shape> map, Graphics2D g2d){
        ArrayList<Ray> rays = new ArrayList<>();
        ArrayList<Point2D> vertices = new ArrayList<>(),
                            visibilityPoints = new ArrayList<>();
        ArrayList<Line2D> segments = new ArrayList<>();

        //populate vertices and segments
        for(Shape s : map){
            vertices.addAll(Arrays.asList(s.getPoints()));
            segments.addAll(s.getLines());
        }

        //remove duplicate vertices
        ArrayList<Point2D> correctedVertices = new ArrayList<>();
        for(Point2D v : vertices){
            if(correctedVertices.size() == 0)
                correctedVertices.add(v);
            else{
                boolean contained = false;
                for(Point2D c : correctedVertices)
                    if(c.getX() == v.getX() && c.getY() == v.getY())
                        contained = true;
                if(!contained)
                    correctedVertices.add(v);
            }
        }
        vertices = correctedVertices;

        //cast the rays and populate visibilityPoints
        for(Point2D v : vertices){
            Ray[] raysToCheck = new Ray[]{
                    new Ray(x, y, (int) v.getX(), (int) v.getY()),
                    new Ray(x, y, (int) v.getX(), (int) v.getY()).rotate(-1).increaseLength(1000),
                    new Ray(x, y, (int) v.getX(), (int) v.getY()).rotate(1).increaseLength(1000),
                    new Ray(x, y, (int) v.getX(), (int) v.getY()).rotate(-5).increaseLength(1000),
                    new Ray(x, y, (int) v.getX(), (int) v.getY()).rotate(5).increaseLength(1000)
            };
            ArrayList<Point2D> rayIntersects = new ArrayList<>();
            Point2D closestIntersect;

            for(int i = 0; i < raysToCheck.length; i++){
                for(Line2D s : segments)
                    if(Utils.getLineIntersection(raysToCheck[i].getLine(), s) != null)
                        rayIntersects.add(Utils.getLineIntersection(raysToCheck[i].getLine(), s));
                closestIntersect = rayIntersects.get(0);
                for(int ii = 1; ii < rayIntersects.size(); ii++){
                    if(Utils.getLineLength(new Line2D.Float(x, y, (int) closestIntersect.getX(), (int) closestIntersect.getY()))
                            > Utils.getLineLength(new Line2D.Float(x, y, (int) rayIntersects.get(ii).getX(), (int) rayIntersects.get(ii).getY())))
                        closestIntersect = rayIntersects.get(ii);
                }

                if(closestIntersect != null){
                    rays.add(new Ray(x, y, (int) closestIntersect.getX(), (int) closestIntersect.getY()));
                    visibilityPoints.add(closestIntersect);
                }

                rayIntersects = new ArrayList<>();

            }
        }

        int np = visibilityPoints.size();
        ArrayList<Point2D> sortedPoints = new ArrayList<>();
        Point2D currentPoint = null;

        //sort points by angle
        while(visibilityPoints.size() > 0){
            int ci = 0;
            for(int i = 0; i < visibilityPoints.size(); i++){
                if(i == 0)
                    currentPoint = visibilityPoints.get(0);
                else if(new Ray(x, y, (int) visibilityPoints.get(i).getX(), (int) visibilityPoints.get(i).getY()).getAngle() < new Ray(x, y, (int) currentPoint.getX(), (int) currentPoint.getY()).getAngle()){
                    currentPoint = visibilityPoints.get(i);
                    ci = i;
                }
            }
            sortedPoints.add(currentPoint);
            visibilityPoints.remove(ci);
        }

        //populate xp, yp
        int[] xp = new int[sortedPoints.size()], yp = new int[sortedPoints.size()];
        for(int i = 0; i < sortedPoints.size(); i++){
            xp[i] = (int) sortedPoints.get(i).getX();
            yp[i] = (int) sortedPoints.get(i).getY();
        }

        return new Area(new Polygon(xp, yp, np));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
