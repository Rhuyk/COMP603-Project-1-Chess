/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author rh200
 */
public class ChessGame {
    
    public static void main(String[] args) 
    {
        PiecesOnBoard board = new PiecesOnBoard();
        Scanner scanner = new Scanner(System.in);
        
        // Test out creating players
        Player player1 = new Player(ChessPieceColour.WHITE,"PJ");
        Player player2 = new Player(ChessPieceColour.BLACK,"Jamar");
        boolean isWhiteTurn = true;
        
        while(true)
        {
            Player currentPlayer = player1;
            if(!isWhiteTurn)
            {
                currentPlayer = player2;
            }
                
            PrintBoard.printBoard(board, currentPlayer);

            System.out.println("Enter 'quit' to leave anytime.");
            System.out.println("Enter 'save' to save your chess game.");
            System.out.println(currentPlayer.getPlayerName()+" Enter your chess move(e.g from c2 to c3): ");
            String chessMove = scanner.nextLine();
            
            if(chessMove.equalsIgnoreCase("save"))
            {
                try
                {
                ChessBoardFileIO.saveGameForUser(currentPlayer.getPlayerName(),board);
                }
                catch(FileNotFoundException e)
                {
                    System.out.println("");
                }
                catch(IOException e)
                {
                    System.out.println("");
                }
            }
            
            else if(chessMove.equalsIgnoreCase("quit"))
            {
                break;
            }
            else
            {
                String[] positions = chessMove.split(" ");

                int fromCol = positions[0].charAt(0) - 'a';
                int fromRow = Integer.parseInt(positions[0].substring(1)) - 1;
                int toCol = positions[1].charAt(0) - 'a';
                int toRow = Integer.parseInt(positions[1].substring(1)) - 1;

                board.movePiece(fromCol, fromRow, toCol, toRow);

                isWhiteTurn = !isWhiteTurn;
            }
        }
        
        
//        PiecesOnBoard loadedGame = ChessBoardFileIO.loadGame(player2.getPlayerName());
//        PrintBoard.printBoard(loadedGame, player2);
//        String test = scanner.nextLine();
    }
}