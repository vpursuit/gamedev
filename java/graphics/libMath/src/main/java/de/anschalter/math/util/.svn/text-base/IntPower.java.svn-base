/**
 * Copyright (c) 2007 Peter Trebing. All rights reserved.
 * 
 * Any unauthorised copying, duplication, reproduction and
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
package de.anschalter.math.util;

public final class IntPower {

	/**
	 * Computes x^power
	 * 
	 * @param x
	 * @param power
	 * @return
	 */
	public static double raise(final double x, final int power) {

		// If -1*Integer.MIN_VALUE is not defined, so assure power is greater.
		// Otherwise the recursive call below will fail an cause a stack
		// overflow!
		if (power == Integer.MIN_VALUE)
			return 1 / raise(x, -(power + 1));

		if (power < 0)
			return 1 / raise(x, -power);

		double result = 1;

		if (power > 0) {

			double xTmp = x;
			int e = power;

			while (e > 0) {
				if ((e & 1) == 1)
					result *= xTmp;
				xTmp *= xTmp;
				e >>= 1;
			}
		}
		return result;
	}
}
