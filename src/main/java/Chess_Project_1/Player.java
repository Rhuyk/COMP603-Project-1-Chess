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

    /**
    * Method: getColourPiece
    * 
    * This method will return the player chess piece 'colour'.
    * 
    * @return 
    */ 
    
    public ChessPieceColour getColourPiece() 
    {
        return colourPiece;
    }

    /**
    * Method: getColourPiece
    * 
    * This method will modify the player chess piece 'colour'.
    * 
    * @param colourPiece
    */ 
    
    public void setColourPiece(ChessPieceColour colourPiece) 
    {
        this.colourPiece = colourPiece;
    }

    /**
    * Method: getPlayerName
    * 
    * This method will return the player name.
    * 
    * @return 
    */     
    
    public String getPlayerName() 
    {
        return playerName;
    }
 
    /**
    * Method: getColourPiece
    * 
    * This method will modify the player name.
    * 
    * @param playerName
    */    
    
    public void setPlayerName(String playerName) 
    {
        this.playerName = playerName;
    }
}