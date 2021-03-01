package fr.welsy.mcsinfos;

import fr.welsy.mcsinfos.builder.MinecraftServerBuilder;

public enum MinecraftServerInformations {

    INSTANCE;

    public MinecraftServerBuilder getBuilder(){
        return new MinecraftServerBuilder();
    }

}
