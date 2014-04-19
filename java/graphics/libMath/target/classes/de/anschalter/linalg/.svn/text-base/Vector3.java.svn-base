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

import java.util.Arrays;

/**
 *
 * @author keeper
 */
public final class Vector3 {

    public float[] v = new float[3];

    public Vector3() {
    }

    public Vector3(final float v0, final float v1, final float v2) {
        v[0] = v0;
        v[1] = v1;
        v[2] = v2;
    }

    public Vector3(final Vector3 vec) {
        set(vec);
    }

    public Vector3 clear() {
        Arrays.fill(v, 0.0f);
        return this;
    }

    public void set(final float v0, final float v1, final float v2) {
        v[0] = v0;
        v[1] = v1;
        v[2] = v2;
    }

    public void set(final Vector3 vec) {
        System.arraycopy(vec.v, 0, v, 0, 3);
    }

    public Vector3 add(final Vector3 vec) {
        VectorMath.addVectors(this, vec, this);
        return this;
    }

    public Vector3 addScaledVector(final Vector3 vector, float scale) {
        VectorMath.addScaledVector(this, vector, scale);
        return this;
    }

    public Vector3 sub(final Vector3 vec) {
        VectorMath.subtractVectors(this, vec, this);
        return this;
    }

    public Vector3 cross(final Vector3 vec) {
        Vector3 result = new Vector3();
        VectorMath.vectorCrossProduct(this, vec, result);
        return result;
    }

    public float dot(final Vector3 vec) {
        return VectorMath.dotProduct(this, vec);
    }

    public float getLengthSqrd() {
        return VectorMath.getVectorLengthSqrd(this);
    }

// Gets the length of a vector
    public float getLength() {
        return VectorMath.getVectorLength(this);
    }

// Scales a vector by it's length - creates a unit vector
    public void normalize() {
        VectorMath.normalizeVector(this);
    }

    // Scales a vector by a scalar
    public void scale(final float fScale) {
        VectorMath.scaleVector(this, fScale);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Vector3)) {
            return false;
        }
        final Vector3 that = (Vector3) obj;
        return Float.floatToIntBits(v[0]) == Float.floatToIntBits(that.v[0]) && Float.
                floatToIntBits(v[1]) == Float.floatToIntBits(that.v[1]) && Float.
                floatToIntBits(v[2]) == Float.floatToIntBits(that.v[2]);
    }

    @Override
    public int hashCode() {
        int result = 646;
        result += Float.floatToIntBits(v[0]);
        result = 37 * result + Float.floatToIntBits(v[1]);
        result = 37 * result + Float.floatToIntBits(v[2]);
        return result;
    }

    @Override
    public String toString() {
        return "Vector3(" + v[0] + ", " + v[1] + ", " + v[2] + ")";
    }
}
