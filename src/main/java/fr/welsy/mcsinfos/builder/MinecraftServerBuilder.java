package fr.welsy.mcsinfos.builder;

import fr.welsy.mcsinfos.MinecraftServerInformations;
import fr.welsy.mcsinfos.callback.ICallback;
import fr.welsy.mcsinfos.impl.AServer;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public class MinecraftServerBuilder {

    private String host;
    private int port;
    private ICallback callback;

    public MinecraftServerBuilder host(String host) {
        this.host = host;
        return this;
    }


    public void onPingSuccess() {

    }

    public MinecraftServerBuilder port(int port) {
        this.port = port;
        return this;
    }

    public AServer build() {
        System.out.println("MinecraftServerInformations » Building a new Pinger...");
        return new AServer(host, port, callback);
    }

    public MinecraftServerBuilder onPingSuccess(ICallback callback) {
        this.callback = callback;
        return this;
    }
}
