package gg.steve.mc.saphirus.sf.framework.permission;

import gg.steve.mc.saphirus.sf.framework.yml.Files;
import org.bukkit.command.CommandSender;

public enum PermissionNode {
    // cmd
    RELOAD("command.reload"),
    HELP("command.help");

    private String path;

    PermissionNode(String path) {
        this.path = path;
    }

    public String get() {
        return Files.PERMISSIONS.get().getString(this.path);
    }

    public boolean hasPermission(CommandSender sender) {
        return sender.hasPermission(get());
    }

    public static boolean isPurchasePerms() {
        return Files.PERMISSIONS.get().getBoolean("purchase.enabled");
    }
}
