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

    private Piece[][] board; // Piece[row][column]
    
    public ChessBoard()
    {
        this.board = new Piece[8][8];
    }
    
    public void startBoard()
    {
        board[7][4] = new King(ChessPieceColour.BLACK,7,4);
        board[0][4] = new King(ChessPieceColour.WHITE,0,4);
        
        board[7][3] = new Queen(ChessPieceColour.BLACK,7 ,3);
        board[0][3] = new Queen(ChessPieceColour.WHITE,0 ,3);
        
        board[7][2] = new Bishop(ChessPieceColour.BLACK,7,2);
        board[7][5] = new Bishop(ChessPieceColour.BLACK,7,5);
        board[0][5] = new Bishop(ChessPieceColour.WHITE,0,5);
        board[0][2] = new Bishop(ChessPieceColour.WHITE,0,2);
        
        board[7][0] = new Rook(ChessPieceColour.BLACK,7,0);
        board[7][7] = new Rook(ChessPieceColour.BLACK,7,7);
        board[0][7] = new Rook(ChessPieceColour.WHITE, 0, 7);
        board[0][0] = new Rook(ChessPieceColour.WHITE, 0, 0);
        
        board[7][1] = new Knight(ChessPieceColour.BLACK,7,1);
        board[7][6] = new Knight(ChessPieceColour.BLACK,7,6);
        board[0][1] = new Knight(ChessPieceColour.WHITE,0,1);
        board[0][6] = new Knight(ChessPieceColour.WHITE,0,6);
        
        for(int i = 0; i < 8; i++)
        {
            board[1][i] = new Pawn(ChessPieceColour.WHITE,1,i);
            board[6][i] = new Pawn(ChessPieceColour.BLACK,6,i);
        }
    }
    
    public void printBoard(boolean isPlayingAsWhite)
{
    System.out.println("Chess Board: \n");
    System.out.println("Currently Playing as White: " + isPlayingAsWhite + "\n");
    System.out.println("     a    b    c    d    e    f    g    h\n");

    int currentRow = 0;
    int rowNumber = 1;
    int rowChange = 1;
    if(isPlayingAsWhite)
    {
        currentRow = 7;
        rowNumber = 8;
        rowChange = -1;
    }
    
    for(int i = 7; i >= 0; i--)
    {
        System.out.println("   +----+----+----+----+----+----+----+----+");
        System.out.print((rowNumber) + "  | ");
        

        for(int j = 0; j < 8; j++)
        {
            Piece piece = board[currentRow][j];

            if(piece == null)
            {
                System.out.print("-- | ");
            }
            else
            {
                System.out.print(piece.getSymbol() + " | ");
            }
        }

        System.out.println(rowNumber);
        currentRow += rowChange;
        rowNumber += rowChange;
    }

    System.out.println("\n     a    b    c    d    e    f    g    h");
    }
    
    public Piece getPiece(int row, int column)
    {
        return board[row][column];
    }
}