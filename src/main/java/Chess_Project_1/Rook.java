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
    
    public Rook(ChessPieceColour colour,int col, int row)
    {
        super(colour, col, row);
    }
    
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
    
    public void rookMoves()
    {
        ChessPieces pieces = new ChessPieces();
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
        
        row = super.getRow() -1;
        while(row <= 7)
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
        
        super.setAvailableMoves(availableMoves);
    }
}
