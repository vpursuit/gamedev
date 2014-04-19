/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.anschalter.polygonal;

import de.anschalter.linalg.Vector3;
import de.anschalter.polygonal.Face;
import de.anschalter.polygonal.Material;
import de.anschalter.scene.Mesh;

/**
 *
 * @author loewe
 */
public class TruncatedCube extends Mesh {

    static final float LAMBDA = 2.0f * ((float) Math.sqrt(2.0d) - 1.0f);
    static final float LAMBDA1 = (1.0f + LAMBDA) / 2.0f;
    static final float LAMBDA2 = (1.0f - LAMBDA) / 2.0f;
    static final float omega1 = -0.4f;
    static final float omega2 = -1.0f * omega1;
    
    static final Vector3 Wf1 = new Vector3(omega2, 1.0f, 1.0f);
    static final Vector3 Wf2 = new Vector3(omega1, 1.0f, 1.0f);
    static final Vector3 Wf3 = new Vector3(-1.0f, omega2, 1.0f);
    static final Vector3 Wf4 = new Vector3(-1.0f, omega1, 1.0f);
    static final Vector3 Wf5 = new Vector3(omega1, -1.0f, 1.0f);
    static final Vector3 Wf6 = new Vector3(omega2, -1.0f, 1.0f);
    static final Vector3 Wf7 = new Vector3(1.0f, omega1, 1.0f);
    static final Vector3 Wf8 = new Vector3(1.0f, omega2, 1.0f);
    static final Vector3 Wb1 = new Vector3(omega2, 1.0f, -1.0f);
    static final Vector3 Wb2 = new Vector3(omega1, 1.0f, -1.0f);
    static final Vector3 Wb3 = new Vector3(-1.0f, omega2, -1.0f);
    static final Vector3 Wb4 = new Vector3(-1.0f, omega1, -1.0f);
    static final Vector3 Wb5 = new Vector3(omega1, -1.0f, -1.0f);
    static final Vector3 Wb6 = new Vector3(omega2, -1.0f, -1.0f);
    static final Vector3 Wb7 = new Vector3(1.0f, omega1, -1.0f);
    static final Vector3 Wb8 = new Vector3(1.0f, omega2, -1.0f);
    static final Vector3 Wr1 = new Vector3(1.0f, 1.0f, omega2);
    static final Vector3 Wr2 = new Vector3(1.0f, 1.0f, omega1);
    static final Vector3 Wr3 = new Vector3(1.0f, omega2, -1.0f);
    static final Vector3 Wr4 = new Vector3(1.0f, omega1, -1.0f);
    static final Vector3 Wr5 = new Vector3(1.0f, -1.0f, omega1);
    static final Vector3 Wr6 = new Vector3(1.0f, -1.0f, omega2);
    static final Vector3 Wr7 = new Vector3(1.0f, omega1, 1.0f);
    static final Vector3 Wr8 = new Vector3(1.0f, omega2, 1.0f);
    static final Vector3 Wl1 = new Vector3(-1.0f, 1.0f, omega2);
    static final Vector3 Wl2 = new Vector3(-1.0f, 1.0f, omega1);
    static final Vector3 Wl3 = new Vector3(-1.0f, omega2, -1.0f);
    static final Vector3 Wl4 = new Vector3(-1.0f, omega1, -1.0f);
    static final Vector3 Wl5 = new Vector3(-1.0f, -1.0f, omega1);
    static final Vector3 Wl6 = new Vector3(-1.0f, -1.0f, omega2);
    static final Vector3 Wl7 = new Vector3(-1.0f, omega1, 1.0f);
    static final Vector3 Wl8 = new Vector3(-1.0f, omega2, 1.0f);
    static final Vector3 Wu1 = new Vector3(omega2, 1.0f, -1.0f);
    static final Vector3 Wu2 = new Vector3(omega1, 1.0f, -1.0f);
    static final Vector3 Wu3 = new Vector3(-1.0f, 1.0f, omega1);
    static final Vector3 Wu4 = new Vector3(-1.0f, 1.0f, omega2);
    static final Vector3 Wu5 = new Vector3(omega1, 1.0f, 1.0f);
    static final Vector3 Wu6 = new Vector3(omega2, 1.0f, 1.0f);
    static final Vector3 Wu7 = new Vector3(1.0f, 1.0f, omega2);
    static final Vector3 Wu8 = new Vector3(1.0f, 1.0f, omega1);
    static final Vector3 Wd1 = new Vector3(omega2, -1.0f, -1.0f);
    static final Vector3 Wd2 = new Vector3(omega1, -1.0f, -1.0f);
    static final Vector3 Wd3 = new Vector3(-1.0f, -1.0f, omega1);
    static final Vector3 Wd4 = new Vector3(-1.0f, -1.0f, omega2);
    static final Vector3 Wd5 = new Vector3(omega1, -1.0f, 1.0f);
    static final Vector3 Wd6 = new Vector3(omega2, -1.0f, 1.0f);
    static final Vector3 Wd7 = new Vector3(1.0f, -1.0f, omega2);
    static final Vector3 Wd8 = new Vector3(1.0f, -1.0f, omega1);
    static Vector3[] FRONT = new Vector3[]{Wf1, Wf2, Wf3, Wf4, Wf5, Wf6, Wf7, Wf8};
    static Vector3[] BACK = new Vector3[]{Wb8, Wb7, Wb6, Wb5, Wb4, Wb3, Wb2, Wb1};
    static Vector3[] LEFT = new Vector3[]{Wl1, Wl2, Wl3, Wl4, Wl5, Wl6, Wl7, Wl8};
    static Vector3[] RIGHT = new Vector3[]{Wr8, Wr7, Wr6, Wr5, Wr4, Wr3, Wr2, Wr1};
    static Vector3[] TOP = new Vector3[]{Wu1, Wu2, Wu3, Wu4, Wu5, Wu6, Wu7, Wu8};
    static Vector3[] BOTTOM = new Vector3[]{Wd8, Wd7, Wd6, Wd5, Wd4, Wd3, Wd2, Wd1};
    static final Vector3[] leftBottomBack = new Vector3[]{Wl4, Wb5, Wl5};
    static final Vector3[] rightBottomBack = new Vector3[]{Wr4, Wr5, Wb6};
    static final Vector3[] leftTopBack = new Vector3[]{Wb2, Wl3, Wl2};
    static final Vector3[] rightTopBack = new Vector3[]{Wr2, Wb8, Wb1};
    static final Vector3[] leftBottomFront = new Vector3[]{Wf4, Wl6, Wf5};
    static final Vector3[] rightBottomFront = new Vector3[]{Wf7, Wf6, Wr6};
    static final Vector3[] leftTopFront = new Vector3[]{Wf2, Wl1, Wf3};
    static final Vector3[] rightTopFront = new Vector3[]{Wf1, Wf8, Wr1};

