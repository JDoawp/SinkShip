package com.JoelLovgrenNordell.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class Main {

    //Connect to a server, immediately get the board and then decode it to a board Object
    public static void main(String[] args) {
        Random ran = new Random();
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
