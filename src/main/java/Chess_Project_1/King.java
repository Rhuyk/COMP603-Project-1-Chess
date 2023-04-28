/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class King extends Piece {
    
    //king piece constructor
    public King(ChessPieceColour colour, int col, int row)
    {
        super(colour, col, row);
    }
    
    //return white king or black king symbol
    @Override
    public String getSymbol()
    {
        if (getColour() == ChessPieceColour.WHITE) 
        {
            return "wK";
        } 
        else
        {
            return "bK";
        }
    }
    
    //return king's available moves (the 8 squares around the king)
    //move can be unavailable due to the board boundary and the same colour pieces
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
        if(col <= 7)
        {
            if(pieces.getPiece(col, super.getRow()) == null)
            {
                availableMoves[col][super.getRow()] = true;
            }
            else
            {
                if(pieces.getPiece(col, super.getRow()).getColour() != super.getColour())
                {
                    availableMoves[col][super.getRow()] = true;
                }
            }
        }
                
        col = super.getColumn() -1;
        if(col >= 0)
        {
            if(pieces.getPiece(col, super.getRow()) == null)
            {
                availableMoves[col][super.getRow()] = true;
            }
            else
            {
                if(pieces.getPiece(col, super.getRow()).getColour() != super.getColour())
                {
                    availableMoves[col][super.getRow()] = true;
                }
            }
        }
        
        row = super.getRow() +1;
        if(row <= 7)
        {
            if(pieces.getPiece(super.getColumn(), row) == null)
            {
                availableMoves[super.getColumn()][row] = true;
            }
            else
            {
                if(pieces.getPiece(super.getColumn(), row).getColour() != super.getColour())
                {
                    availableMoves[super.getColumn()][row] = true;
                }
            }
        }
        
        row = super.getRow() -1;
        if(row >= 0)
        {
            if(pieces.getPiece(super.getColumn(), row) == null)
            {
                availableMoves[super.getColumn()][row] = true;
            }
            else
            {
                if(pieces.getPiece(super.getColumn(), row).getColour() != super.getColour())
                {
                    availableMoves[super.getColumn()][row] = true;
                }
            }
        }
        
        col = super.getColumn() +1;
        row = super.getRow() +1;
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
        
        col = super.getColumn() +1;
        row = super.getRow() -1;
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
        
        col = super.getColumn() -1;
        row = super.getRow() +1;
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
        
        col = super.getColumn() -1;
        row = super.getRow() -1;
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
        
        return availableMoves;
    }
    
    //return king's targeting squares (the 8 squares around the king)
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
        if(col <= 7)
        {
            targetArea[col][super.getRow()] = true;
        }
                
        col = super.getColumn() -1;
        if(col >= 0)
        {
            targetArea[col][super.getRow()] = true;
        }
        
        row = super.getRow() +1;
        if(row <= 7)
        {
            targetArea[super.getColumn()][row] = true;
        }
        
        row = super.getRow() -1;
        if(row >= 0)
        {
            targetArea[super.getColumn()][row] = true;
        }
        
        col = super.getColumn() +1;
        row = super.getRow() +1;
        if(col <= 7 && row <= 7)
        {
            targetArea[col][row] = true;
        }
        
        col = super.getColumn() +1;
        row = super.getRow() -1;
        if(col <= 7 && row >= 0)
        {
            targetArea[col][row] = true;
        }
        
        col = super.getColumn() -1;
        row = super.getRow() +1;
        if(col >= 0 && row <= 7)
        {
            targetArea[col][row] = true;
        }
        
        col = super.getColumn() -1;
        row = super.getRow() -1;
        if(col >= 0 && row >= 0)
        {
            targetArea[col][row] = true;
        }
        
        return targetArea;
    }
}
