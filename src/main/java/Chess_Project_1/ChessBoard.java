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

    private Piece[][] board; // int[row][column]
    
    public ChessBoard()
    {
        this.board = new Piece[8][8];
    }
    
    public void startBoard()
    {
        board[7][4] = new King(ChessPieceColour.WHITE,7,4);
        board[0][4] = new King(ChessPieceColour.BLACK,0,4);
        
        board[7][3] = new Queen(ChessPieceColour.WHITE,7 ,3);
        board[0][3] = new Queen(ChessPieceColour.BLACK,0 ,3);
        
        board[7][2] = new Bishop(ChessPieceColour.WHITE,7,2);
        board[7][5] = new Bishop(ChessPieceColour.WHITE,7,5);
        board[0][5] = new Bishop(ChessPieceColour.BLACK,0,5);
        board[0][2] = new Bishop(ChessPieceColour.BLACK,0,2);
        
        board[7][0] = new Rook(ChessPieceColour.WHITE,7,0);
        board[7][7] = new Rook(ChessPieceColour.WHITE,7,7);
        board[0][7] = new Rook(ChessPieceColour.BLACK, 0, 7);
        board[0][0] = new Rook(ChessPieceColour.BLACK, 0, 0);
        
        board[7][1] = new Knight(ChessPieceColour.WHITE,7,1);
        board[7][6] = new Knight(ChessPieceColour.WHITE,7,6);
        board[0][1] = new Knight(ChessPieceColour.WHITE,0,1);
        board[0][6] = new Knight(ChessPieceColour.WHITE,0,6);
        
        for(int i = 0; i < 8; i++)
        {
            board[1][i] = new Pawn(ChessPieceColour.WHITE,1,i);
            board[6][i] = new Pawn(ChessPieceColour.WHITE,6,i);
        }
    }
    
    public void printBoard()
    {
        int chessRows = 8;
        
        System.out.println("Chess Board: \n");
        System.out.println("     a   b   c   d   e   f   g   h\n");
        
        for(Piece[] row: board)
        {
            System.out.println("   +---+---+---+---+---+---+---+---+");
            System.out.print(chessRows + "  | ");
            for(Piece piece: row)
            {
                
                if(piece == null)
                {
                    System.out.print("- | ");
                }
                else
                {
                    System.out.print(piece.getSymbol() +" | ");
                }
            }
            System.out.println(" "+chessRows);
            chessRows--;
        }
        
        System.out.println("\n     a   b   c   d   e   f   g   h");
    }
}