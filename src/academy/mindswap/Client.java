package academy.mindswap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {


    public static void main(String[] args) throws IOException {
        int portNumber = 8080;
        String hostName = "localhost";
        DatagramSocket socket = new DatagramSocket();
// CREATE A DATAGRAM PACKET AND SEND IT FROM THE SOCKET

        byte[] message = "Hello World this is another message".getBytes();
        //reads a string from console
        String input = "";
        while (!input.equals("exit")) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a message to send to the server: ");
            input = br.readLine();
            message = input.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(message, message.length, InetAddress.getByName(hostName), portNumber);
            socket.send(sendPacket);
            System.out.println("Message sent to the server: " + input);

            // RECEIVE A PACKET FROM THE SERVER

//            byte[] receiveData = new byte[1024];
            byte[] receiveData = new byte[24 + message.length];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println(modifiedSentence);
        }

    }


}