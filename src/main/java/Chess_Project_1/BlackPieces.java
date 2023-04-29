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

    //black pieces list
    private static ArrayList<Piece> blackPieces = new ArrayList<Piece>();

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
    
    //black pieces list constructor
    public BlackPieces()
    {
        blackPieces.clear();
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
    
    //return a list of black pieces
    public ArrayList<Piece> getAllPieces()
    {
        return this.blackPieces;
    }
    
    //return a piece that is located at the selected square from the list
    public Piece getPiece(int col, int row)
    {
        for(Piece i : blackPieces)
        {
            if(i.getColumn() == col && i.getRow() == row)
            {
                return i;
            }
        }
        return null;
    }
    
    //return the king piece
    public Piece getKing()
    {
        for(Piece i : blackPieces)
        {
            if(i.getSymbol().equals("bK"))
            {
                return i;
            }
        }
        return null;
    }
    
    //add a piece into the list
    public void addPiece(Piece piece)
    {
        blackPieces.add(piece);
    }
    
    //remove all pieces from the list
    public void clearPieces()
    {
        blackPieces.clear();
    }
    
    //remove a piece that is located at a square from the list
    public void removePiece(int col, int row)
    {
        for(Piece i : blackPieces)
        {
            if(i.getColumn() == col && i.getRow() == row)
            {
                blackPieces.remove(i);
                break;
            }
        }
    }
    
    //replace a piece from the list that is located at a square with another piece
    public void replacePiece(Piece replacement, int col, int row)
    {
        for(Piece i : blackPieces)
        {
            if(i.getColumn() == col && i.getRow() == row)
            {
                removePiece(col, row);
                blackPieces.add(replacement);
                break;
            }
        }
    }
    
    //return the target area on the board from all the black pieces
    public boolean[][] getTargetAreas()
    {
        boolean[][] allTargetArea = new boolean[8][8];
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                allTargetArea[i][j] = false;
            }
        }
        
        for(Piece i : blackPieces)
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
    
    //return a piece available moves
    public boolean[][] getAvailableMoves(Piece piece)
    {
        return piece.getAvailableMoves();
    }
}
