/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class FileIOTest {
    
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our File IO Chess game Test!\n");
        
        while(true)
        {
        System.out.println("Remember to type quit to exit out.\n ");
        System.out.print("Please type in 'new' to start up a fresh new game or ");
        System.out.println("type 'load' to load your old saved game: ");
        String game = scanner.nextLine();
        String username = "";
        String filename = "";
        String sides = "";
        if(game.equalsIgnoreCase("new"))
        {
            System.out.print("Please enter a new username: ");
            username = scanner.nextLine();
            if(username.equals("quit"))
            {
                System.out.println("Successfully quitted!");
                break;
            }
            
            System.out.print("\nPlease enter a new name for your chess game(E.g savedGame01): ");
            filename = scanner.nextLine();
            
            System.out.print("\nWould you like to play as black or white?: ");
            sides = scanner.nextLine();
            ChessBoard chessboard = new ChessBoard();

            if(sides.equalsIgnoreCase("white"))
            {
                PrintBoard.printBoard(chessboard,true);
            }
            else if(sides.equalsIgnoreCase("black"))
            {
                PrintBoard.printBoard(chessboard,false);
            }
            
             System.out.print("\nType in 'save' to keep your game data in a text file: ");
        
        String choices = scanner.nextLine();
        
        if(choices.equalsIgnoreCase("save"))
        {
            ChessBoardFileIO.saveGameForUser(username,chessboard, filename);
        }
        //break;
        }
        
        else if(game.equalsIgnoreCase("load"))
        {
            System.out.print("Please enter your previous username: ");
            username = scanner.nextLine();
            
            System.out.print("\nPlease enter your previous name for your chess game(E.g savedGame01): ");
            filename = scanner.nextLine();
            
            ChessBoard loadedGame = ChessBoardFileIO.loadGame(username, filename);
            
            if(loadedGame == null)
            {
                System.out.println("Game file cannot be accessed!");
                break;
            }
            
            System.out.print("\nWould you like to play as black or white?: ");
            sides = scanner.nextLine();
        
            if(sides.equalsIgnoreCase("white"))
            {
                PrintBoard.printBoard(loadedGame,true);
            }
            else if(sides.equalsIgnoreCase("black"))
            {
                PrintBoard.printBoard(loadedGame,false);
            }
            }
        else if(game.equalsIgnoreCase("quit"))
        {
            break;
        }
        }
    }
}