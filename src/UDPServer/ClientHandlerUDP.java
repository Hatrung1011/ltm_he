package UDPServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientHandlerUDP implements Runnable {
    private final DatagramSocket serverSocket;
    private final DatagramPacket receivePacket;

    public ClientHandlerUDP(DatagramSocket serverSocket, DatagramPacket receivePacket) {
        this.serverSocket = serverSocket;
        this.receivePacket = receivePacket;
    }

    @Override
    public void run() {
        try {
            // Handle the client request and send response
            // Implement the logic to handle CRUD operations using the ClientServiceImpl instance
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
