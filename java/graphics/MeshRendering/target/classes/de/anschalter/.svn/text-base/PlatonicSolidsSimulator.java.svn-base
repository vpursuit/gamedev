package de.anschalter;

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
import de.anschalter.dna.framework.IWorldRenderer;
import de.anschalter.simulator.Simulator;
import de.anschalter.dna.framework.SimulationTime;
import de.anschalter.scene.World;

/**
 *
 * @author keeper
 */
public class PlatonicSolidsSimulator extends Simulator {

    private IWorldRenderer renderer = Platform.getRenderer();
    private World world;

    public PlatonicSolidsSimulator() {
        super();
        world = new PlatonicWorld();
        updateLoop = new PhysicsUpdater(world, gameTime, this);
    }

    @Override
    protected void initialize() {
        super.initialize();

    }

    @Override
    protected void beginRun() {
        super.beginRun();
    }

    @Override
    protected void loadContent() {
    }

    @Override
    protected void unloadContent() {
    }

    @Override
    public boolean beginUpdate() {
        return super.beginUpdate();
    }

    @Override
    public void update(SimulationTime gameTime) {
        super.update(gameTime);
    }

    @Override
    public void endUpdate() {
        super.endUpdate();
    }

    @Override
    public void draw(SimulationTime gameTime) {
        renderer.render(world);
    }

    public boolean init() {
        return true;
    }

    public void finish() {
    }

    @Override
    public World getWorld() {
        return world;
    }
}
