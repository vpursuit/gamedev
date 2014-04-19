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
package de.anschalter.scene;

import de.anschalter.polygonal.*;
import de.anschalter.linalg.Vector3;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

public class Mesh {

    public ArrayList<Face> mFaceList = new ArrayList<Face>();
    public ArrayList<Vertex> mVertexList = new ArrayList<Vertex>();
    public ArrayList<Vector3> mNormList = new ArrayList<Vector3>();
    public ArrayList<Integer> mIndexList = new ArrayList<Integer>();
    private FloatBuffer mColorBuffer = null;
    private ShortBuffer mIndexBuffer = null;
    private FloatBuffer mVertexBuffer = null;
    private FloatBuffer mNormalBuffer = null;
    private int indexCount = -1;

    public Mesh() {
    }

    public Face addFace(Face face) {
        indexCount = -1;
        mFaceList.add(face);
        return face;
    }

    private void putIndices(ShortBuffer buffer) {
        for (int i = 0; i < mFaceList.size(); i++) {
            Face face = mFaceList.get(i);
            face.addToIndexBuffer(buffer);
        }
    }

    private void putVertices(FloatBuffer buffer) {
        final ArrayList<Vertex> vList = mVertexList;
        for (int i = 0; i < vList.size(); i++) {
            Vertex vertex = vList.get(i);
            buffer.put(vertex.point.v);
        }
    }

    private void putNormals(FloatBuffer buffer) {
        final ArrayList<Vertex> vList = mVertexList;
        for (int i = 0; i < vList.size(); i++) {
            Vertex vertex = vList.get(i);
            buffer.put(vertex.normal.v);
        }
    }

    private void putColors(FloatBuffer buffer) {
        final ArrayList<Vertex> vList = mVertexList;
        for (int i = 0; i < vList.size(); i++) {
            Vertex vertex = vList.get(i);
            buffer.put(vertex.color.v[0]); // red
            buffer.put(vertex.color.v[1]); // green
            buffer.put(vertex.color.v[2]); // blue
            buffer.put(vertex.color.v[3]); // alpha
        }
    }

    static public short toShort(float x) {
        return (short) (x * 32768.0f);
    }

    public int getIndexCount() {
        if (indexCount < 0) {
            indexCount = 0;
            for (int i = 0; i < mFaceList.size(); i++) {
                Face face = mFaceList.get(i);
                indexCount += face.getIndexCount();
            }
        }
        return indexCount;
    }

    public int addVertex(Vertex vertex) {
//        int index = mVertexList.indexOf(vertex);
//        if (index < 0) {
//            index = mVertexList.size();
//            mVertexList.add(vertex);
//        }
//        return index;
        //For performance reasons it is not possible to do the optimizations above
        //This needs to be done before the mesh is used by this program.
        final int index = mVertexList.size();
        mVertexList.add(vertex);
        return index;
    }

    public FloatBuffer getColorBuffer() {
        if (mColorBuffer == null) {
            mColorBuffer = newFloatBuffer(mVertexList.size() * 4);
            putColors(mColorBuffer);
        }
        return mColorBuffer;
    }

    public FloatBuffer getVertexBuffer() {
        if (mVertexBuffer == null) {
            mVertexBuffer = newFloatBuffer(mVertexList.size() * 3);
            putVertices(mVertexBuffer);
        }
        return mVertexBuffer;
    }

    public FloatBuffer getNormalBuffer() {
        if (mNormalBuffer == null) {
            mNormalBuffer = newFloatBuffer(mVertexList.size() * 3);
            putNormals(mNormalBuffer);
        }
        return mNormalBuffer;
    }

    public ShortBuffer getIndexBuffer() {
        if (mIndexBuffer == null) {
            mIndexBuffer = newShortBuffer(getIndexCount());
            putIndices(mIndexBuffer);
        }
        return mIndexBuffer;
    }

    // public float mdRot = 0.f;
    // public float mRotation = 0.0f;
    public static ShortBuffer newShortBuffer(int numElements) {
        ByteBuffer bb = newByteBuffer(numElements * SIZEOF_SHORT);
        return bb.asShortBuffer();
    }

    public static IntBuffer newIntBuffer(int numElements) {
        ByteBuffer bb = newByteBuffer(numElements * SIZEOF_INT);
        return bb.asIntBuffer();
    }

    public static FloatBuffer newFloatBuffer(int numElements) {
        ByteBuffer bb = newByteBuffer(numElements * SIZEOF_FLOAT);
        return bb.asFloatBuffer();
    }

    public static ByteBuffer newByteBuffer(int numElements) {
        ByteBuffer bb = ByteBuffer.allocateDirect(numElements);
        bb.order(ByteOrder.nativeOrder());
        return bb;
    }
    public static final int SIZEOF_BYTE = 1;
    public static final int SIZEOF_SHORT = 2;
    public static final int SIZEOF_INT = 4;
    public static final int SIZEOF_FLOAT = 4;
    public static final int SIZEOF_LONG = 8;
    public static final int SIZEOF_DOUBLE = 8;
}
