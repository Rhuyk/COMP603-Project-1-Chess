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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ChessBoardFileIO {
    
    public static void saveGameForUser(String username, PiecesOnBoard board) throws FileNotFoundException, IOException 
    {
    String filename = "chessData.txt";
    boolean overwrite = false;
    String line;
    StringBuilder fileDataBuilder = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
    {
        boolean userFound = false;

        while ((line = reader.readLine()) != null) 
        {
            if (username.equals(line)) 
            {
                System.out.println("Player Name already exists in the file.");
                System.out.print("Do you wish to overwrite (Y/N): ");
                Scanner scanner = new Scanner(System.in);
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("Y")) 
                {
                    overwrite = true;
                } 
                else 
                {
                    System.out.println("Game not saved.");
                    return;
                }
            }
        }
        } 
        catch (IOException e) 
        {
            System.out.println("Error: Game file could not be loaded!");
        }

        if(overwrite) 
        {
            String fileData = fileDataBuilder.toString();
            String[] lines = fileData.split("\n");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) 
            {
                for (String fileLine : lines) 
                {
                    if (!fileLine.startsWith(username)) 
                    {
                        writer.write(fileLine + "\n");
                    }
                }
            } 
            catch (IOException e) 
            {
                System.out.println("Error deleting user data from file: " + filename);
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) 
        {
            writer.write(username + "\n");

            for (int row = 0; row < 8; row++) 
            {
                for (int col = 0; col < 8; col++) 
                {
                    Piece piece = board.getPiece(col, row);
                    if (piece != null) 
                    {
                        String symbol = piece.getSymbol();
                        writer.write(symbol + " " + row + " " + col + "\n");
                    }
                }
            }
            writer.write("###\n");

            System.out.println("Game saved successfully to file: " + filename);
        } 
        catch (IOException e) 
        {
            System.out.println("Error saving game: " + filename);
        }
    }

    public static PiecesOnBoard loadGame(String username) 
    {
        String filename = "chessData.txt";

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
                    board.clear();
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
                        board.setPiece(col, row, piece);
                    }
                }
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