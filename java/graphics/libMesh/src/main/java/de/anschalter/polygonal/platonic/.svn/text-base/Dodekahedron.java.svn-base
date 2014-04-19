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
public class Dodekahedron extends Mesh {

    private static final float A = 0.618034f;
    private static final float B = 1.0f + A;
    private static final Vector3 v0 = new Vector3(1.0f, 1.0f, 1.0f);
    private static final Vector3 v1 = new Vector3(1.0f, 1.0f, -1.0f);
    private static final Vector3 v2 = new Vector3(1.0f, -1.0f, 1.0f);
    private static final Vector3 v3 = new Vector3(1.0f, -1.0f, -1.0f);
    private static final Vector3 v4 = new Vector3(-1.0f, 1.0f, 1.0f);
    private static final Vector3 v5 = new Vector3(-1.0f, 1.0f, -1.0f);
    private static final Vector3 v6 = new Vector3(-1.0f, -1.0f, 1.0f);
    private static final Vector3 v7 = new Vector3(-1.0f, -1.0f, -1.0f);
    private static final Vector3 v8 = new Vector3(A, B, 0.0f);
    private static final Vector3 v9 = new Vector3(-A, B, 0.0f);
    private static final Vector3 v10 = new Vector3(A, -B, 0.0f);
    private static final Vector3 v11 = new Vector3(-A, -B, 0.0f);
    private static final Vector3 v12 = new Vector3(B, 0.0f, A);
    private static final Vector3 v13 = new Vector3(B, 0.0f, -A);
    private static final Vector3 v14 = new Vector3(-B, 0.0f, A);
    private static final Vector3 v15 = new Vector3(-B, 0.0f, -A);
    private static final Vector3 v16 = new Vector3(0.0f, A, B);
    private static final Vector3 v17 = new Vector3(0.0f, -A, B);
    private static final Vector3 v18 = new Vector3(0.0f, A, -B);
    private static final Vector3 v19 = new Vector3(0.0f, -A, -B);

    public Dodekahedron() {
        // vertices are added in a clockwise orientation (when viewed from the outside)
        // bottom
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v1, v8, v0, v12, v13}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v4, v9, v5, v15, v14}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v2, v10, v3, v13, v12}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v7, v11, v6, v14, v15}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v2, v12, v0, v16, v17}));

        addFace(new Face(this, Material.getRandom(), new Vector3[]{v1, v13, v3, v19, v18}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v4, v14, v6, v17, v16}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v7, v15, v5, v18, v19}));

        addFace(new Face(this, Material.getRandom(), new Vector3[]{v4, v16, v0, v8, v9}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v2, v17, v6, v11, v10}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v1, v18, v5, v9, v8}));

        addFace(new Face(this, Material.getRandom(), new Vector3[]{v7, v19, v3, v10, v11}));
    }
}
