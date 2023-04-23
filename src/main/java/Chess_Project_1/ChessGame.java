/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;


/**
 *
 * @author rh200
 */
public class ChessGame {
    
    public static void main(String[] args) 
    {
        ChessBoard board = new ChessBoard();
        
        board.pieces.movePiece(1, 1, 1, 2);
        
        
        PrintBoard.printBoard(board,true);
        
    }
}