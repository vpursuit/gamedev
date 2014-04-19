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


import de.anschalter.linalg.Vector3;
import de.anschalter.polygonal.Face;
import de.anschalter.polygonal.Material;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class OBJLoader {

	private final List<Vector3> vertices = new ArrayList<Vector3>();
	private final List<Vector3> normals = new ArrayList<Vector3>();
	private Mesh mesh;
	private static final Map<String, Integer> VERTEX_DATA = new HashMap<String, Integer>();
	private static final int VERTEX_DATA_V = 0;
	private static final int VERTEX_DATA_VN = 1;
	private static final int VERTEX_DATA_VT = 2;
	private static final int VERTEX_DATA_VP = 3;
	private String currentMat = Material.DEFAULT;

	static {
		VERTEX_DATA.put("v", VERTEX_DATA_V); // geometric vertices
		VERTEX_DATA.put("vt", VERTEX_DATA_VT); // texture vertices
		VERTEX_DATA.put("vn", VERTEX_DATA_VN); // vertex normals
		VERTEX_DATA.put("vp", VERTEX_DATA_VP); // parameter space vertices
		VERTEX_DATA.put("cstype", 4); // rational or non-rational forms of curve or surface type
		VERTEX_DATA.put("deg", 5); // degree
		VERTEX_DATA.put("bmat", 6); // basis matrix
		VERTEX_DATA.put("step", 7); // step size
	}
	private static final Map<String, Integer> ELEMENTS = new HashMap<String, Integer>();
	private static final int ELEMENTS_P = 0;
	private static final int ELEMENTS_L = 1;
	private static final int ELEMENTS_F = 2;
	private static final int ELEMENTS_CURV = 3;
	private static final int ELEMENTS_CURV2 = 4;
	private static final int ELEMENTS_SURF = 5;

	static {
		ELEMENTS.put("p", ELEMENTS_P); // point
		ELEMENTS.put("l", ELEMENTS_L); // line
		ELEMENTS.put("f", ELEMENTS_F); // face
		ELEMENTS.put("curv", ELEMENTS_CURV); // curve
		ELEMENTS.put("curv2", ELEMENTS_CURV2); // 2D curve
		ELEMENTS.put("surf", ELEMENTS_SURF); // surface
	}
	private static final Map<String, Integer> BODY = new HashMap<String, Integer>();

	static {
		BODY.put("parm", 0); // parameter values
		BODY.put("trim", 1); // outer trimming loop
		BODY.put("hole", 2); // inner trimming loop
		BODY.put("scrv", 3); // special curve
		BODY.put("sp", 4); // special point
		BODY.put("end", 5); // end statement
		BODY.put("con", 6); // connect
	}
	private static final Map<String, Integer> GROUPING = new HashMap<String, Integer>();

	static {
		GROUPING.put("g", 0); // group name
		GROUPING.put("s", 1); // smoothing group
		GROUPING.put("mg", 2); // merging group
		GROUPING.put("o", 3); // object name
	}
	private static final Map<String, Integer> RENDER = new HashMap<String, Integer>();
	private static final int RENDER_bevel = 0;
	private static final int RENDER_c_interp = 1;
	private static final int RENDER_d_interp = 2;
	private static final int RENDER_lod = 3;
	private static final int RENDER_usemtl = 4;
	private static final int RENDER_mtlib = 5;
	private static final int RENDER_shadow_obj = 6;
	private static final int RENDER_trace_obj = 7;
	private static final int RENDER_ctech = 8;
	private static final int RENDER_stech = 9;

	static {
		RENDER.put("bevel", RENDER_bevel); // bevel interpolation
		RENDER.put("c_interp", RENDER_c_interp); // color interpolation
		RENDER.put("d_interp", RENDER_d_interp); // dissolve interpolation
		RENDER.put("lod", RENDER_lod); // level of detail
		RENDER.put("usemtl", RENDER_usemtl); // material name
		RENDER.put("mtllib", RENDER_mtlib); // material library
		RENDER.put("shadow_obj", RENDER_shadow_obj); // shadow casting
		RENDER.put("trace_obj", RENDER_trace_obj); // ray tracing
		RENDER.put("ctech", RENDER_ctech); // curve approximation technique
		RENDER.put("stech", RENDER_stech); // surface approximation technique
	}

	public OBJLoader(Mesh mesh) {
		this.mesh = mesh;
	}

	public static Mesh load(Mesh mesh, String resourcePath) {
		
		Mesh result = null;
		final InputStream is = OBJLoader.class.getClassLoader().getResourceAsStream(resourcePath);
		
		if (is != null){
			result = OBJLoader.load(mesh, is);
		}

		return result;
	}

	public static Mesh load(Mesh mesh, InputStream is) {
		final OBJLoader loader = new OBJLoader(mesh);
		loader.load0(mesh, is);
		return mesh;
	}

	public Mesh load0(Mesh mesh, InputStream is) {

		this.mesh = mesh;
		BufferedReader in = null;
		int line = 0;
		try {

			in = new BufferedReader(new InputStreamReader(is));
			String zeile = null;

			while ((zeile = in.readLine()) != null) {
				System.out.println(zeile);

				if (zeile.length() == 0) {
					continue;
				}
				if (zeile.charAt(0) == '#') {
					continue;
				}

				final String[] tokens = zeile.split(" ");
				final String start = tokens[0];

				Integer i = VERTEX_DATA.get(start);
				if (i != null) {
					parseVertexData(line, i, tokens);
				} else {
					i = ELEMENTS.get(start);
					if (i != null) {
						parseElements(line, i, tokens);
					} else {
						i = BODY.get(start);
						if (i != null) {
						} else {
							i = GROUPING.get(start);
							if (i != null) {
							} else {
								i = RENDER.get(start);
								if (i != null) {
									parseRender(line, i, tokens);
								} else {
									throw new IllegalStateException("Syntaxerror line: " + line);
								}
							}
						}
					}
				}

				line++;

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					// intensionally ignored
				}
			}
		}
		return mesh;
	}

	private void parseVertexData(int line, int i, String[] tokens) {

		switch (i) {

		case VERTEX_DATA_V: {
			vertices.add(getVector3(tokens));
			break;
		}

		case VERTEX_DATA_VN: {
			normals.add(getVector3(tokens));
			break;
		}

		case VERTEX_DATA_VT: {
			break;
		}

		case VERTEX_DATA_VP: {
			break;
		}

		default: {
			throw new IllegalStateException("Unknown vertex data at line " + line);
		}
		}


	}

	private void parseElements(int line, int i, String[] tokens) {

		switch (i) {

		case ELEMENTS_F: {

			final List<Integer> indice = new ArrayList<Integer>();
			for (int j = 1; j < tokens.length; j++) {
				String indexData = tokens[j];
				String[] vSplit = indexData.split("/");
				if (vSplit != null && vSplit.length > 0) {
					indexData = vSplit[0];
				}
				indice.add(Integer.parseInt(indexData));
			}

			Vector3[] points = new Vector3[indice.size()];
			for (int j = 0; j < indice.size(); j++) {
				points[j] = vertices.get(indice.get(j) - 1);
			}

			mesh.addFace(new Face(mesh, Material.get(currentMat), points));

			break;
		}

		case ELEMENTS_L: {
			break;
		}

		case ELEMENTS_CURV: {
			break;
		}

		case ELEMENTS_CURV2: {
			break;
		}
		case ELEMENTS_SURF: {
			break;
		}

		default: {
			throw new IllegalStateException("Unknown element at line " + line);
		}
		}
	}

	private void parseRender(int line, int i, String[] tokens) {

		switch (i) {

		case RENDER_bevel: {
			break;
		}

		case RENDER_c_interp: {
			break;
		}

		case RENDER_ctech: {
			break;
		}

		case RENDER_d_interp: {
			break;
		}

		case RENDER_lod: {
			break;
		}

		case RENDER_mtlib: {
			break;
		}

		case RENDER_shadow_obj: {
			break;
		}

		case RENDER_stech: {
			break;
		}

		case RENDER_trace_obj: {
			break;
		}

		case RENDER_usemtl: {
			currentMat = tokens[1].toUpperCase();
			if (Material.get(currentMat) == null) {
				currentMat = Material.DEFAULT;
			}
			break;
		}

		default: {
			throw new IllegalStateException("Unknown element at line " + line);
		}
		}
	}

	private Vector3 getVector3(String[] tokens) throws NumberFormatException {
		final Vector3 vector = new Vector3();
		for (int j = 1; j < tokens.length && j < 4; j++) {
			vector.v[j - 1] = Float.parseFloat(tokens[j]);
		}
		return vector;
	}
}
