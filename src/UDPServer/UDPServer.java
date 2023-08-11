package UDPServer;

import TCPServer.ClientHandler;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UDPServer {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10); // Adjust pool size as needed

        try (DatagramSocket serverSocket = new DatagramSocket(12345)) { // Use a suitable port number
            System.out.println("Server is running...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                executor.submit(new ClientHandlerUDP(serverSocket, receivePacket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
