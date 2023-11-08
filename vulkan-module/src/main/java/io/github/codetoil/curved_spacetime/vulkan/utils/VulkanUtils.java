package io.github.codetoil.curved_spacetime.vulkan.utils;

import org.lwjgl.vulkan.VK13;

import java.util.Locale;

public class VulkanUtils {
    private VulkanUtils() {
        // Utility class
    }

    public static OSType getOS() {
        OSType result;
        String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((os.contains("mac")) || (os.contains("darwin"))) {
            result = OSType.MACOS;
        } else if (os.contains("win")) {
            result = OSType.WINDOWS;
        } else if (os.contains("nux")) {
            result = OSType.LINUX;
        } else {
            result = OSType.OTHER;
        }

        return result;
    }

    public static void vkCheck(int err, String errMsg) {
        if (err != VK13.VK_SUCCESS) {
            throw new AssertionError(errMsg + ": " + err);
        }
    }

    public enum OSType
    {
        WINDOWS, LINUX, OTHER, MACOS
    }
}
