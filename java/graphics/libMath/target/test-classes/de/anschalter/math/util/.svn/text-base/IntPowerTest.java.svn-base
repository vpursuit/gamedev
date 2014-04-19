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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test method for 'de.anschalter.math.util.IntPower.raise(double, int)'
 * 
 * @author keeper
 * 
 */
public class IntPowerTest {

	private static Logger _log = Logger.getLogger(IntPowerTest.class.getName());

	private static int TEST_CYCLES = 1000000;
	private static long intRaise = 0;
	private static long mathPow = 0;

	/**
	 * Meassure runtimes of IntPower and Math.pow()
	 */
	@BeforeClass
	public static void meassureRuntimes() {

		long start = 0;

		// meassure runtime of IntPower
		start = System.currentTimeMillis();
		int ti = 100;
		for (int i = 0; i < TEST_CYCLES; i++) {
			IntPower.raise(2.0d, ti);
		}
		intRaise = System.currentTimeMillis() - start;

		// meassure runtime of Math.pow
		start = System.currentTimeMillis();
		double test = 100;
		for (int i = 0; i < TEST_CYCLES; i++) {
			Math.pow(2.0d, test);
		}
		mathPow = System.currentTimeMillis() - start;

		_log.log(Level.INFO, "Runtime ratio of Math.pow/IntPower: " + mathPow
				/ intRaise + " (" + mathPow + " msec / " + intRaise + " msec)");

	}

	@Test
	public void intPowerIsFasterMathPow() {

		assertTrue("IntPower is not faster than Math.pow", intRaise < mathPow);

	}

	@Test
	public final void testBoundaryValues() {

		assertTrue(Double.isInfinite(IntPower.raise(1.1d, Integer.MAX_VALUE)));
		assertTrue(Double.isInfinite(IntPower.raise(0.1d, Integer.MIN_VALUE)));

	}

	@Test
	public final void testValues() {

		assertEquals(1.0d, IntPower.raise(1.0d, Integer.MIN_VALUE), 0);
		assertEquals(1.0d, IntPower.raise(0.5d, -0), 0);
		assertEquals(1.0d, IntPower.raise(0.0d, 0), 0);
		assertEquals(1.0d, IntPower.raise(2.5d, 0), 0);
		assertEquals(2.5d, IntPower.raise(2.5d, 1), 0);

	}

	@Test
	public final void compareWithMathpow() {

		assertEquals(Math.pow(Math.PI, 2.0d), IntPower.raise(Math.PI, 2), 0.0d);
		assertEquals(Math.pow(Math.E, -3.0d), IntPower.raise(Math.E, -3), 0.0d);

	}

}