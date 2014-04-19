/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.anschalter.scene;

import de.anschalter.linalg.Frame;
import de.anschalter.linalg.Matrix;
import de.anschalter.linalg.SpaceAndTime;
import de.anschalter.linalg.Vector3;

/**
 *
 * @author keeper
 */
public class Node {
    
    public Frame frame; //position and direction
    public Vector3 v; //velocity
    public Vector3 a; //acceleration
    public Vector3 dRot; //rotation
    
   // public SpaceAndTime[] sAt = new SpaceAndTime[2];
    public Matrix transform;

    public Node parent;

    public boolean isRenderingEnabled = true;
    
}