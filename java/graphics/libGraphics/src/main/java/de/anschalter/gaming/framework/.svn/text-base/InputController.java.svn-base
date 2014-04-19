/*
 * Copyright by Peter Trebing; All rights reserved
 * 
 * mail@peter-trebing.de
 * http://www.peter-trebing.de
 * 
 * Created on 07.05.2005
 *
 */
package de.anschalter.gaming.framework;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.event.EventListenerList;

import de.anschalter.gaming.framework.impl.GameAction;
import de.anschalter.gaming.framework.impl.IGameEngine;

/**
 * @author keeper
 * 
 */
public class InputController implements KeyListener, MouseListener,
        MouseMotionListener, MouseWheelListener {

    // An invisible cursor
    public static final Cursor CURSOR_NULL = Toolkit.getDefaultToolkit().
            createCustomCursor(
            Toolkit.getDefaultToolkit().createImage(""), new Point(0, 0),
            "invisible");

    public enum MouseControl {

        MOVE_LEFT("Mouse Left"),
        MOVE_RIGHT("Mouse Right"),
        MOVE_UP("Mouse Up"),
        MOVE_DOWN("Mouse Down"),
        DRAG_LEFT("Mouse Drag Left"),
        DRAG_RIGHT("Mouse Drag Right"),
        DRAG_UP("Mouse Drag Up"),
        DRAG_DOWN("Mouse Drag Down"),
        WHEEL_UP("Mouse Wheel Up"),
        WHEEL_DOWN("Mouse Wheel Down"), BUTTON_1("Mouse Button 1"), BUTTON_2(
        "Mouse Button 2"),
        BUTTON_3("Mouse Button 3");
        
        private final String caption;

        MouseControl(String caption) {
            this.caption = caption;
        }

        @Override
        public String toString() {
            return caption;
        }
    };

    // Standard Actions
    public enum StandardAction implements IGameAction{

        EXIT(new GameAction("exit", GameAction.DETECT_INITIAL_PRESS_ONLY)),
        PAUSE(new GameAction("pause", GameAction.DETECT_INITIAL_PRESS_ONLY));
        public final IGameAction action;

        StandardAction(IGameAction action) {
            this.action = action;
        }

        @Override
        public String getName() {
            return action.getName();
        }

        @Override
        public void reset() {
            action.release();
        }

        @Override
        public void tap() {
            action.tap();
        }

        @Override
        public void release() {
           action.release();
        }

        @Override
        public void press() {
           action.press();
        }

        @Override
        public void press(int amount) {
            action.press(amount);
        }

        @Override
        public boolean isPressed() {
            return action.isPressed();
        }
    };
    
    private static final int NUM_KEY_CODES = 600;
    private IGameAction[] keyActions = new IGameAction[NUM_KEY_CODES];
    private Map<MouseControl, IGameAction> mouseActions =
            new EnumMap<MouseControl, IGameAction>(MouseControl.class);
    private static final Logger log = Logger.getLogger(InputController.class.
            getName());
    private Point mouseLocation;
    private Point centerLocation;
    private Component comp;
    private IGameEngine engine;
    private Robot robot;
    private boolean isRecentering;
    private EventListenerList listenerList;
    private final IControllerEvent DEFAULT_CONTROLLER_EVENT = new IControllerEvent() {

        public Object getSource() {
            return InputController.this;
        }
    };

    /**
     * @param comp
     */
    public InputController(Component comp, IGameEngine engine) {

        init(comp, engine);
        registerListeners(comp);

        // allow input of the TAB key and other keys normally
        // used for focus traversal
        comp.setFocusTraversalKeysEnabled(false);

        // Map standard game keys
        mapToKey(StandardAction.EXIT, KeyEvent.VK_ESCAPE);
        mapToKey(StandardAction.PAUSE, KeyEvent.VK_P);

    }

    public void addControllerListener(IInputControllerListener listener) {
        getListenerList().add(IInputControllerListener.class, listener);
    }

    public void removeControllerListener(IInputControllerListener listener) {
        getListenerList().remove(IInputControllerListener.class, listener);
    }

    private void fireControllerEvent(IControllerEvent ev) {
        EventListenerList listeners = getListenerList();
        IInputControllerListener[] lisAr = listeners.getListeners(
                IInputControllerListener.class);
        for (int i = 0; i < lisAr.length; i++) {
            lisAr[i].inputEvent(ev);
        }
    }

    private EventListenerList getListenerList() {
        if (listenerList == null) {
            listenerList = new EventListenerList();
        }
        return listenerList;
    }

    /**
     * @param comp
     * @param engine
     */
    private final void init(Component comp, IGameEngine engine) {
        this.comp = comp;
        this.engine = engine;
        mouseLocation = new Point();
        centerLocation = new Point();
    }

    /**
     * @param comp
     */
    private void registerListeners(Component comp) {
        comp.addKeyListener(this);
        comp.addMouseListener(this);
        comp.addMouseMotionListener(this);
        comp.addMouseWheelListener(this);
    }

    public void setCursor(Cursor cursor) {
        comp.setCursor(cursor);
    }

    /**
     * Lock the mouse in the center of the component and measure only the
     * relative mouse movements.
     * 
     * @param mode
     *            true: relative mouse mode on; false: mouse moves around
     */
    public void setRelativeMouseMode(boolean mode) {
        if (mode == isRelativeMouseMode()) {
            return;
        }
        if (mode) {
            try {
                robot = new Robot();
                recenterMouse();
            } catch (AWTException e) {
                log.log(Level.SEVERE, "Couldn't create robot!", e);
            }
        } else {
            robot = null;
        }
    }

    public void mapToKey(IGameAction action, int keyCode) {
        keyActions[keyCode] = action;
    }

    public void mapToKey(StandardAction sAction, int keyCode) {
        keyActions[keyCode] = sAction.action;
    }

    public void mapToMouse(IGameAction action, MouseControl mouseCode) {
        mouseActions.put(mouseCode, action);
    }

    // public void clearMap(IGameAction action) {
    // for (int i = 0; i < keyActions.length; i++)
    // if (keyActions[i] == action)
    // keyActions[i] = null;
    //
    // for (int i = 0; i < mouseActions.length; i++)
    // if (mouseActions[i] == action)
    // mouseActions[i] = null;
    //
    // action.reset();
    // }

    // public List<String> getMaps(IGameAction action) {
    // ArrayList<String> list = new ArrayList<String>();
    // for (int i = 0; i < keyActions.length; i++)
    // if (keyActions[i] == action)
    // list.add(getKeyName(i));
    // for (int i = 0; i < mouseActions.length; i++)
    // if (mouseActions[i] == action)
    // list.add(getMouseName(i));
    // return list;
    // }
    public void resetAllGameActions() {
        for (int i = 0; i < keyActions.length; i++) {
            if (keyActions[i] != null) {
                keyActions[i].reset();
            }
        }
        for (IGameAction element : mouseActions.values()) {
            element.reset();
        }
    }

    public static String getKeyName(int keyCode) {
        return KeyEvent.getKeyText(keyCode);
    }

    public int getMouseX() {
        return mouseLocation.x;
    }

    public int getMouseY() {
        return mouseLocation.y;
    }

    private synchronized void recenterMouse() {
        if (robot != null && comp.isShowing()) {
            centerLocation.x = comp.getWidth() / 2;
            centerLocation.y = comp.getHeight() / 2;
            SwingUtilities.convertPointToScreen(centerLocation, comp);
            isRecentering = true;
            robot.mouseMove(centerLocation.x, centerLocation.y);
        }
    }

    private boolean isRelativeMouseMode() {
        return (robot != null);
    }

    private IGameAction getKeyAction(KeyEvent e) {
        int keyCode = e.getKeyCode();
        IGameAction result = null;
        if (keyCode < keyActions.length) {
            result = keyActions[keyCode];
        }
        return result;
    }

    public static MouseControl getMouseButtonCode(MouseEvent e) {
        MouseControl result = null;
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                result = MouseControl.BUTTON_1;
                break;
            case MouseEvent.BUTTON2:
                result = MouseControl.BUTTON_2;
                break;
            case MouseEvent.BUTTON3:
                result = MouseControl.BUTTON_3;
                break;
            default:
                break;
        }
        return result;
    }

    private IGameAction getMouseButtonAction(MouseEvent e) {
        MouseControl mouseCode = getMouseButtonCode(e);
        return mouseActions.get(mouseCode);
    }

    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    public void keyPressed(KeyEvent e) {
        IGameAction action = getKeyAction(e);
        if (action == StandardAction.PAUSE.action) {
            if (engine.isPaused()) {
                engine.resume();
            } else {
                engine.pause();
            }
        } else if (action == StandardAction.EXIT.action) {
            engine.shutdown();
        } else if (action != null) {
            action.press();
        }
        e.consume();
        fireControllerEvent(DEFAULT_CONTROLLER_EVENT);
    }

    public void keyReleased(KeyEvent e) {
        IGameAction action = getKeyAction(e);
        if (action != null) {
            action.release();
        }
        e.consume();
        fireControllerEvent(DEFAULT_CONTROLLER_EVENT);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        IGameAction action = getMouseButtonAction(e);
        if (action != null) {
            action.press();
        }
        fireControllerEvent(DEFAULT_CONTROLLER_EVENT);
    }

    public void mouseReleased(MouseEvent e) {
        IGameAction action = getMouseButtonAction(e);
        if (action != null) {
            action.release();
        }
        fireControllerEvent(DEFAULT_CONTROLLER_EVENT);
    }

    public void mouseEntered(MouseEvent e) {
        mouseMoved(e);
        fireControllerEvent(DEFAULT_CONTROLLER_EVENT);
    }

    public void mouseExited(MouseEvent e) {
        mouseMoved(e);
        fireControllerEvent(DEFAULT_CONTROLLER_EVENT);
    }

    public synchronized void mouseMoved(MouseEvent e) {
        if (isRecentering && centerLocation.x == e.getX() && centerLocation.y == e.
                getY()) {
            isRecentering = false;
        } else {
            int dx = e.getX() - mouseLocation.x;
            int dy = e.getY() - mouseLocation.y;
            mouseHelper(MouseControl.MOVE_LEFT, MouseControl.MOVE_RIGHT, dx);
            mouseHelper(MouseControl.MOVE_UP, MouseControl.MOVE_DOWN, dy);
            if (isRelativeMouseMode()) {
                recenterMouse();
            }
            mouseLocation.x = e.getX();
            mouseLocation.y = e.getY();
        }
        fireControllerEvent(DEFAULT_CONTROLLER_EVENT);
    }

    public synchronized void mouseDragged(MouseEvent e) {
        if (isRecentering && centerLocation.x == e.getX() && centerLocation.y == e.
                getY()) {
            isRecentering = false;
        } else {
            int dx = e.getX() - mouseLocation.x;
            int dy = e.getY() - mouseLocation.y;
            mouseHelper(MouseControl.DRAG_LEFT, MouseControl.DRAG_RIGHT, dx);
            mouseHelper(MouseControl.DRAG_UP, MouseControl.DRAG_DOWN, dy);
            if (isRelativeMouseMode()) {
                recenterMouse();
            }
            mouseLocation.x = e.getX();
            mouseLocation.y = e.getY();
        }
        fireControllerEvent(DEFAULT_CONTROLLER_EVENT);
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseHelper(MouseControl.WHEEL_UP, MouseControl.WHEEL_DOWN, e.
                getWheelRotation());
        fireControllerEvent(DEFAULT_CONTROLLER_EVENT);
    }

    private void mouseHelper(MouseControl codeNeg, MouseControl codePos,
            int amount) {
        IGameAction action;
        if (amount < 0) {
            action = mouseActions.get(codeNeg);
        } else {
            action = mouseActions.get(codePos);
        }
        if (action != null) {
            action.press(Math.abs(amount));
            action.release();
        }
    }
}
