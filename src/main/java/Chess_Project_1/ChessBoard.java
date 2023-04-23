/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class ChessBoard {
    PiecesOnBoard pieces;
    
    public ChessBoard()
    {
        pieces = new PiecesOnBoard();
    }
    
    public Piece[][] getBoard()
    {
        return pieces.getBoard();
    }
    
    public Piece getPiece(int column, int row)
    {
        return pieces.getPiece(column, row);
    }
    
    public void clear()
    {
        Piece[][] board = pieces.getBoard();
        for (Piece[] board1 : board) 
        {
            for (int j = 0; j < board1.length; j++) 
            {
                board1[j] = null;
            }
        }
    }
}
