/**
 * Curved Spacetime is an easy-to-use modular simulator for General Relativity.<br>
 * Copyright (C) 2023-2024 Anthony Michalek (Codetoil)<br>
 * Copyright (c) 2023 Antonio Hernández Bejarano<br>
 * <br>
 * This file is part of Curved Spacetime<br>
 * <br>
 * This program is free software: you can redistribute it and/or modify <br>
 * it under the terms of the GNU General Public License as published by <br>
 * the Free Software Foundation, either version 3 of the License, or <br>
 * (at your option) any later version.<br>
 * <br>
 * This program is distributed in the hope that it will be useful,<br>
 * but WITHOUT ANY WARRANTY; without even the implied warranty of<br>
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br>
 * GNU General Public License for more details.<br>
 * <br>
 * You should have received a copy of the GNU General Public License<br>
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.<br>
 */

package io.github.codetoil.curved_spacetime.render.vulkan;

import io.github.codetoil.curved_spacetime.api.engine.Engine;
import io.github.codetoil.curved_spacetime.api.scene.Scene;
import io.github.codetoil.curved_spacetime.api.render.Renderer;
import io.github.codetoil.curved_spacetime.vulkan.VulkanInstance;
import org.lwjgl.glfw.GLFWVulkan;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VulkanRenderer extends Renderer {
    public final VulkanRenderConfig vulkanRenderConfig;
    protected final VulkanInstance vulkanInstance;
    protected final VulkanGraphicsQueue vulkanGraphicsQueue;
    protected final VulkanSurface vulkanSurface;
    protected final VulkanSwapChain vulkanSwapChain;


    public VulkanRenderer(Engine engine, Scene scene) {
        super(engine, scene);

        try {
            this.vulkanRenderConfig = new VulkanRenderConfig().load();
            if (this.vulkanRenderConfig.isDirty()) this.vulkanRenderConfig.save();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load Vulkan Render Config", ex);
        }

        this.window = new VulkanWindow(engine);

        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.window.init();
        this.window.showWindow();

        this.vulkanInstance = new VulkanInstance(GLFWVulkan::glfwGetRequiredInstanceExtensions);
        this.vulkanSurface = new VulkanSurface(this.vulkanInstance.getVulkanPhysicalDevice(), ((VulkanWindow) window)
                .getWindowHandle());
        this.vulkanGraphicsQueue = new VulkanGraphicsQueue(this.vulkanInstance.getVulkanLogicalDevice(), 0);

        this.vulkanSwapChain = new VulkanSwapChain(this.vulkanInstance.getVulkanLogicalDevice(), this.vulkanSurface,
                (VulkanWindow) this.window, this.vulkanRenderConfig.getRequestedImages(),
                this.vulkanRenderConfig.hasVSync());

        this.frameHandler = this.executor.scheduleAtFixedRate(this.window::loop,
                1_000 / this.vulkanRenderConfig.getFPS(), 1_000 / this.vulkanRenderConfig.getFPS(),
                TimeUnit.MILLISECONDS);
    }

    public void clean() {
        super.clean();
        this.vulkanSwapChain.cleanup();
        this.vulkanSurface.cleanup();
        this.vulkanInstance.cleanup();
    }

    public void render() {
    }
}
