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
package de.anschalter.linalg;

/**
 *
 * @author keeper
 */
public final class FrameMath {

    private FrameMath() {
    }

    public static Frame gltInitFrame(Frame pFrame) {

        pFrame.vLocation.v[0] = 0.0f;
        pFrame.vLocation.v[1] = 0.0f;
        pFrame.vLocation.v[2] = 0.0f;

        pFrame.vUp.v[0] = 0.0f;
        pFrame.vUp.v[1] = 1.0f;
        pFrame.vUp.v[2] = 0.0f;

        pFrame.vForward.v[0] = 0.0f;
        pFrame.vForward.v[1] = 0.0f;
        pFrame.vForward.v[2] = -1.0f;

        return pFrame;

    }

///////////////////////////////////////////////////////////////////
// Derives a 4x4 transformation matrix from a frame of reference
    public static Matrix gltGetMatrixFromFrame(Frame pFrame) {

        Matrix result = new Matrix();

        // Calculate X Axis
        Vector3 vXAxis = pFrame.vUp.cross(pFrame.vForward);

        // Just populate the matrix
        // X column vector
        System.arraycopy(vXAxis.v, 0, result.m, 0, 3);
        result.m[3] = 0.0f;

        // y column vector
        System.arraycopy(pFrame.vUp.v, 0, result.m, 4, 3);
        result.m[7] = 0.0f;

        // z column vector
        System.arraycopy(pFrame.vForward.v, 0, result.m, 8, 3);
        result.m[11] = 0.0f;

        // Translation/Location vector
        System.arraycopy(pFrame.vLocation.v, 0, result.m, 12, 3);
        result.m[15] = 1.0f;

        return result;

    }

//////////////////////////////////////////////////////////////////
// Apply a camera transform given a frame of reference. This is
// pretty much just an alternate implementation of gluLookAt using
// floats instead of doubles and having the forward vector specified
// instead of a point out in front of me.
    public static void gltApplyCameraTransform(Frame pCamera) {

        Matrix mMatrix = new Matrix();
        Vector3 zFlipped = new Vector3();

        zFlipped.v[0] = -pCamera.vForward.v[0];
        zFlipped.v[1] = -pCamera.vForward.v[1];
        zFlipped.v[2] = -pCamera.vForward.v[2];

        // Derive X vector
        Vector3 vAxisX = pCamera.vUp.cross(zFlipped);

        // Populate matrix, note this is just the rotation and is transposed
        mMatrix.m[0] = vAxisX.v[0];
        mMatrix.m[4] = vAxisX.v[1];
        mMatrix.m[8] = vAxisX.v[2];
        mMatrix.m[12] = 0.0f;

        mMatrix.m[1] = pCamera.vUp.v[0];
        mMatrix.m[5] = pCamera.vUp.v[1];
        mMatrix.m[9] = pCamera.vUp.v[2];
        mMatrix.m[13] = 0.0f;

        mMatrix.m[2] = zFlipped.v[0];
        mMatrix.m[6] = zFlipped.v[1];
        mMatrix.m[10] = zFlipped.v[2];
        mMatrix.m[14] = 0.0f;

        mMatrix.m[3] = 0.0f;
        mMatrix.m[7] = 0.0f;
        mMatrix.m[11] = 0.0f;
        mMatrix.m[15] = 1.0f;

        // Do the rotation first
        //glMultMatrixf(mMatrix);

        // Now, translate backwards
//        glTranslatef(-pCamera.vLocation.v[0], -pCamera.vLocation.v[1],
//    -pCamera.vLocation.v[2]);
    }

/////////////////////////////////////////////////////////
// March a frame of reference forward. This simply moves
// the location forward along the forward vector.
    public static void gltMoveFrameForward(Frame pFrame, float fStep) {
        pFrame.vLocation.v[0] +=
                pFrame.vForward.v[0] * fStep;
        pFrame.vLocation.v[1] += pFrame.vForward.v[1] * fStep;
        pFrame.vLocation.v[2] += pFrame.vForward.v[2] * fStep;
    }

/////////////////////////////////////////////////////////
// Move a frame of reference up it's local Y axis
    public static void gltMoveFrameUp(Frame pFrame, float fStep) {
        pFrame.vLocation.v[0] +=
                pFrame.vUp.v[0] * fStep;
        pFrame.vLocation.v[1] += pFrame.vUp.v[1] * fStep;
        pFrame.vLocation.v[2] += pFrame.vUp.v[2] * fStep;
    }

////////////////////////////////////////////////////////
// Move a frame of reference along it's local X axis
    public static void gltMoveFrameRight(Frame pFrame, float fStep) {
        Vector3 vCross = pFrame.vUp.cross(pFrame.vForward);
        pFrame.vLocation.v[0] += vCross.v[0] * fStep;
        pFrame.vLocation.v[1] += vCross.v[1] * fStep;
        pFrame.vLocation.v[2] += vCross.v[2] * fStep;
    }

/////////////////////////////////////////////////////////
// Translate a frame in world coordinates
    public static void gltTranslateFrameWorld(Frame pFrame, float x, float y,
            float z) {
        pFrame.vLocation.v[0] += x;
        pFrame.vLocation.v[1] += y;
        pFrame.vLocation.v[2] += z;
    }

/////////////////////////////////////////////////////////
// Translate a frame in local coordinates
    public static void gltTranslateFrameLocal(Frame pFrame, float x, float y,
            float z) {
        gltMoveFrameRight(pFrame, x);
        gltMoveFrameUp(pFrame, y);
        gltMoveFrameForward(pFrame, z);
    }

/////////////////////////////////////////////////////////
// Rotate a frame around it's local Y axis
    public static void gltRotateFrameLocalY(Frame pFrame, float fAngle) {
        Matrix mRotation = new Matrix();
        Vector3 vNewForward = new Vector3();

        // Only the up vector needs to be rotated
        MatrixMath.gltRotationMatrix((float) VectorMath.degToRad(fAngle), pFrame.vUp.v[0],
                pFrame.vUp.v[1], pFrame.vUp.v[2], mRotation);

        VectorMath.rotateVector(pFrame.vForward, mRotation, vNewForward);

        System.arraycopy(vNewForward.v, 0, pFrame.vForward.v, 0, 3);

    }

//////////////////////////////////////////////////////////
//  Rotate a frame around it's local X axis
    public static void gltRotateFrameLocalX(Frame pFrame, float fAngle) {

        Matrix mRotation = new Matrix();
        Vector3 vCross = pFrame.vUp.cross(pFrame.vForward);

        MatrixMath.gltRotationMatrix((float) VectorMath.degToRad(fAngle), vCross.v[0], vCross.v[1],
                vCross.v[2],
                mRotation);

        Vector3 vNewVect = new Vector3();

         // Inline 3x3 matrix multiply for rotation only
        VectorMath.rotateVector(pFrame.vForward, mRotation, vNewVect);      
        System.arraycopy(vNewVect.v, 0, pFrame.vForward.v, 0, 3);

        // Update pointing up vector
        VectorMath.rotateVector(pFrame.vUp, mRotation, vNewVect);
        System.arraycopy(vNewVect.v, 0, pFrame.vUp.v, 0, 3);
    }

/////////////////////////////////////////////////////////////
// Rotate a frame around it's local Z axis
    public static void gltRotateFrameLocalZ(Frame pFrame, float fAngle) {
        Matrix mRotation = new Matrix();

        // Only the up vector needs to be rotated
        MatrixMath.gltRotationMatrix((float) VectorMath.degToRad(fAngle), pFrame.vForward.v[0],
                pFrame.vForward.v[1], pFrame.vForward.v[2], mRotation);
        Vector3 vNewVect = new Vector3();
        VectorMath.rotateVector(pFrame.vUp, mRotation, vNewVect);
        System.arraycopy(vNewVect.v, 0, pFrame.vUp.v, 0, 3);
    }
}
