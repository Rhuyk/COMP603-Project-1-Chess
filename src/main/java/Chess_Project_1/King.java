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
    boolean[][] availableMoves = new boolean[8][8];
    boolean[][] targetArea = new boolean[8][8];
    PiecesOnBoard pieces;
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
    @Override
    public boolean[][] getAvailableMoves()
    {
        pieces = new PiecesOnBoard();
        int col;
        int row;

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                availableMoves[i][j] = false;
            }
        }
        
        col = super.getColumn() +1;
        row = super.getRow();
        setAvailableMoves(col, row);
                
        col = super.getColumn() -1;
        row = super.getRow();
        setAvailableMoves(col, row);
        
        col = super.getColumn();
        row = super.getRow() +1;
        setAvailableMoves(col, row);
        
        col = super.getColumn();
        row = super.getRow() -1;
        setAvailableMoves(col, row);
        
        col = super.getColumn() +1;
        row = super.getRow() +1;
        setAvailableMoves(col, row);
        
        col = super.getColumn() +1;
        row = super.getRow() -1;
        setAvailableMoves(col, row);
        
        col = super.getColumn() -1;
        row = super.getRow() +1;
        setAvailableMoves(col, row);
        
        col = super.getColumn() -1;
        row = super.getRow() -1;
        setAvailableMoves(col, row);
        
        if(super.getColour() == ChessPieceColour.WHITE && pieces.whiteIsInCheck())
        {
            setUnavailableMoves();
        }
        else if(super.getColour() == ChessPieceColour.BLACK && pieces.blackIsInCheck())
        {
            setUnavailableMoves();
        }
        
        return availableMoves;
    }
    
    //return king's targeting squares (the 8 squares around the king)
    @Override
    public boolean[][] getTargetArea()
    {
        pieces = new PiecesOnBoard();
        int col, row;
        
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                targetArea[i][j] = false;
            }
        }
        
        col = super.getColumn() +1;
        row = super.getRow();
        setTargetArea(col, row);
                
        col = super.getColumn() -1;
        row = super.getRow();
        setTargetArea(col, row);
        
        col = super.getColumn();
        row = super.getRow() +1;
        setTargetArea(col, row);
        
        col = super.getColumn();
        row = super.getRow() -1;
        setTargetArea(col, row);
        
        col = super.getColumn() +1;
        row = super.getRow() +1;
        setTargetArea(col, row);
        
        col = super.getColumn() +1;
        row = super.getRow() -1;
        setTargetArea(col, row);
        
        col = super.getColumn() -1;
        row = super.getRow() +1;
        setTargetArea(col, row);
        
        col = super.getColumn() -1;
        row = super.getRow() -1;
        setTargetArea(col, row);
        
        return targetArea;
    }
    
    //set the square as availbale if within the board boundary and not occupied by same colour piece
    private void setAvailableMoves(int col, int row)
    {
        if(col <= 7 && col >= 0 && row <= 7 && row >= 0)
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
    }
    
    //set the square as targeted
    private void setTargetArea(int col, int row)
    {
        if(col <= 7 && col >= 0 && row <= 7 && row >= 0)
        {
            targetArea[col][row] = true;
        }
    }
    
    //set the square behind the king while being checked as unavailable square
    private void setUnavailableMove(int col, int row)
    {
        if(col <= 7 && col >= 0 && row <= 7 && row >= 0)
        {
            if(pieces.getCheckPath()[col][row])
            {
                if((col - super.getColumn()) > 0)
                {
                    col = super.getColumn() - 1;
                }
                else if((col - super.getColumn()) < 0)
                {
                    col = super.getColumn() + 1;
                }
                if((row - super.getRow()) > 0)
                {
                    row = super.getRow() - 1;
                }
                else if((row - super.getRow()) < 0)
                {
                    row = super.getRow() + 1;
                }
                if(col <= 7 && col >= 0 && row <= 7 && row >= 0)
                {
                    availableMoves[col][row] = false;
                }
            }
        }
    }
    
    //set the square behind the king while being checked as unavailable square (from any direction)
    private void setUnavailableMoves()
    {
        int col, row;

        col = super.getColumn()+1;
        row = super.getRow();
        setUnavailableMove(col, row);

        col = super.getColumn()-1;
        row = super.getRow();
        setUnavailableMove(col, row);

        col = super.getColumn();
        row = super.getRow()+1;
        setUnavailableMove(col, row);

        col = super.getColumn();
        row = super.getRow()-1;
        setUnavailableMove(col, row);

        col = super.getColumn()+1;
        row = super.getRow()+1;
        setUnavailableMove(col, row);

        col = super.getColumn()-1;
        row = super.getRow()+1;
        setUnavailableMove(col, row);

        col = super.getColumn()+1;
        row = super.getRow()-1;
        setUnavailableMove(col, row);

        col = super.getColumn()-1;
        row = super.getRow()-1;
        setUnavailableMove(col, row);
    }
}
