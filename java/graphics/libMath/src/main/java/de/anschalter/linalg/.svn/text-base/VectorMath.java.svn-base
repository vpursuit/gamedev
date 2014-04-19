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
public final class VectorMath {

    private VectorMath() {
    }
    public static final float PI =
            3.14159265358979323846f;
    public static final float PI_DIV_180 = 0.017453292519943296f;
    public static final float INV_PI_DIV_180 = 57.2957795130823229f;

    public static final float degToRad(float x) {
        return (x) * PI_DIV_180;
    }

    public static final float radToDeg(float x) {
        return x * INV_PI_DIV_180;
    }

    // Copies a vector
    public static final void copyVector(final Vector3 vSource,
            Vector3 vDest) {
        System.arraycopy(vSource.v, 0, vDest.v, 0, 3);
    }

// Adds two vectors together
    public static final void addVectors(final float[] vFirst,
            final float[] vSecond,
            float[] vResult) {
        vResult[0] = vFirst[0] + vSecond[0];
        vResult[1] = vFirst[1] + vSecond[1];
        vResult[2] = vFirst[2] + vSecond[2];
    }

    public static final void addVectors(final Vector3 vFirst,
            final Vector3 vSecond, Vector3 vResult) {
        addVectors(vFirst.v, vSecond.v, vResult.v);
    }

// Subtract one vector from another
    public static final void subtractVectors(final float[] vFirst,
            final float[] vSecond,
            float[] vResult) {
        vResult[0] = vFirst[0] - vSecond[0];
        vResult[1] = vFirst[1] - vSecond[1];
        vResult[2] = vFirst[2] - vSecond[2];
    }

    public static final void subtractVectors(final Vector3 vFirst,
            final Vector3 vSecond, Vector3 vResult) {
        subtractVectors(vFirst.v, vSecond.v, vResult.v);
    }

// Scales a vector by a scalar
    public static final void scaleVector(float[] vVector, final float fScale) {
        vVector[0] *= fScale;
        vVector[1] *= fScale;
        vVector[2] *= fScale;
    }

    public static final void scaleVector(Vector3 vVector,
            final float fScale) {
        scaleVector(vVector.v, fScale);
    }

    public static final void addScaledVector(float[] vVector, final float[] v,
            final float scale) {
        vVector[0] += v[0] * scale;
        vVector[1] += v[1] * scale;
        vVector[2] += v[2] * scale;
    }

    public static final void addScaledVector(Vector3 vVector,
            final Vector3 v,
            final float scale) {
        addScaledVector(vVector.v, v.v, scale);
    }

// Gets the length of a vector squared
    public static final float getVectorLengthSqrd(final float[] vVector) {
        return (vVector[0] * vVector[0]) + (vVector[1] * vVector[1]) + (vVector[2] * vVector[2]);
    }

    public static final float getVectorLengthSqrd(final Vector3 vVector) {
        return getVectorLengthSqrd(vVector.v);
    }

// Gets the length of a vector
    public static final float getVectorLength(final float[] vVector) {
        return (float) Math.sqrt(getVectorLengthSqrd(vVector));
    }

    public static final float getVectorLength(final Vector3 vVector) {
        return getVectorLength(vVector.v);
    }

// Scales a vector by it's length - creates a unit vector
    public static final void normalizeVector(float[] vNormal) {
        float fLength = 1.0f / getVectorLength(
                vNormal);
        scaleVector(vNormal, fLength);
    }

    public static final void normalizeVector(Vector3 vNormal) {
        normalizeVector(vNormal.v);
    }

// Get the dot product between two vectors
    public static final float dotProduct(
            final float[] vU, final float[] vV) {
        return vU[0] * vV[0] + vU[1] * vV[1] + vU[2] * vV[2];
    }

