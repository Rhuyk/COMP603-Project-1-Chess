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
        
        if(super.getIsUnderPinned())
        {
            boolean[][] newAvailableMoves = new boolean[8][8];
            for(boolean[] i : newAvailableMoves)
            {
                for(boolean j : i)
                {
                    j = false;
                }
            }
            
            int index1 = 0;
            for(boolean[] i : newAvailableMoves)
            {
                for(boolean[] j : newAvailableMoves)
                {
                    int index2 = 0;
                    for(boolean k : j)
                    {
                        if(pieces.getPinPath()[index1][index2] && availableMoves[index1][index2])
                        {
                            k = true;
                        }
                        index2++;
                    }
                    index1++;
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
        
        col = super.getColumn() +1;
        row = super.getRow() +1;
        while(col <= 7 && row <= 7)
        {
            if(pieces.getPiece(col, row) != null)
            {
                targetArea[col][row] = true;
                
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    pinnedCol = col;
                    pinnedRow = row;
                    col++;
                    row++;
                    
                    for(; col <= 7 && row <= 7; col++, row++)
                    {
                        if(pieces.getPiece(col, row) == null)
                        {
                            continue;
                        }
                        else if(pieces.getPiece(col, row).getColour() != super.getColour() 
                                && pieces.getPiece(col, row).getSymbol().contains("K"))
                        {
                            pieces.getPiece(pinnedCol, pinnedRow).setIsUnderPinned(true);
                            
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
                            while(pathCol != col && pathRow != row)
                            {
                                pinPath[pathCol][pathRow] = true;
                                pathCol++;
                                pathRow++;
                            }
                            pieces.setPinPath(pinPath);
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
            row++;
        }
        
        col = super.getColumn() +1;
        row = super.getRow() -1;
        while(col <= 7 && row >= 0)
        {
            if(pieces.getPiece(col, row) != null)
            {
                targetArea[col][row] = true;
                
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    pinnedCol = col;
                    pinnedRow = row;
                    col++;
                    row--;
                    
                    for(; col <= 7 && row >= 0; col++, row--)
                    {
                        if(pieces.getPiece(col, row) == null)
                        {
                            continue;
                        }
                        else if(pieces.getPiece(col, row).getColour() != super.getColour() 
                                && pieces.getPiece(col, row).getSymbol().contains("K"))
                        {
                            pieces.getPiece(pinnedCol, pinnedRow).setIsUnderPinned(true);
                            
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
                            while(pathCol != col && pathRow != row)
                            {
                                pinPath[pathCol][pathRow] = true;
                                pathCol++;
                                pathRow--;
                            }
                            pieces.setPinPath(pinPath);
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
            row--;
        }
        
        col = super.getColumn() -1;
        row = super.getRow() +1;
        while(col >= 0 && row <= 7)
        {
            if(pieces.getPiece(col, row) != null)
            {
                targetArea[col][row] = true;
                
                if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    pinnedCol = col;
                    pinnedRow = row;
                    col--;
                    row++;
                    
                    for(; col >= 0 && row <= 7; col--, row++)
                    {
                        if(pieces.getPiece(col, row) == null)
                        {
                            continue;
                        }
                        else if(pieces.getPiece(col, row).getColour() != super.getColour() 
                                && pieces.getPiece(col, row).getSymbol().contains("K"))
                        {
                            pieces.getPiece(pinnedCol, pinnedRow).setIsUnderPinned(true);
                            
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
                            while(pathCol != col && pathRow != row)
                            {
                                pinPath[pathCol][pathRow] = true;
                                pathCol--;
                                pathRow++;
                            }
                            pieces.setPinPath(pinPath);
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
                    
                    if(pieces.getPiece(col, row).getColour() != super.getColour())
                {
                    pinnedCol = col;
                    pinnedRow = row;
                    col--;
                    row--;
                    
                    for(; col >= 0 && row >= 0; col--, row--)
                    {
                        if(pieces.getPiece(col, row) == null)
                        {
                            continue;
                        }
                        else if(pieces.getPiece(col, row).getColour() != super.getColour() 
                                && pieces.getPiece(col, row).getSymbol().contains("K"))
                        {
                            pieces.getPiece(pinnedCol, pinnedRow).setIsUnderPinned(true);
                            
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
                            while(pathCol != col && pathRow != row)
                            {
                                pinPath[pathCol][pathRow] = true;
                                pathCol--;
                                pathRow--;
                            }
                            pieces.setPinPath(pinPath);
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
        }
        
        return targetArea;
    }
}