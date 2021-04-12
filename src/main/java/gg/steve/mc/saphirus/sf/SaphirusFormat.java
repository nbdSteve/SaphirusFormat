package gg.steve.mc.saphirus.sf;

import gg.steve.mc.saphirus.sf.framework.SetupManager;
import gg.steve.mc.saphirus.sf.framework.utils.LogUtil;
import gg.steve.mc.saphirus.sf.framework.yml.utils.FileManagerUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SaphirusFormat extends JavaPlugin {
    private static SaphirusFormat instance;
    private static Economy economy;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        // setup plugin
        SetupManager.setupFiles(new FileManagerUtil(instance));
        SetupManager.registerCommands(instance);
        SetupManager.registerEvents(instance);
        // this method loads things like modules, tools, maps and data lists
        SetupManager.loadPluginCache();
        // register vault connection
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            try {
                economy = getServer().getServicesManager().getRegistration(Economy.class).getProvider();
                LogUtil.info("Vault found, hooking into economy now...");
            } catch (NullPointerException e) {
                LogUtil.info("Tried to hook into Vault but failed, please install an economy plugin e.g. EssentialsX.");
            }
        } else {
            LogUtil.info("Unable to find economy instance, disabling economy features. If you intend to use economy please install Vault and an economy plugin.");
            economy = null;
        }
        SetupManager.registerPlaceholderExpansions(instance);
        LogUtil.info("Thanks for using SaphirusFormat v" + getDescription().getVersion() + ", please contact nbdSteve#0583 on discord if you find any bugs.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        SetupManager.shutdownPluginCache();
        LogUtil.info("Thanks for using SaphirusFormat v" + getDescription().getVersion() + ", please contact nbdSteve#0583 on discord if you find any bugs.");
    }

    public static SaphirusFormat getInstance() {
        return instance;
    }

    public static Economy getEconomy() {
        return economy;
    }
}
