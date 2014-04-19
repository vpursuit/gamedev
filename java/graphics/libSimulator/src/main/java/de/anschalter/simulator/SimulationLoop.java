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

import de.anschalter.dna.framework.*;
import de.anschalter.concurrent.ToolBox;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author keeper
 */
public abstract class SimulationLoop implements Runnable {

    /**
     * 60 frames per second
     */
    public static final long FPS60 = 16666670L;
    /**
     * The target period of the simulation loop
     */
    public AtomicLong targetPeriod = new AtomicLong(FPS60);
    /**
     * The current real period of the simulation loop
     */
    public volatile long currentPeriod = Long.MAX_VALUE;
    /**
     * Shows wether or not the simulation is running. Set this to false to terminate
     * the simulation. Once it is set to false the simulation cannont be restarted. Use
     * isPause instead to start and stop the simulation.
     *
     * isRunning: true - running; false - abaout to terminate or already terminated
     */
    public volatile boolean isRunning = true;
    /**
     * Shows wether or not the simulation is paused. Set this to false to pause
     * the simulation. Set it to true to continue simulation.
     *
     * isPaused: true - simulation is paused; false - simulation is running
     */
    public volatile boolean isPaused = false;
    /**
     * Tags wether or not the work method is called as often as possible.
     *
     * true - a mionimum of 'targetperiod' nanoseconds between two calls of work
     * false - work method is called as often as possible
     */
    public volatile boolean isFixedPeriod = true;
    /**
     * Contains the amount of overtime periods.
     */
    public volatile long lostPeriods = 0L;
    /**
     * Private members.
     */
    private final SimulationTime simTime;

    public SimulationLoop(SimulationTime simTime) {
        this.simTime = simTime;
    }

    @Override
    final public void run() {

        if (beginSimulation()) {

            long overSleepTime = 0L;
            long period = 0L;
            long lost = 0L;
            boolean isSlow = false;

            while (isRunning) {

                if (isPaused) {

                    ToolBox.sleep(targetPeriod.get());
                    overSleepTime = 0L;
                    isSlow = false;
                    if (!isRunning) break;

                } else if (isSlow) {

                    ToolBox.sleep(targetPeriod.get());
                    overSleepTime = 0L;
                    isSlow = false;
                    lost++;
                    lostPeriods = lost; //atomic access to api attribute

                } else {

                    long start = System.nanoTime();
                    beforeStep();
                    step(simTime);
                    period = System.nanoTime() - start;

                    currentPeriod = period; //atomic access to api attribute

                    isSlow = period > targetPeriod.get();

                    if (!isRunning) break;

                    if (isFixedPeriod) {

                        overSleepTime =
                                ToolBox.sleep(
                                targetPeriod.get() - currentPeriod - overSleepTime);

                    }
                }

                if (!isRunning) break;
                afterStep();
                
            }

            endSimulation();
        }
    }

    /**
     * Place here all operations to prepare the simulation.
     *
     * @return true: simulation is allowed to start; false: simualtion won't start
     */
    protected abstract boolean beginSimulation();

    /**
     * Preparation of the next simulation step.
     */
    protected abstract void beforeStep();

    /**
     * Computation of next simulation step.
     *
     * @param time the current simulation time
     */
    protected abstract void step(SimulationTime time);

    /**
     * Publishing of the current simulation step.
     */
    protected abstract void afterStep();

    /**
     * Place here all operations to shutdown the simulation
     */
    protected abstract void endSimulation();
}
