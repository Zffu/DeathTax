package net.zffu.deathtax.player.provider.impl;

import net.zffu.deathtax.player.ITaxablePlayer;
import net.zffu.deathtax.player.impl.ConfigPlayer;
import net.zffu.deathtax.player.provider.IPlayerProvider;

/**
 * Uses the config to store player balances <b>Not recomended!</b>
 */
public class ConfigPlayerProvider implements IPlayerProvider {

    @Override
    public ITaxablePlayer getPlayerFromName(String name) {
        return new ConfigPlayer(name);
    }

}
