/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class Pawn extends Piece {
    private boolean firstMove = true;
    
    public Pawn(ChessPieceColour colour,int col, int row)
    {
        super(colour, col, row);
    }
    
    @Override
    public String getSymbol()
    {
        if (getColour() == ChessPieceColour.WHITE) 
        {
            return "wP";
        } 
        else
        {
            return "bP";
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
        
        if(super.getColour() == ChessPieceColour.WHITE)
        {
            row = super.getRow() + 1;
            if(row <= 7)
            {
                if(pieces.getPiece(super.getColumn(), row) == null)
                {
                    availableMoves[super.getColumn()][row] = true;
                }
            }

            row = super.getRow()+ 2;
            if(this.firstMove)
            {
                if(pieces.getPiece(super.getColumn(), row) == null)
                {
                    availableMoves[super.getColumn()][row] = true;
                }
            }
            
            col = super.getColumn() + 1;
            row = super.getRow() + 1;
            if(col <= 7 && row <= 7)
            {
                if(pieces.getPiece(col, row) != null 
                        && pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
            }
            
            col = super.getColumn() - 1;
            row = super.getRow() + 1;
            if(col >= 0 && row <= 7)
            {
                if(pieces.getPiece(col, row) != null 
                        && pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
            }
        }
        
        if(super.getColour() == ChessPieceColour.BLACK)
        {
            row = super.getRow() - 1;
            if(row >= 0)
            {
                if(pieces.getPiece(super.getColumn(), row) == null)
                {
                    availableMoves[super.getColumn()][row] = true;
                }
            }

            row = super.getRow() - 2;
            if(this.firstMove)
            {
                if(pieces.getPiece(super.getColumn(), row) == null)
                {
                    availableMoves[super.getColumn()][row] = true;
                }
            }
            
            col = super.getColumn() + 1;
            row = super.getRow() - 1;
            if(col <= 7 && row >= 0)
            {
                if(pieces.getPiece(col, row) != null 
                        && pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
            }
            
            col = super.getColumn() - 1;
            row = super.getRow() - 1;
            if(col >= 0 && row >= 0)
            {
                if(pieces.getPiece(col, row) != null 
                        && pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    availableMoves[col][row] = true;
                }
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
        
        if(super.getColour() == ChessPieceColour.WHITE)
        {
            col = super.getColumn() + 1;
            row = super.getRow() + 1;
            if(col <= 7 && row <= 7)
            {
                    targetArea[col][row] = true;
            }
            
            col = super.getColumn() - 1;
            row = super.getRow() + 1;
            if(col >= 0 && row <= 7)
            {
                    targetArea[col][row] = true;
            }
        }
        
        if(super.getColour() == ChessPieceColour.BLACK)
        {
            col = super.getColumn() + 1;
            row = super.getRow() - 1;
            if(col <= 7 && row >= 0)
            {
                    targetArea[col][row] = true;
            }
            
            col = super.getColumn() - 1;
            row = super.getRow() - 1;
            if(col >= 0 && row >= 0)
            {
                    targetArea[col][row] = true;
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
