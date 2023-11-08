package io.github.codetoil.curved_spacetime.render.glfw;

import io.github.codetoil.curved_spacetime.api.engine.Engine;
import io.github.codetoil.curved_spacetime.api.render.Window;
import org.lwjgl.Version;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.system.MemoryUtil;
import org.tinylog.Logger;

public abstract class GLFWWindow extends Window {
    protected long windowHandle;

    protected GLFWWindow(Engine engine)
    {
        super(engine);
        Logger.debug("LWJGL Version: " + Version.getVersion());
    }

    public void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !GLFW.glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        if (!this.isSupported()) {
            throwUnsupportedException();
        }

        // Configure GLFW
        setWindowHints();

        // Create the window
        this.windowHandle = GLFW.glfwCreateWindow(620, 480, "Curved Spacetime",
                MemoryUtil.NULL, MemoryUtil.NULL);
        if ( this.windowHandle == MemoryUtil.NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        GLFW.glfwSetKeyCallback(this.windowHandle, (window, key, scancode, action, mods) -> {
            if ( key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE )
                GLFW.glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });
    }

    public void loop() {
        // Poll for window events. The key callback above will only be
        // invoked during this call.
        GLFW.glfwPollEvents();
        if (GLFW.glfwWindowShouldClose(this.windowHandle)) {
            this.engine.stop();
        }
    }

    public abstract boolean isSupported();
    protected abstract void throwUnsupportedException();

    protected abstract void setWindowHints();

    public void showWindow() {
        GLFW.glfwShowWindow(this.windowHandle);
    }

    public void hideWindow() {
        GLFW.glfwDestroyWindow(this.windowHandle);
    }

    public void clean() {
        Callbacks.glfwFreeCallbacks(this.windowHandle);
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    public long getWindowHandle() {
        return this.windowHandle;
    }
}