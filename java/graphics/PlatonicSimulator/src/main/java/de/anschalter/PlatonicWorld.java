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
import java.util.Random;

import de.anschalter.linalg.Frame;
import de.anschalter.linalg.FrameMath;
import de.anschalter.linalg.Matrix;
import de.anschalter.linalg.MatrixMath;
import de.anschalter.linalg.Vector3;
import de.anschalter.polygonal.TruncatedCube;
import de.anschalter.polygonal.platonic.Dodekahedron;
import de.anschalter.polygonal.platonic.Hexahedron;
import de.anschalter.polygonal.platonic.Isocahedron;
import de.anschalter.polygonal.platonic.Octahedron;
import de.anschalter.polygonal.platonic.Tetrahedron;
import de.anschalter.scene.Camera;
import de.anschalter.scene.Mesh;
import de.anschalter.scene.Node;
import de.anschalter.scene.Shape;
import de.anschalter.scene.World;

/**
 *
 * @author loewe
 */
public class PlatonicWorld extends World {

    private static float dx = 50.0f;
    private static float dy = 50.0f;
    private static float dz = 10.0f;
    private static Random r = new Random(System.currentTimeMillis());

    public PlatonicWorld() {

        final Mesh tetrahedron = new Tetrahedron();
        final Mesh truncatedCube = new TruncatedCube();
        final Mesh octahedron = new Octahedron();
        final Mesh dodekahedron = new Dodekahedron();
        final Mesh isokahedron = new Isocahedron();

        camera = Camera.STANDARD16x9;
        light_ambient = new float[]{1.0f, 1.0f, 1.0f, 1.0f};

        for (int i = 0; i < 5; i++) {
            this.addChild(transform(new Shape(tetrahedron)));
            this.addChild(transform(new Shape(Hexahedron.instance())));
            this.addChild(transform(new Shape(truncatedCube)));
            this.addChild(transform(new Shape(octahedron)));
            this.addChild(transform(new Shape(dodekahedron)));
            this.addChild(transform(new Shape(isokahedron)));
        }
    }

    private static Node transform(Node mesh) {
        float xp = dx * r.nextFloat() - dx / 2;
        float yp = dy * r.nextFloat() - dy / 2;
        float zp = dz * r.nextFloat() - dz - 4.0f;
        mesh.transform = new Matrix();
        MatrixMath.gltLoadIdentityMatrix(mesh.transform);
        mesh.dRot = new Vector3((r.nextFloat() - 0.5f) * 3.0f, (r.nextFloat() - 0.5f) * 3.0f, (r.nextFloat() - 0.5f) * 3.0f);
        // mesh.mdRot = (r.nextFloat() - 0.5f) * 3.0f;
        Frame doFrame = FrameMath.gltInitFrame(new Frame());
        doFrame.vLocation = new Vector3(xp, yp, zp);
        mesh.frame = doFrame;
        return mesh;
    }
}
