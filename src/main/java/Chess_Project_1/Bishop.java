/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class Bishop extends Piece{
    private boolean firstMove = true;
    
    public Bishop(ChessPieceColour colour,int col, int row)
    {
        super(colour, col, row);
    }
    
    @Override
    public String getSymbol()
    {
        if (getColour() == ChessPieceColour.WHITE) 
        {
            return "wB";
        } 
        else
        {
            return "bB";
        }
    }
    
    @Override
    public boolean[][] getAvailableMoves()
    {
        PiecesOnBoard pieces = new PiecesOnBoard();
        int col;
        int row;
        
        boolean[][] availableMoves = new boolean[8][8];
        for(boolean[] i : availableMoves)
        {
            for(boolean j : i)
            {
                j = false;
            }
        }
        
        col = super.getColumn() +1;
        row = super.getRow() +1;
        while(col <= 7 && row <= 7)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
                col++;
                row++;
            }
            else
            {
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
                break;
            }
        }
        
        col = super.getColumn() +1;
        row = super.getRow() -1;
        while(col <= 7 && row >= 0)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
                col++;
                row--;
            }
            else
            {
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
                break;
            }
        }
        
        col = super.getColumn() -1;
        row = super.getRow() +1;
        while(col >= 0 && row <= 7)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
                col--;
                row++;
            }
            else
            {
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
                break;
            }
        }
        
        col = super.getColumn() -1;
        row = super.getRow() -1;
        while(col >= 0 && row >= 0)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
                col--;
                row--;
            }
            else
            {
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
                break;
            }
        }
        
        return availableMoves;
    }
    
    @Override
    public boolean[][] getTargetArea()
    {
        PiecesOnBoard pieces = new PiecesOnBoard();
        int col;
        int row;
        
        boolean[][] targetArea = new boolean[8][8];
        for(boolean[] i : targetArea)
        {
            for(boolean j : i)
            {
                j = false;
            }
        }
        
        col = super.getColumn() +1;
        row = super.getRow() +1;
        while(col <= 7 && row <= 7)
        {
            if(pieces.getPiece(col, row) != null)
            {
                targetArea[col][row] = true;
                break;
            }
            targetArea[col][row] = true;
            col++;
            row++;
        }
        
        col = super.getColumn() +1;
        row = super.getRow() -1;
        while(col <= 7 && row >= 0)
        {
            if(pieces.getPiece(col, row) != null)
            {
                targetArea[col][row] = true;
                break;
            }
            targetArea[col][row] = true;
            col++;
            row--;
        }
        
        col = super.getColumn() -1;
        row = super.getRow() +1;
        while(col >= 0 && row <= 7)
        {
            if(pieces.getPiece(col, row) != null)
            {
                targetArea[col][row] = true;
                break;
            }
            targetArea[col][row] = true;
            col--;
            row++;
        }
        
        col = super.getColumn() -1;
        row = super.getRow() -1;
        while(col >= 0 && row >= 0)
        {
                targetArea[col][row] = true;
                col--;
                row--;
                if(pieces.getPiece(col, row) != null)
                {
                    targetArea[col][row] = true;
                    break;
                }
        }
        
        return targetArea;
    }
    
    @Override
    public void setFirstMove()
    {
        this.firstMove = false;
    }
}