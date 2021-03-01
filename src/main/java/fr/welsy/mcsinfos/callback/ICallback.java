package fr.welsy.mcsinfos.callback;

import fr.welsy.mcsinfos.impl.AServer;
import fr.welsy.mcsinfos.impl.ServerInformations;

public interface ICallback {

    void call(ServerInformations informations);
}
