/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author User
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChessBoardFileIO {
    
    private static String createGameFile(String username)
    {
        String filename = username + "_chessData.txt";
        File file = new File(filename);
        try 
        {
            if (!file.exists()) 
            {
                file.createNewFile();
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error in creating a new file.");
        }
        
        return filename;
    }
    
    public static void saveGameForUser(String username, PiecesOnBoard board)
    {
        String filename = createGameFile(username);
        boolean overwrite = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
        {
            
            boolean userFound = checkIfUserExists(username, reader);

            if (userFound) 
            {
                overwrite = getOverwriteChoice();
                if (!overwrite) 
                {
                    System.out.println("Game not saved.");
                    return;
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Chess game can not be loaded!");
            return;
        }

        if(overwrite) 
        {
            try {
                deleteUserDataFromFile(username, filename);
            } catch (IOException ex) {
                Logger.getLogger(ChessBoardFileIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        saveUserDataToFile(username, board);
    }
    
    public static void saveUserDataToFile(String username, PiecesOnBoard board)
    {
        
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(username + "_chessData.txt"))) 
        {
            writer.println(username);
                
            for (int row = 0; row < 8; row++) 
            {
                for (int col = 0; col < 8; col++) 
                {
                    Piece piece = board.getPiece(col, row);
                    if (piece != null)
                    {
                        String symbol = piece.getSymbol();
                        writer.println(symbol + " " + row + " " + col);
                    }
                }
            }
            writer.println("###");
            System.out.println("Game saved successfully to file!");
            writer.close();
        }  
        catch (IOException e) 
        {
            System.out.println("Chess game can not be saved!");
        }
    }

    public static PiecesOnBoard loadGame(String username) 
    {
        String filename = username + "_chessData.txt";
        PiecesOnBoard board = new PiecesOnBoard();
        
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filename));
            String line;
            StringBuilder gameDataBuilder = new StringBuilder();

            boolean userFound = false;
            while ((line = reader.readLine()) != null) 
            {
                if(username.equals(line))
                {
                    userFound = true;
                }

                if (line.equals("###")) 
                {
                    board.clearBoard();
                    board.clearAllPieces();
                    userFound = true;
                    String gameData = gameDataBuilder.toString();
                    String[] lines = gameData.split("\n");
                    
                    for (String gameLine : lines) 
                    {
                        String[] parts = gameLine.split(" ");
                        if (parts.length >= 3) 
                        { 
                            String symbol = parts[0];
                            int row = Integer.parseInt(parts[1]);
                            int col = Integer.parseInt(parts[2]);

                        Piece piece = createPiece(symbol, col, row);
                        board.addPiece(col, row, piece);
                        }
                    }
                    board.resfreshBoard();
                    gameDataBuilder = new StringBuilder();
                    userFound = false;
                } 
                else 
                {
                    gameDataBuilder.append(line).append("\n");
                }
            }

            System.out.println("Game file has been loaded!");
        } 
        catch (IOException e) 
        {
            System.out.println("Error: Game file could not be loaded!");
        }
        
        return board;
    }

    private static boolean checkIfUserExists(String username, BufferedReader reader) throws IOException 
    {
        String line;
        
        while ((line = reader.readLine()) != null) 
        {
            if (username.equals(line)) 
            {
                System.out.println("Player Name already exists in the file.");
                return true;
            }
        }

        return false;
    }

    private static boolean getOverwriteChoice() 
    {
        System.out.print("Do you wish to overwrite (Y/N): ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        return choice.equalsIgnoreCase("Y");
    }

    private static void deleteUserDataFromFile(String username, String filename) throws IOException 
    {
        String fileData = "";
        String[] lines = fileData.split("\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) 
        {
            for (String fileLine : lines) 
            {
                writer.write(fileLine + "\n");
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Chess game can not be deleted: !" + filename);
        }
    }
    
    private static Piece createPiece(String symbol,int column,int row) {
        switch (symbol) {
            case "wP":
                return new Pawn(ChessPieceColour.WHITE,column,row);
            case "wR":
                return new Rook(ChessPieceColour.WHITE,column,row);
            case "wN":
                return new Knight(ChessPieceColour.WHITE,column,row);
            case "wB":
                return new Bishop(ChessPieceColour.WHITE,column,row);
            case "wQ":
                return new Queen(ChessPieceColour.WHITE,column,row);
            case "wK":
                return new King(ChessPieceColour.WHITE,column,row);
            case "bP":
                return new Pawn(ChessPieceColour.BLACK,column,row);
            case "bR":
                return new Rook(ChessPieceColour.BLACK,column,row);
            case "bN":
                return new Knight(ChessPieceColour.BLACK,column,row);
            case "bB":
                return new Bishop(ChessPieceColour.BLACK,column,row);
            case "bQ":
                return new Queen(ChessPieceColour.BLACK,column,row);
            case "bK":
                return new King(ChessPieceColour.BLACK,column,row);
            default:
                return null;
        }
    }
}