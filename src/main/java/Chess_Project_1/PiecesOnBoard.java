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
    private static ArrayList<Piece> allPieces = new ArrayList<Piece>();
    private static WhitePieces whitepieces = new WhitePieces();
    private static BlackPieces blackpieces = new BlackPieces();
    private static int moveCounter = 0;
    
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
                        moveCounter++;
                        selectedPiece.setLastMoveNum(moveCounter);
                        selectedPiece.setFirstMove();
                        selectedPiece.setColAndRow(toCol, toRow);
                        board[toCol][toRow] = selectedPiece;
                        board[fromCol][fromRow] = null;
                    }
                    //castling
                    else if(isCastling(selectedPiece, toCol) && toRow == 0)
                    {
                        castle(selectedPiece, toCol);
                    }
                    else
                    {
                        //unavailable move
                    }
                }
                //en passant
                else if(isEnPassant(selectedPiece, toCol))
                {
                    enPassant(selectedPiece, toCol);
                }
                else
                {
                    if(whitepieces.getAvailableMoves(selectedPiece)[toCol][toRow])
                    {
                        moveCounter++;
                        selectedPiece.setLastMoveNum(moveCounter);
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
                    if(!whitepieces.getTargetAreas()[toCol][toRow] 
                            && blackpieces.getAvailableMoves(selectedPiece)[toCol][toRow])
                    {
                        moveCounter++;
                        selectedPiece.setLastMoveNum(moveCounter);
                        selectedPiece.setFirstMove();
                        selectedPiece.setColAndRow(toCol, toRow);
                        board[toCol][toRow] = selectedPiece;
                        board[fromCol][fromRow] = null;
                    }
                    //castling
                    else if(isCastling(selectedPiece, toCol) && toRow == 7)
                    {
                        castle(selectedPiece, toCol);
                    }
                    else
                    {
                        //unavailable move
                    }
                }
                //en passant
                else if(isEnPassant(selectedPiece, toCol))
                {
                    enPassant(selectedPiece, toCol);
                }
                else
                {
                    if(whitepieces.getAvailableMoves(selectedPiece)[toCol][toRow])
                    {
                        moveCounter++;
                        selectedPiece.setLastMoveNum(moveCounter);
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
                
                //pawn promotion
                promote(board[toCol][toRow]);
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
    
    public void clear() 
    {
        for(int col = 0; col < 8; col++) 
        {
            for(int row = 0; row < 8; row++) 
            {
                board[col][row] = null;
            }
        }
    }
    
    public boolean isWinning()
    {
        return isCheckMate();
    }
    
    public boolean isDrawing()
    {
        return (isDeadPosition() || isStalemate() || isThreefoldRepetition() || isFiftyMoveRule());
    }
    
    private boolean isCastling(Piece king, int toCol)
    {
        boolean availability = false;
        if(king.isFirstMove())
        {
            //long castle
            if(toCol == 2 && board[0][king.getRow()].isFirstMove() 
                    && board[1][king.getRow()] == null && board[2][king.getRow()] == null && board[3][king.getRow()] == null)
            {
                availability = true;
                if(king.getColour() == ChessPieceColour.WHITE)
                {
                    for(int col = 0; col <= 4; col++)
                    {
                        if(blackpieces.getTargetAreas()[col][0])
                        {
                            availability = false;
                        }
                    }
                }
                else if(king.getColour() == ChessPieceColour.BLACK)
                {
                    for(int col = 0; col <= 4; col++)
                    {
                        if(whitepieces.getTargetAreas()[col][7])
                        {
                            availability = false;
                        }
                    }
                }
            }
            //short castle
            else if(toCol == 6 && board[7][king.getRow()].isFirstMove() 
                    && board[5][king.getRow()] == null && board[6][king.getRow()] == null)
            {
                availability = true;
                if(king.getColour() == ChessPieceColour.WHITE)
                {
                    for(int col = 7; col >= 4; col--)
                    {
                        if(blackpieces.getTargetAreas()[col][0])
                        {
                            availability = false;
                        }
                    }
                }
                else if(king.getColour() == ChessPieceColour.BLACK)
                {
                    for(int col = 7; col >= 4; col--)
                    {
                        if(whitepieces.getTargetAreas()[col][7])
                        {
                            availability = false;
                        }
                    }
                }
            }
        }
        return availability;
    }
    
    private void castle(Piece king, int toCol)
    {
        int col = king.getColumn();
        int row = king.getRow();
        
        if(king.getColour() == ChessPieceColour.WHITE)
        {
            moveCounter++;
            king.setLastMoveNum(moveCounter);
            king.setFirstMove();
            king.setColAndRow(toCol, 0);
            board[toCol][0] = king;
            board[col][row] = null;

            if(toCol == 2) //long castle
            {
                board[0][0].setFirstMove();
                board[3][0] = board[0][0];
                board[0][0] = null;
            }
            else if(toCol == 6) //short castle
            {
                board[7][0].setFirstMove();
                board[5][0] = board[7][0];
                board[7][0] = null;
            }
        }
        else if(king.getColour() == ChessPieceColour.BLACK)
        {
            moveCounter++;
            king.setLastMoveNum(moveCounter);
            king.setFirstMove();
            king.setColAndRow(toCol, 7);
            board[toCol][7] = king;
            board[col][row] = null;

            if(toCol == 2) //long castle
            {
                board[0][7].setFirstMove();
                board[3][7] = board[0][7];
                board[0][7] = null;
            }
            else if(toCol == 6) //short castle
            {
                board[7][7].setFirstMove();
                board[5][7] = board[7][7];
                board[7][7] = null;
            }
        }
    }
    
    private boolean isEnPassant(Piece pawn, int toCol)
    {
        boolean availability = false;
        if(pawn.getSymbol().equals("wP") && pawn.getRow() == 4 
                && board[toCol][pawn.getRow()].getSymbol().equals("bP") 
                && board[toCol][pawn.getRow()].getLastMoveNum() == this.moveCounter 
                && board[toCol][pawn.getRow()].isWasFirstMove())
        {
            availability = true;
        }
        else if(pawn.getSymbol().equals("bP") && pawn.getRow() == 3 
                && board[toCol][pawn.getRow()].getSymbol().equals("wP") 
                && board[toCol][pawn.getRow()].getLastMoveNum() == this.moveCounter 
                && board[toCol][pawn.getRow()].isWasFirstMove())
        {
            availability = true;
        }
        
        return availability;
    }
    
    private void enPassant(Piece pawn, int toCol)
    {
        int col = pawn.getColumn();
        int row = pawn.getRow();
        
        if(pawn.getColour() == ChessPieceColour.WHITE)
        {
            moveCounter++;
            pawn.setLastMoveNum(moveCounter);
            pawn.setFirstMove();
            pawn.setColAndRow(toCol, pawn.getRow()+1);
            board[toCol][pawn.getRow()+1] = pawn;
            board[col][row] = null;
            board[toCol][row] = null;
        }
        else if(pawn.getColour() == ChessPieceColour.BLACK)
        {
            moveCounter++;
            pawn.setLastMoveNum(moveCounter);
            pawn.setFirstMove();
            pawn.setColAndRow(toCol, pawn.getRow()-1);
            board[toCol][pawn.getRow()-1] = pawn;
            board[col][row] = null;
            board[toCol][row] = null;
        }
    }
    
    private void promote(Piece pawn)
    {
        int col = pawn.getColumn();
        int row = pawn.getRow();
        
        if(pawn.getSymbol().equals("wP") && pawn.getColour() == ChessPieceColour.WHITE && pawn.getRow() == 7)
        {
            //player select the which to promote
//            board[col][row] = new Queen(ChessPieceColour.WHITE, col, row);
//            board[col][row] = new Bishop(ChessPieceColour.WHITE, col, row);
//            board[col][row] = new Knight(ChessPieceColour.WHITE, col, row);
//            board[col][row] = new Rook(ChessPieceColour.WHITE, col, row);
        }
        else if(pawn.getSymbol().equals("bP") && pawn.getColour() == ChessPieceColour.BLACK && pawn.getRow() == 0)
        {
            //player select the which to promote
//            board[col][row] = new Queen(ChessPieceColour.BLACK, col, row);
//            board[col][row] = new Bishop(ChessPieceColour.BLACK, col, row);
//            board[col][row] = new Knight(ChessPieceColour.BLACK, col, row);
//            board[col][row] = new Rook(ChessPieceColour.BLACK, col, row);
        }
    }
    
    private boolean isPinning()
    {
        return false;
    }
    
    private boolean isChecking()
    {
        return false;
    }
    
    private boolean isCheckMate()
    {
        return false;
    }
    
    private boolean isStalemate()
    {
        return false;
    }
    
    private boolean isDeadPosition()
    {
        return false;
    }
    
    private boolean isThreefoldRepetition()
    {
        return false;
    }
    
    private boolean isFiftyMoveRule()
    {
        return false;
    }
}