    public static final float dotProduct(
            final Vector3 vU, final Vector3 vV) {
        return dotProduct(vU.v, vV.v);
    }

// Calculate the cross product of two vectors
    public static final void vectorCrossProduct(final float[] vU,
            final float[] vV, float[] vResult) {
        vResult[0] =
                vU[1] * vV[2] - vV[1] * vU[2];
        vResult[1] = -vU[0] * vV[2] + vV[0] * vU[2];
        vResult[2] = vU[0] * vV[1] - vV[0] * vU[1];

    }

    public static final void vectorCrossProduct(final Vector3 vU,
            final Vector3 vV, Vector3 vResult) {
        vectorCrossProduct(vU.v, vV.v, vResult.v);
    }

// Given three points on a plane in counter clockwise order, calculate the unit normal
    public static final void getNormalVector(final float[] vP1,
            final float[] vP2, final float[] vP3, float[] vNormal) {

        float[] vV1 = new float[3];
        float[] vV2 = new float[3];

        subtractVectors(vP2, vP1, vV1);
        subtractVectors(vP3, vP1, vV2);
        vectorCrossProduct(vV1, vV2, vNormal);

        normalizeVector(vNormal);

    }

    public static final void getNormalVector(final Vector3 vP1,
            final Vector3 vP2, final Vector3 vP3, Vector3 vNormal) {

        getNormalVector(vP1.v, vP2.v, vP3.v, vNormal.v);

    }

// Transform a point by a 4x4 matrix
    public static final void transformPoint(
            final Vector3 vSrcVector, final Matrix mMatrix,
            Vector3 vOut) {
        vOut.v[0] =
                mMatrix.m[0] * vSrcVector.v[0] + mMatrix.m[4] * vSrcVector.v[1] + mMatrix.m[8] * vSrcVector.v[2] + mMatrix.m[12];
        vOut.v[1] =
                mMatrix.m[1] * vSrcVector.v[0] + mMatrix.m[5] * vSrcVector.v[1] + mMatrix.m[9] * vSrcVector.v[2] + mMatrix.m[13];
        vOut.v[2] =
                mMatrix.m[2] * vSrcVector.v[0] + mMatrix.m[6] * vSrcVector.v[1] + mMatrix.m[10] * vSrcVector.v[2] + mMatrix.m[14];
    }

// Rotates a vector using a 4x4 matrix. Translation column is ignored
    public static final void rotateVector(final Vector3 vSrcVector,
            final Matrix mMatrix,
            Vector3 vOut) {
        vOut.v[0] =
                mMatrix.m[0] * vSrcVector.v[0] + mMatrix.m[4] * vSrcVector.v[1] + mMatrix.m[8] * vSrcVector.v[2];
        vOut.v[1] =
                mMatrix.m[1] * vSrcVector.v[0] + mMatrix.m[5] * vSrcVector.v[1] + mMatrix.m[9] * vSrcVector.v[2];
        vOut.v[2] =
                mMatrix.m[2] * vSrcVector.v[0] + mMatrix.m[6] * vSrcVector.v[1] + mMatrix.m[10] * vSrcVector.v[2];
    }


// Gets the three coefficients of a plane equation given three points on the plane.
    public static final void getPlaneEquation(float[] vPoint1,
            float[] vPoint2,
            float[] vPoint3, float[] vPlane) {
        // Get normal vector from three points. The normal vector is the first three coefficients
        // to the plane equation...
        getNormalVector(vPoint1, vPoint2, vPoint3, vPlane);

        // Final coefficient found by back substitution
        vPlane[3] =
                -(vPlane[0] * vPoint3[0] + vPlane[1] * vPoint3[1] + vPlane[2] * vPoint3[2]);
    }

// Determine the distance of a point from a plane, given the point and the
// equation of the plane.
    public static final float distanceToPlane(Vector3 vPoint,
            Vector4 vPlane) {
        return vPoint.v[0] * vPlane.v[0] + vPoint.v[1] * vPlane.v[1] + vPoint.v[2] * vPlane.v[2] + vPlane.v[3];
    }
}
