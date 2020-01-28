package com.JoelLovgrenNordell.Client;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        JSONHandler json = new JSONHandler();

        try {
            Socket socket = new Socket("localhost", 8008);
            System.out.println("Connecting to port 8008");
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            Board board = json.decode(reader.readLine());
            System.out.println("Board recieved: ");
            board.printBoard();

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
