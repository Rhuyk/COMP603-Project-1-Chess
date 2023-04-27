/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

import java.util.Scanner;

/**
 *
 * @author rh200
 */
public class ChessGame {
    
    public static void printBoard(PiecesOnBoard board, Player CurrentPlayer)
    {
        System.out.println("Chess Board: \n");
        System.out.println("Currently playing as: " + CurrentPlayer.getColourPiece() + "\n");
        
        String column = "     a    b    c    d    e    f    g    h\n";
        int currentRow = 7;
        int rowNumber = 8;
        int rowChange = -1;

        if(CurrentPlayer.getColourPiece() == ChessPieceColour.BLACK)
        {
            currentRow = 0;
            rowNumber = 1;
            rowChange = 1;
            column = "     h    g    f    e    d    c    b    a\n";
        }

        System.out.println(column);
        
        for(int i = 0; i < 8; i++)
        {
            System.out.println("   +----+----+----+----+----+----+----+----+");
            System.out.print((rowNumber) + "  | ");

            for(int j = 0; j < 8; j++)
            {
                int currentCol = j;
                
                if(CurrentPlayer.getColourPiece() == ChessPieceColour.BLACK)
                {
                    currentCol = 7 - j;
                }
                
                Piece piece = board.getBoard()[currentCol][currentRow];

                if(piece == null)
                {
                    System.out.print("-- | ");
                }
                else
                {
                    System.out.print(piece.getSymbol() + " | ");
                }
            }

            System.out.println(rowNumber);
            currentRow += rowChange;
            rowNumber += rowChange;
        }
        
        System.out.println("   +----+----+----+----+----+----+----+----+");
        System.out.println(column);
    }
    
    public static void main(String[] args) 
    {
        PiecesOnBoard board = new PiecesOnBoard();
        Scanner scanner = new Scanner(System.in);
        
        Player player1 = new Player(ChessPieceColour.WHITE,"Jamar");
        Player player2 = new Player(ChessPieceColour.BLACK,"Richard");
        boolean isWhiteTurn = true;
        
        System.out.println("Enter 'quit' to leave anytime.");
        System.out.println("Enter 'save' to save your chess game.");
        System.out.println("Enter 'load' to load your saved game data.");
        
        while(true)
        {
            Player currentPlayer = player1;
            if(!isWhiteTurn)
            {
                currentPlayer = player2;
            }
                
            printBoard(board, currentPlayer);

            System.out.println(currentPlayer.getPlayerName()+" Enter your chess move(e.g 'c2 c4'): ");
            String chessMove = scanner.nextLine();
            
            if(chessMove.equalsIgnoreCase("save"))
            {
                ChessBoardFileIO.saveGameForUser(currentPlayer.getPlayerName(),board);
            }
            
            else if(chessMove.equalsIgnoreCase("load"))
            {
                board = ChessBoardFileIO.loadGame(currentPlayer.getPlayerName());
                System.out.println("Welcome back! " + currentPlayer.getPlayerName());
            }
            
            else if(chessMove.equalsIgnoreCase("quit"))
            {
                break;
            }
            else if(chessMove.equalsIgnoreCase("reset"))
            {
                board.resetBoard();
                isWhiteTurn = true;
            }
            else
            {
                String[] positions = chessMove.split(" ");
                
                if(positions.length != 2)
                {
                    System.out.println("You have entered a incorrect format! Please try again.");
                    continue; 
                }
                try
                {
                    int fromCol = positions[0].charAt(0) - 'a';
                    int fromRow = Integer.parseInt(positions[0].substring(1)) - 1;
                    int toCol = positions[1].charAt(0) - 'a';
                    int toRow = Integer.parseInt(positions[1].substring(1)) - 1;

                    if(board.getPiece(fromCol, fromRow) == null)
                    {
                        System.out.println("You cannot move that piece. It doesn't exist.");
                        continue; 
                    }
                    if(board.getPiece(fromCol, fromRow).getColour() != currentPlayer.getColourPiece())
                    {
                        System.out.println("You cannot move that piece. It does not belong to you.");
                        continue; 
                    }

                    if(board.movePiece(fromCol, fromRow, toCol, toRow) == true)
                    {
                        isWhiteTurn = !isWhiteTurn;
                    }
                }
                catch (NumberFormatException e)
                {
                    System.out.println("You have entered a incorrect format: Row number must be an integer. e.g 'c2 c4'.");
                }
                catch(StringIndexOutOfBoundsException e)
                {
                    System.out.println("You have entered a incorrect format: move must be in the format 'from to', e.g. 'c3 d4'.");
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("You have entered a incorrect format: move must be between a - h, 1-8. e.g 'c2 c4'");
                }
            }
        }
    }
}