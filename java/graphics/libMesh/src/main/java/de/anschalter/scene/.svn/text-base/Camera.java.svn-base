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

import de.anschalter.linalg.Frame;
import de.anschalter.linalg.FrameMath;
import de.anschalter.linalg.Matrix;
import de.anschalter.linalg.Vector3;

/**
 * 
 * @author keeper
 */
public class Camera extends Node {

	public static int PROJECTION_PERSPECTIVE = 0;
	public static int PROJECTION_PERSPECTIVE_INFINITE_FAR = 1;
	public static int PROJECTION_PARALLEL = 2;
	
	public static final Camera STANDARD16x9;

	static {

		Frame frame = FrameMath.gltInitFrame(new Frame());
		frame.vLocation = new Vector3(0.0f, 0.0f, 100.0f);

		STANDARD16x9 = new Camera(Camera.PROJECTION_PERSPECTIVE, frame,
				16.0f / 9.0f);

	}
	
	private int projectionType = PROJECTION_PERSPECTIVE;
	public Frustum frustum;
	private Matrix projection;
	private Matrix modelViewMatrix;
	private boolean frameChanged = true;

	public Camera(final int projectionType, final Frame frame, float aspectRatio) {
		this(projectionType, frame, new Frustum(45.0f, aspectRatio, 1.0f,
				200.0f));
	}

	public Camera(final int projectionType, final Frame frame,
			final Frustum frustum) {

		this.projectionType = projectionType;
		setFrame(frame);
		setFrustum(frustum);

	}

	public void setFrame(Frame frame) {
		this.frame = frame;
		frameChanged = true;
	}

	public void setFrustum(final Frustum frustum) {
		this.frustum = frustum;
		this.frustum.isDirty = true;
	}

	public void roll(float angle) {
		throw new UnsupportedOperationException();
	}

	public void pitch(float angle) {
		throw new UnsupportedOperationException();
	}

	public void yaw(float angle) {
		throw new UnsupportedOperationException();
	}

	public void slide(float delU, float delV, float delN) {
		throw new UnsupportedOperationException();
	}

	public Matrix getProjectionMatrix() {
		if (frustum.isDirty) {

			switch (projectionType) {
			case 0:
				projection = getPerspectiveProjectionMatrix(frustum.left,
						frustum.right, frustum.bottom, frustum.top,
						frustum.near, frustum.far);
				break;

			case 1:
				projection = getPerspectiveInfiniteFarProjectionMatrix(
						frustum.left, frustum.right, frustum.bottom,
						frustum.top, frustum.near);
				break;
			case 2:
				projection = getParallelProjectionMatrix(-2.5f, 2.5f, -2.5f,
						2.5f, -20, 20);
				break;

			default:
				throw new IllegalStateException("Unknown projection type");
			}

			frustum.isDirty = false;

		}
		return projection;
	}

	/**
	 * Computes a parallel projection matrix.
	 * 
	 * Math from "Mathematics For 3D Game Programming & Computer Graphics" page
	 * 126.
	 * 
	 * @param left
	 *            left plane distance of the viewing frustum
	 * @param right
	 *            right plane distance of the viewing frustum
	 * @param bottom
	 *            bottom plane distance of the viewing frustum
	 * @param top
	 *            top plane distance of the viewing frustum
	 * @param near
	 *            near plane distance of the viewing frustum
	 * @param far
	 *            far plane distance of the viewing frustum
	 * 
	 * @return parallel projection matrix
	 */
	public static final Matrix getParallelProjectionMatrix(final float left,
			final float right, final float bottom, final float top,
			final float near, final float far) {

		final Matrix p = new Matrix();

		final float rightMinusLeft = right - left;
		final float topMinusBottom = top - bottom;

		p.m[0] = 2 / rightMinusLeft;
		p.m[1] = 0;
		p.m[2] = 0;
		p.m[3] = 0;

		p.m[4] = 0;
		p.m[5] = 2 / topMinusBottom;
		p.m[6] = 0;
		p.m[7] = 0;

		p.m[8] = 0;
		p.m[9] = 0;
		p.m[10] = -2 / (near - far);
		p.m[11] = 0;

		p.m[12] = -1 * (right + left) / rightMinusLeft;
		p.m[13] = -1 * (top + bottom) / topMinusBottom;
		p.m[14] = (near + far) / (near - far);
		p.m[15] = 1;

		return p;

	}

