package com.JoelLovgrenNordell.Server;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    //Start a server, wait until a client logs on, when they do create a new board and encode that to JSON before sending it.
    public static void main(String[] args) {
        try{
            //ServerSocket server = new ServerSocket(8008);
            System.out.println("Listening to port 8008");
            //Socket socket = server.accept();

            System.out.println("Generating board...");
            BoardGenerator board = new BoardGenerator();
            ShipFactory factory = new ShipFactory(board);
            Cannons cannons = new Cannons(board);
            board.generate();
            factory.randomPieces();
            if(cannons.fire(2, 3)){
                System.out.println("Hit!");
            }
            board.printBoard();


            //OutputStream output = socket.getOutputStream();
            //PrintWriter writer = new PrintWriter(output, true);

            JSONHandler json = new JSONHandler();
            //writer.println(json.encode(board));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
