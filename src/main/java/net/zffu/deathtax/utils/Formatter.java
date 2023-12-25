package net.zffu.deathtax.utils;

public class Formatter {

    public static String formatNotification(String raw, double coins, String playerName) {
        raw = raw.replaceAll("%coins%", "" + coins);
        raw = raw.replaceAll("%name%", playerName);
        return raw;
    }

}
