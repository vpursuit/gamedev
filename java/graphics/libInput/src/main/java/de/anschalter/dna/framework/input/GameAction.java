/*
 * Copyright by Peter Trebing; All rights reserved
 * 
 * mail@peter-trebing.de
 * http://www.peter-trebing.de
 * 
 * Created on 07.05.2005
 *
 */
package de.anschalter.dna.framework.input;

public class GameAction implements IGameAction {

    private static final int STATE_RELEASED = 0;
    private static final int STATE_PRESSED = 1;
    private static final int STATE_WAITING_FOR_RELEASE = 2;
    private String name;
    private volatile int behavior;
    private volatile int amount;
    private volatile int state;

    public GameAction(String name) {
        this(name, DETECT_INITIAL_PRESS_ONLY);
    }

    public GameAction(String name, int behaviour) {
        this.name = name;
        this.behavior = behaviour;
        reset();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.anschalter.gaming.framework.IGameAction#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.anschalter.gaming.framework.IGameAction#reset()
     */
    public synchronized void reset() {
        state = STATE_RELEASED;
        amount = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.anschalter.gaming.framework.IGameAction#tap()
     */
    public synchronized void tap() {
        press();
        release();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.anschalter.gaming.framework.IGameAction#release()
     */
    public synchronized void release() {
        state = STATE_RELEASED;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.anschalter.gaming.framework.IGameAction#press()
     */
    public synchronized void press() {
        press(1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.anschalter.gaming.framework.IGameAction#press(int amount)
     */
    public synchronized void press(int amount) {
        if (state != STATE_WAITING_FOR_RELEASE) {
            this.amount += amount;
            state = STATE_PRESSED;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.anschalter.gaming.framework.IGameAction#isPressed()
     */
    public synchronized boolean isPressed() {
        return (getAmount() != 0);
    }

    private synchronized int getAmount() {
        int result = amount;
        if (result != 0) {
            if (state == STATE_RELEASED) {
                amount = 0;
            } else if (behavior == DETECT_INITIAL_PRESS_ONLY) {
                state = STATE_WAITING_FOR_RELEASE;
                amount = 0;
            }
        }
        return result;
    }
}
