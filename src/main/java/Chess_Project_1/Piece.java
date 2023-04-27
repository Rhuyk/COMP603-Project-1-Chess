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
    private boolean firstMove;
    private boolean wasFirstMove;
    private int lastmoveNum;
    private boolean isPinning;
    private boolean isUnderPinned;
    
    public Piece(ChessPieceColour colour, int col, int row)
    {
        this.colour = colour;
        this.column = col;
        this.row = row;
        this.firstMove = true;
        this.wasFirstMove = false;
        this.lastmoveNum = 0;
    }
    
    public int getColumn()
    {
        return this.column;
    }
    
    public int getRow()
    {
        return this.row;
    }
    
    public void setColAndRow(int col, int row)
    {
        this.column = col;
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
    
    public int getLastMoveNum()
    {
        return this.lastmoveNum;
    }
    
    public void setLastMoveNum(int num)
    {
        this.lastmoveNum = num;
    }
    
    public void setFirstMove()
    {
        if(firstMove)
        {
            this.wasFirstMove = true;
        }
        else
        {
            this.wasFirstMove = false;
        }
        this.firstMove = false;
    }
    
    public boolean isFirstMove()
    {
        return this.firstMove;
    }
    
    public boolean isWasFirstMove()
    {
        return this.wasFirstMove;
    }
    
    public void checkPin()
    {

    }
    
    public void setIsUnderPinned(boolean is)
    {
        this.isUnderPinned = is;
    }
    
    public boolean isUnderPinned()
    {
        return this.isUnderPinned;
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
    
    public boolean isWhite()
    {
        return getColour() == ChessPieceColour.WHITE;
    }
}