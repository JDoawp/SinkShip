package com.JoelLovgrenNordell.Server;

import java.util.Random;

public class ShipFactory {
    BoardGenerator board;
    public ShipFactory(BoardGenerator board){
        this.board = board;
    }
    private boolean[] ships = new boolean[]{false, false, false, false, false};

    //This would be replaced by the players placing their own pieces
    public void randomPieces(){
        Random ranBool = new Random();
        boatMaker(ranBool.nextBoolean(), "Carrier");
        boatMaker(ranBool.nextBoolean(), "Battleship");
        boatMaker(ranBool.nextBoolean(), "Destroyer");
        boatMaker(ranBool.nextBoolean(), "Submarine");
        boatMaker(ranBool.nextBoolean(), "Patrol ship");
    }

    private int returnPos(String shipType){
        int pos = 0;

        switch (shipType.charAt(0)){
            case 'C':
                pos = 0;
                break;

            case 'B':
                pos = 1;
                break;

            case 'D':
                pos = 2;
                break;

            case 'S':
                pos = 3;
                break;

            case 'P':
                pos = 4;
                break;
        }
        return pos;
    }

    private int returnSize(String shipType){
        int size = 0;

        switch (shipType.charAt(0)){
            case 'C':
                size = 5;
                break;

            case 'B':
                size = 4;
                break;

            case 'D':
                size = 3;
                break;

            case 'S':
                size = 3;
                break;

            case 'P':
                size = 2;
                break;
        }

        return size;
    }

    public boolean boatMaker(int x, int y, boolean direction, String shipType){
        shipType = shipType.substring(0,1).toUpperCase();
        int size = returnSize(shipType);

        try {
            if (direction) {
                if (!board.hPlacer(x, y, size, shipType)) {
                    System.out.println("Invalid position, make sure nothing overlaps.");
                    return false;
                }
            } else {
                if (!board.vPlacer(x, y, size, shipType)) {
                    System.out.println("Invalid position, make sure nothing overlaps.");
                    return false;
                }
            }
        }catch (IndexOutOfBoundsException OOB){
            System.out.println("Invalid position make sure nothing is out of bounds.");
            return false;
        }
        return true;
    }

    private boolean boatMaker(boolean direction, String shipType){
        shipType = shipType.substring(0,1).toUpperCase();
        Random rand = new Random();
        int size = returnSize(shipType);

        int x, y;
        if(direction){
            x = rand.nextInt(10);
            y = rand.nextInt(10-(size-1));

            if(!board.hPlacer(x, y, size, shipType)){     //If the placer method returns false (meaning there's an intersection) re-run this function with new random numbers (but the same direction)
                boatMaker(true, shipType);
            }
        }else{
            x = rand.nextInt(10-(size-1));
            y = rand.nextInt(10);

            if(!board.vPlacer(x, y, size, shipType)){
                boatMaker(false, shipType);
            }
        }

        ships[returnPos(shipType)] = true;
        return true;
    }

}