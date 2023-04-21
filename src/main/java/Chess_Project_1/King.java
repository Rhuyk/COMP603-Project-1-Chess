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
    
    public King(ChessPieceColour colour, int col, int row)
    {
        super(colour, col, row);
    }
    
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
    
    public void kingMoves()
    {
        boolean[][] availableMoves = new boolean[8][8];
        for(boolean[] i : availableMoves)
        {
            for(boolean j : i)
            {
                j = false;
            }
        }
        
        if(super.getColumn()+1 <= 7)
        {
            availableMoves[super.getColumn()+1][super.getRow()] = true;
            
            if(super.getRow()+1 <= 7)
            {
                availableMoves[super.getColumn()+1][super.getRow()+1] = true;
            }
            if(super.getRow()-1 >= 0)
            {
                availableMoves[super.getColumn()+1][super.getRow()-1] = true;
            }
        }
        if(super.getColumn()-1 >= 0)
        {
            availableMoves[super.getColumn()-1][super.getRow()] = true;
            
            if(super.getRow()+1 <= 7)
            {
                availableMoves[super.getColumn()-1][super.getRow()+1] = true;
            }
            if(super.getRow()-1 >= 0)
            {
                availableMoves[super.getColumn()-1][super.getRow()-1] = true;
            }
        }
        if(super.getRow()+1 <= 7)
        {
            availableMoves[super.getColumn()][super.getRow()+1] = true;
        }
        if(super.getRow()-1 >= 0)
        {
            availableMoves[super.getColumn()][super.getRow()-1] = true;
        }
        
        ChessPieces pieces = new ChessPieces();
        int counter = 0;
        for(boolean[] i : availableMoves)
        {
            int counter2 = 0;
            for(boolean j : i)
            {
                if(j == true && pieces.getPiece(counter, counter2).getColour() == super.getColour())
                {
                    j = false;
                }
                counter2++;
            }
            counter++;
        }
        
        super.setAvailableMoves(availableMoves);
    }
}
