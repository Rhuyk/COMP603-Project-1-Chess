/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

import java.util.ArrayList;

/**
 *
 * @author rh200
 */
public class WhitePieces {

    private static ArrayList<Piece> whitePieces = new ArrayList<Piece>();
    
    private static Piece king = new King(ChessPieceColour.WHITE, 4, 0);
    private static Piece queen = new Queen(ChessPieceColour.WHITE, 3, 0);
    
    private static Piece bishop1 = new Bishop(ChessPieceColour.WHITE, 2, 0);
    private static Piece bishop2 = new Bishop(ChessPieceColour.WHITE, 5, 0);
    
    private static Piece knight1 = new Knight(ChessPieceColour.WHITE, 1, 0);
    private static Piece knight2 = new Knight(ChessPieceColour.WHITE, 6, 0);
    
    private static Piece rook1 = new Rook(ChessPieceColour.WHITE, 0, 0);
    private static Piece rook2 = new Rook(ChessPieceColour.WHITE, 7, 0);
    
    private static Piece pawn1 = new Pawn(ChessPieceColour.WHITE, 0, 1);
    private static Piece pawn2 = new Pawn(ChessPieceColour.WHITE, 1, 1);
    private static Piece pawn3 = new Pawn(ChessPieceColour.WHITE, 2, 1);
    private static Piece pawn4 = new Pawn(ChessPieceColour.WHITE, 3, 1);
    private static Piece pawn5 = new Pawn(ChessPieceColour.WHITE, 4, 1);
    private static Piece pawn6 = new Pawn(ChessPieceColour.WHITE, 5, 1);
    private static Piece pawn7 = new Pawn(ChessPieceColour.WHITE, 6, 1);
    private static Piece pawn8 = new Pawn(ChessPieceColour.WHITE, 7, 1);
    
    private static boolean isCastlingAvailable = true;
    
    public WhitePieces()
    {
        whitePieces.add(king);
        whitePieces.add(queen);
        whitePieces.add(bishop1);
        whitePieces.add(bishop2);
        whitePieces.add(knight1);
        whitePieces.add(knight2);
        whitePieces.add(rook1);
        whitePieces.add(rook2);
        whitePieces.add(pawn1);
        whitePieces.add(pawn2);
        whitePieces.add(pawn3);
        whitePieces.add(pawn4);
        whitePieces.add(pawn5);
        whitePieces.add(pawn6);
        whitePieces.add(pawn7);
        whitePieces.add(pawn8);
    }
    
    public ArrayList<Piece> getAllPieces()
    {
        return this.whitePieces;
    }
    
    public Piece getPiece(int col, int row)
    {
        for(Piece i : whitePieces)
        {
            if(i.getColumn() == col && i.getRow() == row)
            {
                return i;
            }
        }
        return null;
    }
    
    public void removePiece(int col, int row)
    {
        for(Piece i : whitePieces)
        {
            if(i.getColumn() == col && i.getRow() == row)
            {
                whitePieces.remove(i);
                break;
            }
        }
    }
    
    public boolean[][] getTargetAreas()
    {
        boolean[][] allTargetArea = new boolean[8][8];
        for(boolean[] i : allTargetArea)
        {
            for(boolean j : i)
            {
                j = false;
            }
        }
        
        for(Piece i : whitePieces)
        {
            boolean[][] targetArea = i.getTargetArea();
            for(int col = 0; col < 8; col++)
            {
                for(int row = 0; row < 8; row++)
                {
                    if(targetArea[col][row])
                    {
                        allTargetArea[col][row] = true;
                    }
                }
            }
        }
        return allTargetArea;
    }
    
    public boolean[][] getAvailableMoves(Piece piece)
    {
        return piece.getAvailableMoves();
    }
}
