package gg.steve.mc.saphirus.sf.framework.cmd.misc;

import gg.steve.mc.saphirus.sf.SaphirusFormat;
import gg.steve.mc.saphirus.sf.framework.cmd.SubCommand;
import gg.steve.mc.saphirus.sf.framework.message.GeneralMessage;
import gg.steve.mc.saphirus.sf.framework.permission.PermissionNode;
import gg.steve.mc.saphirus.sf.framework.yml.Files;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class ReloadSubCmd extends SubCommand {

    public ReloadSubCmd() {
        super("reload", 1, 1, false, PermissionNode.RELOAD);
        addAlias("r");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Files.reload();
        Bukkit.getPluginManager().disablePlugin(SaphirusFormat.getInstance());
        Bukkit.getPluginManager().enablePlugin(SaphirusFormat.getInstance());
        GeneralMessage.RELOAD.message(sender, SaphirusFormat.getInstance().getDescription().getVersion());
    }
}
