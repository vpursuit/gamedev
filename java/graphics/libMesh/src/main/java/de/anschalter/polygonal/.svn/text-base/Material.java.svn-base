package de.anschalter.polygonal;

import de.anschalter.linalg.Vector4;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 *  Copyright (c) 2010 Peter Trebing. All rights reserved.
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
public class Material {

    public static final String DEFAULT = "DEFAULT";
    public static final String GREEN = "GREEN";
    public static final String MAGENTA = "MAGENTA";
    public static final String RED = "RED";
    public static final String BLUE = "BLUE";
    public static final String WHITE = "WHITE";
    public static final String BLACK = "BLACK";
    private static final Vector4 DEFAULT_AMBIENT = new Vector4(0.2f, 0.2f, 0.2f, 1.0f);
    private static final Vector4 DEFAULT_DIFFUSE = new Vector4(0.8f, 0.8f, 0.8f, 1.0f);
    private static final Vector4 DEFAULT_SPECULAR = new Vector4(0.8f, 0.8f, 0.8f, 1.0f);
    private static final Vector4 DEFAULT_EMISION = new Vector4(0.0f, 0.0f, 0.0f, 1.0f);
    private static final int DEFAULT_ILLUM = 2;
    public static final Vector4 COL_WHITE = new Vector4(1.0f, 1.0f, 1.0f, 1.0f);
    public static final Vector4 COL_BLACK = new Vector4(0.0f, 0.0f, 0.0f, 1.0f);
    public static final Vector4 COL_RED = new Vector4(1.0f, 0.0f, 0.0f, 1.0f);
    public static final Vector4 COL_GREEN = new Vector4(0.0f, 1.0f, 0.0f, 1.0f);
    public static final Vector4 COL_BLUE = new Vector4(0.0f, 0.0f, 1.0f, 1.0f);
    public static final Vector4 COL_MAGENTA = new Vector4(0.0f, 1.0f, 1.0f, 1.0f);
    private static final Map<String, Material> materials = new HashMap<String, Material>();

    public static final class MaterialDef {

        public MaterialDef(final String id, final Vector4 ambient, final Vector4 diffuse, final Vector4 specular, final Vector4 emision, final int illum) {
            this.id = id;
            this.ambient = ambient;
            this.diffuse = diffuse;
            this.specular = specular;
            this.emision = emision;
            this.illum = illum;
        }
        public String id;
        public Vector4 ambient;
        public Vector4 diffuse;
        public Vector4 specular;
        public Vector4 emision;
        public int illum;
    }

    static {
        create(new MaterialDef(DEFAULT, DEFAULT_AMBIENT, DEFAULT_DIFFUSE, DEFAULT_SPECULAR, DEFAULT_EMISION, DEFAULT_ILLUM));
        create(new MaterialDef(WHITE, COL_WHITE, COL_WHITE, DEFAULT_SPECULAR, DEFAULT_EMISION, DEFAULT_ILLUM));
        create(new MaterialDef(BLACK, COL_BLACK, COL_BLACK, DEFAULT_SPECULAR, DEFAULT_EMISION, DEFAULT_ILLUM));
        create(new MaterialDef(RED, COL_RED, COL_RED, DEFAULT_SPECULAR, DEFAULT_EMISION, DEFAULT_ILLUM));
        create(new MaterialDef(GREEN, COL_GREEN, COL_GREEN, DEFAULT_SPECULAR, DEFAULT_EMISION, DEFAULT_ILLUM));
        create(new MaterialDef(BLUE, COL_BLUE, COL_BLUE, DEFAULT_SPECULAR, DEFAULT_EMISION, DEFAULT_ILLUM));
        create(new MaterialDef(MAGENTA, COL_MAGENTA, COL_MAGENTA, DEFAULT_SPECULAR, DEFAULT_EMISION, DEFAULT_ILLUM));
    }
    private static int autoId = 0;
    private static Random r = new Random();
    public final String id;
    public final Vector4 ambient;
    public final Vector4 diffuse;
    public final Vector4 specular;
    public final Vector4 emision;
    public final int illum;

    public static final MaterialDef getDefaultDef() {
        return new MaterialDef(null, DEFAULT_AMBIENT, DEFAULT_DIFFUSE, DEFAULT_SPECULAR, DEFAULT_EMISION, DEFAULT_ILLUM);
    }

    public static final Material get(final String id) {
        return materials.get(id);
    }
    private static final float MINVALUE = 0.3f;

    public static final Material getRandom() {
        MaterialDef def = getDefaultDef();
        float ac = 0.0f;
        while (ac < MINVALUE) {
            ac = r.nextFloat();
        }
        def.ambient.v[0] = ac;
        ac = 0.0f;
        while (ac < MINVALUE) {
            ac = r.nextFloat();
        }
        def.ambient.v[1] = ac;
        ac = 0.0f;
        while (ac < MINVALUE) {
            ac = r.nextFloat();
        }
        def.ambient.v[2] = ac;
        def.ambient.v[3] = 1.0f;
        System.arraycopy(def.ambient.v, 0, def.diffuse.v, 0, 4);
        return create(def);
    }

    public static synchronized final Material create(final MaterialDef def) {
        if (def.id == null) {
            def.id = Integer.toString(autoId++);
        }
        if (get(def.id) == null) {
            Material m = new Material(def);
            materials.put(m.id, m);
            return m;
        } else {
            return null;
        }
    }

    private Material(final MaterialDef def) {
        this.id = def.id;
        this.ambient = new Vector4(def.ambient);
        this.diffuse = new Vector4(def.diffuse);
        this.specular = new Vector4(def.specular);
        this.emision = new Vector4(def.emision);
        this.illum = def.illum;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
