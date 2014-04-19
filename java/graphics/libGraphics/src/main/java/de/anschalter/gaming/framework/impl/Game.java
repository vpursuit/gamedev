package de.anschalter.gaming.framework.impl;

import java.awt.*;
import java.awt.DisplayMode;
import java.awt.Window;

import de.anschalter.gaming.framework.IGame;

/**
 * @author keeper
 */
public abstract class Game implements IGame {

    protected static final int FONT_SIZE = 24;

    private boolean isRunning = false;
    protected ScreenManager screen;

    /*
     * (non-Javadoc)
     * 
     * @see de.anschalter.gaming.framework.IGame#stop()
     */
    public void shutdown() {
        isRunning = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.anschalter.gaming.framework.IGame#run()
     */
    public void run() {
        run(null);
    }

    public void run(DisplayMode displayMode) {
        try {
            init(displayMode);
            gameLoop();
        } finally {
            screen.restoreScreen();
        }
    }

    protected void init(DisplayMode displayMode) {
        screen = ScreenManager.getInstance();

        DisplayMode dsplayMode = displayMode;
        if (dsplayMode == null)
            dsplayMode = screen.findFirstCompatibleMode();
        screen.initScreen(dsplayMode, false);

        Window window = screen.getScreen();
        window.setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
        window.setBackground(Color.BLUE);
        window.setForeground(Color.WHITE);

        isRunning = true;
    }

    protected void gameLoop() {
        long startTime = System.currentTimeMillis();
        long currTime = startTime;

        while (isRunning) {
            long elapsedTime = System.currentTimeMillis() - currTime;
            currTime += elapsedTime;

            // update
            update(elapsedTime);

            // draw the screen
            Graphics2D g = screen.getGraphics();
            draw(g);
            g.dispose();
            screen.update();
            
            // take a nap
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }

        }
    }

    protected void update(long elapsedTime) {
    }

    public abstract void draw(Graphics2D g);

}