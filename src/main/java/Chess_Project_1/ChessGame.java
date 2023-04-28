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
    
    private static String moveHistory = "";
    
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
        
        Player player1 = new Player(ChessPieceColour.WHITE,"Default1");
        Player player2 = new Player(ChessPieceColour.BLACK,"Default2");
        boolean isWhiteTurn = true;
        
        System.out.println("Welcome to our chess game! ");
        System.out.println("Created by Feng-Min Hu and Prom Jack Sirisukha for COMP603");
        
        while(true)
        {
            System.out.print("\nPlayer 1, Please enter your name: ");
            String player1Name = scanner.nextLine();
            
            if(player1Name.isBlank())
            {
                System.out.println("Invalid input! Please try again, username cannot be empty");
                continue;
            }
                
            System.out.print("\n"+player1Name + ", What colour would you like to play? (white or black): ");
            String piece1Colour = scanner.nextLine();
            
            if(piece1Colour.equalsIgnoreCase("black"))
            {
                player1 = new Player(ChessPieceColour.BLACK,player1Name);
            }
            else if(piece1Colour.equalsIgnoreCase("white"))
            {
                player1 = new Player(ChessPieceColour.WHITE,player1Name);
            }
            else
            {
                
                System.out.println("Invalid input! Colour has to be either black or white");
                continue;
            }
            
            System.out.print("\nPlayer 2, Please enter your name: ");
            String player2Name = scanner.nextLine();
            if(player2Name.isBlank())
            {
                System.out.println("Invalid input! Please try again, username cannot be empty.");
                continue;
            }
            System.out.println();
            
            if(player1.getColourPiece() != ChessPieceColour.BLACK)
            {
                player2 = new Player(ChessPieceColour.BLACK,player2Name);
            }
            else if(player1.getColourPiece() != ChessPieceColour.WHITE)
            {
                player2 = new Player(ChessPieceColour.WHITE,player2Name);
            }
            
            break;
        }
        
        moveHistory += player1.getPlayerName() + " is playing the " + player1.getColourPiece() + " chess pieces \n";
        moveHistory += player2.getPlayerName() + " is playing the " + player2.getColourPiece() + " chess pieces \n\n";
        
        System.out.println("Enter 'quit' to leave anytime.");
        System.out.println("Enter 'save' to save your chess game.");
        System.out.println("Enter 'load' to load your saved game data.");
        System.out.println("Enter 'resign' to resign the game.");
        System.out.println("Enter 'draw' to ask for a draw.");
        
        System.out.println("");
        
        while(true)
        {
            Player currentPlayer = player1;
            if(!isWhiteTurn)
            {
                currentPlayer = player2;
            }
                
            printBoard(board, currentPlayer);
            
            if(board.isStalemate(currentPlayer.getColourPiece()))
            {
                System.out.println("Board is a stalement.");
                break;
            }
            
            if(board.isCheckmate(currentPlayer.getColourPiece()))
            {
                System.out.println(currentPlayer.getPlayerName() + " has been check mated.");
                System.out.println("This game has offically ended.");
                
                isWhiteTurn = !isWhiteTurn;
                
                if(!isWhiteTurn)
                {
                    currentPlayer = player2;
                }
                else
                {
                    currentPlayer = player1;
                }
                
                System.out.println(currentPlayer.getPlayerName() + " is the winner.");
                break;
            }
            
            System.out.println(currentPlayer.getPlayerName()+" Enter your chess move(e.g 'c2 c4'): ");
            String chessMove = scanner.nextLine();
            
            if(chessMove.equalsIgnoreCase("save"))
            {
                if(ChessBoardFileIO.saveGameForUser(currentPlayer.getPlayerName(),board))
                {
                    ChessBoardFileIO.saveMovesToText(moveHistory);
                }
            }
            
            else if(chessMove.equalsIgnoreCase("resign"))
            {
                System.out.println(currentPlayer.getPlayerName() + " has resigned.");
                break;
            }
            
            else if(chessMove.equalsIgnoreCase("draw"))
            {
                System.out.println(currentPlayer.getPlayerName() + " has suggested a draw.");
                
                isWhiteTurn = !isWhiteTurn;
                
                if(!isWhiteTurn)
                {
                    currentPlayer = player2;
                }
                else
                {
                    currentPlayer = player1;
                }
                
                
                System.out.print(currentPlayer.getPlayerName() + " do you accept? (Y/N): ");
                String drawChoice = scanner.nextLine();
                
                if(drawChoice.equalsIgnoreCase("Y"))
                {
                    System.out.println(currentPlayer.getPlayerName() + " has accepted a draw.");
                    System.out.println("This game has offically ended as a draw.");
                    break;
                }
                else if(drawChoice.equalsIgnoreCase("N"))
                {
                    System.out.println(currentPlayer.getPlayerName() + " has rejected a draw.");
                    System.out.println("This game will continue.");
                }
                else
                {
                    System.out.println("Invalid input.");
                    System.out.println("This game will continue.");
                }
                isWhiteTurn = !isWhiteTurn;
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
                    
                    if(board.movePiece(fromCol, fromRow, toCol, toRow))
                    {
                        moveHistory += currentPlayer.getPlayerName() + " has moved " + chessMove + "\n";
                        System.out.println(currentPlayer.getPlayerName() +" moved "+ chessMove +"\n");
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
                    System.out.println("You have entered a incorrect format: move must be between a - h, 1-8. e.g 'c2 c4'.");
                }
            }
        }
        System.out.println("Thank you for playing our chess game!");
    }
}
