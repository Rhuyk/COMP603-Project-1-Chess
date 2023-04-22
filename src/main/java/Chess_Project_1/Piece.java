/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class Piece {
    
    private int row;
    private int column;
    private ChessPieceColour colour;
    
    public Piece(ChessPieceColour colour, int col, int row)
    {
        this.colour = colour;
        this.column = col;
        this.row = row;
    }
    
    public int getColumn()
    {
        return this.column;
    }
    
    public void setColumn(int col)
    {
        this.column = col;
    }
    
    public int getRow()
    {
        return this.row;
    }
    
    public void setRow(int row)
    {
        this.row = row;
    }
    
    public ChessPieceColour getColour()
    {
        return colour;
    }
    
    public String getSymbol()
    {
        return "?";
    }
    
    public boolean[][] getAvailableMoves()
    {
        boolean[][] availableMoves = new boolean[8][8];
        for(boolean[] i : availableMoves)
        {
            for(boolean j : i)
            {
                j = false;
            }
        }
        return availableMoves;
    }
    
    public boolean[][] getTargetArea()
    {
        boolean[][] targetArea = new boolean[8][8];
        for(boolean[] i : targetArea)
        {
            for(boolean j : i)
            {
                j = false;
            }
        }
        return targetArea;
    }
    
    public void setFirstMove()
    {
        
    }
}