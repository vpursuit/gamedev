package de.anschalter.io.loader.wafefront;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import de.anschalter.linalg.Vector4;
import de.anschalter.polygonal.Material;

/**
 * An reader for Alias/Wavefront .obj file format
 * 
 * @author keeper
 * 
 */
public class OBJReader {

	protected final static int BUFFER_CAPACITY = 800000;
	protected final static String SUFFIX_OBJ = "obj";
	protected final static String SUFFIX_MTL = "mtl";
	protected final static String STR_COMMENT = "#";

	protected interface LineParser {

		public void parseLine(String line) throws IOException,
				NumberFormatException, NoSuchElementException;
	}

	protected File path;
	private FloatBuffer vertices;
	private FloatBuffer normals;
	private FloatBuffer textures;
	private List<Face> faces;
	protected List lights;
	protected float ambienteLightIntensity;
	protected Map<String, LineParser> parsers;

	// private PolygonGroup polygonGroup;
	// private PolygonGroup currentPolygonGroup;
	public OBJReader() {

		faces = new ArrayList<Face>();
		vertices = FloatBuffer.allocate(BUFFER_CAPACITY);
		normals = FloatBuffer.allocate(BUFFER_CAPACITY);
		textures = FloatBuffer.allocate(BUFFER_CAPACITY);

		parsers = new HashMap<String, LineParser>();

		// Initialize the line parsers:
		parsers.put(SUFFIX_OBJ, new ObjectLineParser());
		parsers.put(SUFFIX_MTL, new MtlLineParser());

	}

	public void loadObject(String filename) throws IOException {

		File file = new File(filename);
		path = file.getParentFile();
		String name = file.getName();

		vertices.clear();
		parseFile(name);

	}

