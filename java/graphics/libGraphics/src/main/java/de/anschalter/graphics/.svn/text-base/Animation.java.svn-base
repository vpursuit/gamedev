package de.anschalter.graphics;

import java.awt.Image;
import java.util.ArrayList;

/**
 * The Animation class manages a series of images (frames) and the amount of
 * time to display each frame.
 */
public class Animation implements IAnimation {

    private ArrayList frames;
    private int currFrameIndex;
    private long animTime;
    private long totalDuration;
    private boolean upToDate = false;

    /**
     * Creates a new, empty Animation.
     */
    public Animation() {
        frames = new ArrayList();
        totalDuration = 0;
        reset();
    }

    /**
     * Adds an image to the animation with the specified duration (time to
     * display the image).
     */
    public synchronized void addFrame(Image image, long duration) {
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));
        upToDate = false;
    }

    /**
     * Starts this animation over from the beginning.
     */
    public synchronized void reset() {
        animTime = 0;
        setCurrFrameIndex(0);
        upToDate = true;
    }

    /**
     * Updates this animation's current image (frame), if neccesary.
     */
    public synchronized void update(long elapsedTime) {
        animTime += elapsedTime;
        if (animTime >= totalDuration) {
            animTime = animTime % totalDuration;
        }
        upToDate = false;
    }

    /**
     *  
     */
    private int getFrameIndex() {
        if (!upToDate) {
            setCurrFrameIndex(0);
            while (getCurrFrameIndex() < frames.size()) {
                if (animTime > ((AnimFrame) frames.get(getCurrFrameIndex())).endTime) {
                    setCurrFrameIndex(getCurrFrameIndex() + 1);
                } else {
                    break;
                }
            }
            upToDate = true;
        }
        return getCurrFrameIndex();
    }

    /**
     * Gets this Animation's current image. Returns null if this animation has
     * no images.
     */
    public synchronized Image getImage() {
        final int frame = getFrameIndex();
        if (frame < frames.size()) {
            return ((AnimFrame) frames.get(getFrameIndex())).image;
        } else {
            return null;
        }
    }

    /**
     * @param currFrameIndex The currFrameIndex to set.
     */
    private void setCurrFrameIndex(int currFrameIndex) {
        this.currFrameIndex = currFrameIndex;
    }

    /**
     * @return Returns the currFrameIndex.
     */
    protected int getCurrFrameIndex() {
        return currFrameIndex;
    }

    private static class AnimFrame {

        Image image;
        long endTime;

        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }
}