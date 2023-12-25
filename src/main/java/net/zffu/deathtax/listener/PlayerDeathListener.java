package net.zffu.deathtax.listener;

import net.zffu.deathtax.DeathTaxPlugin;
import net.zffu.deathtax.player.ITaxablePlayer;
import net.zffu.deathtax.utils.Formatter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        String notificationDeath = DeathTaxPlugin.getInstance().getConfig().getString("died-notification");
        String notificationGain = DeathTaxPlugin.getInstance().getConfig().getString("gain-notification");

        ITaxablePlayer playerWhoDied = DeathTaxPlugin.getInstance().provider.getPlayerFromName(event.getEntity().getName());
        double tax = DeathTaxPlugin.getInstance().getConfig().getDouble("death-tax");
        playerWhoDied.looseCoins(tax);

        ITaxablePlayer banker = DeathTaxPlugin.getInstance().provider.getPlayerFromName(DeathTaxPlugin.getInstance().getConfig().getString("banker-name"));
        ITaxablePlayer killer = null;

        double bankerPercentage = 1.0;
        double killerPercentage = 0;

        if(DeathTaxPlugin.getInstance().getConfig().getBoolean("should-split")) {
            bankerPercentage = DeathTaxPlugin.getInstance().getConfig().getDouble("banker-split-percentage");
            killerPercentage = DeathTaxPlugin.getInstance().getConfig().getDouble("killer-split-percentage");

            if(event.getEntity().getKiller() != null) {
                killer = DeathTaxPlugin.getInstance().provider.getPlayerFromName(event.getEntity().getKiller().getName());
            }
        }

        banker.recieveCoins(tax * bankerPercentage);
        banker.sendNotification(Formatter.formatNotification(notificationGain, tax * bankerPercentage, event.getEntity().getName()));

        if(killer != null) {
            killer.recieveCoins(tax * killerPercentage);
            killer.sendNotification(Formatter.formatNotification(notificationGain, tax * killerPercentage, event.getEntity().getName()));
        }

        playerWhoDied.sendNotification(Formatter.formatNotification(notificationDeath, tax, DeathTaxPlugin.getInstance().getConfig().getString("banker-name") + ((killer != null) ? " and " + event.getEntity().getKiller().getName() : "")));
    }

}
