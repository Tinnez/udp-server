package academy.mindswap;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server2 {

    public static void main(String[] args) throws IOException {
        // write your code here
        // OPEN AN UDP SOCKET
        int portNumber = 8080;
        String hostName = "localhost";
        DatagramSocket socket = new DatagramSocket(portNumber);

// CREATE A DATAGRAM PACKET AND SEND IT FROM THE SOCKET

        // byte[] message = "Hello World".getBytes();
        // DatagramPacket sendPacket = new DatagramPacket(message, message.length, InetAddress.getByName(hostName), portNumber);
        //  socket.send(sendPacket);

// CREATE A DATAGRAM PACKET AND RECEIVE DATA FROM THE THE SOCKET
        byte[] recvBuffer = new byte[1024];

        while (socket.isBound()) {

            DatagramPacket receivedPacket = new DatagramPacket(recvBuffer, recvBuffer.length);

            System.out.println("Waiting for packet...");
            socket.receive(receivedPacket); // blocking method!

            String receivedString = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            System.out.println("Received: " + receivedString);


            //Send a message to our client

            InetAddress address = receivedPacket.getAddress();
            int port = receivedPacket.getPort();
            String response= "Unsupported operation";
            if (receivedString.equalsIgnoreCase("HIT ME")) {
                response = writeQuote();
            }
                byte[] responseBytes = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, address, port);
                socket.send(responsePacket);
            }


// CLOSE THE SOCKET
        socket.close();

    }

    public static String writeQuote() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("./src/motivational_quotes.txt"));
        int lines = 0;
        while (reader.readLine() != null) {
            lines++;
        }

        int randomNumber = (int) ((Math.random() * (lines - 0)) + 0);
        String randomQuote = Files.readAllLines(Paths.get("./src/motivational_quotes.txt")).get(randomNumber);
        reader.close();
        return randomQuote;
    }

//    public static String writeQuote_() throws IOException {
//
//        FileInputStream fileInputStream = new FileInputStream("./src/motivational_quotes.txt");
//
//
//        int randomNumber = (int) ((Math.random() * (13 - 0)) + 0);
//        String randomQuote = Files.readAllLines(Paths.get("./src/motivational_quotes.txt")).get(randomNumber);
//        fileInputStream.close();
//        return randomQuote;
//    }


}
