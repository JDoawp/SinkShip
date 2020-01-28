package com.JoelLovgrenNordell.Client;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<String[]> board = new ArrayList<String[]>();

    public Board(){
    }

    public Board(List board){
        this.board = board;
    }

    public void printBoard(){
        for (String[] column : board) {
            for (String pos : column) {
                System.out.print(pos + "  ");
            }
            System.out.println("");
        }
    }
}
