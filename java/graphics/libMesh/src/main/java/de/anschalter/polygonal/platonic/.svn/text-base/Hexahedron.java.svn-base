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
package de.anschalter.polygonal.platonic;

import de.anschalter.scene.Mesh;
import de.anschalter.linalg.Vector3;
import de.anschalter.polygonal.*;

public final class Hexahedron extends Mesh {

    private static Hexahedron instance;

    public static final synchronized Mesh instance() {

        if (instance == null) {
            
            instance = new Hexahedron();

            final Vector3 leftBottomBack = new Vector3(-1.0f, -1.0f,
                    -1.0f);
            final Vector3 rightBottomBack = new Vector3(1.0f, -1.0f,
                    -1.0f);
            final Vector3 leftTopBack = new Vector3(-1.0f, 1.0f, -1.0f);
            final Vector3 rightTopBack = new Vector3(1.0f, 1.0f, -1.0f);
            final Vector3 leftBottomFront = new Vector3(-1.0f, -1.0f,
                    1.0f);
            final Vector3 rightBottomFront = new Vector3(1.0f, -1.0f,
                    1.0f);
            final Vector3 leftTopFront = new Vector3(-1.0f, 1.0f, 1.0f);
            final Vector3 rightTopFront = new Vector3(1.0f, 1.0f, 1.0f);
            // vertices are added in a clockwise orientation (when viewed from the outside)
            final Vector3[] BOTTOM = new Vector3[]{leftBottomBack, leftBottomFront, rightBottomFront, rightBottomBack};
            final Vector3[] FRONT = new Vector3[]{leftBottomFront, leftTopFront, rightTopFront, rightBottomFront};
            final Vector3[] LEFT = new Vector3[]{leftBottomBack, leftTopBack, leftTopFront, leftBottomFront};
            final Vector3[] RIGHT = new Vector3[]{rightBottomBack, rightBottomFront, rightTopFront, rightTopBack};
            final Vector3[] BACK = new Vector3[]{leftBottomBack, rightBottomBack, rightTopBack, leftTopBack};
            final Vector3[] TOP = new Vector3[]{leftTopBack, rightTopBack, rightTopFront, leftTopFront};

            // bottom
            instance.addFace(new Face(instance, Material.getRandom(), BOTTOM));
            // front
            instance.addFace(new Face(instance, Material.getRandom(), FRONT));
            // left
            instance.addFace(new Face(instance, Material.getRandom(), LEFT));
            // right
            instance.addFace(new Face(instance, Material.getRandom(), RIGHT));
            // back
            instance.addFace(new Face(instance, Material.getRandom(), BACK));
            // top
            instance.addFace(new Face(instance, Material.getRandom(), TOP));
        }
        return instance;
    }

    private Hexahedron() {
    }
}
