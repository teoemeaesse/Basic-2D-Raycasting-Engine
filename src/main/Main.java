package main;

import gfx.Window;

/**
 * Created by tomas on 14/07/2017.
 */
public class Main {
    private static Window window;

    public static void main(String[] args){
        init();
    }

    private static void init(){
        window = new Window(800, 600, "Raycasting");
    }


    public static Window getWindow() {
        return window;
    }
}
