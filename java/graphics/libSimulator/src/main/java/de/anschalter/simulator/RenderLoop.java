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
import de.anschalter.dna.framework.*;
import de.anschalter.gaming.framework.IGraphicsDevice;

/**
 *
 * @author keeper
 */
public final class RenderLoop extends SimulationLoop {

    private static final int PWIDTH = 800;
    private static final int PHEIGHT = 600;

    private final IDrawable drawable;
    private IGraphicsDevice graphicsDevice;

    public RenderLoop(
            SimulationTime gameTime,
            IDrawable drawable) {

        super(gameTime);
        this.drawable = drawable;
        this.graphicsDevice = Platform.getGraphicsDevice();
        graphicsDevice.createWindow("Simulation", PWIDTH, PHEIGHT);
    }

    @Override
    protected boolean beginSimulation() {
        return drawable.init();
    }

    @Override
    protected void beforeStep() {
        graphicsDevice.makeContextCurrent();
    }

    @Override
    protected void step(SimulationTime time) {
        drawable.draw(time);
    }

    @Override
    protected void afterStep() {
        graphicsDevice.swapBuffers();
    }

    @Override
    protected void endSimulation() {
        drawable.finish();
        graphicsDevice.dispose();
    }
}
