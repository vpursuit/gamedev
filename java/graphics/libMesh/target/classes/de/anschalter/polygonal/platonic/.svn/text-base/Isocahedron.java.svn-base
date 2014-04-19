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
public class Isocahedron extends Mesh {

    private static final float X = 0.525731112119133606f;
    private static final float Z = 0.850650808352039932f;
    private static final Vector3 v0 = new Vector3(-X, 0.0f, Z);
    private static final Vector3 v1 = new Vector3(X, 0.0f, Z);
    private static final Vector3 v2 = new Vector3(-X, 0.0f, -Z);
    private static final Vector3 v3 = new Vector3(X, 0.0f, -Z);
    private static final Vector3 v4 = new Vector3(0.0f, Z, X);
    private static final Vector3 v5 = new Vector3(0.0f, Z, -X);
    private static final Vector3 v6 = new Vector3(0.0f, -Z, X);
    private static final Vector3 v7 = new Vector3(0.0f, -Z, -X);
    private static final Vector3 v8 = new Vector3(Z, X, 0.0f);
    private static final Vector3 v9 = new Vector3(-Z, X, 0.0f);
    private static final Vector3 v10 = new Vector3(Z, -X, 0.0f);
    private static final Vector3 v11 = new Vector3(-Z, -X, 0.0f);

    public Isocahedron() {

        addFace(new Face(this, Material.getRandom(), new Vector3[]{v1, v4, v0}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v4, v9, v0}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v4, v5, v9}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v8, v5, v4}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v1, v8, v4}));

        addFace(new Face(this, Material.getRandom(), new Vector3[]{v1, v10, v8}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v10, v3, v8}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v8, v3, v5}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v3, v2, v5}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v3, v7, v2}));

        addFace(new Face(this, Material.getRandom(), new Vector3[]{v3, v10, v7}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v10, v6, v7}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v6, v11, v7}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v6, v0, v11}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v6, v1, v0}));

        addFace(new Face(this, Material.getRandom(), new Vector3[]{v10, v1, v6}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v11, v0, v9}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v2, v11, v9}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v5, v2, v9}));
        addFace(new Face(this, Material.getRandom(), new Vector3[]{v11, v2, v7}));

    }
}
