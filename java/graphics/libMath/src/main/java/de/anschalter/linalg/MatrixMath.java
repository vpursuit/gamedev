/*
 *  Copyright (c) 2008 Peter Trebing. All rights reserved.
 *   
 *  Any unauthorised copying, duplication, reproduction and
 *  compilation will finalitute an infringement of copyright.
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
package de.anschalter.linalg;

/**
 *
 * @author keeper
 */
public final class MatrixMath {


    private MatrixMath() {}

    private static final float[] IDENTITY = new float[]{
        1.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 1.0f};


///////////////////////////////////////////////////////////////////////////////
// Load a matrix with the Idenity matrix
    public static void gltLoadIdentityMatrix(Matrix m) {
        System.arraycopy(IDENTITY, 0, m.m, 0, 16);
    }


///////////////////////////////////////////////////////////////////////////////
// Multiply two 4x4 matricies. Assumes normal OpenGL column major ordering
    public static void gltMultiplyMatrix(final Matrix m1, final Matrix m2,
            Matrix mProduct) {
        mProduct.m[0] =
                m1.m[0] * m2.m[0] + m1.m[4] * m2.m[1] + m1.m[8] * m2.m[2] + m1.m[12] * m2.m[3];
        mProduct.m[4] =
                m1.m[0] * m2.m[4] + m1.m[4] * m2.m[5] + m1.m[8] * m2.m[6] + m1.m[12] * m2.m[7];
        mProduct.m[8] =
                m1.m[0] * m2.m[8] + m1.m[4] * m2.m[9] + m1.m[8] * m2.m[10] + m1.m[12] * m2.m[11];
        mProduct.m[12] =
                m1.m[0] * m2.m[12] + m1.m[4] * m2.m[13] + m1.m[8] * m2.m[14] + m1.m[12] * m2.m[15];

        mProduct.m[1] =
                m1.m[1] * m2.m[0] + m1.m[5] * m2.m[1] + m1.m[9] * m2.m[2] + m1.m[13] * m2.m[3];
        mProduct.m[5] =
                m1.m[1] * m2.m[4] + m1.m[5] * m2.m[5] + m1.m[9] * m2.m[6] + m1.m[13] * m2.m[7];
        mProduct.m[9] =
                m1.m[1] * m2.m[8] + m1.m[5] * m2.m[9] + m1.m[9] * m2.m[10] + m1.m[13] * m2.m[11];
        mProduct.m[13] =
                m1.m[1] * m2.m[12] + m1.m[5] * m2.m[13] + m1.m[9] * m2.m[14] + m1.m[13] * m2.m[15];

        mProduct.m[2] =
                m1.m[2] * m2.m[0] + m1.m[6] * m2.m[1] + m1.m[10] * m2.m[2] + m1.m[14] * m2.m[3];
        mProduct.m[6] =
                m1.m[2] * m2.m[4] + m1.m[6] * m2.m[5] + m1.m[10] * m2.m[6] + m1.m[14] * m2.m[7];
        mProduct.m[10] =
                m1.m[2] * m2.m[8] + m1.m[6] * m2.m[9] + m1.m[10] * m2.m[10] + m1.m[14] * m2.m[11];
        mProduct.m[14] =
                m1.m[2] * m2.m[12] + m1.m[6] * m2.m[13] + m1.m[10] * m2.m[14] + m1.m[14] * m2.m[15];

        mProduct.m[3] =
                m1.m[3] * m2.m[0] + m1.m[7] * m2.m[1] + m1.m[11] * m2.m[2] + m1.m[15] * m2.m[3];
        mProduct.m[7] =
                m1.m[3] * m2.m[4] + m1.m[7] * m2.m[5] + m1.m[11] * m2.m[6] + m1.m[15] * m2.m[7];
        mProduct.m[11] =
                m1.m[3] * m2.m[8] + m1.m[7] * m2.m[9] + m1.m[11] * m2.m[10] + m1.m[15] * m2.m[11];
        mProduct.m[15] =
                m1.m[3] * m2.m[12] + m1.m[7] * m2.m[13] + m1.m[11] * m2.m[14] + m1.m[15] * m2.m[15];
    }

//////////////////////////////////////////////////////////////////////////////
// Create a translation matrix
    public static void gltTranslationMatrix(float x, float y, float z,
            Matrix mTranslate) {
        gltLoadIdentityMatrix(mTranslate);
        mTranslate.m[12] = x;
        mTranslate.m[13] = y;
        mTranslate.m[14] = z;
    }

///////////////////////////////////////////////////////////////////////////////
// Create a scaling matrix
    public static void gltScalingMatrix(float x, float y, float z,
            Matrix mScale) {
        gltLoadIdentityMatrix(mScale);
        mScale.m[0] = x;
        mScale.m[5] = y;
        mScale.m[11] = z;
    }

///////////////////////////////////////////////////////////////////////////////
// Creates a 4x4 rotation matrix, takes radians NOT degrees
    public static void gltRotationMatrix(float angle, float x, float y, float z,
            Matrix mMatrix) {

        float vecLength, sinSave, cosSave, oneMinusCos;
        float xx, yy, zz, xy, yz, zx, xs, ys, zs;

        // If NULL vector passed in, this will blow up...
        if (x == 0.0f && y == 0.0f && z == 0.0f) {
            gltLoadIdentityMatrix(mMatrix);
            return;
        }

        // Scale vector
        vecLength = (float) Math.sqrt(x * x + y * y + z * z);

        // Rotation matrix is normalized
        x /= vecLength;
        y /= vecLength;
        z /= vecLength;

        sinSave = (float) Math.sin(angle);
        cosSave = (float) Math.cos(angle);
        oneMinusCos = 1.0f - cosSave;

        xx = x * x;
        yy = y * y;
        zz = z * z;
        xy = x * y;
        yz = y * z;
        zx = z * x;
        xs = x * sinSave;
        ys = y * sinSave;
        zs = z * sinSave;

        mMatrix.m[0] = (oneMinusCos * xx) + cosSave;
        mMatrix.m[4] = (oneMinusCos * xy) - zs;
        mMatrix.m[8] = (oneMinusCos * zx) + ys;
        mMatrix.m[12] = 0.0f;

        mMatrix.m[1] = (oneMinusCos * xy) + zs;
        mMatrix.m[5] = (oneMinusCos * yy) + cosSave;
        mMatrix.m[9] = (oneMinusCos * yz) - xs;
        mMatrix.m[13] = 0.0f;

        mMatrix.m[2] = (oneMinusCos * zx) - ys;
        mMatrix.m[6] = (oneMinusCos * yz) + xs;
        mMatrix.m[10] = (oneMinusCos * zz) + cosSave;
        mMatrix.m[14] = 0.0f;

        mMatrix.m[3] = 0.0f;
        mMatrix.m[7] = 0.0f;
        mMatrix.m[11] = 0.0f;
        mMatrix.m[15] = 1.0f;
    }

// Creates a shadow projection matrix out of the plane equation
// coefficients and the position of the light. The return value is stored
// in destMat
//    void gltMakeShadowMatrix(GLTVector3[] vPoints, GLTVector4 vLightPos,
//            GLTMatrix destMat) {
//        GLTVector4 vPlaneEquation = null;
//        float dot;
//
//        VectorMath.gltGetPlaneEquation(vPoints[0],
//                vPoints[1], vPoints[2], vPlaneEquation);
//
//        // Dot product of plane and light position
//        dot = vPlaneEquation.v[0] * vLightPos.v[0] +
//                vPlaneEquation.v[1] * vLightPos.v[1] +
//                vPlaneEquation.v[2] * vLightPos.v[2] +
//                vPlaneEquation.v[3] * vLightPos.v[3];
//
//
//        // Now do the projection
//        // First column
//        destMat.m[0] = dot - vLightPos.v[0] * vPlaneEquation.v[0];
//        destMat.m[4] = 0.0f - vLightPos.v[0] * vPlaneEquation.v[1];
//        destMat.m[8] = 0.0f - vLightPos.v[0] * vPlaneEquation.v[2];
//        destMat.m[12] = 0.0f - vLightPos.v[0] * vPlaneEquation.v[3];
//
//        // Second column
//        destMat.m[1] = 0.0f - vLightPos.v[1] * vPlaneEquation.v[0];
//        destMat.m[5] = dot - vLightPos.v[1] * vPlaneEquation.v[1];
//        destMat.m[9] = 0.0f - vLightPos.v[1] * vPlaneEquation.v[2];
//        destMat.m[13] = 0.0f - vLightPos.v[1] * vPlaneEquation.v[3];
//
//        // Third Column
//        destMat.m[2] = 0.0f - vLightPos.v[2] * vPlaneEquation.v[0];
//        destMat.m[6] = 0.0f - vLightPos.v[2] * vPlaneEquation.v[1];
//        destMat.m[10] = dot - vLightPos.v[2] * vPlaneEquation.v[2];
//        destMat.m[14] = 0.0f - vLightPos.v[2] * vPlaneEquation.v[3];
//
//        // Fourth Column
//        destMat.m[3] = 0.0f - vLightPos.v[3] * vPlaneEquation.v[0];
//        destMat.m[7] = 0.0f - vLightPos.v[3] * vPlaneEquation.v[1];
//        destMat.m[11] = 0.0f - vLightPos.v[3] * vPlaneEquation.v[2];
//        destMat.m[15] = dot - vLightPos.v[3] * vPlaneEquation.v[3];
//    }

////////////////////////////////////////////////////////////////////////////
///
// Transpose the matrix in place
    public static void gltTransposeMatrix(Matrix mTranspose) {
        float temp;

        temp = mTranspose.m[ 1];
        mTranspose.m[ 1] = mTranspose.m[ 4];
        mTranspose.m[ 4] = temp;

        temp = mTranspose.m[ 2];
        mTranspose.m[ 2] = mTranspose.m[ 8];
        mTranspose.m[ 8] = temp;

        temp = mTranspose.m[ 3];
        mTranspose.m[ 3] = mTranspose.m[12];
        mTranspose.m[12] = temp;

        temp = mTranspose.m[ 6];
        mTranspose.m[ 6] = mTranspose.m[ 9];
        mTranspose.m[ 9] = temp;

        temp = mTranspose.m[ 7];
        mTranspose.m[ 7] = mTranspose.m[13];
        mTranspose.m[13] = temp;

        temp = mTranspose.m[11];
        mTranspose.m[11] = mTranspose.m[14];
        mTranspose.m[14] = temp;
    }

////////////////////////////////////////////////////////////////////////////
/// This function is not exported by library, just for this modules use only
// 3x3 determinant
    public static float detIJ(final Matrix m,
            int i, int j) {
        int x, y, ii, jj;
        float ret;
        float[][] mat = new float[3][3];

        x = 0;
        for (ii = 0; ii < 4; ii++) {
            if (ii == i) {
                continue;
            }
            y = 0;
            for (jj = 0; jj < 4; jj++) {
                if (jj == j) {
                    continue;
                }
                mat[x][y] = m.m[(ii * 4) + jj];
                y++;
            }
            x++;
        }

        ret = mat[0][0] * (mat[1][1] * mat[2][2] - mat[2][1] * mat[1][2]);
        ret -= mat[0][1] * (mat[1][0] * mat[2][2] - mat[2][0] * mat[1][2]);
        ret += mat[0][2] * (mat[1][0] * mat[2][1] - mat[2][0] * mat[1][1]);

        return ret;
    }

////////////////////////////////////////////////////////////////////////////
///
// Invert matrix
    public static void gltInvertMatrix(final Matrix m, Matrix mInverse) {
        int i, j;
        float det, detij;

        // calculate 4x4 determinant
        det = 0.0f;
        for (i = 0; i < 4; i++) {
            det +=
                    ((i & 0x1) == 1) ? (-m.m[i] * detIJ(m, 0, i)) : (m.m[i] * detIJ(
                    m, 0, i));
        }
        det = 1.0f / det;

        // calculate inverse
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                detij = detIJ(m, j, i);
                mInverse.m[(i * 4) + j] =
                        (((i + j) & 0x1) == 1) ? (-detij * det) : (detij * det);
            }
        }
    }
}
