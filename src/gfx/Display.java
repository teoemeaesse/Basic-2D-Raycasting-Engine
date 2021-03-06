package gfx;

import lighting.RayCaster;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by tomas on 14/07/2017.
 */
public class Display extends JPanel {
    private RayCaster rayCaster1, rayCaster2, rayCaster3, rayCaster4;

    public void init(){
        new geom.Shape(Color.BLACK, new Point2D.Double(0, 0), new Point2D.Double(getWidth(), 0), new Point2D.Double(getWidth(), getHeight()), new Point2D.Double(0, getHeight()));
        new geom.Shape(Color.BLACK, new Point2D.Double(200, 200), new Point2D.Double(400, 200), new Point2D.Double(400, 500), new Point2D.Double(200, 500));
        rayCaster1 = new RayCaster(300, 300);
        //rayCaster2 = new RayCaster(100, 550);
        //rayCaster3 = new RayCaster(700, 500);
        //rayCaster4 = new RayCaster(650, 100);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        drawBackground(g);
        geom.Shape.renderInstances(g2d);
        rayCaster1.renderComposite(g2d, Color.YELLOW, getWidth(), getHeight());
        g.fillOval(rayCaster1.getX() - 3, rayCaster1.getY() - 3, 5, 5);
        /*rayCaster2.renderComposite(g2d, Color.RED, getWidth(), getHeight());
        g.fillOval(rayCaster2.getX() - 3, rayCaster2.getY() - 3, 5, 5);
        rayCaster3.renderComposite(g2d, Color.GREEN, getWidth(), getHeight());
        g.fillOval(rayCaster3.getX() - 3, rayCaster3.getY() - 3, 5, 5);
        rayCaster4.renderComposite(g2d, Color.CYAN, getWidth(), getHeight());
        g.fillOval(rayCaster4.getX() - 3, rayCaster4.getY() - 3, 5, 5);*/
    }

    public void update(){
        if(getMousePosition() != null){
            rayCaster1.updatePosition(getMousePosition().x, getMousePosition().y);
        }
    }


    private void drawBackground(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
