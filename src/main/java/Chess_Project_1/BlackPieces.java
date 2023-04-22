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
public class BlackPieces {

    private ArrayList<Piece> blackPieces = new ArrayList<Piece>();

    private Piece king = new King(ChessPieceColour.BLACK, 4, 7);
    private Piece queen = new Queen(ChessPieceColour.BLACK, 3, 7);
    
    private Piece bishop1 = new Bishop(ChessPieceColour.BLACK, 2, 7);
    private Piece bishop2 = new Bishop(ChessPieceColour.BLACK, 5, 7);
    
    private Piece knight1 = new Knight(ChessPieceColour.BLACK, 1, 7);
    private Piece knight2 = new Knight(ChessPieceColour.BLACK, 6, 7);
    
    private Piece rook1 = new Rook(ChessPieceColour.BLACK, 0, 7);
    private Piece rook2 = new Rook(ChessPieceColour.BLACK, 7, 7);
    
    private Piece pawn1 = new Pawn(ChessPieceColour.BLACK, 0, 6);
    private Piece pawn2 = new Pawn(ChessPieceColour.BLACK, 1, 6);
    private Piece pawn3 = new Pawn(ChessPieceColour.BLACK, 2, 6);
    private Piece pawn4 = new Pawn(ChessPieceColour.BLACK, 3, 6);
    private Piece pawn5 = new Pawn(ChessPieceColour.BLACK, 4, 6);
    private Piece pawn6 = new Pawn(ChessPieceColour.BLACK, 5, 6);
    private Piece pawn7 = new Pawn(ChessPieceColour.BLACK, 6, 6);
    private Piece pawn8 = new Pawn(ChessPieceColour.BLACK, 7, 6);
    
    public BlackPieces()
    {
            blackPieces.add(king);
            blackPieces.add(queen);
            blackPieces.add(bishop1);
            blackPieces.add(bishop2);
            blackPieces.add(knight1);
            blackPieces.add(knight2);
            blackPieces.add(rook1);
            blackPieces.add(rook2);
            blackPieces.add(pawn1);
            blackPieces.add(pawn2);
            blackPieces.add(pawn3);
            blackPieces.add(pawn4);
            blackPieces.add(pawn5);
            blackPieces.add(pawn6);
            blackPieces.add(pawn7);
            blackPieces.add(pawn8);
    }
    
    public ArrayList<Piece> getAllPieces()
    {
        return this.blackPieces;
    }
    
    public Piece getPiece(int index)
    {
        return this.blackPieces.get(index);
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
        
        int index1 = 0;
        for(Piece i : blackPieces)
        {
            boolean[][] targetArea = i.getTargetArea();
            for(boolean[] j : allTargetArea)
            {
                int index2 = 0;
                for(boolean k : j)
                {
                    if(targetArea[index1][index2] == true)
                    {
                        k = true;
                    }
                    index2++;
                }
                index1++;
            }
        }
        return allTargetArea;
    }
    
    public boolean[][] getAvailableMoves(Piece piece)
    {
        return piece.getAvailableMoves();
    }
}
