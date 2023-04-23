/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class Player {
    
    private ChessPieceColour colourPiece;
    private String playerName;
    
    public Player(ChessPieceColour colourPiece,String playerName)
    {
        this.playerName = playerName;
        this.colourPiece = colourPiece;
    }

    public ChessPieceColour getColourPiece() 
    {
        return colourPiece;
    }

    public void setColourPiece(ChessPieceColour colourPiece) 
    {
        this.colourPiece = colourPiece;
    }

    public String getPlayerName() 
    {
        return playerName;
    }
 
    public void setPlayerName(String playerName) 
    {
        this.playerName = playerName;
    }
}
