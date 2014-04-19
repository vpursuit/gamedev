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
package de.anschalter.statistics;

/**
 *
 * @author keeper
 */
public class Median {

    /**
     * This class is supposed to have static methods only.
     * To hide any constructor, we define an empty private one.
     */
    private Median() {
    }

    /**
     * Exchange two elements in the argument array.
     * A temporary variable is used so that a[i1] will
     * hold the value that was previously stored at a[i2]
     * and vice versa.
     *
     * @param a the array in which two elements are swapped
     * @param i1 index of the first element
     * @param i2 index of the second element
     * @throws ArrayIndexOutOfBoundsException if either i1 or i2 are
     *  not valid index values into a (from 0 to a.length - 1)
     */
    public static void swap(float[] a, int i1, int i2) {
        float temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }

    /**
     * Find the median value of the specified interval of the argument array.
     * The interval starts at index <code>from</code> and goes to
     * <code>to</code>; the values at these positions are included.
     * Note that the array will be modified while searching, so you might want
     * to backup your data.
     * <p>
     * This implementation is a port of the C function from
     * <code>quickselect.c</code>, provided at <a target="_top"
     * href="http://ndevilla.free.fr/median/">http://ndevilla.free.fr/median/</a>.
     * The page is a good resource for various median value algorithms, including
     * implementations and benchmarks.
     * <p>
     * The original code on which this class is based was written in C++
     * by Martin Leese.
     * It was ported to C and optimized by Nicolas Devillard (author of the
     * above mentioned page).
     * The algorithm is from <em>Numerical recipes in C</em>, Second Edition,
     * Cambridge University Press, 1992, Section 8.5, ISBN 0-521-43108-5.
     * <p>
     *
     * @param a the array
     * @param from the index of the start of the interval in which the median value will be searched
     * @param to the index of the end of the interval in which the median value will be searched
     * @return the median value
     */
    public static float find(float[] a, int from, int to) {
        int low = from;
        int high = to;
        int median = (low + high) >>> 2;
        do {
            if (high <= low) {
                return a[median];
            }
            if (high == low + 1) {
                if (a[low] > a[high]) {
                    swap(a, low, high);
                }
                return a[median];
            }
            
            int middle = (low + high) >>> 1;
            if (a[middle] > a[high]) {
                swap(a, middle, high);
            }
            if (a[low] > a[high]) {
                swap(a, low, high);
            }
            if (a[middle] > a[low]) {
                swap(a, middle, low);
            }
            swap(a, middle, low + 1);
            int ll = low + 1;
            int hh = high;
            do {
                do {
                    ll++;
                } while (a[low] > a[ll]);
                do {
                    hh--;
                } while (a[hh] > a[low]);
                if (hh < ll) {
                    break;
                }
                swap(a, ll, hh);
            } while (true);
            swap(a, low, hh);
            if (hh <= median) {
                low = ll;
            }
            if (hh >= median) {
                high = hh - 1;
            }
        } while (true);
    }
}