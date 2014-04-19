/*
 * Copyright by Peter Trebing; All rights reserved
 * 
 * mail@peter-trebing.de
 * http://www.peter-trebing.de
 * 
 * Created on 08.05.2005
 *
 */
package de.anschalter.graphics;

import de.anschalter.dna.framework.SimulationTime;
import de.anschalter.gaming.framework.impl.Vect3D;

public class Player extends Sprite {

    public static final int STATE_NORMAL = 0;
    public static final int STATE_JUMPING = 1;
    public static final float SPEED = .3f;
    public static final float GRAVITY = .002f;
    private float floorY;
    private int state;

    public Player(int width, int height, IAnimation anim) {
        super(anim, new Vect3D(Vect3D.SINGULAITY).setY(height),
                new Vect3D(Vect3D.SINGULAITY));
        setState(STATE_NORMAL);
    }

    private void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setFloorY(float floorY) {
        this.floorY = floorY;
        getPosition().setY(floorY);
    }

    public void jump() {
        setVelocity(getVelocity().setY(-1.5f));
        state = STATE_JUMPING;
    }

    @Override
    public void update(SimulationTime time) {
        // Set vertical velocity based on gravity effect
        if (getState() == STATE_JUMPING) {
            setVelocity(getVelocity().setY(getVelocity().getY() + GRAVITY * time.elapsedGameTime()));
        }
        super.update(time);
        // Check if the player reached the ground
        if (getState() == STATE_JUMPING && getPosition().getY() >= floorY) {
            setVelocity(getVelocity().setY(0));
            getPosition().setY(floorY);
            setState(STATE_NORMAL);
        }
    }
}