	private void parseFile(String filename) throws IOException {

		File file = new File(path, filename);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		LineParser actParser = getParser(filename);

		// parse every line in the file
		while (true) {

			String line = reader.readLine();
			if (line == null) {
				reader.close();
				return;
			}

			line = line.trim();

			if (line.length() > 0 && !line.startsWith(STR_COMMENT)) {

				try {
					actParser.parseLine(line);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	/**
	 * Identifies the parser using the filename extension
	 * 
	 * @param filename
	 * @return a parser
	 */
	private LineParser getParser(String filename) {
		LineParser parser = null;
		int extIndex = filename.lastIndexOf('.');
		if (extIndex != -1) {
			String ext = filename.substring(extIndex + 1);
			parser = parsers.get(ext.toLowerCase());
		}
		if (parser == null) {
			parser = parsers.get(SUFFIX_OBJ);
		}
		return parser;
	}

	public List<Face> getFaces() {
		return faces;
	}

	public FloatBuffer getVertices() {
		return vertices;
	}

	public FloatBuffer getNormals() {
		return normals;
	}

	public FloatBuffer getTextures() {
		return textures;
	}

	protected class ObjectLineParser implements LineParser {

		// statements strings for .obj files
		protected final static String STATEMENT_VERTEX = "v";
		protected final static String STATEMENT_POINT = "vp";
		protected final static String STATEMENT_NORMAL = "vn";
		protected final static String STATEMENT_TEXTURE = "vt";
		protected final static String STATEMENT_FACE = "f";
		protected final static String STATEMENT_GROUP = "g";
		protected final static String STATEMENT_MTLIB = "mtllib";
		protected final static String STATEMENT_USEMTL = "usemtl";
		private String currentMat = null;

		public void parseLine(String line) throws IOException,
				NumberFormatException, NoSuchElementException {

			StringTokenizer tokenizer = new StringTokenizer(line);
			String command = tokenizer.nextToken();

			if (command.equals(STATEMENT_VERTEX)) {
				// 3-dim and optional weight
				putFloats(getVertices(), tokenizer, 3, 1, 1.0f);
			} else if (command.equals(STATEMENT_NORMAL)) {
				// 3-dim
				putFloats(getNormals(), tokenizer, 3);
			} else if (command.equals(STATEMENT_TEXTURE)) {
				// horizontal directon, optional vertical and depth
				putFloats(getTextures(), tokenizer, 1, 2, 0.0f);
			} else if (command.equals(STATEMENT_FACE)) {
				Face f = parseFace(tokenizer);
				if (currentMat != null) {
					f.setMaterial(currentMat);
				}
				getFaces().add(f);

			} else if (command.equals(STATEMENT_GROUP)) {
			} else if (command.equals(STATEMENT_MTLIB)) {
				String name = tokenizer.nextToken();
				parseFile(name);
			} else if (command.equals(STATEMENT_USEMTL)) {
				currentMat = tokenizer.nextToken();
				if (Material.get(currentMat) == null) {
					currentMat = Material.DEFAULT;
				}
			} else {
				// ignore all other comands
			}

		}

		private Face parseFace(StringTokenizer tokenizer) throws IOException {
			Face face = new Face();

			while (tokenizer.hasMoreTokens()) {

				int v = 0;
				int t = 0;
				int n = 0;

				String indexStr = tokenizer.nextToken();
				String[] tmp = indexStr.split("/", 3);

				if (tmp != null) {
					if (tmp.length > 0) {
						v = Integer.parseInt(tmp[0]);
					} else {
						throw new IOException("Syntax error in face");
					}

					if (tmp.length > 1) {
						t = (tmp[1].length() > 0) ? Integer.parseInt(tmp[1])
								: 0;
					}

					if (tmp.length > 2) {
						n = (tmp[2].length() > 0) ? Integer.parseInt(tmp[2])
								: 0;
					}

				} else {
					throw new IOException("Syntax error in face");
				}

				face.addTripel(new Face.Tripel(v, t, n));
			}

			face.setMaterial(currentMat);
			return face;
		}
	}

	private static class MtlLineParser implements LineParser {

		// statements strings for .mtl files
		protected final static String STATEMENT_NEWMTL = "newmtl";
		protected final static String STATEMENT_AMBIENTE = "Ka";
		protected final static String STATEMENT_DIFFUSE = "Kd";
		protected final static String STATEMENT_SPECULAR = "Ks";
		protected final static String STATEMENT_ILLUM = "illum";
		protected final static String STATEMENT_SHININESS = "Ns";
		protected final static String STATEMENT_TRANSPARENCY = "d";
		protected final static String STATEMENT_MAP_KD = "map_kd";
		private Material.MaterialDef currentMaterialDef = null;

		public void parseLine(String line) throws IOException,
				NumberFormatException, NoSuchElementException {

			StringTokenizer tokenizer = new StringTokenizer(line);
			String command = tokenizer.nextToken();

			if (command.equals(STATEMENT_NEWMTL)) {
				String name = tokenizer.nextToken();
				if (Material.get(name) == null) {
					currentMaterialDef = Material.getDefaultDef();
					currentMaterialDef.id = name;
				} else {
					currentMaterialDef = null;
				}
			} else if (command.equals(STATEMENT_AMBIENTE)) {
				if (currentMaterialDef != null) {
					currentMaterialDef.ambient = new Vector4(getFloats(
							tokenizer, 4, 3));
				}
			} else if (command.equals(STATEMENT_DIFFUSE)) {
				if (currentMaterialDef != null) {
					currentMaterialDef.diffuse = new Vector4(getFloats(
							tokenizer, 4, 3));
				}
			} else if (command.equals(STATEMENT_SPECULAR)) {
				if (currentMaterialDef != null) {
					currentMaterialDef.specular = new Vector4(getFloats(
							tokenizer, 4, 3));
				}
			} else if (command.equals(STATEMENT_ILLUM)) {
				if (currentMaterialDef != null) {
					currentMaterialDef.illum = getInts(tokenizer, 1)[0];
				}
			} else if (command.equals(STATEMENT_SHININESS)) {
				// if (currentMat != null)
				// currentMat.setShininess(new Vector4(getFloats(tokenizer, 1,
				// 1)));
			} else if (command.equals(STATEMENT_TRANSPARENCY)) {
				// if (currentMat != null)
				// currentMat.setTransparency(new Vector4(getFloats(tokenizer,
				// 1, 1)));
			} else if (command.equals(STATEMENT_MAP_KD)) {
			}

			// TODO time of creation my be wrong. This could be lead to missing
			// materials
			Material.create(currentMaterialDef);
		}
	}

	public static class Face {

		private String material;
		private List<Tripel> tripels;

		public Face() {
			tripels = new ArrayList<Tripel>();
		}

		public void addTripel(Tripel t) {
			tripels.add(t);
		}

		public Iterator<Tripel> iterator() {
			return tripels.iterator();
		}

		public static class Tripel {

			int vertex;
			int normal;
			int texture;

			public Tripel(int v, int t, int n) {
				vertex = v;
				normal = n;
				texture = t;
			}

			public float[] getVertex(FloatBuffer buffer) {
				return accessBuffer(buffer, vertex, 4);
			}

			public float[] getNormal(FloatBuffer buffer) {
				return accessBuffer(buffer, normal, 3);
			}

			public float[] getTexture(FloatBuffer buffer) {
				return accessBuffer(buffer, texture, 3);
			}

			private float[] accessBuffer(FloatBuffer buffer, int index,
					int length) {
				float[] result = new float[length];
				float[] ba = buffer.array();
				int start = (index - 1) * length;
				int end = start + length;
				int j = 0;
				for (int i = start; i < end; i++) {
					result[j++] = ba[i];
				}
				return result;
			}
		}

		public String getMaterial() {
			return material;
		}

		public void setMaterial(String material) {
			this.material = material;
		}
	}

	private static int[] getInts(StringTokenizer tokenizer, int count) {

		int[] result = new int[count];

		for (int i = 0; i < count; i++) {
			result[i] = Integer.parseInt(tokenizer.nextToken());
		}

		return result;
	}

	private static float[] getFloats(StringTokenizer tokenizer, int length,
			int count) {

		float[] result = new float[length];

		for (int i = 0; i < count; i++) {
			result[i] = Float.parseFloat(tokenizer.nextToken());
		}
		if (length > count) {
			for (int i = count; i < length; i++) {
				result[i] = 1.0f;
			}
		}
		return result;
	}

	private static void putFloats(FloatBuffer buffer,
			StringTokenizer tokenizer, int count, int optional, float defVal) {
		for (int i = 0; i < count; i++) {
			buffer.put(Float.parseFloat(tokenizer.nextToken()));
		}
		for (int i = 0; i < optional; i++) {
			if (tokenizer.hasMoreTokens()) {
				buffer.put(Float.parseFloat(tokenizer.nextToken()));
			} else {
				buffer.put(defVal);
			}
		}
	}

	private static void putFloats(FloatBuffer buffer,
			StringTokenizer tokenizer, int count) {
		putFloats(buffer, tokenizer, 3, 0, 1.0f);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OBJReader objReader = new OBJReader();

		try {
			objReader
					.loadObject("C:\\Dokumente und Einstellungen\\keeper\\Eigene Dateien\\Blender\\test.obj");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
