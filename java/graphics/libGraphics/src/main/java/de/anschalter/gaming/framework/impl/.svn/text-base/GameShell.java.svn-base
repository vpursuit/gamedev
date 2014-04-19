package de.anschalter.gaming.framework.impl;

import de.anschalter.dna.framework.SimulationTime;
import java.awt.Color;
import java.awt.Component;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import de.anschalter.gaming.framework.IDrawable;
import de.anschalter.gaming.framework.IGraphicsDevice;
import de.anschalter.gaming.framework.InputController;
import de.anschalter.graphics.IPhysicalSimulator;

/**
 * @author keeper
 */
public class GameShell implements IGameEngine {

    private boolean isRunning = false;
    private List<IDrawable> drawAbles;
    private List<IPhysicalSimulator> phySims;
    private List<IAI> ais;
    private Runnable paintRunner;
    private Runnable physRunner;
    private Runnable aiRunner;
    private long startTime = System.currentTimeMillis();
    private long currTime = startTime;
    private long currRealTime = startTime;
    private DisplayMode displayMode;
    private ExecutorService excutorService;
    private ReentrantLock pauseLock = new ReentrantLock();
    private Condition unpaused = pauseLock.newCondition();
    private boolean isPaused = false;
    private static long frameNo = 0;
    private static long races = 0;
    private static long start = System.currentTimeMillis();
    private static final Logger log = Logger.getLogger(GameShell.class.getName());
    private InputController inputController;

    public GameShell() {
        // this(ScreenManager.DEFAULTMODE);
        this(null, true);
    }

