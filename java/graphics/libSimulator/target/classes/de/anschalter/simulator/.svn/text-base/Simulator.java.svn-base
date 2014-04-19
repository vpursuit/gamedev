/*
 *  Copyright (c) 2008 Peter Trebing. All rights reserved.
 *   
 *  Any unauthorised copying, duplication, reproduction and
 *  compilation will constitute an infringement of copyright.
 *   
 *   
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  AS IS AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 *  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.anschalter.simulator;

import de.anschalter.Platform;
import de.anschalter.concurrent.ToolBox;
import de.anschalter.dna.framework.EventArgs;
import de.anschalter.dna.framework.IDrawable;
import de.anschalter.dna.framework.IGameComponent;
import de.anschalter.dna.framework.IUpdateable;
import de.anschalter.dna.framework.SimulationTime;
import de.anschalter.dna.framework.input.IGameAction;
import de.anschalter.dna.framework.input.IInputController;
import de.anschalter.scene.World;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author keeper
 */
public abstract class Simulator implements IUpdateable, IDrawable {

    public static final String RENDER_THREAD_NAME = "Renderer";
    public static final String UPDATE_THREAD_NAME = "Updater";
    //Standard fixed framerate for LCD monitors
    public static final long STANDARD_FPS = 60;
    public static final long NANOSECONDSPERSECOND = 1000000000;
    protected SimulationTime gameTime;
    private volatile boolean isFixedTimeStep = true;
    private long targetElapsedTime;
    private long inactiveSleepTime;
    public SimulationLoop renderLoop;
    public SimulationLoop updateLoop;
    private ArrayList<IGameComponent> gameComponents =
            new ArrayList<IGameComponent>(3);
    protected IGameAction exit;
    protected IGameAction pause;
    protected IGameAction zoomIn, zoomOut, forward, backward;

