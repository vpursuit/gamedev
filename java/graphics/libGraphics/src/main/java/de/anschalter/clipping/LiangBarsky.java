/**
 * Copyright (c) 2007 Peter Trebing. All rights reserved.
 * 
 * Any unauthorized copying, duplication, reproduction and
 * compilation will constitute an infringement of copyright.
 * 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.anschalter.clipping;

/**
 * @author keeper
 * 
 */
public class LiangBarsky {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static class Line2D {
		public int x1;
		public int y1;
		public int x2;
		public int y2;
	}

	public static class Line3D {
		public int x1;
		public int y1;
		public int z1;
		public int x2;
		public int y2;
		public int z2;
	}

	public static class Intervalf {
		public float a;
		public float b;

		public Intervalf(final float low, final float high) {
			a = low;
			b = high;
		}

	}

	public Line2D clip2D(
			final int x1,
			final int y1,
			final int x2,
			final int y2,
			final int xl,
			final int xr,
			final int yb,
			final int yt) {

		Line2D result = new Line2D();

		final Intervalf range = new Intervalf(0, 1);
		final int deltaX = x2 - x1;

		if (clipt(-deltaX, x1 - xl, range))
			if (clipt(deltaX, xr - x1, range)) {
				final int deltaY = y2 - y1;
				if (clipt(-deltaY, y1 - yb, range))
					if (clipt(deltaY, yt - y1, range)) {
						if (range.b < 1) {
							result.x1 = x1;
							result.y1 = y1;
							result.x2 = x1 + (int) range.b * deltaX;
							result.y2 = y1 + (int) range.b * deltaY;
						}
						if (range.a > 0) {
							result.x1 = x1 + (int) range.a * deltaX;
							result.y1 = y1 + (int) range.a * deltaY;
							result.x2 = x1;
							result.y2 = y1;
						}
					}
			}
		return result;
	}

	public Line3D clip3D(
			final int x1,
			final int y1,
			final int z1,
			final int x2,
			final int y2,
			final int z2,
			final int xl,
			final int xr,
			final int yb,
			final int yt) {

		Line3D result = new Line3D();

		final Intervalf range = new Intervalf(0, 1);
		final int deltaX = x2 - x1;
		final int deltaZ = z2 - z1;

		if (clipt(-deltaX - deltaZ, x1 + z1, range))
			if (clipt(deltaX - deltaZ, z1 - x1, range)) {
				final int deltaY = y2 - y1;
				if (clipt(-deltaY - deltaZ, y1 + z1, range))
					if (clipt(deltaY - deltaZ, z1 - y1, range)) {
						if (range.b < 1) {
							result.x1 = x1;
							result.y1 = y1;
							result.z1 = z1;
							result.x2 = x1 + (int) range.b * deltaX;
							result.y2 = y1 + (int) range.b * deltaY;
							result.z2 = z1 + (int) range.b * deltaZ;
						}
						if (range.a > 0) {
							result.x1 = x1 + (int) range.a * deltaX;
							result.y1 = y1 + (int) range.a * deltaY;
							result.z1 = z1 + (int) range.a * deltaZ;
							result.x2 = x1;
							result.y2 = y1;
							result.z2 = z2;
						}
					}
			}
		return result;
	}

	private boolean clipt(final int d, final int q, Intervalf range) {

		if (d == 0 && q < 0) {
			return false; // ausserhalb und parallel zu Begrenzung
		} else {
			final float t = (float)q / (float)d;
			if (d < 0) {
				if (t > range.b) {
					return false;
				} else if (t > range.a) {
					range.a = t;
				}
			} else if (d > 0) {
				if (t < range.a) {
					return false;
				} else if (t < range.b) {
					range.b = t;
				}
			}
		}
		return true;
	}

}
