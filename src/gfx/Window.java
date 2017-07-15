package gfx;

import threads.RenderThread;
import threads.UpdateThread;

import javax.swing.*;

/**
 * Created by tomas on 13/07/2017.
 */
public class Window extends JFrame {
    private Display display = new Display();

    public Window(int width, int height, String title){
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        //setIgnoreRepaint(false);
        display.setIgnoreRepaint(false);
        add(display);
        setVisible(true);

        new RenderThread();
        new UpdateThread();
        display.init();
    }

    public Display getDisplay() {
        return display;
    }
}
