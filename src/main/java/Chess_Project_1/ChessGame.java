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
        PiecesOnBoard board = new PiecesOnBoard();
        
        
        // Test out avaliable moves for pawn. 
        boolean[][] moves = board.getPiece(3, 1).getAvailableMoves();
        System.out.println("Available moves for white Pawn at (3, 1):");
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if (moves[i][j]) 
                {
                    System.out.println("(" + i + ", " + j + ")");
                }
            }  
        }
        
        //Test out moving pawns
          board.movePiece(1, 1, 1, 3);
//        board.movePiece(0, 1, 0, 2);
//        board.movePiece(0, 2, 0, 3);
//        board.movePiece(0, 0, 0, 1);
//        board.movePiece(1, 0, 2, 2);
//        board.movePiece(2, 0, 0, 2);
          board.movePiece(0, 1, 0, 3);
        
        // Test out creating players
        Player player1 = new Player(ChessPieceColour.WHITE,"PJ");
        Player player2 = new Player(ChessPieceColour.BLACK,"Jamar");
        
        // Test out board flip for both players
        PrintBoard.printBoard(board,player1);
//        PrintBoard.printBoard(board,player2);

        moves = board.getPiece(4, 6).getAvailableMoves();
        System.out.println("Available moves for black Pawn at (4, 6):");
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if (moves[i][j]) {
                    System.out.println("(" + i + ", " + j + ")");
                }
            }
        }
        
        moves = board.getPiece(0, 0).getAvailableMoves();
        System.out.println("Available moves for white Rook at (0, 0):");
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if (moves[i][j]) {
                    System.out.println("(" + i + ", " + j + ")");
                }
            }
        }
        
        moves = board.getPiece(2, 0).getAvailableMoves();
        System.out.println("Available moves for white Bishop at (2, 0):");
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if (moves[i][j]) {
                    System.out.println("(" + i + ", " + j + ")");
                }
            }
        }
        
        
        
    }
}