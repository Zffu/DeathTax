package net.zffu.deathtax;

import net.zffu.deathtax.listener.PlayerDeathListener;
import net.zffu.deathtax.player.provider.IPlayerProvider;
import net.zffu.deathtax.player.provider.impl.ConfigPlayerProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathTaxPlugin extends JavaPlugin {

    private static DeathTaxPlugin INSTANCE;

    public IPlayerProvider provider;


    @Override
    public void onEnable() {
        INSTANCE = this;

        saveDefaultConfig();

        // todo: change this when poster tells me the plugin
        this.provider = new ConfigPlayerProvider();

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static DeathTaxPlugin getInstance() {
        return INSTANCE;
    }

}
