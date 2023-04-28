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
    
    //pawn piece constructor
    public Pawn(ChessPieceColour colour,int col, int row)
    {
        super(colour, col, row);
    }
    
    //return white pawn or black pawn symbol
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
    
    //return pawn's available moves (one square forward, early two squares advance, or one square diagonally forward for capturing)
    //move can be unavailable due to occupied square by any piece infront, or under pin.
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
        
        //if pawn is white, move row positively
        if(super.getColour() == ChessPieceColour.WHITE)
        {
            //one square forward
            row = super.getRow() + 1;
            if(row <= 7)
            {
                if(pieces.getPiece(super.getColumn(), row) == null)
                {
                    availableMoves[super.getColumn()][row] = true;
                    
                    //two squares advance
                    if(super.hasNotMoved())
                    {
                        if(pieces.getPiece(super.getColumn(), row+1) == null)
                        {
                            availableMoves[super.getColumn()][row+1] = true;
                        }
                    }
                }
            }

            //diagonal capture square
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
            
            //diagonal capture square
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
        
        //if pawn is black, move row negatively
        if(super.getColour() == ChessPieceColour.BLACK)
        {
            //one square forward
            row = super.getRow() - 1;
            if(row >= 0)
            {
                if(pieces.getPiece(super.getColumn(), row) == null)
                {
                    availableMoves[super.getColumn()][row] = true;
                    
                    //two squares advance
                    if(super.hasNotMoved())
                    {
                        if(pieces.getPiece(super.getColumn(), row-1) == null)
                        {
                            availableMoves[super.getColumn()][row-1] = true;
                        }
                    }
                }
            }

            //diagonal capture square
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
            
            //diagonal capture square
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
        
        //if pawn is under pin, then return the available moves within the pin path
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
    
    //return pawn's targeting squares (one square diagonally infront)
    //if pawn check the opponent king, send the check path to the PiecesOnBoard class for movement restriction.
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
        
        //if pawn is white, facing row positively
        if(super.getColour() == ChessPieceColour.WHITE)
        {
            col = super.getColumn() + 1;
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
            
            col = super.getColumn() - 1;
            row = super.getRow() + 1;
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
        }
        
        //if pawn is black, facing row negatively
        if(super.getColour() == ChessPieceColour.BLACK)
        {
            col = super.getColumn() + 1;
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
            
            col = super.getColumn() - 1;
            row = super.getRow() - 1;
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
        }
        
        return targetArea;
    }
}