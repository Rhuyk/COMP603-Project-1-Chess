/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class Knight extends Piece {
    
    public Knight(ChessPieceColour colour,int col, int row)
    {
        super(colour, col, row);
    }
    
    @Override
    public String getSymbol()
    {
        if (getColour() == ChessPieceColour.WHITE) 
        {
            return "wN";
        } 
        else
        {
            return "bN";
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
        
        col = super.getColumn() + 2;
        row = super.getRow() + 1;
        if(col <= 7 && row <= 7)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
            }
            else
            {
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
            }
        }
                
        col = super.getColumn() + 2;
        row = super.getRow() - 1;
        if(col <= 7 && row >= 0)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
            }
            else
            {
                if(pieces.getPiece(col, super.getRow()).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
            }
        }
        
        col = super.getColumn() + 1;
        row = super.getRow() + 2;
        if(col <= 7 && row <= 7)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
            }
            else
            {
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
            }
        }
        
        col = super.getColumn() + 1;
        row = super.getRow() - 2;
        if(col <= 7 && row >= 0)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
            }
            else
            {
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
            }
        }
        
        col = super.getColumn() - 1;
        row = super.getRow() + 2;
        if(col >= 0 && row <= 7)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
            }
            else
            {
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
            }
        }
        
        col = super.getColumn() - 1;
        row = super.getRow() - 2;
        if(col >= 0 && row >= 0)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
            }
            else
            {
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
            }
        }
        
        col = super.getColumn() - 2;
        row = super.getRow() + 2;
        if(col >= 0 && row <= 7)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
            }
            else
            {
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
            }
        }
        
        col = super.getColumn() - 2;
        row = super.getRow() - 2;
        if(col >= 0 && row >= 0)
        {
            if(pieces.getPiece(col, row) == null)
            {
                availableMoves[col][row] = true;
            }
            else
            {
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
            }
        }
        
        if(super.isUnderPinned())
        {
            boolean[][] newAvailableMoves = new boolean[8][8];
            for(boolean[] i : newAvailableMoves)
            {
                for(boolean j : i)
                {
                    j = false;
                }
            }
            
            for(col = 0; col < 8; col++)
            {
                for(row = 0; row < 8; row++)
                {
                    if(super.getPinPath()[col][row] && availableMoves[col][row])
                    {
                        newAvailableMoves[col][row] = true;
                    }
                }
            }
            
            return newAvailableMoves;
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
        
        col = super.getColumn() + 2;
        row = super.getRow() + 1;
        if(col <= 7 && row <= 7)
        {
            targetArea[col][row] = true;
        }
                
        col = super.getColumn() + 2;
        row = super.getRow() - 1;
        if(col <= 7 && row >= 0)
        {
            targetArea[col][row] = true;
        }
        
        col = super.getColumn() + 1;
        row = super.getRow() + 2;
        if(col <= 7 && row <= 7)
        {
            targetArea[col][row] = true;
        }
        
        col = super.getColumn() + 1;
        row = super.getRow() - 2;
        if(col <= 7 && row >= 0)
        {
            targetArea[col][row] = true;
        }
        
        col = super.getColumn() - 1;
        row = super.getRow() + 2;
        if(col >= 0 && row <= 7)
        {
            targetArea[col][row] = true;
        }
        
        col = super.getColumn() - 1;
        row = super.getRow() - 2;
        if(col >= 0 && row >= 0)
        {
            targetArea[col][row] = true;
        }
        
        col = super.getColumn() - 2;
        row = super.getRow() + 2;
        if(col >= 0 && row <= 7)
        {
            targetArea[col][row] = true;
        }
        
        col = super.getColumn() - 2;
        row = super.getRow() - 2;
        if(col >= 0 && row >= 0)
        {
            targetArea[col][row] = true;
        }
        
        return targetArea;
    }
}
