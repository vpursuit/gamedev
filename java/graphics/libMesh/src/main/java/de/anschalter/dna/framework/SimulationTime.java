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
package de.anschalter.dna.framework;

/**
 *
 * @author keeper
 */
public class SimulationTime {

    public volatile long elapsedGameTime = 0;
    public volatile long elapsedRealTime = 0;
    public volatile long totalGameTime = 0;
    public volatile long totalRealTime = 0;
    public volatile boolean isRunningSlowly = false;

    /**
     * GameTime gameTime = new GameTime();
     */
    public SimulationTime() {
    }

    /**
     * Creates a new instance of GameTime
     * 
     * @param totalRealTime
     *      The amount of real time (wall clock) since the start of the game.
     * @param elapsedRealTime
     *      The amount of elapsed real time (wall clock) since the last frame.
     * @param totalGameTime
     *       The amount of game time since the start of the game.
     * @param elapsedGameTime
     *      The amount of elapsed game time since the last update.
     * 
     */
    public SimulationTime(long totalRealTime, long elapsedRealTime,
            long totalGameTime, long elapsedGameTime) {
    }

    /**
     * Creates a new instance of GameTime
     * 
     * @param totalRealTime
     *      The amount of real time (wall clock) since the start of the game.
     * @param elapsedRealTime
     *      The amount of elapsed real time (wall clock) since the last frame.
     * @param totalGameTime
     *      The amount of game time since the start of the game.
     * @param elapsedGameTime
     *      The amount of elapsed game time since the last update.
     * @param isRunningSlowly
     *      Whether the game is running multiple updates this frame.
     */
    public SimulationTime(long totalRealTime, long elapsedRealTime,
            long totalGameTime, long elapsedGameTime,
            boolean isRunningSlowly) {
    }

    /**
     * The amount of elapsed game time since the last update
     * @return
     *      Elapsed game time since the last update.
     */
    public long elapsedGameTime() {
        return elapsedGameTime;
    }

    /**
     * The amount of elapsed real time (wall clock) since the last frame.
     * @return
     *      Elapsed real time since the last frame.
     */
    public long elapsedRealTime() {
        return elapsedRealTime;
    }

    /**
     * Gets a value indicating that the game loop is taking longer than its Game.TargetElapsedTime.
     * In this case, the game loop can be considered to be running too slowly and
     * should do something to "catch up."
     * 
     * @return
     *      true if the game loop is taking too long; false otherwise.
     */
    public boolean isRunningSlowly() {
        return isRunningSlowly;
    }

    /**
     * The amount of game time since the start of the game.
     * 
     * @return
     *       Game time since the start of the game.
     */
    public long totalGameTime() {
        return totalGameTime;
    }

    /**
     * The amount of real time (wall clock) since the start of the game.
     * 
     * @return
     *      Real time since the start of the game.
     */
    public long totalRealTime() {
        return totalRealTime;
    }
}
