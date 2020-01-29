package com.JoelLovgrenNordell.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardGenerator {
    List<String[]> board = new ArrayList<String[]>();   //Columns

    public BoardGenerator(){
    }

    public void generate(){
        String[] arrayTemplate = new String[]{"~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};    //Rows

        for(int i = 0; i < 10; i++){        //Insert 10 Rows into the columns
            board.add(arrayTemplate.clone());
        }
    }

    //Check if any of the given places have a piece already there, if so return false if not place pieces and return true
    public boolean hPlacer(int startX, int startY, int size, String shipType){
        for(int i = 0; i < size; i++){
            if(check(startX, startY+i)){
                System.out.println("Intersect for Horizontal");
                return false;
            }
        }
        for(int i = 0; i < size; i++){
            place(startX, startY+i, shipType);
            //board.get(startX)[startY+i] = shipType;
        }

        return true;
    }

    //Same as above but switch out flipping through Y with X
    public boolean vPlacer(int startX, int startY, int size, String shipType){
        for(int i = 0; i < size; i++){
            if(check(startX+i, startY)){
                System.out.println("Intersect for Vertical");
                return false;
            }
        }
        for(int i = 0; i < size; i++){
            place(startX+i, startY, shipType);
            //board.get(startX+i)[startY] = shipType;
        }

        return true;
    }

    void printBoard(){
        for (String[] column : board) {
            for (String pos : column) {
                System.out.print(pos + "  ");
            }
            System.out.println("");
        }
    }

    //If selected coords doesn't match # (empty space) and doesn't match X (space where it shot) then return true (meaning it's hit a ship)
    public boolean check(int X, int Y){
        return !board.get(X)[Y].matches("~") && !board.get(X)[Y].matches("X");
    }

    public void place(int X, int Y, String type){
        board.get(X)[Y] = type;
    }
}
