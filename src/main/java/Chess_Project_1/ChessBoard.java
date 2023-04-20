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
    //private enum Column {a,b,c,d,e,f,g,h};
    private Piece[][] board; // int[column][row]
    
    public ChessBoard()
    {
        this.board = new Piece[8][8];
    }
    
    public void startBoard()
    {
        board[0][0] = new Rook(ChessPieceColour.WHITE,0,0);
        board[0][1] = new Knight(ChessPieceColour.WHITE, 0, 1);
    }
    
    public void printBoard()
    {
        for(Piece[] row: board)
        {
            for(Piece piece: row)
            {
                if(piece == null)
                {
                    System.out.println("- ");
                }
                else
                {
                    System.out.println(piece.getSymbol() +" ");
                }
            }
            System.out.println(8 - row[0].getRow());
        }
        System.out.println("a b c d e f g h");
    }
}
