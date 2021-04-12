package gg.steve.mc.saphirus.sf.framework.utils;

import gg.steve.mc.saphirus.sf.SaphirusFormat;

public class LogUtil {

    public static void info(String message) {
        SaphirusFormat.getInstance().getLogger().info(message);
    }

    public static void warning(String message) {
        SaphirusFormat.getInstance().getLogger().warning(message);
    }
}
