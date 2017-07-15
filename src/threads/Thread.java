package threads;

/**
 * Created by tomas on 14/07/2017.
 */
public abstract class Thread implements Runnable {
    private boolean running = false;
    private double fps;

    public Thread(int fps){
        this.fps = fps;
        startThread();
    }

    public void run() {
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / (1000000000 / fps);
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                action();
                delta--;
            }

            if(timer >= 1000000000)
                timer = 0;
        }
    }

    private void startThread(){
        if(running) return;
        running = true;
        java.lang.Thread thread = new java.lang.Thread(this);
        thread.start();
    }

    public abstract void action();
}
