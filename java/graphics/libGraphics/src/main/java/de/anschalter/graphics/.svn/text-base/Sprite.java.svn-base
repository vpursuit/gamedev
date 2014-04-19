package de.anschalter.graphics;

import de.anschalter.dna.framework.SimulationTime;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import de.anschalter.gaming.framework.IDrawable;
import de.anschalter.gaming.framework.IGraphicsDevice;
import de.anschalter.gaming.framework.impl.ScreenManager;
import de.anschalter.gaming.framework.impl.Vect3D;

public class Sprite implements IDrawable, IPhysicalSimulator {

    protected IAnimation anim;
    protected BufferedImage bi;
    // position (pixels)
    private Vect3D position;
    // velocity (pixels per millisecond)
    private Vect3D velocity;
    // Indicates to do a image flip before next draw
    private boolean doFlipX = false;
    private boolean doFlipY = false;

    /**
     * Creates a new Sprite object with the specified Animation.
     */
    public Sprite(IAnimation anim, Vect3D position, Vect3D velocity) {
        this.anim = anim;
        this.position = position;
        this.velocity = velocity;
        this.bi = ScreenManager.getInstance().createCompatibleImage(
                Math.round(getVolume().getX()), Math.round(getVolume().getY()),
                Transparency.BITMASK);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.graphics.IPhysicalSimulator#update(long)
     */
    @Override
    public void update(SimulationTime time) {
        position.add(new Vect3D(velocity).mult(time.elapsedGameTime()));
        anim.update(time.elapsedGameTime());
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.graphics.IPhysicalSimulator#getVolume()
     */
    public Vect3D getVolume() {
        return new Vect3D(anim.getImage().getWidth(null), anim.getImage().getHeight(null), 0);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.graphics.IPhysicalSimulator#setPosition(de.anschalter.gaming.framework.impl.Vect3D)
     */
    public void setPosition(Vect3D v) {
        this.position = v;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.graphics.IPhysicalSimulator#getPosition()
     */
    public Vect3D getPosition() {
        return position;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.graphics.IPhysicalSimulator#setVelocity(de.anschalter.gaming.framework.impl.Vect3D)
     */
    public void setVelocity(Vect3D v) {
        if (v.getX() < 0.0f) {
            doFlipX = true;
        } else if (v.getX() > 0.0f) {
            doFlipX = false;
        }
        this.velocity = v;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.anschalter.graphics.IPhysicalSimulator#getVelocity()
     */
    public Vect3D getVelocity() {
        return velocity;
    }

    /**
     * Gets this Sprite's current image.
     */
    protected Image getImage() {

        Graphics2D g2D = (Graphics2D) bi.getGraphics();
        clearTransparent(g2D);

        // Get the curretn transformation
        AffineTransform pre = g2D.getTransform();

        // Create an identical transformation
        AffineTransform transform = new AffineTransform();

        // if the sprite is moving left, flip the image
        if (doFlipX) {
            transform.concatenate(getFlipTransformationX());
        }

        // if the sprite is moving up/down, flip the image
        if (doFlipY) {
            transform.concatenate(getFlipTransformationY());
        }

        transform.concatenate(pre);
        g2D.transform(transform);
        g2D.drawImage(anim.getImage(), 0, 0, null);

        // Restore original transformation
        g2D.setTransform(pre);
        return bi;
    }

    private AffineTransform getFlipTransformationX() {
        AffineTransform result = new AffineTransform();
        result.scale(-1, 1);
        result.translate(-getVolume().getX(), 0);
        return result;
    }

    private AffineTransform getFlipTransformationY() {
        AffineTransform result = new AffineTransform();
        result.scale(1, -1);
        result.translate(0, -getVolume().getY());
        return result;
    }

    /**
     * Overwrites the sprite rectangel with tansparent color
     *
     * @param g2D
     */
    private void clearTransparent(Graphics2D g2D) {
        Color transparent = new Color(0, 0, 0, 0);
        g2D.setColor(transparent);
        g2D.setComposite(AlphaComposite.Src);
        g2D.fill(new Rectangle2D.Float(0, 0, getVolume().getX(), getVolume().getY()));
    }

    public void draw(IGraphicsDevice dev) {
        Vect3D position = getPosition();
        dev.drawImage(getImage(), Math.round(position.getX()), Math.round(position.getY()));
    }
}