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
package de.anschalter.fp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author loewe
 */
public class FPTest {

    public FPTest() {
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
     * Test of toInt method, of class FP.
     */
    @Test
    public void testToInt() {
        System.out.println("toInt");
        assertEquals(0, FP.toInt(0));
        assertEquals(1 >> 16, FP.toInt(1));
        System.out.println("FP.toInt(1)=" + FP.toInt(1));
        assertEquals(256 >> 16, FP.toInt(256));
        System.out.println("FP.toInt(256)=" + FP.toInt(256));
        System.out.println("FP.toInt(512)=" + FP.toInt(512));
        System.out.println("FP.toInt(1024)=" + FP.toInt(1024));
        System.out.println("FP.toInt(2048)=" + FP.toInt(2048));
        System.out.println("FP.toInt(4096)=" + FP.toInt(4096));
        System.out.println("FP.toInt(8192)=" + FP.toInt(8192));
        System.out.println("FP.toInt(16392)=" + FP.toInt(16392));
        System.out.println("FP.toInt(32748)=" + FP.toInt(32748));
        System.out.println("FP.toInt(65568)=" + FP.toInt(65568));
        System.out.println("FP.toInt(65569)=" + FP.toInt(65569));
        System.out.println("FP.toInt(131136)=" + FP.toInt(131136));
        System.out.println("FP.toInt(262272)=" + FP.toInt(262272));
        System.out.println("FP.toInt(" + (int) Integer.MAX_VALUE + ")=" + FP.toInt((int) Integer.MAX_VALUE));
        System.out.println("FP.toInt(" + (int) Integer.MIN_VALUE + ")=" + FP.toInt((int) Integer.MIN_VALUE));
        System.out.println("FP.toInt(" + (int) (Integer.MAX_VALUE + 1) + ")=" + FP.toInt((int) (Integer.MAX_VALUE + 1)));

    }

    /**
     * Test of intToFP method, of class FP.
     */
    @Test
    public void testIntToFP() {
        System.out.println("intToFP");
        int x = 0;
        int expResult = 0;
        int result = FP.intToFP(x);
        assertEquals(expResult, result);
    }

    /**
     * Test of Mul method, of class FP.
     */
    @Test
    public void testMul() {
        System.out.println("Mul");
        System.out.println("FP.Mul(2, 2)=" + FP.toInt(FP.Mul(FP.intToFP(2), FP.intToFP(2))));
        //assertEquals(4, FP.Mul(FP.intToFP(2), FP.intToFP(2)));

        final int n = 100000000;
        float result = 0.0f;
        
        float af = 27.3f;
        float bf = 4.5f;
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            result = af * bf;
        }
        System.out.println("float mult:" + String.valueOf(System.currentTimeMillis() - start));

        int ax = FP.floatToFP(27.3f);
        int bx = FP.floatToFP(4.5f);
        int resultx = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            resultx = FP.Mul(ax, bx);
        }
        System.out.println("fixpoint mult:" + String.valueOf(System.currentTimeMillis() - start));



    }

    /**
     * Test of Div method, of class FP.
     */
    @Test
    public void testDiv() {
        System.out.println("Div");
        System.out.println("Mul");
        System.out.println("FP.Div(4, 2)=" + FP.toInt(FP.Div(FP.intToFP(4), FP.intToFP(2))));
        assertEquals(2, FP.toInt(FP.Div(FP.intToFP(4), FP.intToFP(2))));
    }

    /**
     * Test of Sqrt method, of class FP.
     */
    
    public void testSqrt() {
        System.out.println("Sqrt");
        int n = 0;
        int expResult = 0;
        int result = FP.Sqrt(n);
        assertEquals(expResult, result);
    }

    /**
     * Test of round method, of class FP.
     */
    @Test
    public void testRound() {
        System.out.println("round");
        int n = 0;
        int expResult = 0;
        int result = FP.round(n);
        assertEquals(expResult, result);
    }

    /**
     * Test of Sin method, of class FP.
     */
    @Test
    public void testSin() {
        System.out.println("Sin");
        int f = 0;
        int expResult = 0;
        int result = FP.Sin(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of Cos method, of class FP.
     */
    public void testCos() {
        System.out.println("Cos");
        int f = 0;
        int expResult = 0;
        int result = FP.Cos(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of Tan method, of class FP.
     */
    @Test
    public void testTan() {
        System.out.println("Tan");
        int f = 0;
        int expResult = 0;
        int result = FP.Tan(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of ArcTan method, of class FP.
     */
    @Test
    public void testArcTan() {
        System.out.println("ArcTan");
        int f = 0;
        int expResult = 0;
        int result = FP.ArcTan(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of ArcSin method, of class FP.
     */
  
    public void testArcSin() {
        System.out.println("ArcSin");
        int f = 0;
        int expResult = 0;
        int result = FP.ArcSin(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of ArcCos method, of class FP.
     */
    public void testArcCos() {
        System.out.println("ArcCos");
        int f = 0;
        int expResult = 0;
        int result = FP.ArcCos(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of Exp method, of class FP.
     */
    public void testExp() {
        System.out.println("Exp");
        int x = 0;
        int expResult = 0;
        int result = FP.Exp(x);
        assertEquals(expResult, result);
    }

    /**
     * Test of Ln method, of class FP.
     */
    @Test
    public void testLn() {
        System.out.println("Ln");
        int x = 0;
        int expResult = 0;
        int result = FP.Ln(x);
        assertEquals(expResult, result);
    }

    /**
     * Test of intersects method, of class FP.
     */
    public void testIntersects() {
        System.out.println("intersects");
        int ax0 = 0;
        int ay0 = 0;
        int ax1 = 0;
        int ay1 = 0;
        int bx0 = 0;
        int by0 = 0;
        int bx1 = 0;
        int by1 = 0;
        boolean expResult = false;
        boolean result = FP.intersects(ax0, ay0, ax1, ay1, bx0, by0, bx1, by1);
        assertEquals(expResult, result);
    }
}