/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class ChessPieces {
    private static Piece[][] board = new Piece[8][8]; // Piece[column][row]
    
    public ChessPieces()
    {
        board[4][7] = new King(ChessPieceColour.BLACK,4,7);
        board[4][0] = new King(ChessPieceColour.WHITE,4,0);
        
        board[3][7] = new Queen(ChessPieceColour.BLACK,3,7);
        board[3][0] = new Queen(ChessPieceColour.WHITE,3,0);
        
        board[2][7] = new Bishop(ChessPieceColour.BLACK,2,7);
        board[5][7] = new Bishop(ChessPieceColour.BLACK,5,7);
        board[5][0] = new Bishop(ChessPieceColour.WHITE,5,0);
        board[2][0] = new Bishop(ChessPieceColour.WHITE,2,0);
        
        board[0][7] = new Rook(ChessPieceColour.BLACK,0,7);
        board[7][7] = new Rook(ChessPieceColour.BLACK,7,7);
        board[7][0] = new Rook(ChessPieceColour.WHITE,7,0);
        board[0][0] = new Rook(ChessPieceColour.WHITE,0,0);
        
        board[1][7] = new Knight(ChessPieceColour.BLACK,1,7);
        board[6][7] = new Knight(ChessPieceColour.BLACK,6,7);
        board[1][0] = new Knight(ChessPieceColour.WHITE,1,0);
        board[6][0] = new Knight(ChessPieceColour.WHITE,6,0);
        
        for(int i = 0; i < 8; i++)
        {
            board[i][1] = new Pawn(ChessPieceColour.WHITE,i,1);
            board[i][6] = new Pawn(ChessPieceColour.BLACK,i,6);
        }
    }

    public Piece[][] getBoard()
    {
        return this.board;
    }
    
    public Piece getPiece(int column, int row)
    {
        return this.board[column][row];
    }
    
    public void setPiece(int column, int row, Piece piece)
    {
        this.board[column][row] = piece;
    }
}
