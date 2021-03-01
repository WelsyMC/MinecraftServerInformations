package fr.welsy.mcsinfos.impl;

import fr.welsy.mcsinfos.callback.ICallback;
import fr.welsy.mcsinfos.threading.RunnablePing;
import fr.welsy.mcsinfos.threading.Threading;

public class AServer {

    private String hostname;
    private int port;
    private ICallback callback;

    public AServer(String hostname, int port, ICallback callback) {
        this.hostname = hostname;
        this.port = port;
        this.callback = callback;
    }

    public void ping(Threading threading) {
        if(threading == Threading.THREADING){
            Thread thread = new Thread(new RunnablePing(this));
            thread.setName("MinecraftServerInformations - Thread");
            thread.start();
        }else{
            new RunnablePing(this).run();
        }
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public ICallback getCallback() {
        return callback;
    }
}
