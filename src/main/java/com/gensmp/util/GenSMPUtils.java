package com.gensmp.util;

import com.gensmp.GenSMP;
import org.bukkit.NamespacedKey;

/**
 * Utility extension methods for GenSMP
 */
public class GenSMPUtils {
    private static GenSMP plugin;

    public GenSMPUtils(GenSMP plugin) {
        GenSMPUtils.plugin = plugin;
    }

    /**
     * Creates a namespaced key for GenSMP
     */
    public static NamespacedKey createKey(String key) {
        return new NamespacedKey(plugin, key);
    }

    /**
     * Formats a duration in milliseconds to a readable string
     */
    public static String formatTime(long milliseconds) {
        long seconds = milliseconds / 1000;
        if (seconds < 60) {
            return seconds + "s";
        }
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return minutes + "m " + seconds + "s";
    }

    /**
     * Converts seconds to milliseconds
     */
    public static long secondsToMillis(int seconds) {
        return (long) seconds * 1000;
    }
}
