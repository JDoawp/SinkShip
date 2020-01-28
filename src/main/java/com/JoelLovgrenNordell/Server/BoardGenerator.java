package com.JoelLovgrenNordell.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardGenerator {
    List<String[]> board = new ArrayList<String[]>();   //Columns

    public BoardGenerator(){
    }

    public void generate(){
        String[] arrayTemplate = new String[]{"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"};    //Rows

        for(int i = 0; i < 10; i++){        //Insert 10 Rows into the columns
            board.add(arrayTemplate.clone());
        }
    }

    //This would be replaced by the players placing their own pieces
    public void placePieces(){
        Random ranBool = new Random();
        carrier(ranBool.nextBoolean());
        battleship(ranBool.nextBoolean());
        destroyerSub(ranBool.nextBoolean(), true);
        destroyerSub(ranBool.nextBoolean(), false);
        patrolBoat(ranBool.nextBoolean());

        printBoard();
    }

    //Check if any of the given places have a piece already there, if so return false if not place pieces and return true
    private boolean hPlacer(int startX, int startY, int size, String shipType){
        for(int i = 0; i < size; i++){
            if(check(startX, startY+i)){
                System.out.println("Intersect for Horizontal");
                return false;
            }
        }
        for(int i = 0; i < size; i++){
            board.get(startX)[startY+i] = shipType;
        }

        return true;
    }

    //Same as above but switch out flipping through Y with X
    private boolean vPlacer(int startX, int startY, int size, String shipType){
        for(int i = 0; i < size; i++){
            if(check(startX+i, startY)){
                System.out.println("Intersect for Vertical");
                return false;
            }
        }
        for(int i = 0; i < size; i++){
            board.get(startX+i)[startY] = shipType;
        }

        return true;
    }

    //Create 2 new random numbers that's within the array for its size (this would be replaced with a user placing their own pieces)
    private void carrier(boolean direction){
        Random rand = new Random();
        int x, y;
        if(direction){
            x = rand.nextInt(10);
            y = rand.nextInt(6);

            if(!hPlacer(x, y, 5, "C")){     //If the placer method returns false (meaning there's an intersection) re-run this function with new random numbers (but the same direction)
                carrier(direction);
            }
        }else{
            x = rand.nextInt(6);
            y = rand.nextInt(10);

            if(!vPlacer(x, y, 5, "C")){
                carrier(direction);
            }
        }
    }


    private void battleship(boolean direction){
        Random rand = new Random();
        int x, y;
        if(direction){
            x = rand.nextInt(10);
            y = rand.nextInt(7);

            if(!hPlacer(x, y, 4, "B")){
                battleship(direction);
            }
        }else{
            x = rand.nextInt(7);
            y = rand.nextInt(10);

            if(!vPlacer(x, y, 4, "B")){
                battleship(direction);
            }
        }
    }

    //The rest of these does the same thing except for destroyerSub which also has a boolean checking if it's a destroyed or a sub that's placed, both are 3 long so the same function can be used.
    private void destroyerSub(boolean direction, boolean isSub){
        Random rand = new Random();
        String ship;
        int x, y;
        if(isSub){
            ship = "S";
        }else{
            ship = "D";
        }

        if(direction){
            x = rand.nextInt(10);
            y = rand.nextInt(8);

            if(!hPlacer(x, y, 3, ship)){
                destroyerSub(direction, isSub);
            }
        }else{
            x = rand.nextInt(8);
            y = rand.nextInt(10);

            if(!vPlacer(x, y, 3, ship)){
                destroyerSub(direction, isSub);
            }
        }
    }

    private void patrolBoat(boolean direction){
        Random rand = new Random();
        int x, y;
        if(direction){
            x = rand.nextInt(10);
            y = rand.nextInt(9);

            if(!hPlacer(x, y, 2, "P")){
                patrolBoat(direction);
            }
        }else{
            x = rand.nextInt(9);
            y = rand.nextInt(10);

            if(!vPlacer(x, y, 2, "P")){
                patrolBoat(direction);
            }
        }
    }

    private void printBoard(){
        for (String[] column : board) {
            for (String pos : column) {
                System.out.print(pos + "  ");
            }
            System.out.println("");
        }
    }

    //If selected coords doesn't match # (empty space) and doesn't match X (space where it shot) then return true (meaning it's hit a ship)
    private boolean check(int X, int Y){
        return !board.get(X)[Y].matches("#") && !board.get(X)[Y].matches("X");
    }

    //Unused future function that takes 2 coords, and fires, if it hits print out hit (would replace with return true / false).
    public void attack(int X, int Y){
        System.out.println("Attacking X: " +X +" Y: " +Y);

        if(check(X, Y)){
            System.out.println("Hit!");
        }else{
            System.out.println("Miss!");
        }
        board.get(X)[Y] = "X";

        printBoard();
    }
}