	/**
	 * Computes a perspective projection matrix.
	 * 
	 * Math from "Computer Graphics using OpenGL 3rd Edition" page 356.
	 * 
	 * @param left
	 *            left plane distance of the viewing frustum
	 * @param right
	 *            right plane distance of the viewing frustum
	 * @param bottom
	 *            bottom plane distance of the viewing frustum
	 * @param top
	 *            top plane distance of the viewing frustum
	 * @param near
	 *            near plane distance of the viewing frustum
	 * @param far
	 *            far plane distance of the viewing frustum
	 * 
	 * @return perspective projection matrix
	 */
	public static final Matrix getPerspectiveProjectionMatrix(final float left,
			final float right, final float bottom, final float top,
			final float near, final float far) {

		final Matrix p = new Matrix();

		final float rightMinusLeft = right - left;
		final float topMinusBottom = top - bottom;
		final float farMinusNear = far - near;

		p.m[0] = 2 * near / rightMinusLeft;
		p.m[1] = 0;
		p.m[2] = 0;
		p.m[3] = 0;

		p.m[4] = 0;
		p.m[5] = 2 * near / topMinusBottom;
		p.m[6] = 0;
		p.m[7] = 0;

		p.m[8] = (right + left) / rightMinusLeft;
		p.m[9] = (top + bottom) / topMinusBottom;
		p.m[10] = -(far + near) / farMinusNear;
		p.m[11] = -1;

		p.m[12] = 0;
		p.m[13] = 0;
		p.m[14] = -2 * far * near / farMinusNear;
		p.m[15] = 0;

		return p;

	}

	/**
	 * Computes a perspective projection matrix with far plane distance to tend
	 * to infinity.
	 * 
	 * Math from "Mathematics For 3D Game Programming & Computer Graphics" page
	 * 130.
	 * 
	 * @param left
	 *            left plane distance of the viewing frustum
	 * @param right
	 *            right plane distance of the viewing frustum
	 * @param bottom
	 *            bottom plane distance of the viewing frustum
	 * @param top
	 *            top plane distance of the viewing frustum
	 * @param near
	 *            near plane distance of the viewing frustum
	 * 
	 * @return perspective projection matrix
	 */
	public static final Matrix getPerspectiveInfiniteFarProjectionMatrix(
			final float left, final float right, final float bottom,
			final float top, final float near) {

		final Matrix p = new Matrix();

		final float rightMinusLeft = right - left;
		final float topMinusBottom = top - bottom;

		p.m[0] = 2 * near / rightMinusLeft;
		p.m[1] = 0;
		p.m[2] = 0;
		p.m[3] = 0;

		p.m[4] = 0;
		p.m[5] = 2 * near / topMinusBottom;
		p.m[6] = 0;
		p.m[7] = 0;

		p.m[8] = (right + left) / rightMinusLeft;
		p.m[9] = (top + bottom) / topMinusBottom;
		p.m[10] = -1;
		p.m[11] = -1;

		p.m[12] = 0;
		p.m[13] = 0;
		p.m[14] = -2 * near;
		p.m[15] = 0;

		return p;

	}

	public final Matrix getModelViewMatrix() {

		Vector3 u, v, n;
		modelViewMatrix = new Matrix();

		if (frameChanged) {

			// Compute new Coordinate frame
			n = new Vector3(frame.vLocation).sub(frame.vForward);
			u = new Vector3(frame.vUp).cross(n);

			n.normalize();
			u.normalize();

			v = n.cross(u);

			modelViewMatrix.m[0] = u.v[0];
			modelViewMatrix.m[1] = v.v[0];
			modelViewMatrix.m[2] = n.v[0];
			modelViewMatrix.m[3] = 0;

			modelViewMatrix.m[4] = u.v[1];
			modelViewMatrix.m[5] = v.v[1];
			modelViewMatrix.m[6] = n.v[1];
			modelViewMatrix.m[7] = 0;

			modelViewMatrix.m[8] = u.v[2];
			modelViewMatrix.m[9] = v.v[2];
			modelViewMatrix.m[10] = n.v[2];
			modelViewMatrix.m[11] = 0;

			modelViewMatrix.m[12] = -frame.vLocation.dot(u);
			modelViewMatrix.m[13] = -frame.vLocation.dot(v);
			modelViewMatrix.m[14] = -frame.vLocation.dot(n);
			modelViewMatrix.m[15] = 1f;
		}

		return modelViewMatrix;

	}

	public static final class Frustum {

		public float left, right, bottom, top, near, far, fovy, aspectRatio;
		volatile boolean isDirty = true;

		public Frustum(final float fovy, final float aspectRatio,
				final float near, final float far) {

			this.fovy = fovy;
			this.aspectRatio = aspectRatio;
			this.near = near;
			this.far = far;
			setFovy(fovy, near, aspectRatio);

		}

		public synchronized void addFovy(float d) {

			isDirty = true;
			fovy += d;

			if (fovy > 180.0f) {
				fovy = 180.0f;
				return;
			} else if (fovy < 0.0f) {
				fovy = 0.0f;
				return;
			}

			setFovy(this.fovy, this.near, this.aspectRatio);
		}

		public synchronized void setAspectRatio(int width, int height) {
			isDirty = true;
			aspectRatio = (float) width / (float) height;
			this.right = aspectRatio * this.top;
			this.left = -right;
		}

		private void setFovy(final float fovy, final float near,
				final float aspectRatio) {
			float fovyArc = (float) (fovy * Math.PI / 180);
			this.top = (float) (near * Math.tan(fovyArc / 2));
			this.bottom = -top;
			this.right = aspectRatio * top;
			this.left = -right;
		}
	}
}
