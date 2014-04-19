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

import de.anschalter.linalg.Vector3;
import de.anschalter.polygonal.Face;
import de.anschalter.polygonal.Material;
import de.anschalter.scene.Mesh;

/**
 *
 * @author keeper
 */
public class Octahedron extends Mesh {

    private static final Vector3 v0 = new Vector3(0.0f, 0.0f, -1.0f);
    private static final Vector3 v2 = new Vector3(0.0f, 0.0f, 1.0f);
    private static final Vector3 v1 = new Vector3(-1.0f, 0.0f, 0.0f);
    private static final Vector3 v3 = new Vector3(1.0f, 0.0f, 0.0f);
    private static final Vector3 v4 = new Vector3(0.0f, 1.0f, 0.0f);
    private static final Vector3 v5 = new Vector3(0.0f, -1.0f, 0.0f);

    public Octahedron() {

        // vertices are added in a clockwise orientation (when viewed from the outside)
        // bottom
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v4, v0, v1}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v4, v1, v2}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v4, v2, v3}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v4, v3, v0}));

        addFace(new Face(this, Material.getRandom(), new Vector3[]{v5, v1, v0}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v5, v2, v1}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v5, v3, v2}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v5, v0, v3}));

    }
}
