package de.anschalter.polygonal;

/*
 *  Copyright (c) 2008 Peter Trebing. All rights reserved.
 *   
 *  Any unauthorized copying, duplication, reproduction and
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
import de.anschalter.linalg.Vector3;
import de.anschalter.linalg.Vector4;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Vertex {

    public Vector3 point;
    public Vector3 normal;
    public Vector4 color;

    Vertex(Vector3 point, Vector3 normal, Vector4 color) {
        this.point = point;
        this.normal = normal;
        this.color = color;
    }

    Vertex(Vector3 point, Vector3 normal) {
        this(point, normal, null);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Vertex) {
            Vertex v = (Vertex) other;
            return (point.equals(v.point) && normal.equals(v.normal) && color.equals(v.color));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.point != null ? this.point.hashCode() : 0);
        hash = 83 * hash + (this.normal != null ? this.normal.hashCode() : 0);
        hash = 83 * hash + (this.color != null ? this.color.hashCode() : 0);
        return hash;
    }

    static public int toFixed(float x) {
        return (int) (x * 65536.0f);
    }

    public void put(FloatBuffer vertexBuffer, IntBuffer colorBuffer) {
        vertexBuffer.put(point.v);
        if (color == null) {
            colorBuffer.put(255);
            colorBuffer.put(255);
            colorBuffer.put(255);
            colorBuffer.put(255);
        } else {
            colorBuffer.put(toFixed(color.v[0])); // red
            colorBuffer.put(toFixed(color.v[1])); // green
            colorBuffer.put(toFixed(color.v[2])); // blue
            colorBuffer.put(toFixed(color.v[3])); // alpha
        }
    }

    public void put(IntBuffer vertexBuffer, IntBuffer colorBuffer) {
        vertexBuffer.put(toFixed(point.v[0]));
        vertexBuffer.put(toFixed(point.v[1]));
        vertexBuffer.put(toFixed(point.v[2]));
        if (color == null) {
            colorBuffer.put(65536);
            colorBuffer.put(65536);
            colorBuffer.put(65536);
            colorBuffer.put(65536);
        } else {
            colorBuffer.put(toFixed(color.v[0])); // red
            colorBuffer.put(toFixed(color.v[1])); // green
            colorBuffer.put(toFixed(color.v[2])); // blue
            colorBuffer.put(toFixed(color.v[3])); // alpha
        }
    }
}
