/*
f *  Copyright (c) 2008 Peter Trebing. All rights reserved.
 *   
 *  Any unauthorised copying, duplication, reproduction and
 *  compilation will constitute an infringement of copyright.
 *   
 *   
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  AS IS AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 *  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.anschalter.jogl.adapter;

import de.anschalter.gaming.framework.IGraphicsDevice;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.media.opengl.AWTGraphicsConfiguration;
import javax.media.opengl.AWTGraphicsDevice;
import javax.media.opengl.GL;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLDrawable;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.glu.GLU;
import javax.swing.JPanel;

/**
 *
 * @author keeper
 */
public class GraphicsDevice implements IGraphicsDevice {

    private static GameCanvas _canvas;
    private static GLDrawable drawable;   // the rendering 'surface'
    private static GLContext context;     // the rendering context (holds rendering state info)

    /**
     * @return the height
     */
    public static int getHeight() {
        return height;
    }

    /**
     * @return the width
     */
    public static int getWidth() {
        return width;
    }
    private Frame frame;
    private static GraphicsConfiguration config;
    private static GLCapabilities caps;
    private static volatile int height;
    private static volatile int width;

    public static synchronized Canvas getCanvas() {

        if (_canvas == null) {

            caps = new GLCapabilities();
            caps.setDoubleBuffered(true);
            AWTGraphicsDevice dev = new AWTGraphicsDevice(null);
            AWTGraphicsConfiguration awtConfig = (AWTGraphicsConfiguration) GLDrawableFactory.getFactory().
                    chooseGraphicsConfiguration(caps, null, dev);

            if (awtConfig != null) {
                config = awtConfig.getGraphicsConfiguration();
            }

            _canvas = new GameCanvas(config, caps);

            // get a rendering surface and a context for this canvas
            drawable = GLDrawableFactory.getFactory().
                    getGLDrawable(_canvas, caps, null);
            context = getDrawable().createContext(null);
        }
        return _canvas;

    }

    public static void resizeView(int panelWidth, int panelHeight) {
        height = panelHeight;
        width = panelWidth;
    }

    public void makeContextCurrent() // make the rendering context current for this thread
    {
        try {
            while (getContext().makeCurrent() == GLContext.CONTEXT_NOT_CURRENT) {
                System.out.println("Context not yet current...");
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static GLContext getContext() {
        return context;
    }

    public static GLDrawable getDrawable() {
        return drawable;
    }

    public static void setDrawable(GLDrawable aDrawable) {
        drawable = aDrawable;
    }

    public void dispose() {
        getContext().release();
        getContext().destroy();
        frame.dispose();
    }

    public void swapBuffers() {
        getDrawable().swapBuffers();
    }

    public void fillRect(int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawText(String s, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawImage(Image im, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setColor(Color c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Color getColor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void createWindow(String name, int width, int height) {

        frame = new Frame() {

            @Override
            public void addNotify() {
                super.addNotify();
                GraphicsDevice.getDrawable().setRealized(true);

                GL gl = GraphicsDevice.getContext().getGL();
                System.err.println("INIT GL IS: " + gl.getClass().getName());

            }
        };

        frame.setName(name);
        //frame.setUndecorated(true);

        frame.setLayout(new BorderLayout());
        frame.add(makeRenderPanel(width, height), BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

    }

    private JPanel makeRenderPanel(int width, int height) // construct the canvas, inside a JPanel
    {

        JPanel renderPane = new JPanel();
        renderPane.setLayout(new BorderLayout());
        renderPane.setOpaque(false);
        renderPane.setPreferredSize(new Dimension(width, height));

        Canvas canvas = getCanvas();
        renderPane.add(canvas, BorderLayout.CENTER);

        canvas.setFocusable(true);
        canvas.requestFocus();    // the canvas now has focus, so receives key events

        // detect window resizes, and reshape the canvas accordingly
        renderPane.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent evt) {
                Dimension d = evt.getComponent().getSize();
                GraphicsDevice.resizeView(d.width, d.height);
            }
        });

        return renderPane;
    }  // end of makeRenderPanel()

    private static class GameCanvas extends Canvas {

        public GLDrawable drawable;   // the rendering 'surface'
        public GLContext context;     // the rendering context (holds rendering state info)
        public GL gl;
        public GLU glu;    // rotation variables

        public GameCanvas(GraphicsConfiguration config, GLCapabilities caps) {

            super(config);

            // get a rendering surface and a context for this canvas
            drawable =
                    GLDrawableFactory.getFactory().getGLDrawable(this, caps,
                    null);
            context = drawable.createContext(null);

        }

        public void reshape(int w, int h) /* called by the JFrame's ComponentListener when the window is resized
        (similar to the reshape() callback in GLEventListener) */ {

            if (h == 0) {
                h = 1;   // to avoid division by 0 in aspect ratio in resizeView()
            }

            GraphicsDevice.resizeView(w, h);
        }  // end of reshape()

        @Override
        public void update(Graphics g) {
        }

        @Override
        public void paint(Graphics g) {
        }
    }
}
