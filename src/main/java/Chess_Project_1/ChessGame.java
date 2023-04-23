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
        
        board.pieces.movePiece(1, 1, 1, 3);
        board.pieces.movePiece(0, 1, 0, 2);
        board.pieces.movePiece(0, 2, 0, 3);
        board.pieces.movePiece(0, 0, 0, 1);
        board.pieces.movePiece(1, 0, 2, 2);
        board.pieces.movePiece(2, 0, 0, 2);
        
        
        Player player1 = new Player(ChessPieceColour.WHITE,"PJ");
        Player player2 = new Player(ChessPieceColour.BLACK,"Jamar");
        
        PrintBoard.printBoard(board,player1);
        PrintBoard.printBoard(board,player2);
        
    }
}