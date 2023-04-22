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
public class PiecesOnBoard {
    private static Piece[][] board = new Piece[8][8]; // Piece[column][row]
    private ArrayList<Piece> allPieces = new ArrayList<Piece>();
    private WhitePieces whitepieces = new WhitePieces();
    private BlackPieces blackpieces = new BlackPieces();
    
    public PiecesOnBoard()
    {
        allPieces.addAll(whitepieces.getAllPieces());
        allPieces.addAll(blackpieces.getAllPieces());
        
        for(Piece i : allPieces)
        {
            board[i.getColumn()][i.getRow()] = i;
        }
    }
    
    public void movePiece(int fromCol, int fromRow, int toCol, int toRow)
    {
        Piece selectedPiece = board[fromCol][fromRow];
        if(selectedPiece != null)
        {
            if(selectedPiece.getColour() == ChessPieceColour.WHITE)
            {
                if(selectedPiece.getSymbol().equals("wK"))
                {
                    if(!blackpieces.getTargetAreas()[toCol][toRow] 
                            && whitepieces.getAvailableMoves(selectedPiece)[toCol][toRow])
                    {
                        selectedPiece.setColAndRow(toCol, toRow);
                        board[toCol][toRow] = selectedPiece;
                        board[fromCol][fromRow] = null;
                    }
                    else
                    {
                        //unavailable move
                    }
                }
                else
                {
                    if(whitepieces.getAvailableMoves(selectedPiece)[toCol][toRow])
                    {
                        selectedPiece.setFirstMove();
                        selectedPiece.setColAndRow(toCol, toRow);
                        board[toCol][toRow] = selectedPiece;
                        board[fromCol][fromRow] = null;
                    }
                    else
                    {
                        //unavailable move
                    }
                }
            }
            else if(selectedPiece.getColour() == ChessPieceColour.BLACK)
            {
                if(selectedPiece.getSymbol().equals("bK"))
                {
                    if(!blackpieces.getTargetAreas()[toCol][toRow] 
                            && whitepieces.getAvailableMoves(selectedPiece)[toCol][toRow])
                    {
                        selectedPiece.setColAndRow(toCol, toRow);
                        board[toCol][toRow] = selectedPiece;
                        board[fromCol][fromRow] = null;
                    }
                    else
                    {
                        //unavailable move
                    }
                }
                else
                {
                    if(whitepieces.getAvailableMoves(selectedPiece)[toCol][toRow])
                    {
                        selectedPiece.setFirstMove();
                        selectedPiece.setColAndRow(toCol, toRow);
                        board[toCol][toRow] = selectedPiece;
                        board[fromCol][fromRow] = null;
                    }
                    else
                    {
                        //unavailable move
                    }
                }
            }
        }
        else
        {
            //invalid selection
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
