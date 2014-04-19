/*
 * Copyright by Peter Trebing; All rights reserved
 * 
 * mail@peter-trebing.de
 * http://www.peter-trebing.de
 * 
 * Created on 07.05.2005
 *
 */
package de.anschalter.gaming.framework.impl;

public class Vect3D {

	public float x;
	public float y;
	public float z;

	
	public Vect3D(Vect3D v) {
		this.setX(v.x);
		this.setY(v.y);
		this.setZ(v.z);
	}	
	
	public Vect3D(float x, float y, float z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}

	public Vect3D setX(float x) {
		this.x = x;
		return this;
	}

	public float getX() {
		return x;
	}

	public Vect3D setY(float y) {
		this.y = y;
		return this;
	}

	public float getY() {
		return y;
	}

	public Vect3D setZ(float z) {
		this.z = z;
		return this;
	}

	public float getZ() {
		return z;
	}

	public Vect3D mult(float m) {
		x *= m;
		y *= m;
		z *= m;
		return this;
	}

	public Vect3D add(Vect3D v2) {
		x += v2.x;
		y += v2.y;
		z += v2.z;
		return this;
	}
	
	public static final Vect3D SINGULAITY = new Vect3D(0,0,0);
}
