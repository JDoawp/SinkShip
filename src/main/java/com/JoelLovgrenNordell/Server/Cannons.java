package com.JoelLovgrenNordell.Server;

public class Cannons {
    BoardGenerator board;
    public Cannons(BoardGenerator board){
        this.board = board;
    }

    public boolean fire(int x, int y){
        x--; y--;
        if(x > 9 || x < 0 || y > 9 || y < 0){
            System.out.println("Missed the map.");
            return false;
        }
        if(board.check(x, y)){
            board.place(x, y, "X");
            return true;
        }
        board.place(x, y, "X");
        return false;
    }
}
