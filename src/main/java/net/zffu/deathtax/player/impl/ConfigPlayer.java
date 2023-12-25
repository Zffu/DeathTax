package net.zffu.deathtax.player.impl;

import net.zffu.deathtax.DeathTaxPlugin;
import net.zffu.deathtax.player.ITaxablePlayer;
import net.zffu.deathtax.player.provider.impl.ConfigPlayerProvider;
import org.bukkit.Bukkit;

/**
 * Represents a player that is stored in the config <b>Not recommended!</b>
 */
public class ConfigPlayer implements ITaxablePlayer {

    private String name;

    public ConfigPlayer(String name) {
        this.name = name;
    }

    @Override
    public void recieveCoins(double coins) {
        setBalance(getBalance() + coins);
    }

    @Override
    public void looseCoins(double coins) {
        setBalance(getBalance() - coins);
    }

    @Override
    public double getBalance() {
        if(DeathTaxPlugin.getInstance().getConfig().contains("data." + name)) return DeathTaxPlugin.getInstance().getConfig().getDouble("data." + name);
        return 0;
    }

    @Override
    public void setBalance(double coins) {
        DeathTaxPlugin.getInstance().getConfig().set("data." + name, coins);
        DeathTaxPlugin.getInstance().saveConfig();
    }

    @Override
    public void sendNotification(String msg) {
        if(Bukkit.getPlayer(name) == null) return;
        Bukkit.getPlayer(name).sendMessage(msg);
    }
}
