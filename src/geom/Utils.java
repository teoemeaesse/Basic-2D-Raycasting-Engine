package geom;

import java.awt.*;
import java.awt.geom.*;

/**
 * Created by tomas on 14/07/2017.
 */
public class Utils {
    public static double getLineLength(Line2D line){
        double dx = Math.abs(line.getX2() - line.getX1()),
                dy = Math.abs(line.getY2() - line.getY1());
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static Point2D getLineIntersection(Line2D ray, Line2D segment) {
        if(ray.intersectsLine(segment)){
            double rx1 = ray.getX1(),
                    ry1 = ray.getY1(),
                    rx2 = ray.getX2(),
                    ry2 = ray.getY2(),
                    sx1 = segment.getX1(),
                    sy1 = segment.getY1(),
                    sx2 = segment.getX2(),
                    sy2 = segment.getY2(),
                    rdx = rx2 - rx1,
                    rdy = ry2 - ry1,
                    sdx = sx2 - sx1,
                    sdy = sy2 - sy1,
                    t1, t2,
                    ix, iy;

            t2 = (rdx * (sy1 - ry1) + rdy * (rx1 - sx1)) / (sdx * rdy - sdy * rdx);
            t1 = (sx1 + sdx * t2 - rx1) / rdx;

            ix = rx1 + rdx * t1;
            iy = ry1 + rdy * t1;
            return new Point2D.Double((int) ix, (int) iy);
    }else
            return null;
    }
}