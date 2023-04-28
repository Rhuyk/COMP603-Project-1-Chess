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
    
    //knight piece constructor
    public Knight(ChessPieceColour colour,int col, int row)
    {
        super(colour, col, row);
    }
    
    //return white knight or black knight symbol
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
    
    //return knight's available moves (L-patterned move)
    //move can be unavailable due to the board boundary, the same colour pieces, or under pin.
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
                if(pieces.getPiece(col, row).getColour() != super.getColour())
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
        
        //if knight is under pin, then there is no available move for knight
        if(super.isUnderPin())
        {
            boolean[][] newAvailableMoves = new boolean[8][8];
            for(boolean[] i : newAvailableMoves)
            {
                for(boolean j : i)
                {
                    j = false;
                }
            }

            return newAvailableMoves;
        }
        
        return availableMoves;
    }
    
    //return knight's targeting squares (L-patterned)
    //if knight check the opponent king, send the check path to the PiecesOnBoard class for movement restriction.
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
            
            if(pieces.getPiece(col, row) != null)
            {
                //if check king
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
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
            }
        }
                
        col = super.getColumn() + 2;
        row = super.getRow() - 1;
        if(col <= 7 && row >= 0)
        {
            targetArea[col][row] = true;
            
            if(pieces.getPiece(col, row) != null)
            {
                //if check king
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
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
            }
        }
        
        col = super.getColumn() + 1;
        row = super.getRow() + 2;
        if(col <= 7 && row <= 7)
        {
            targetArea[col][row] = true;
            
            if(pieces.getPiece(col, row) != null)
            {
                //if check king
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
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
            }
        }
        
        col = super.getColumn() + 1;
        row = super.getRow() - 2;
        if(col <= 7 && row >= 0)
        {
            targetArea[col][row] = true;
            
            if(pieces.getPiece(col, row) != null)
            {
                //if check king
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
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
            }
        }
        
        col = super.getColumn() - 1;
        row = super.getRow() + 2;
        if(col >= 0 && row <= 7)
        {
            targetArea[col][row] = true;
            
            if(pieces.getPiece(col, row) != null)
            {
                //if check king
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
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
            }
        }
        
        col = super.getColumn() - 1;
        row = super.getRow() - 2;
        if(col >= 0 && row >= 0)
        {
            targetArea[col][row] = true;
            
            if(pieces.getPiece(col, row) != null)
            {
                //if check king
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
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
            }
        }
        
        col = super.getColumn() - 2;
        row = super.getRow() + 2;
        if(col >= 0 && row <= 7)
        {
            targetArea[col][row] = true;
            
            if(pieces.getPiece(col, row) != null)
            {
                //if check king
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
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
            }
        }
        
        col = super.getColumn() - 2;
        row = super.getRow() - 2;
        if(col >= 0 && row >= 0)
        {
            targetArea[col][row] = true;
            
            if(pieces.getPiece(col, row) != null)
            {
                //if check king
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
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
            }
        }
        
        return targetArea;
    }
}
