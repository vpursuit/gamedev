/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.anschalter.dna.framework.input;

/**
 *
 * @author keeper
 */
public interface IInputController {

    public enum StandardAction implements IGameAction {

        EXIT(new GameAction("exit", GameAction.DETECT_INITIAL_PRESS_ONLY)),
        PAUSE(new GameAction("pause", GameAction.DETECT_INITIAL_PRESS_ONLY)),
        WHEEL_UP(new GameAction("mouse wheel up", GameAction.DETECT_IS_PRESSED)),
        WHEEL_DOWN(new GameAction("mouse wheel down", GameAction.DETECT_IS_PRESSED)),
        ZOOM_IN(new GameAction("zoomIn", GameAction.DETECT_IS_PRESSED)),
        ZOOM_OUT(new GameAction("zoomIn", GameAction.DETECT_IS_PRESSED)),
        FORWARD(new GameAction("forward", GameAction.DETECT_IS_PRESSED)),
        BACKWARD(new GameAction("backward", GameAction.DETECT_IS_PRESSED));
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
            action.reset();
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
        WHEEL_DOWN("Mouse Wheel Down"),
        BUTTON_1("Mouse Button 1"),
        BUTTON_2("Mouse Button 2"),
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
}
