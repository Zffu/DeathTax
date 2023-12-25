package net.zffu.deathtax.player.provider;


import net.zffu.deathtax.player.ITaxablePlayer;

/**
 * How we get the players
 */
public interface IPlayerProvider {

    ITaxablePlayer getPlayerFromName(String name);

}
