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
package de.anschalter;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author keeper
 */
public class TimeSpanTest {

	public TimeSpanTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of compareTo method, of class TimeSpan.
	 */
	@Test
	public void testConstructor() {

		TimeSpan instance = new TimeSpan(TimeSpan.TICKS_PER_MILLISECOND + 1);

		System.out.println(instance);
		assertEquals(instance.getMilliSeconds(), 1);
		assertEquals(instance.getTicks(), (long) 1);

		// instance = new TimeSpan(Long.MAX_VALUE);

		// assertEquals(instance.getMilliSeconds(), 1);
		// assertEquals(instance.getTicks(), 100);

	}

	/**
	 * Test of compareTo method, of class TimeSpan.
	 */
	@Test
	public void testCompareTo() {

		assertEquals(TimeSpan.MAX_VALUE.compareTo(TimeSpan.ZERO), 1);

		assertEquals(TimeSpan.MIN_VALUE.compareTo(TimeSpan.ZERO), -1);

		assertEquals(TimeSpan.MAX_VALUE.compareTo(TimeSpan.MAX_VALUE), 0);

		assertEquals(TimeSpan.MIN_VALUE.compareTo(TimeSpan.MIN_VALUE), 0);

		assertEquals(
				new TimeSpan(25, 0, 0).compareTo(new TimeSpan(1, 1, 0, 0)), 0);

	}
}
