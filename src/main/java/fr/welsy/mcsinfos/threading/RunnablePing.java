package fr.welsy.mcsinfos.threading;

import fr.welsy.mcsinfos.impl.AServer;
import fr.welsy.mcsinfos.impl.ServerInformations;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;

public class RunnablePing implements Runnable {
    private AServer server;
    private String motd;
    private int players;
    private int maxPlayers;

    public RunnablePing(AServer aServer) {
        this.server = aServer;
    }

    @Override
    public void run() {
        System.out.printf("MinecraftServerInformations » Pinging server %s with port %s...\n", this.server.getHostname(), this.server.getPort());

        try {
            getMOTD(server.getHostname(), server.getPort());
            System.out.println("MinecraftServerInformations » Pinged successfully, calling callback !");
            server.getCallback().call(new ServerInformations(players, maxPlayers, motd));
        } catch (IOException e) {
            System.out.println("MinecraftServerInformations » An error has occurred...");

            e.printStackTrace();
        }
    }

    public void getMOTD(String ip, int port) throws IOException {
        Socket socket = new Socket();
        OutputStream outputStream;
        DataOutputStream dataOutputStream;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        socket.setSoTimeout(1000);

        socket.connect(new InetSocketAddress(ip, port), 1000);
        outputStream = socket.getOutputStream();
        dataOutputStream = new DataOutputStream(outputStream);
        inputStream = socket.getInputStream();
        inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-16BE"));
        dataOutputStream.write(new byte[]{(byte) 0xFE, (byte) 0x01});
        int packetId = inputStream.read();

        if (packetId == -1) {
            dataOutputStream.close();
            outputStream.close();
            inputStreamReader.close();
            inputStream.close();
            socket.close();
            throw new IOException("Premature end of stream");
        }
        if (packetId != 0xFF) {
            dataOutputStream.close();
            outputStream.close();
            inputStreamReader.close();
            inputStream.close();
            socket.close();
            throw new IOException("Invalid packet ID (" + packetId + ").");
            //packet moze byt spravny
        }

        int length = inputStreamReader.read();

        if (length == -1) {
            dataOutputStream.close();
            outputStream.close();
            inputStreamReader.close();
            inputStream.close();
            socket.close();
            throw new IOException("Premature end of stream");
        }
        if (length == 0) {
            dataOutputStream.close();
            outputStream.close();
            inputStreamReader.close();
            inputStream.close();
            socket.close();
            throw new IOException("Invalid string length");
        }

        char[] chars = new char[length];

        if (inputStreamReader.read(chars, 0, length) != length) {
            dataOutputStream.close();
            outputStream.close();
            inputStreamReader.close();
            inputStream.close();
            socket.close();
            throw new IOException("Premature end of stream.");
        }

        String string = new String(chars);
        if (string.startsWith("§")) {
            String[] data = string.split("\0");
            String motd = data[3];

            int onlinePlayers = Integer.valueOf(data[4]);
            int maxPlayers = Integer.valueOf(data[5]);
            this.motd = motd;
            this.players = onlinePlayers;
            this.maxPlayers = maxPlayers;


            dataOutputStream.close();
            outputStream.close();
            inputStreamReader.close();
            inputStream.close();
            socket.close();
        }

    }
}
