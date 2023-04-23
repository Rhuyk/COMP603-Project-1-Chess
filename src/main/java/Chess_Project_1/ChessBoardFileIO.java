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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ChessBoardFileIO {
    
    public static void saveGame(ChessBoard board, String filename) 
    {
        if(!filename.endsWith(".txt"))
        {
            filename+= ".txt";
        }
        
        try 
        {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(filename));
            
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
            writer.close();
            System.out.println("Game saved successfully to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving game: " + filename);
        }
    }
    
    public static void saveGameForUser(String username, ChessBoard board, String filename) 
    {
        if(!filename.endsWith(".txt"))
        {
            filename+= ".txt";
        }
        
        try 
        {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(filename));

            writer.write(username + "\n");

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Piece piece = board.getPiece(col, row);
                    if (piece != null) {
                        String symbol = piece.getSymbol();
                        writer.write(symbol + " " + row + " " + col + "\n");
                    }
                }
            }
            writer.close();
            System.out.println("Game saved successfully to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving game: " + filename);
        }
    }

    public static ChessBoard loadGame(String username, String filename) 
    {
        ChessBoard board = new ChessBoard();
        board.clear();
        
        if(!filename.endsWith(".txt"))
        {
            filename+= ".txt";
        }
        
        try 
        {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filename));
            String line;
            
            String fileUsername = reader.readLine();
            if(!username.equals(fileUsername))
            {
                System.out.println("You are not authorised to access this file!");
                return null;
            }
            
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(" ");
                String symbol = parts[0];
                int row = Integer.parseInt(parts[1]);
                int col = Integer.parseInt(parts[2]);
                
                Piece piece = createPiece(symbol,col,row);
                board.pieces.setPiece(col,row,piece);
            }
            
            
            reader.close();
            System.out.println("Game File has been loaded!");
        } 
        catch (IOException e) 
        {
            System.out.println("Error Game file could not be loaded!");
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