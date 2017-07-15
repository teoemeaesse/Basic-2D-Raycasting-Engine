package threads;

import main.Main;

/**
 * Created by tomas on 14/07/2017.
 */
public class RenderThread extends Thread {
    public RenderThread(){
        super(60);
    }

    @Override
    public void action() {
        Main.getWindow().getDisplay().repaint();
    }
}
