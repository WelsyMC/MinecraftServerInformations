package fr.welsy.mcsinfos.impl;

public class ServerInformations {

    private int players;
    private int maxPlayers;
    private String motd;

    public ServerInformations(int players, int maxPlayers, String motd) {
        this.players = players;
        this.maxPlayers = maxPlayers;
        this.motd = motd;
    }

    public int getPlayers() {
        return players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public String getMotd() {
        return motd;
    }
}
