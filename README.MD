# MinecraftServerInformations
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity)
![Maintaner](https://img.shields.io/badge/maintainer-WelsyMC-blue)
[![GitHub version](https://badge.fury.io/gh/WelsyMC%2FMinecraftServerInformations.svg)](https://github.com/WelsyMC/MinecraftServerInformations)


MinecraftServerInformation is a java library for retrieving informations about Minecraft Servers.
## Installation

1. Download the latest version from the Releases tab
2. Add the .jar to your project libraries

## Usage

```java
class Test{
    void retrievePaladiumInfos(){
        AServer server = MinecraftServerInformations.INSTANCE.getBuilder()
                .host("proxy.paladium-pvp.fr")
                .port(25565)
                .onPingSuccess(this::serverPinged)
                .build();
        /*
         * Threading.THREADING = Use a separated thread (asynchronously call the onPingSuccess method)
         * Threading.NO_THREADING = Don't use a separated thread (the current thread will wait for the ping to finish)
         */
        server.ping(Threading.THREADING);
    }
    
    public void serverPinged(ServerInformations infos){
        int players = infos.getPlayers();
        int maxPlayers = infos.getMaxPlayers();
        String motd = infos.getMotd();
        // do whatever you want
    }
}

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)