/**
 * @author: Arman Sidhu
 * @description: This program implements a 2-player game of Battleship
 */

import java.util.Scanner;

public class Problem2 {

    // Declaring board size as a static variable
    static int size;

    // Method to print both players' boards
    static void printBoards(String[][] player1, String[][] player2) {
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                System.out.print(player1[i][j]);
            }
            System.out.print("\t");
            for(int j = 0; j < size; j++) {
                System.out.print(player2[i][j]);
            }
            System.out.println();
        }
    }

    // Method to add boats to a player's board
    static void addBoats(String[][] player, int x, int y, int boatSize, int orientation) {
        for(int i = 0; i < boatSize && x < size && y < size; i++) {
            if(orientation == 0) {
                player[y][x++] = "B";
            } else if(orientation == 1) {
                player[y++][x] = "B";
            }
        }
    }

    // Method to fire the last shot on a player's board
    static void lastShot(String[][] player, int x, int y) {
        // Vertical damage
        for(int i = y - 2; i <= y + 2 && i < size; i++) {
            if(i >= 0) {
                player[i][x] = "X";
            }
        }
        // Horizontal damage
        for(int j = x - 2; j <= x + 2 && j < size; j++) {
            if(j >= 0) {
                player[y][j] = "X";
            }
        }
        // Diagonal damage
        for(int i = y - 1; i <= y + 1 && i < size; i += 2) {
            for(int j = x - 1; j <= x + 1 && j < size; j += 2) {
                if(i >= 0 && j >= 0) {
                    player[i][j] = "X";
                }
            }
        }
    }

    public static void main(String[] args) {

        // Setting up a keyboard scanner
        Scanner keyboard = new Scanner(System.in);

        // Inputting board size
        size = keyboard.nextInt();

        // Array declaration for both players' boards
        String[][] player1 = new String[size][size];
        String[][] player2 = new String[size][size];

        // Array initialization
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                player1[i][j] = "-";
                player2[i][j] = "-";
            }
        }

        // Inputting the number of boats to be added
        int nBoats = keyboard.nextInt();

        // Total number of turns
        int totalTurns = nBoats * 2;

        // Adding ships to each player's board in alternating turns
        for(int n = 0; n < totalTurns; n++) {

            int x = keyboard.nextInt(); // X coordinate of the boat
            int y = keyboard.nextInt(); // Y coordinate of the boat
            int boatSize = keyboard.nextInt(); // Size of the boat
            int orientation = keyboard.nextInt(); // Orientation of the boat

            // if condition to ignore the boat coordinates outside the board
            if((x < size && x >= 0) && (y < size && y >= 0)) {

                // Player1's turn to add boat
                if(n % 2 == 0) {
                    // Calling the method to add boat to player1's board
                    addBoats(player1, x, y, boatSize, orientation);
                }
                // Player2's turn to add boat
                else {
                    // Calling the method to add boat to player2's board
                    addBoats(player2, x, y, boatSize, orientation);
                }
            }
        }

        // Calling the method to print both players' boards
        printBoards(player1, player2);

        // Inputting number of shots allowed by each player
        int S = keyboard.nextInt();

        // Total number of shots
        int totalShots = S * 2;

        // Both the players fire shots on each other's board in alternating turns
        for(int n = 0; n < totalShots; n++) {

            int x = keyboard.nextInt(); // X coordinate of the shot
            int y = keyboard.nextInt(); // Y coordinate of the shot

            // if condition to ignore the shot coordinates outside the board
            if((x < size && x >= 0) && (y < size && y >= 0)) {

                // Player1's shot on player2's board
                if(n % 2 == 0) {
                    // Firing the shot if it is not the last one
                    if(n != (totalShots - 2)) {
                        player2[y][x] = "X";
                    }
                    // Calling the method to fire the last shot
                    else {
                        lastShot(player2, x, y);
                    }
                }
                // Player2's shot on player1's board
                else {
                    // Firing the shot if it is not the last one
                    if(n != (totalShots - 1)) {
                        player1[y][x] = "X";
                    }
                    // Calling the method to fire the last shot
                    else {
                        lastShot(player1, x, y);
                    }
                }
            }
        }

        System.out.println();

        // Calling the method to print both players' boards again
        printBoards(player1, player2);

        // Finding the number of boats left in both players' boards

        int p1Boats = 0; // Number of boats left in player1's board
        int p2Boats = 0; // Number of boats left in player2's board

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(player1[i][j].equals("B")) {
                    p1Boats++;
                }
                if(player2[i][j].equals("B")) {
                    p2Boats++;
                }
            }
        }

        System.out.println();

        // Game result
        if(p1Boats > 0 && p2Boats > 0) {
            System.out.println("Draw");
        } else if(p1Boats > 0 && p2Boats == 0) {
            System.out.println("P1 Win");
        } else if(p1Boats == 0 && p2Boats > 0) {
            System.out.println("P2 Win");
        } else {
            System.out.println("All destroyed");
        }
    }
}