    public GameShell(DisplayMode mode, boolean fullScreen) {
        configureDisplay(mode, fullScreen);
        configureInput(ScreenManager.getInstance().getScreen());
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.IGame#stop()
     */
    public void shutdown() {
        // alway run the shutdown sequence in the AWT Event Queue
        SwingUtilities.invokeLater(new Runnable() {

            public synchronized void run() {
                isRunning = false;
                physRunner = null;
                aiRunner = null;
                paintRunner = null;
                excutorService.shutdown();
                ScreenManager.getInstance().restoreScreen();
            }
        });
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#startup()
     */
    public synchronized void startup() {
        if (!isRunning) {
            // getPainter().reshape(
            // getScreenManager().getFullScreenWindow().getGraphics(),
            // getScreenManager().getX(), getScreenManager().getY(),
            // getScreenManager().getWidth(),
            // getScreenManager().getWidth());
            isRunning = true;
            excutorService = Executors.newFixedThreadPool(4);
            excutorService.execute(getphysRunner());
            excutorService.execute(getaIRunner());
            excutorService.execute(getPaintRunner());
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#configureDisplay(java.awt.DisplayMode)
     */
    public synchronized void configureDisplay(DisplayMode mode, boolean fullScreen) {
        displayMode = mode;
        if (displayMode == null) {
            log.log(Level.WARNING,
                    "No display mode set. Trying to find a compatible mode...");
            displayMode = ScreenManager.getInstance().findFirstCompatibleMode();
        }
        if (displayMode != null) {
            log.log(Level.INFO, "Setting displayMode to " + displayMode.getWidth() + "x" + displayMode.getHeight() + "," + displayMode.getBitDepth());
            ScreenManager.getInstance().initScreen(displayMode, fullScreen);
        } else {
            throw new IllegalStateException("No display mode given!");
        }
        Window window = ScreenManager.getInstance().getScreen();
        window.setFont(new Font("Dialog", Font.PLAIN, ScreenManager.FONT_SIZE));
        window.setBackground(Color.BLUE);
        window.setForeground(Color.WHITE);
    }

    public synchronized void configureInput(Component comp) {
        inputController = new InputController(comp, this);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#pause()
     */
    public void pause() {
        pauseLock.lock();
        try {
            isPaused = true;
            log.log(Level.INFO, "Paused!!!");
        } finally {
            pauseLock.unlock();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#resume()
     */
    public void resume() {
        pauseLock.lock();
        try {
            isPaused = false;
            log.log(Level.INFO, "Unpaused!!!");
            unpaused.signalAll();
        } finally {
            pauseLock.unlock();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#removeGameObjects(java.util.List)
     */
    public void addGameObjects(List<Object> gObjects) {
        for (Iterator iter = gObjects.iterator(); iter.hasNext();) {
            Object gObject = iter.next();
            if (gObject instanceof IDrawable) {
                getDrawAbles().add((IDrawable) gObject);
            }
            if (gObject instanceof IPhysicalSimulator) {
                getPhySims().add((IPhysicalSimulator) gObject);
            }
            if (gObject instanceof IAI) {
                getAis().add((IAI) gObject);
            }

        }
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#removeGameObjects(java.util.List)
     */
    public void removeGameObjects(List<Object> gObjects) {
        for (Iterator iter = gObjects.iterator(); iter.hasNext();) {
            Object gObject = iter.next();
            if (gObject instanceof IDrawable) {
                getDrawAbles().remove((IDrawable) gObject);
            }
            if (gObject instanceof IPhysicalSimulator) {
                getPhySims().remove((IPhysicalSimulator) gObject);
            }
            if (gObject instanceof IAI) {
                getAis().remove((IAI) gObject);
            }

        }
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#addDrawAble(java.util.List)
     */
    public void addDrawAble(List<IDrawable> drawAbles) {
        getDrawAbles().addAll(drawAbles);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#removeDrawAble(java.util.List)
     */
    public void removeDrawAble(List<IDrawable> drawAbles) {
        getDrawAbles().removeAll(drawAbles);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#addPhysicalSimulator(de.anschalter.gaming.framework.impl.IPhysicalSimulator)
     */
    public synchronized void addPhysicalSimulators(List<IPhysicalSimulator> pSim) {
        getPhySims().addAll(pSim);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#removePhysicalSimulator(de.anschalter.gaming.framework.impl.IPhysicalSimulator)
     */
    public synchronized void removePhysicalSimulators(
            List<IPhysicalSimulator> pSim) {
        getPhySims().removeAll(pSim);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#addAI(de.anschalter.gaming.framework.impl.IAI)
     */
    public synchronized void addAIs(List<IAI> ai) {
        getAis().addAll(ai);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#removeAI(de.anschalter.gaming.framework.impl.IAI)
     */
    public synchronized void removeAIs(List<IAI> ai) {
        getAis().removeAll(ai);
    }

    private List<IDrawable> getDrawAbles() {
        if (drawAbles == null) {
            drawAbles = new ArrayList<IDrawable>();
        }
        return drawAbles;
    }

    private List<IPhysicalSimulator> getPhySims() {
        if (phySims == null) {
            phySims = new ArrayList<IPhysicalSimulator>();
        }
        return phySims;
    }

    private List<IAI> getAis() {
        if (ais == null) {
            ais = new ArrayList<IAI>();
        }
        return ais;
    }

    private Runnable getPaintRunner() {
        if (paintRunner == null) {
            paintRunner = new Runnable() {

                public void run() {

                    log.info("PaintRunner started.");
                    while (isRunning) {
                        pauseLock.lock();
                        try {
                            while (isPaused) {
                                unpaused.await();
                            }
                        } catch (InterruptedException ie) {
                            log.log(Level.SEVERE,
                                    "PaintRunner interrupted", ie);
                            return;
                        } finally {
                            pauseLock.unlock();
                        }

                        Graphics2DDevice g = new Graphics2DDevice(ScreenManager.getInstance().getGraphics());

                        for (IDrawable next : getDrawAbles()) {
                            next.draw(g);
                        }

                        g.dispose();

                        ScreenManager.getInstance().update();

                        if ((System.currentTimeMillis() - start) > 1000) {
                            start = System.currentTimeMillis();
                            races++;
                        }
                        frameNo++;

                        Thread.yield();
                    }
                    log.info("PaintRunner terminated.");
                    if (races > 0) {
                        log.info("Number of frames/sec " + frameNo / races);
                    }
                }
            };
        }
        return paintRunner;
    }

    private Runnable getphysRunner() {
        if (physRunner == null) {
            physRunner = new Runnable() {

                @Override
                public void run() {
                    log.info("Physical simulation started.");
                    while (isRunning) {
                        if (!isPaused()) {
                            if (getPhySims().size() == 0) {
                                break;
                            }
                            for (IPhysicalSimulator next : getPhySims()) {
                                next.update(getElapsedTime());
                            }
                        }
                        Thread.yield();
                    }
                    log.info("Physical simulation terminated.");
                }
            };
        }
        return physRunner;
    }

    private Runnable getaIRunner() {
        if (aiRunner == null) {
            aiRunner = new Runnable() {

                public void run() {
                    log.info("Ai started.");
                    while (isRunning) {
                        if (getAis().size() == 0) {
                            break;
                        }
                        for (IAI next : getAis()) {
                            next.analyze();
                        }
                        Thread.yield();
                    }
                    log.info("Ai terminated.");
                }
            };
        }
        return aiRunner;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.gaming.framework.impl.IGameEngine#isPaused()
     */
    @Override
    public boolean isPaused() {
        return isPaused;
    }
    private SimulationTime simTime = new SimulationTime();

    public SimulationTime getElapsedTime() {

        long elapsedRealTime = System.currentTimeMillis() - currRealTime;
        currRealTime += elapsedRealTime;

        simTime.totalRealTime = currRealTime;
        simTime.elapsedRealTime = elapsedRealTime;

        if (!isPaused()) {
            long elapsedTime = System.currentTimeMillis() - currTime;
            currTime += elapsedTime;
            simTime.elapsedGameTime = elapsedTime;
            simTime.totalGameTime = currTime;
        }

        return simTime;
    }

    public long getCurrentTime() {
        if (currTime == -1) {
            currTime = System.currentTimeMillis();
        }
        return currTime;
    }

    /**
     * A default IPainter if that paints an debug message. Can be used to test
     * the GameShell
     *
     * @author keeper
     */
    public static final class DefaultPainter extends SimpleTextPainter {

        public DefaultPainter(int width, int height) {
            super(width, height);
        }

        @Override
        public void draw(IGraphicsDevice g) {
            Color gC = g.getColor();
            g.setColor(DEFAULT_FOREGROUND);
            setLineNo(1);
            out(g, "GameShell, Copyright By Peter Trebing 2005");
            nextLine();
            out(g, "All Rights Reserved.");
            nextLine();
            out(g, "www.peter-trebing.de, mail@peter-trebing.de");
            nextLine();
            nextLine();
            if (races > 0) {
                out(g, "Number of frames/sec " + frameNo / races);
            }
            nextLine();
            nextLine();
            out(g, "Press ESCAPE to exit.");
            g.setColor(gC);
        }
    }

    public static void main(String[] args) {
        new GameShell().startup();
    }

    /**
     * @return Returns the inputController.
     */
    public InputController getInputController() {
        return inputController;
    }

    public static class LayeredPainter implements IDrawable {

        private List<IDrawable> layers;

        public LayeredPainter(List<IDrawable> layers) {
            this.layers = layers;
        }

        public void draw(IGraphicsDevice g) {
            for (Iterator iter = layers.iterator(); iter.hasNext();) {
                IDrawable element = (IDrawable) iter.next();
                element.draw(g);

            }
        }
    }
}