    public TruncatedCube() {
        addFace(new Face(this,Material.getRandom(), FRONT));
        addFace(new Face(this,Material.getRandom(), BACK));
        addFace(new Face(this,Material.getRandom(), LEFT));
        addFace(new Face(this,Material.getRandom(), RIGHT));
        addFace(new Face(this,Material.getRandom(), TOP));
        addFace(new Face(this,Material.getRandom(), BOTTOM));

        addFace(new Face(this,Material.getRandom(), leftBottomBack));
        addFace(new Face(this,Material.getRandom(), rightBottomBack));
        addFace(new Face(this,Material.getRandom(), leftTopBack));
        addFace(new Face(this,Material.getRandom(), rightTopBack));


        addFace(new Face(this,Material.getRandom(), leftBottomFront));
        addFace(new Face(this,Material.getRandom(), rightBottomFront));
        addFace(new Face(this,Material.getRandom(), leftTopFront));
        addFace(new Face(this,Material.getRandom(), rightTopFront));

    }

    public static void main(String[] args) {

        System.out.println("LAMBDA = " + LAMBDA);
        System.out.println("LAMBDA1 = " + LAMBDA1);
        System.out.println("LAMBDA2 = " + LAMBDA2);

        System.out.println("W1 = " + omega1);
        System.out.println("W2 = " + omega2);



    }
}
