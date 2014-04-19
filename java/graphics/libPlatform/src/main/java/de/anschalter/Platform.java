/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.anschalter;

import de.anschalter.dna.framework.IWorldRenderer;
import de.anschalter.dna.framework.input.IInputController;
import de.anschalter.gaming.framework.IGraphicsDevice;
import de.anschalter.util.service.ServiceProvider;

/**
 *
 * @author loewe
 */
public class Platform {

    private static final String RENDERER = "de.anschalter.jogl.adapter.Renderer";
    private static final String GRAPHICS_DEVICE = "de.anschalter.jogl.adapter.GraphicsDevice";
    private static final String INPUT_CONTROLLER = "de.anschalter.jogl.adapter.InputController";

    static {
        ServiceProvider.register(RENDERER);
        ServiceProvider.register(GRAPHICS_DEVICE);
        ServiceProvider.register(INPUT_CONTROLLER);
    }

    public static final IWorldRenderer getRenderer() {
        return (IWorldRenderer) ServiceProvider.getService(RENDERER);
    }

    public static final IGraphicsDevice getGraphicsDevice() {
        return (IGraphicsDevice) ServiceProvider.getService(GRAPHICS_DEVICE);
    }

    public static final IInputController getInputController() {
        return (IInputController) ServiceProvider.getService(INPUT_CONTROLLER);
    }
}

