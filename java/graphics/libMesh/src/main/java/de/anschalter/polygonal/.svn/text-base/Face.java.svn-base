package de.anschalter.polygonal;
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

import de.anschalter.scene.Mesh;
import de.anschalter.linalg.Vector3;
import de.anschalter.linalg.VectorMath;
import java.nio.ShortBuffer;
import java.util.ArrayList;

public final class Face {

    final Mesh myMesh;
    final Material material;
    final Vector3[] points;
    
    final ArrayList<Integer> mIndexList;

    /**
     * Define the points of the face counter clockwise.
     * All points has to exist in the same plain.
     * @param mesh
     * @param points
     */
    public Face(Mesh mesh, Vector3[] points) {
        this(mesh, Material.get("DEFAULT"), points);
    }

    public Face(Mesh mesh, Material material, Vector3[] points) {
        myMesh = mesh;
        this.material = material;
        this.points = points;
        mIndexList = new ArrayList<Integer>(points.length); //maximum necessary capacity
        index();
    }

    private void index() {
        final Vector3 normal = new Vector3();
        VectorMath.getNormalVector(points[0], points[1], points[2], normal);
        for (int i = 0; i < points.length; i++) {
            mIndexList.add(
                    myMesh.addVertex(new Vertex(points[i], normal, material.diffuse)));
        }
    }

    public void addToIndexBuffer(ShortBuffer buffer) {

        int last = mIndexList.size() - 1;

        int v0 = mIndexList.get(0);
        int vn = mIndexList.get(last);

        // push triangles into the buffer
        for (int i = 1; i < last; i++) {
            int v1 = mIndexList.get(i);
            buffer.put((short) v0);
            buffer.put((short) v1);
            buffer.put((short) vn);
            v0 = v1;
        }
    }

    public int getIndexCount() {
        return (mIndexList.size() - 2) * 3;
    }
}
