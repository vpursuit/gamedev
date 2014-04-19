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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author keeper
 */
public class Vector3Test {

    public Vector3Test() {
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
     * Test of sub method, of class GLTVector3.
     */
    @Test
    public void testSub() {
    }

    /**
     * Test of cross method, of class GLTVector3.
     */
    @Test
    public void testCross() {
        System.out.println("cross");

        //Parallel vectors
        assertEquals(new Vector3(0f, 0f, 0f), new Vector3(1f, 0f, 0f).
                cross(new Vector3(1f, 0f, 0f)));

        assertEquals(new Vector3(0f, 0f, 0f), new Vector3(0f, 1f, 0f).
                cross(new Vector3(0f, 1f, 0f)));

        assertEquals(new Vector3(0f, 0f, 0f), new Vector3(0f, 0f, 1f).
                cross(new Vector3(0f, 0f, 1f)));

        //Orthogonal vectors
        Vector3 a = new Vector3(1f, 0f, 0f);
        Vector3 b = new Vector3(0f, 1f, 0f);
        assertEquals(new Vector3(0f, 0f, 1f), a.cross(b));
        assertEquals(new Vector3(0f, 0f, -1f), b.cross(a));

        a = new Vector3(1f, 0f, 0f);
        b = new Vector3(0f, 0f, 1f);
        assertEquals(new Vector3(0f, -1f, 0f), a.cross(b));
        assertEquals(new Vector3(0f, 1f, 0f), b.cross(a));

        a = new Vector3(0f, 1f, 0f);
        b = new Vector3(0f, 0f, 1f);
        assertEquals(new Vector3(1f, 0f, 0f), a.cross(b));
        assertEquals(new Vector3(-1f, 0f, 0f), b.cross(a));


        //non orthogonal vectors
        a = new Vector3(2f, 1f, 2f);
        b = new Vector3(3f, 3f, 0f);
        assertEquals(new Vector3(-6f, 6f, 3f), a.cross(b));
        assertEquals(new Vector3(6f, -6f, -3f), b.cross(a));

    }
}