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
            deleteUserDataFromFile(username, filename);
        }

        saveUserDataToFile(username, board);
    }
    
    public static void saveUserDataToFile(String username, PiecesOnBoard board)
    {
        String filename = "chessdata.txt";
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
            System.out.println("Chess game can not be saved!" + filename);
        }
    }

    public static PiecesOnBoard loadGame(String username) 
    {
        String filename = "chessData.txt";

        PiecesOnBoard board = new PiecesOnBoard();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            boolean userFound = false;
            String gameData = "";
            String line;

            while ((line = reader.readLine()) != null) 
            {
                if(username.equals(line))
                {
                    userFound = true;
                }

                if (line.equals("###")) 
                {
                    if (userFound) 
                    {
                        System.out.println("Game file has been loaded!");
                        return board;
                    }
                    gameData = "";
                    userFound = false;
                } 
                else 
                {
                    gameData += line + "\n";
                }
            }

            System.out.println("Game file has been loaded!");
        } 
        catch (IOException e) 
        {
            System.out.println("Chess game can not be loaded!");
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
        StringBuilder fileDataBuilder = new StringBuilder();
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
            System.out.println("Chess game can not be deleted: !" + filename);
        }
    }
}