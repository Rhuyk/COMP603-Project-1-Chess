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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class ChessBoardFileIO {
    
    private static String createGameText(String currentPlayer)
    {
        String filename = currentPlayer + "_chessData.txt";
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
    
    public static boolean saveGameForUser(String currentPlayer,Player player1, Player player2, PiecesOnBoard board)
    {
        String filename = createGameText(currentPlayer);
        boolean overwrite;
        HashMap<String,String> chessData = new HashMap();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
        {
            boolean userFound = checkForUser(player1.getPlayerName(), reader);

            if (userFound) 
            {
                overwrite = getOverwriteOption();
                if (!overwrite) 
                {
                    System.out.println("Game not saved.");
                    return false;
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Chess game can not be loaded!");
            return false;
        }

        saveUserDataToText(currentPlayer,player1, player2, board,chessData);
        return true;
    }
    
    public static void saveUserDataToText(String currentPlayer,Player player1, Player player2, PiecesOnBoard board,HashMap<String, String> chessData)
    {
        
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(currentPlayer + "_chessData.txt"))) 
        {
            writer.println(player1.getPlayerName());
            writer.println(player2.getPlayerName());
            
            for (int row = 0; row < 8; row++) 
            {
                for (int col = 0; col < 8; col++) 
                {
                    Piece piece = board.getPiece(col, row);
                    if (piece != null)
                    {
                        String symbol = piece.getSymbol();
                        String position = String.valueOf(col) + " " +String.valueOf(row);
                        chessData.put(position, symbol);
                    }
                }
            }
            for (Map.Entry<String, String> entry : chessData.entrySet()) 
            {
                String position = entry.getKey();
                String symbol = entry.getValue();
                writer.println(symbol + " " + position);
            }
            
                    
            writer.println("###");
            System.out.println("Game saved successfully to file!");
            writer.close();
        }  
        catch (IOException e) 
        {
            System.out.println("Chess game can not be saved!");
        }
        catch (NullPointerException e)
        {
            System.out.println("Error saving player names!");
        }
    }
    
    public static void saveMovesToText(String moves)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("chessMoves.txt")))
        {
            writer.write(moves);
            System.out.println("Game moves successfully to file!");
            writer.close();
        }
        catch (IOException e) 
        {
            System.out.println("Chess game can not be saved!");
        }
    }
    
    public static PiecesOnBoard loadGame(String player1, Player current) 
    {
        String filename = player1 + "_chessData.txt";
        PiecesOnBoard board = new PiecesOnBoard();
        Set<String> uniquePiece = new HashSet<>();

        try 
        {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filename));
            String line;
            StringBuilder gameDataBuilder = new StringBuilder();

            String line2 = reader.readLine();
            if(player1.equals(line2)) 
            {
                current.setColourPiece(ChessPieceColour.WHITE);
            }
            line2 = reader.readLine();
            if(player1.equals(line2)) 
            {
                current.setColourPiece(ChessPieceColour.BLACK);
            }

            while ((line = reader.readLine()) != null) 
            {
                if (line.equals("###")) 
                {
                    board.clearBoard();
                    board.clearAllPieces();
                    String gameData = gameDataBuilder.toString();
                    StringTokenizer tokenizer = new StringTokenizer(gameData, "\n");

                    while (tokenizer.hasMoreTokens()) {
                        String gameLine = tokenizer.nextToken();
                        StringTokenizer lineTokenizer = new StringTokenizer(gameLine, " ");
                        if (lineTokenizer.countTokens() >= 3) {
                            String symbol = lineTokenizer.nextToken();
                            int col = Integer.parseInt(lineTokenizer.nextToken());
                            int row = Integer.parseInt(lineTokenizer.nextToken());
                            String location = col + "," + row;

                            if (!uniquePiece.contains(location)) {
                                Piece piece = createPiece(symbol, col, row);
                                board.addPiece(col, row, piece);
                                uniquePiece.add(location);
                            }
                        }
                    }

                    board.refreshBoard();
                    gameDataBuilder = new StringBuilder();
                    uniquePiece.clear();
                } 
                else 
                {
                    gameDataBuilder.append(line).append("\n");
                }
            }
                System.out.println("Game file has been loaded!");
            } catch (IOException e) {
                System.out.println("Error: Game file could not be loaded!");
            }

        return board;
    }


    private static boolean checkForUser(String player1, BufferedReader reader) throws IOException 
    {
        String line;
        
        while ((line = reader.readLine()) != null) 
        {
            if (player1.equals(line)) 
            {
                System.out.println("Player Name already exists in the file.");
                return true;
            }
        }

        return false;
    }

    private static boolean getOverwriteOption() 
    {
        System.out.print("Do you wish to overwrite (Y/N): ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        return choice.equalsIgnoreCase("Y");
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