    /**
     * Initializes a new instance of this class, which provides basic graphics device initialization, game logic, rendering code, and a game loop.
     */
    public Simulator() {

        gameTime = new SimulationTime();
        renderLoop = new RenderLoop(gameTime, this);
       // updateLoop = new UpdateLoop(gameTime, this);

        setFixedTimeStep(true);
        setTargetElapsedTime(FPS2TargetElapsedTime(60));
        System.out.println(targetElapsedTime);

        Thread.setDefaultUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {

                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        Logger.getLogger(Simulator.class.getName()).
                                log(Level.SEVERE,
                                "Thread " + t.getName() + " failed, but restarted",
                                e);
                    }
                });
    }

    public static final long FPS2TargetElapsedTime(int fps) {
        return NANOSECONDSPERSECOND / fps;
    }

    public static final int targetElapsedTime2FPS(long nanos) {
        return (int) (NANOSECONDSPERSECOND / nanos);
    }

    /**
     * Gets or sets the time to sleep when the game is inactive.
     * 
     * @return
     *      The time to sleep when the game is inactive.
     */
    public long getInactiveSleepTime() {
        return inactiveSleepTime;
    }

    public void setInactiveSleepTime(long timeSpan) {
        inactiveSleepTime = timeSpan;
    }

    /**
     * Indicates whether the game is currently the active application.
     * 
     * @return true if the game is active; false otherwise.
     */
    public boolean isActive() {
        return true;
    }

    /**
     *  Gets or sets a value indicating whether to use fixed time steps.
     * 
     * @return true if using fixed time steps; false otherwise.
     */
    public boolean isFixedTimeStep() {
        return isFixedTimeStep;
    }

    public void setFixedTimeStep(boolean isFixed) {
        isFixedTimeStep = isFixed;
    }

    /**
     * Gets or sets the target time between calls to Game.Update when Game.IsFixedTimeStep
     * is true.
     * 
     * @return
     *      The target time period for the game loop.
     */
    public long getTargetElapsedTime() {
        return targetElapsedTime;
    }

    public void setTargetElapsedTime(long target) {
        targetElapsedTime = target;
    }

    /**
     * Immediately releases the unmanaged resources used by this object.
     */
    public void dispose() {
    }

    /**
     * Exits the game.
     */
    public void exit() {
    }

    /**
     *  Resets the elapsed time counter
     */
    public void resetElapsedTime() {
    }

    /**
     * Call this method to initialize the game, begin running the game loop, and
     * start processing events for the game.
     */
    public void run() {

        initialize();

        loadContent();

        beginRun();

        Thread renderer = new Thread(renderLoop, RENDER_THREAD_NAME);
        Thread updater = new Thread(updateLoop, UPDATE_THREAD_NAME);

        updater.start();
        renderer.start();

        // Watchdog loop: if any thread dies, restart it
        while (updateLoop.isRunning && renderLoop.isRunning) {

            ToolBox.sleep(1000);

            if (updateLoop.isRunning && !updater.isAlive()) {
                updater = new Thread(updateLoop, UPDATE_THREAD_NAME);
                updater.start();
            }

            if (renderLoop.isRunning && !renderer.isAlive()) {
                renderer = new Thread(renderLoop, RENDER_THREAD_NAME);
                renderer.start();
            }

        }

        unloadContent();

        endRun();

    }

    /**
     * Prevents calls to Game.Draw until the next Game.Update.
     */
    public void suppressDraw() {
    }

    /**
     * Called after all components are initialized but before the first update in
     * the game loop. 
     */
    protected void beginRun() {
    }

    /**
     * Called after the game loop has stopped running before exiting.
     */
    protected void endRun() {
    }

    /**
     * 
     * Releases all resources used by the Game class.
     * 
     * @param disposing
     *      true to release both managed and unmanaged resources; false to release only
     *      unmanaged resources.
     */
    protected void dispose(boolean disposing) {
    }

    /** 
     * Called when the game determines it is time to draw a frame. Override this
     * method with game-specific rendering code.
     * 
     * @param gameTime
     *      Time passed since the last call 
     */
    @Override
    public abstract void draw(SimulationTime gameTime);

    /**
     * Displays an error message to the user for the given exception.
     * 
     * @param exception
     *      The exception to display.
     * 
     * @return
     *      Called after the game loop has stopped running before exiting.
     */
    protected boolean showMissingRequirementMessage(Exception exception) {
        return true;
    }

    /**
     * Called after the Game and Graphics.GraphicsDevice are created, but before
     *Game.LoadContent.
     */
    protected void initialize() {

        Platform.getInputController();

        exit = IInputController.StandardAction.EXIT;
        pause = IInputController.StandardAction.PAUSE;

        zoomIn = IInputController.StandardAction.ZOOM_IN;
        zoomOut = IInputController.StandardAction.ZOOM_OUT;

        forward = IInputController.StandardAction.FORWARD;
        backward = IInputController.StandardAction.BACKWARD;

        renderLoop.isPaused = false;
        updateLoop.isPaused = false;

        for (int i = 0; i < gameComponents.size(); i++) {
            IGameComponent iGameComponent = gameComponents.get(i);
            iGameComponent.initialize();
        }

    }

    /**
     * Called when graphics resources need to be loaded. Override this method to
     * load any game-specific graphics resources.
     */
    protected abstract void loadContent();

    /**
     * Raises the Game.Activated event. Override this method to add code to handle
     * when the game gains focus.
     * 
     * @param sender
     *      The Game.
     * @param args
     *      The Game.
     */
    protected void onActivated(Object sender, EventArgs args) {
    }

    /**
     * Raises the Game.Deactivated event. Override this method to add code to handle
    ´* when the game loses focus.
     * 
     * @param sender
     *       The Game.
     * @param args
     *      Arguments for the Game.Deactivated event
     */
    protected void onDeactivated(Object sender, EventArgs args) {
    }

    /** 
     * Raises an Game.Exiting event. Override this method to add code to handle
     * when the game is exiting.
     * 
     * @param sender The Game.
     * @param args  
     *      Arguments for the Game.Exiting event.
     */
    protected void onExiting(Object sender, EventArgs args) {
    }

    /**
     * Called when graphics resources need to be unloaded. Override this method
     * to unload any game-specific graphics resources.
     */
    protected abstract void unloadContent();

    @Override
    public boolean beginUpdate() {
        boolean result = true;

        for (int i = 0; i < gameComponents.size(); i++) {
            IGameComponent iGameComponent = gameComponents.get(i);
            result &= iGameComponent.beginUpdate();
        }

        return result;
    }

    /**
     * Called when the game has determined that game logic needs to be processed. 
     * Override this method with game-specific logic.
     * 
     * @param gameTime 
     *         Time passed since the last call 
     */
    @Override
    public void update(SimulationTime gameTime) {

        if (exit.isPressed()) {
            renderLoop.isRunning = false;
            updateLoop.isRunning = false;
            renderLoop.isPaused = true;
            updateLoop.isPaused = true;
        } else if (pause.isPressed()) {
            renderLoop.isPaused = !renderLoop.isPaused;
        } else {

            if (zoomOut.isPressed()) {
                getWorld().camera.frustum.addFovy(0.5f);
                System.out.println(getWorld().camera.frustum.fovy);
            }
            if (zoomIn.isPressed()) {
                getWorld().camera.frustum.addFovy(-0.5f);
                System.out.println(getWorld().camera.frustum.fovy);
            }

            for (int i = 0; i < gameComponents.size(); i++) {
                IGameComponent iGameComponent = gameComponents.get(i);
                iGameComponent.update(gameTime);
            }
        }

    }

    @Override
    public void endUpdate() {

        for (int i = 0; i < gameComponents.size(); i++) {
            IGameComponent iGameComponent = gameComponents.get(i);
            iGameComponent.endUpdate();
        }

        System.out.println(
                "#####################################################" +
                "\nRenderLoop:" +
                "\ntargetPeriod: " + renderLoop.targetPeriod +
                "\ncurrentPeriod: " + renderLoop.currentPeriod + "\nlost frames: " + renderLoop.lostPeriods + "\n#####################################################");

        System.out.println(
                "UpdateLoop:" +
                "\ntargetPeriod: " + updateLoop.targetPeriod +
                "\ncurrentPeriod: " + updateLoop.currentPeriod + "\nlost frames: " + updateLoop.lostPeriods + "\n#####################################################");

    }

    public abstract World getWorld();
}
