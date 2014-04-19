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
package de.anschalter;

import de.anschalter.dna.framework.*;
import de.anschalter.linalg.FrameMath;
import de.anschalter.scene.Group;
import de.anschalter.scene.Node;
import de.anschalter.scene.Shape;
import de.anschalter.scene.World;
import de.anschalter.simulator.UpdateLoop;

/**
 *
 * @author keeper
 */
public final class PhysicsUpdater extends UpdateLoop {

    World world;

    public PhysicsUpdater(World world,
            SimulationTime gameTime,
            IUpdateable updateable) {

        super(gameTime, updateable);
        this.world = world;

    }

    @Override
    protected boolean beginSimulation() {
        return super.beginSimulation();
    }

    @Override
    protected void beforeStep() {
    }

    @Override
    protected void step(SimulationTime time) {
        super.step(time);
        traverseScene(world);
    }

    @Override
    protected void afterStep() {
    }

    @Override
    protected void endSimulation() {
        super.endSimulation();
    }

    private static void traverseScene(Node node) {

        if (node.isRenderingEnabled) {

            if (node.frame != null) {
            }
            if (node instanceof Group) {

                Group group = (Group) node;
                for (int i = 0; i < group.children.size(); i++) {
                    traverseScene(group.children.get(i));
                }

            } else if (node instanceof Shape) {

                inspectMesh(node);

            } 

        }

    }

    private static void inspectMesh(Node node) {


        FrameMath.gltRotateFrameLocalX(node.frame, node.dRot.v[0]);
        FrameMath.gltRotateFrameLocalY(node.frame, node.dRot.v[1]);
        FrameMath.gltRotateFrameLocalZ(node.frame, node.dRot.v[2]);

        node.transform = FrameMath.gltGetMatrixFromFrame(node.frame);

    }
}
