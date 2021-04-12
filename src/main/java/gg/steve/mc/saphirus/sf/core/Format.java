package gg.steve.mc.saphirus.sf.core;

import gg.steve.mc.saphirus.sf.SaphirusFormat;
import gg.steve.mc.saphirus.sf.framework.utils.LogUtil;
import gg.steve.mc.saphirus.sf.framework.yml.Files;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;

public class Format {
    public static Map<Integer, String> translations;

    public static void onLoad() {
        translations = new HashMap<>();
        for (String entry : Files.CONFIG.get().getKeys(false)) {
            int val;
            try {
                val = Integer.parseInt(entry);
            } catch (NumberFormatException e) {
                LogUtil.warning("Error while assigning a string value to the entry " + entry + ", please make sure it is an integer.");
                continue;
            }
            translations.put(val, Files.CONFIG.get().getString(entry).toUpperCase(Locale.ROOT));
        }
    }

    public static void onShutdown() {
        if (translations != null && !translations.isEmpty()) translations.clear();
    }

    public static String getBalanceAsString(OfflinePlayer player) {
        double bal = SaphirusFormat.getEconomy().getBalance(player);
        int balRound = (int) Math.floor(bal);
        return formatNumberAsString(balRound);
    }

    public static String formatNumberAsString(int num) {
        StringBuilder builder = new StringBuilder();
        IntStream stream = String.valueOf(num).chars().map(Character::getNumericValue);
        for (int i : stream.toArray()) {
            builder.append(translations.get(i));
        }
        return builder.toString();
    }
}
