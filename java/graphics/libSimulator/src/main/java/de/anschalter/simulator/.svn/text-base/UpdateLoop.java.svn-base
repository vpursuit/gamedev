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

/**
 *
 * @author keeper
 */
public class UpdateLoop extends SimulationLoop {

    private final IUpdateable updateable;

    public UpdateLoop(SimulationTime gameTime,
            IUpdateable updateable) {

        super(gameTime);
        this.updateable = updateable;

    }

    @Override
    protected boolean beginSimulation() {
        return updateable.beginUpdate();
    }

    @Override
    protected void beforeStep() {
    }

    @Override
    protected void step(SimulationTime time) {
        updateable.update(time);
    }

    @Override
    protected void afterStep() {
    }

    @Override
    protected void endSimulation() {
        updateable.endUpdate();
    }
}
