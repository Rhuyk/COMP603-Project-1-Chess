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
                    
                    if(super.isFirstMove())
                    {
                        if(pieces.getPiece(super.getColumn(), row+1) == null)
                        {
                            availableMoves[super.getColumn()][row+1] = true;
                        }
                    }
                }
            }

            col = super.getColumn() + 1;
            row = super.getRow() + 1;
            if(col <= 7 && row <= 7)
            {
                if(pieces.getPiece(col, row) != null)
                {
                    if(pieces.getPiece(col, row).getColour() != super.getColour())
                    {
                        availableMoves[col][row] = true;
                    }
                }
            }
            
            col = super.getColumn() - 1;
            row = super.getRow() + 1;
            if(col >= 0 && row <= 7)
            {
                if(pieces.getPiece(col, row) != null)
                {
                    if(pieces.getPiece(col, row).getColour() != super.getColour())
                    {
                        availableMoves[col][row] = true;
                    }
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
                    
                    if(super.isFirstMove())
                    {
                        if(pieces.getPiece(super.getColumn(), row-1) == null)
                        {
                            availableMoves[super.getColumn()][row-1] = true;
                        }
                    }
                }
            }

            
            col = super.getColumn() + 1;
            row = super.getRow() - 1;
            if(col <= 7 && row >= 0)
            {
                if(pieces.getPiece(col, row) != null)
                {
                    if(pieces.getPiece(col, row).getColour() != super.getColour())
                    {
                        availableMoves[col][row] = true;
                    }
                }
            }
            
            col = super.getColumn() - 1;
            row = super.getRow() - 1;
            if(col >= 0 && row >= 0)
            {
                if(pieces.getPiece(col, row) != null)
                {
                    if(pieces.getPiece(col, row).getColour() != super.getColour())
                    {
                        availableMoves[col][row] = true;
                    }
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
        
        if(super.getColour() == ChessPieceColour.WHITE)
        {
            col = super.getColumn() + 1;
            row = super.getRow() + 1;
            if(col <= 7 && row <= 7)
            {
                targetArea[col][row] = true;
                
                if(pieces.getPiece(col, row) != null)
                {
                    if(pieces.getPiece(col, row).getColour() != super.getColour() && pieces.getPiece(col, row).getSymbol().contains("K"))
                    {
                        boolean[][] checkPath = new boolean[8][8];
                        for(boolean[] i : checkPath)
                        {
                            for(boolean j : i)
                            {
                                j = false;
                            }
                        }
                        checkPath[super.getColumn()][super.getRow()] = true;
                        pieces.setCheckPath(checkPath);
                    }
                }
            }
            
            col = super.getColumn() - 1;
            row = super.getRow() + 1;
            if(col >= 0 && row <= 7)
            {
                targetArea[col][row] = true;
                
                if(pieces.getPiece(col, row) != null)
                {
                    if(pieces.getPiece(col, row).getColour() != super.getColour() && pieces.getPiece(col, row).getSymbol().contains("K"))
                    {
                        boolean[][] checkPath = new boolean[8][8];
                        for(boolean[] i : checkPath)
                        {
                            for(boolean j : i)
                            {
                                j = false;
                            }
                        }
                        checkPath[super.getColumn()][super.getRow()] = true;
                        pieces.setCheckPath(checkPath);
                    }
                }
            }
        }
        
        if(super.getColour() == ChessPieceColour.BLACK)
        {
            col = super.getColumn() + 1;
            row = super.getRow() - 1;
            if(col <= 7 && row >= 0)
            {
                targetArea[col][row] = true;
                
                if(pieces.getPiece(col, row) != null)
                {
                    if(pieces.getPiece(col, row).getColour() != super.getColour() && pieces.getPiece(col, row).getSymbol().contains("K"))
                    {
                        boolean[][] checkPath = new boolean[8][8];
                        for(boolean[] i : checkPath)
                        {
                            for(boolean j : i)
                            {
                                j = false;
                            }
                        }
                        checkPath[super.getColumn()][super.getRow()] = true;
                        pieces.setCheckPath(checkPath);
                    }
                }
            }
            
            col = super.getColumn() - 1;
            row = super.getRow() - 1;
            if(col >= 0 && row >= 0)
            {
                targetArea[col][row] = true;
                
                if(pieces.getPiece(col, row) != null)
                {
                    if(pieces.getPiece(col, row).getColour() != super.getColour() && pieces.getPiece(col, row).getSymbol().contains("K"))
                    {
                        boolean[][] checkPath = new boolean[8][8];
                        for(boolean[] i : checkPath)
                        {
                            for(boolean j : i)
                            {
                                j = false;
                            }
                        }
                        checkPath[super.getColumn()][super.getRow()] = true;
                        pieces.setCheckPath(checkPath);
                    }
                }
            }
        }
        
        return targetArea;
    }
}