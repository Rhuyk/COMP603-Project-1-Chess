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
    ChessPieces pieces = new ChessPieces();
    
    public ChessBoard()
    {

    }
    
    public Piece[][] getBoard()
    {
        return pieces.getBoard();
    }
    
    public Piece getPiece(int column, int row)
    {
        return pieces.getPiece(column, row);
    }
}
