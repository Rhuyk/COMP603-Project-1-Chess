/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class PrintBoard {
    
    public static void printBoard(PiecesOnBoard board, Player CurrentPlayer)
    {
        System.out.println("Chess Board: \n");
        System.out.println("Currently playing as: " + CurrentPlayer.getColourPiece() + "\n");
        String column = "     h    g    f    e    d    c    b    a\n";
        
        int currentRow = 0;
        int rowNumber = 1;
        int rowChange = 1;
        if(CurrentPlayer.getColourPiece() == ChessPieceColour.WHITE)
        {
            currentRow = 7;
            rowNumber = 8;
            rowChange = -1;
            column = "     a    b    c    d    e    f    g    h\n";
        }
        
        System.out.println(column);

        for(int i = 7; i >= 0; i--)
        {
            System.out.println("   +----+----+----+----+----+----+----+----+");
            System.out.print((rowNumber) + "  | ");

            for(int j = 0; j < 8; j++)
            {
                Piece piece = board.getBoard()[j][currentRow];

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
        System.out.println("   +----+----+----+----+----+----+----+----+");
        System.out.println(column);
    }
}
