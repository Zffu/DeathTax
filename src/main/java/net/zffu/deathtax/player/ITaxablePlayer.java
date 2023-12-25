package net.zffu.deathtax.player;

/**
 * Represents a taxable player that can recieve coins or loose coins.
 */
public interface ITaxablePlayer {

    void recieveCoins(double coins);
    void looseCoins(double coins);
    double getBalance();
    void setBalance(double coins);

    void sendNotification(String msg);


}
