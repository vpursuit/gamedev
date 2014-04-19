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
package de.anschalter.jogl.adapter;

import de.anschalter.dna.framework.IWorldRenderer;
import de.anschalter.scene.Group;
import de.anschalter.scene.Mesh;
import de.anschalter.scene.Node;
import de.anschalter.scene.Shape;
import de.anschalter.scene.World;
import javax.media.opengl.GL;

/**
 *
 * @author keeper
 */
public class Renderer implements IWorldRenderer {

    public Renderer() {
    }

    public void render(World world) {

        GL gl = GraphicsDevice.getContext().getGL();

        setLightning(world, gl);
        setCamera(world, gl);
        traverseScene(world, gl);

        // gl.glFlush();

    }

    private static void traverseScene(Node node, GL gl) {

        gl.glMatrixMode(GL.GL_MODELVIEW);

        if (node.isRenderingEnabled) {

            if (node.transform != null) {
                gl.glPushMatrix();
                gl.glMultMatrixf(node.transform.m, 0);
            }

            if (node instanceof Group) {

                Group group = (Group) node;
                for (int i = 0; i < group.children.size(); i++) {
                    traverseScene(group.children.get(i), gl);
                }

            } else if (node instanceof Shape) {

                Shape shape = (Shape) node;
                drawMesh(shape.mesh, gl);

            } 

            if (node.transform != null) {
                gl.glPopMatrix();
            }

        }

    }

    public static void setLightning(World world, GL gl) {

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        gl.glShadeModel(GL.GL_SMOOTH);
       // gl.glShadeModel(GL.GL_LINE);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_CULL_FACE);
        gl.glFrontFace(GL.GL_CCW);

        gl.glEnable(GL.GL_LIGHTING);
        gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, world.light_ambient, 0);
      

        gl.glEnable(GL.GL_COLOR_MATERIAL);
        gl.glColorMaterial(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE);

        gl.glClearColor(0.0f, 0.0f, 0.2f, 0.0f);

    }

    private static void setCamera(World world, GL gl) {
        //TODO: viewPort as attribute of world
        final int width = GraphicsDevice.getWidth();
        final int height = GraphicsDevice.getHeight();
        gl.glViewport(0, 0, width, height);
        world.camera.frustum.setAspectRatio(width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadMatrixf(world.camera.getProjectionMatrix().m, 0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadMatrixf(world.camera.getModelViewMatrix().m, 0);
    }

    private static void drawMesh(Mesh mesh, GL gl) {

        gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL.GL_NORMAL_ARRAY);
        gl.glEnableClientState(GL.GL_COLOR_ARRAY);

        gl.glVertexPointer(3, GL.GL_FLOAT, 0, mesh.getVertexBuffer().
                position(0));
        gl.glNormalPointer(GL.GL_FLOAT, 0,
                mesh.getNormalBuffer().position(0));
        gl.glColorPointer(4, GL.GL_FLOAT, 0, mesh.getColorBuffer().position(
                0));
        gl.glDrawElements(GL.GL_TRIANGLES, mesh.getIndexCount(),
                GL.GL_UNSIGNED_SHORT, mesh.getIndexBuffer().position(0));

        gl.glDisableClientState(GL.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL.GL_NORMAL_ARRAY);
        gl.glDisableClientState(GL.GL_COLOR_ARRAY);

    }
}
