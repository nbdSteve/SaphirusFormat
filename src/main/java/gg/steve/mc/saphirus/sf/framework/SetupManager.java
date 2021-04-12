package gg.steve.mc.saphirus.sf.framework;

import gg.steve.mc.saphirus.sf.core.Format;
import gg.steve.mc.saphirus.sf.core.FormatCmd;
import gg.steve.mc.saphirus.sf.core.SaphirusFormatExpansion;
import gg.steve.mc.saphirus.sf.framework.utils.LogUtil;
import gg.steve.mc.saphirus.sf.framework.yml.Files;
import gg.steve.mc.saphirus.sf.framework.yml.utils.FileManagerUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles setting up the plugin on start
 */
public class SetupManager {
    private static FileManagerUtil fileManagerUtil;
    private static List<PlaceholderExpansion> placeholderExpansions;

    private SetupManager() throws IllegalAccessException {
        throw new IllegalAccessException("Manager class cannot be instantiated.");
    }

    /**
     * Loads the files into the file manager
     */
    public static void setupFiles(FileManagerUtil fm) {
        fileManagerUtil = fm;
        Files.CONFIG.load(fm);
        Files.PERMISSIONS.load(fm);
        Files.DEBUG.load(fm);
        Files.MESSAGES.load(fm);
    }

    public static void registerCommands(JavaPlugin instance) {
        instance.getCommand("format").setExecutor(new FormatCmd());
        instance.getCommand("format").setTabCompleter(new FormatCmd());
    }

    /**
     * Register all of the events for the plugin
     *
     * @param instance Plugin, the main plugin instance
     */
    public static void registerEvents(JavaPlugin instance) {
        PluginManager pm = instance.getServer().getPluginManager();
    }

    public static void registerEvent(JavaPlugin instance, Listener listener) {
        instance.getServer().getPluginManager().registerEvents(listener, instance);
    }

    public static void registerPlaceholderExpansions(JavaPlugin instance) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            LogUtil.info("PlaceholderAPI found, registering expansions with it now...");
            new SaphirusFormatExpansion().register();
            for (PlaceholderExpansion expansion : placeholderExpansions) {
                expansion.register();
            }
        }
    }

    public static void addPlaceholderExpansion(PlaceholderExpansion expansion) {
        placeholderExpansions.add(expansion);
    }

    public static void loadPluginCache() {
        // modules
        placeholderExpansions = new ArrayList<>();
        Format.onLoad();
    }

    public static void shutdownPluginCache() {
        Format.onShutdown();
        if (placeholderExpansions != null && !placeholderExpansions.isEmpty()) placeholderExpansions.clear();
    }

//    public static void setupMetrics(JavaPlugin instance, int id) {
//        Metrics metrics = new Metrics(instance, id);
//        metrics.addCustomChart(new Metrics.MultiLineChart("players_and_servers", () -> {
//            Map<String, Integer> valueMap = new HashMap<>();
//            valueMap.put("servers", 1);
//            valueMap.put("players", Bukkit.getOnlinePlayers().size());
//            return valueMap;
//        }));
//    }

    public static FileManagerUtil getFileManagerUtil() {
        return fileManagerUtil;
    }
}
