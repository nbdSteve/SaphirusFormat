package gg.steve.mc.saphirus.sf.core;

import gg.steve.mc.saphirus.sf.SaphirusFormat;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class SaphirusFormatExpansion extends PlaceholderExpansion {

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return SaphirusFormat.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "sf";
    }

    @Override
    public String getVersion() {
        return SaphirusFormat.getInstance().getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equalsIgnoreCase("format_bal")) {
            return Format.getBalanceAsString(player);
        } else if (identifier.contains("format_num")) {
            String num = identifier.split("_")[2];
            return Format.formatNumberAsString(Integer.parseInt(num));
        }
        return "Invalid Placeholder.";
    }
}
