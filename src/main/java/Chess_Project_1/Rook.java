/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class Rook extends Piece {
    
    //rook piece constructor
    public Rook(ChessPieceColour colour,int col, int row)
    {
        super(colour, col, row);
    }
    
    //return white rook or black rook symbol
    @Override
    public String getSymbol()
    {
        if (getColour() == ChessPieceColour.WHITE) 
        {
            return "wR";
        } 
        else
        {
            return "bR";
        }
    }
    
    //return rook's available moves (horizontally and vertically)
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
        
        //horizontal moves
        col = super.getColumn() +1;
        while(col <= 7)
        {
            if(pieces.getPiece(col, super.getRow()) == null)
            {
                availableMoves[col][super.getRow()] = true;
                col++;
            }
            else
            {
                if(pieces.getPiece(col, super.getRow()).getColour() != super.getColour())
                {
                    availableMoves[col][super.getRow()] = true;
                }
                break;
            }
        }
        
        //horizontal moves
        col = super.getColumn() -1;
        while(col >= 0)
        {
            if(pieces.getPiece(col, super.getRow()) == null)
            {
                availableMoves[col][super.getRow()] = true;
                col--;
            }
            else
            {
                if(pieces.getPiece(col, super.getRow()).getColour() != super.getColour())
                {
                    availableMoves[col][super.getRow()] = true;
                }
                break;
            }
        }
        
        //vertical moves
        row = super.getRow() +1;
        while(row <= 7)
        {
            if(pieces.getPiece(super.getColumn(), row) == null)
            {
                availableMoves[super.getColumn()][row] = true;
                row++;
            }
            else
            {
                if(pieces.getPiece(super.getColumn(), row).getColour() != super.getColour())
                {
                    availableMoves[super.getColumn()][row] = true;
                }
                break;
            }
        }
        
        //vertical moves
        row = super.getRow() -1;
        while(row >= 0)
        {
            if(pieces.getPiece(super.getColumn(), row) == null)
            {
                availableMoves[super.getColumn()][row] = true;
                row--;
            }
            else
            {
                if(pieces.getPiece(super.getColumn(), row).getColour() != super.getColour())
                {
                    availableMoves[super.getColumn()][row] = true;
                }
                break;
            }
        }
        
        //if rook is under pin, then return the available moves within the pin path
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
    
    //return rook's targeting squares (horizontally and vertically)
    //if rook pin the opponemt king, send the pin path to the piece that is under the pin and set its isUnderPin to true.
    //if rook check the opponent king, send the check path to the PiecesOnBoard class for movement restriction.
    @Override
    public boolean[][] getTargetArea()
    {
        PiecesOnBoard pieces = new PiecesOnBoard();
        int col;
        int row;
        int pinnedCol;
        int pinnedRow;
        
        boolean[][] targetArea = new boolean[8][8];
        for(boolean[] i : targetArea)
        {
            for(boolean j : i)
            {
                j = false;
            }
        }
        
        //horizontal target squares
        col = super.getColumn() +1;
        row = super.getRow();
        while(col <= 7)
        {
            if(pieces.getPiece(col, row) != null)
            {
                targetArea[col][row] = true;
                
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
                    int pathCol = super.getColumn();
                    int pathRow = super.getRow();
                    while(!(pathCol == col && pathRow == row))
                    {
                        checkPath[pathCol][pathRow] = true;
                        pathCol++;
                    }
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
                //if pin king
                else if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    pinnedCol = col;
                    pinnedRow = row;
                    col++;
                    
                    for(; col <= 7; col++)
                    {
                        if(pieces.getPiece(col, row) == null)
                        {
                            continue;
                        }
                        else if(pieces.getPiece(col, row).getColour() != super.getColour() 
                                && pieces.getPiece(col, row).getSymbol().contains("K"))
                        {
                            pieces.getPiece(pinnedCol, pinnedRow).setUnderPin(true);
                            
                            boolean[][] pinPath = new boolean[8][8];
                            for(boolean[] i : pinPath)
                            {
                                for(boolean j : i)
                                {
                                    j = false;
                                }
                            }
                            int pathCol = super.getColumn();
                            int pathRow = super.getRow();
                            while(!(pathCol == col && pathRow == row))
                            {
                                pinPath[pathCol][pathRow] = true;
                                pathCol++;
                            }
                            pieces.getPiece(pinnedCol, pinnedRow).setPinPath(pinPath);
                            break;
                        }
                        else
                        {
                            break;
                        }
                    }
                }
                break;
            }
            targetArea[col][row] = true;
            col++;
        }
        
        //horizontal target squares
        col = super.getColumn() -1;
        row = super.getRow();
        while(col >= 0)
        {
            if(pieces.getPiece(col, row) != null)
            {
                targetArea[col][row] = true;
                
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
                    int pathCol = super.getColumn();
                    int pathRow = super.getRow();
                    while(!(pathCol == col && pathRow == row))
                    {
                        checkPath[pathCol][pathRow] = true;
                        pathCol--;
                    }
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
                //if pin king
                else if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    pinnedCol = col;
                    pinnedRow = row;
                    col--;
                    
                    for(; col >= 0; col--)
                    {
                        if(pieces.getPiece(col, row) == null)
                        {
                            continue;
                        }
                        else if(pieces.getPiece(col, row).getColour() != super.getColour() 
                                && pieces.getPiece(col, row).getSymbol().contains("K"))
                        {
                            pieces.getPiece(pinnedCol, pinnedRow).setUnderPin(true);
                            
                            boolean[][] pinPath = new boolean[8][8];
                            for(boolean[] i : pinPath)
                            {
                                for(boolean j : i)
                                {
                                    j = false;
                                }
                            }
                            int pathCol = super.getColumn();
                            int pathRow = super.getRow();
                            while(!(pathCol == col && pathRow == row))
                            {
                                pinPath[pathCol][pathRow] = true;
                                pathCol--;
                            }
                            pieces.getPiece(pinnedCol, pinnedRow).setPinPath(pinPath);
                            break;
                        }
                        else
                        {
                            break;
                        }
                    }
                }
                break;
            }
            targetArea[col][row] = true;
            col--;
        }
        
        //vertical target squares
        col = super.getColumn();
        row = super.getRow() +1;
        while(row <= 7)
        {
            if(pieces.getPiece(col, row) != null)
            {
                targetArea[col][row] = true;
                
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
                    int pathCol = super.getColumn();
                    int pathRow = super.getRow();
                    while(!(pathCol == col && pathRow == row))
                    {
                        checkPath[pathCol][pathRow] = true;
                        pathRow++;
                    }
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
                //if pin king
                else if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    pinnedCol = col;
                    pinnedRow = row;
                    row++;
                    
                    for(;row <= 7; row++)
                    {
                        if(pieces.getPiece(col, row) == null)
                        {
                            continue;
                        }
                        else if(pieces.getPiece(col, row).getColour() != super.getColour() 
                                && pieces.getPiece(col, row).getSymbol().contains("K"))
                        {
                            pieces.getPiece(pinnedCol, pinnedRow).setUnderPin(true);
                            
                            boolean[][] pinPath = new boolean[8][8];
                            for(boolean[] i : pinPath)
                            {
                                for(boolean j : i)
                                {
                                    j = false;
                                }
                            }
                            int pathCol = super.getColumn();
                            int pathRow = super.getRow();
                            while(!(pathCol == col && pathRow == row))
                            {
                                pinPath[pathCol][pathRow] = true;
                                pathRow++;
                            }
                            pieces.getPiece(pinnedCol, pinnedRow).setPinPath(pinPath);
                            break;
                        }
                        else
                        {
                            break;
                        }
                    }
                }
                break;
            }
            targetArea[col][row] = true;
            row++;
        }
        
        //vertical target squares
        col = super.getColumn();
        row = super.getRow() -1;
        while(row >= 0)
        {
            if(pieces.getPiece(col, row) != null)
            {
                targetArea[col][row] = true;
                
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
                    int pathCol = super.getColumn();
                    int pathRow = super.getRow();
                    while(!(pathCol == col && pathRow == row))
                    {
                        checkPath[pathCol][pathRow] = true;
                        pathRow--;
                    }
                    pieces.setInCheck(pieces.getPiece(col, row).getColour(), checkPath);
                }
                //if pin king
                else if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    pinnedCol = col;
                    pinnedRow = row;
                    row--;
                    
                    for(;row >= 0; row--)
                    {
                        if(pieces.getPiece(col, row) == null)
                        {
                            continue;
                        }
                        else if(pieces.getPiece(col, row).getColour() != super.getColour() 
                                && pieces.getPiece(col, row).getSymbol().contains("K"))
                        {
                            pieces.getPiece(pinnedCol, pinnedRow).setUnderPin(true);
                            
                            boolean[][] pinPath = new boolean[8][8];
                            for(boolean[] i : pinPath)
                            {
                                for(boolean j : i)
                                {
                                    j = false;
                                }
                            }
                            int pathCol = super.getColumn();
                            int pathRow = super.getRow();
                            while(!(pathCol == col && pathRow == row))
                            {
                                pinPath[pathCol][pathRow] = true;
                                pathRow--;
                            }
                            pieces.getPiece(pinnedCol, pinnedRow).setPinPath(pinPath);
                            break;
                        }
                        else
                        {
                            break;
                        }
                    }
                }
                break;
            }
            targetArea[col][row] = true;
            row--;
        }
        
        return targetArea;
    }
}
