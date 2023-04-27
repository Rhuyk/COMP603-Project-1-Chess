/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

import java.util.ArrayList;
import java.util.Scanner;

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
    private static boolean[][] checkPath = new boolean[8][8];
    
    public PiecesOnBoard()
    {
        resfreshBoard();
    }
    
    public boolean movePiece(int fromCol, int fromRow, int toCol, int toRow)
    {
        Piece selectedPiece = board[fromCol][fromRow];
        if(selectedPiece != null)
        {
            checkPinAndCheck();
            if(selectedPiece.getColour() == ChessPieceColour.WHITE)
            {
                if(selectedPiece.getSymbol().equals("wK"))
                {
                    if(!blackpieces.getTargetAreas()[toCol][toRow] 
                            && whitepieces.getAvailableMoves(selectedPiece)[toCol][toRow])
                    {
                        moveCounter++;
                        whitepieces.getPiece(fromCol, fromRow).setLastMoveNum(moveCounter);
                        whitepieces.getPiece(fromCol, fromRow).setFirstMove();
                        whitepieces.getPiece(fromCol, fromRow).setColAndRow(toCol, toRow);
                        blackpieces.removePiece(toCol, toRow);
                        resfreshBoard();
                    }
                    //castling
                    else if(isCastling(selectedPiece, toCol) && toRow == 0)
                    {
                        castle(selectedPiece, toCol);
                    }
                    else
                    {
                        //unavailable move
                        System.out.println("That was a Illegal move. Please enter a new one!");
                        return false;
                    }
                }
                //en passant
                else if(isEnPassant(selectedPiece, toCol) && toRow == 5)
                {
                    enPassant(selectedPiece, toCol);
                }
                else
                {
                    if(whitepieces.getAvailableMoves(selectedPiece)[toCol][toRow] && checkPath[toCol][toRow])
                    {
                        moveCounter++;
                        whitepieces.getPiece(fromCol, fromRow).setLastMoveNum(moveCounter);
                        whitepieces.getPiece(fromCol, fromRow).setFirstMove();
                        whitepieces.getPiece(fromCol, fromRow).setColAndRow(toCol, toRow);
                        blackpieces.removePiece(toCol, toRow);
                        resfreshBoard();
                    }
                    else
                    {
                        //unavailable move
                        System.out.println("That was a Illegal move. Please enter a new one!");
                        return false;
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
                        blackpieces.getPiece(fromCol, fromRow).setLastMoveNum(moveCounter);
                        blackpieces.getPiece(fromCol, fromRow).setFirstMove();
                        blackpieces.getPiece(fromCol, fromRow).setColAndRow(toCol, toRow);
                        whitepieces.removePiece(toCol, toRow);
                        resfreshBoard();
                    }
                    //castling
                    else if(isCastling(selectedPiece, toCol) && toRow == 7)
                    {
                        castle(selectedPiece, toCol);
                    }
                    else
                    {
                        //unavailable move
                        System.out.println("That was a Illegal move. Please enter a new one!");
                        return false;
                    }
                }
                //en passant
                else if(isEnPassant(selectedPiece, toCol) && toRow == 2)
                {
                    enPassant(selectedPiece, toCol);
                }
                else
                {
                    if(whitepieces.getAvailableMoves(selectedPiece)[toCol][toRow] && checkPath[toCol][toRow])
                    {
                        moveCounter++;
                        blackpieces.getPiece(fromCol, fromRow).setLastMoveNum(moveCounter);
                        blackpieces.getPiece(fromCol, fromRow).setFirstMove();
                        blackpieces.getPiece(fromCol, fromRow).setColAndRow(toCol, toRow);
                        whitepieces.removePiece(toCol, toRow);
                        resfreshBoard();
                    }
                    else
                    {
                        //unavailable move
                        System.out.println("That was a Illegal move. Please enter a new one!");
                        return false;
                    }
                }
            }
            
            if(board[toCol][toRow] != null)
            {
                //pawn promotion
                promote(board[toCol][toRow]);
            }
        }
        else
        {
            //invalid selection
        }
        return true;
    }
    
    public Piece[][] getBoard()
    {
        return this.board;
    }
    
    public Piece getPiece(int col, int row)
    {
        if(this.board[col][row] == null)
        {
            return null;
        }
        else if(this.board[col][row].getColour() == ChessPieceColour.WHITE)
        {
            return whitepieces.getPiece(col, row);
        }
        else if(this.board[col][row].getColour() == ChessPieceColour.BLACK)
        {
            return blackpieces.getPiece(col, row);
        }
        return null;
    }
    
    public void addPiece(int column, int row, Piece piece)
    {
        if(piece.getColour() == ChessPieceColour.WHITE)
        {
            
            whitepieces.addPiece(piece);
        }
        else if(piece.getColour() == ChessPieceColour.BLACK)
        {
            blackpieces.addPiece(piece);
        }
    }
    
    public boolean[][] getCheckPath()
    {
        return this.checkPath;
    }
    
    public void setCheckPath(boolean[][] checkPath)
    {
        this.checkPath = checkPath;
    }
    
    public void clearBoard() 
    {
        for(int col = 0; col < 8; col++) 
        {
            for(int row = 0; row < 8; row++) 
            {
                board[col][row] = null;
            }
        }
    }
    
    public void clearAllPieces()
    {
        whitepieces.clearPieces();
        blackpieces.clearPieces();
    }
    
    public boolean isWinning()
    {
        return isCheckMate();
    }
    
    public boolean isDrawing()
    {
        return (isDeadPosition() || isStalemate() || isThreefoldRepetition() || isFiftyMoveRule());
    }
    
    public void resetBoard()
    {
        this.moveCounter = 0;
        whitepieces = new WhitePieces();
        blackpieces = new BlackPieces();
        resfreshBoard();
    }
    
    public void resfreshBoard()
    {
        allPieces.clear();
        allPieces.addAll(whitepieces.getAllPieces());
        allPieces.addAll(blackpieces.getAllPieces());
        clearBoard();
        for(Piece i : allPieces)
        {
            board[i.getColumn()][i.getRow()] = i;
        }
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
            whitepieces.getPiece(col, row).setLastMoveNum(moveCounter);
            whitepieces.getPiece(col, row).setFirstMove();
            whitepieces.getPiece(col, row).setColAndRow(toCol, 0);

            if(toCol == 2) //long castle
            {
                whitepieces.getPiece(0, 0).setFirstMove();
                whitepieces.getPiece(0, 0).setColAndRow(3, 0);
                resfreshBoard();
            }
            else if(toCol == 6) //short castle
            {
                whitepieces.getPiece(7, 0).setFirstMove();
                whitepieces.getPiece(7, 0).setColAndRow(5, 0);
                resfreshBoard();
            }
        }
        else if(king.getColour() == ChessPieceColour.BLACK)
        {
            moveCounter++;
            blackpieces.getPiece(col, row).setLastMoveNum(moveCounter);
            blackpieces.getPiece(col, row).setFirstMove();
            blackpieces.getPiece(col, row).setColAndRow(toCol, 7);

            if(toCol == 2) //long castle
            {
                blackpieces.getPiece(0, 7).setFirstMove();
                blackpieces.getPiece(0, 7).setColAndRow(3, 7);
                resfreshBoard();
            }
            else if(toCol == 6) //short castle
            {
                blackpieces.getPiece(7, 7).setFirstMove();
                blackpieces.getPiece(7, 7).setColAndRow(5, 7);
                resfreshBoard();
            }
        }
    }
    
    private boolean isEnPassant(Piece pawn, int toCol)
    {
        boolean availability = false;
        if(board[toCol][pawn.getRow()] != null)
        {
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
        }
        
        return availability;
    }
    
    private void enPassant(Piece pawn, int toCol)
    {
        int col = pawn.getColumn();
        
        if(pawn.getColour() == ChessPieceColour.WHITE)
        {
            moveCounter++;
            whitepieces.getPiece(col, 4).setLastMoveNum(moveCounter);
            whitepieces.getPiece(col, 4).setFirstMove();
            whitepieces.getPiece(col, 4).setColAndRow(toCol, 5);
            blackpieces.removePiece(toCol, 4);
            resfreshBoard();
        }
        else if(pawn.getColour() == ChessPieceColour.BLACK)
        {
            moveCounter++;
            blackpieces.getPiece(col, 3).setLastMoveNum(moveCounter);
            blackpieces.getPiece(col, 3).setFirstMove();
            blackpieces.getPiece(col, 3).setColAndRow(toCol, 2);
            whitepieces.removePiece(toCol, 3);
            resfreshBoard();
        }
    }
    
    private void promote(Piece pawn)
    {
        int col = pawn.getColumn();
        int row = pawn.getRow();
        Scanner scanner = new Scanner(System.in);
        
        if(pawn.getSymbol().equals("wP") && pawn.getColour() == ChessPieceColour.WHITE && pawn.getRow() == 7)
        {
            Piece promotion;
            System.out.println("Please enter your promotion: ");
            String userInput = scanner.nextLine();
            //player select which to promote
            if(userInput.equalsIgnoreCase("queen"))
            {
                promotion = new Queen(ChessPieceColour.WHITE, col, row);
                whitepieces.replacePiece(promotion, col, row);
            }
            else if(userInput.equalsIgnoreCase("bishop"))
            {
                promotion = new Bishop(ChessPieceColour.WHITE, col, row);
                whitepieces.replacePiece(promotion, col, row);
            }
            else if(userInput.equalsIgnoreCase("Knight"))
            {
                promotion = new Knight(ChessPieceColour.WHITE, col, row);
                whitepieces.replacePiece(promotion, col, row);
            }
            else if(userInput.equalsIgnoreCase("Rook"))
            {
                promotion = new Rook(ChessPieceColour.WHITE, col, row);
                whitepieces.replacePiece(promotion, col, row);
            }

            
        }
        else if(pawn.getSymbol().equals("bP") && pawn.getColour() == ChessPieceColour.BLACK && pawn.getRow() == 0)
        {
            Piece promotion;
            System.out.println("Please enter your promotion: ");
            String userInput = scanner.nextLine();
            //player select which to promote
            if(userInput.equalsIgnoreCase("queen"))
            {
                promotion = new Queen(ChessPieceColour.BLACK, col, row);
                whitepieces.replacePiece(promotion, col, row);
            }
            else if(userInput.equalsIgnoreCase("bishop"))
            {
                promotion = new Bishop(ChessPieceColour.BLACK, col, row);
                whitepieces.replacePiece(promotion, col, row);
            }
            else if(userInput.equalsIgnoreCase("Knight"))
            {
                promotion = new Knight(ChessPieceColour.BLACK, col, row);
                whitepieces.replacePiece(promotion, col, row);
            }
            else if(userInput.equalsIgnoreCase("Rook"))
            {
                promotion = new Rook(ChessPieceColour.BLACK, col, row);
                whitepieces.replacePiece(promotion, col, row);
            }
        }
    }
    
    private void checkPinAndCheck()
    {
        for(Piece i : allPieces)
        {
            i.setIsUnderPinned(false);
        }
        for(int col = 0; col < 8; col++)
        {
            for(int row = 0; row < 8; row++)
            {
                checkPath[col][row] = true;
            }
        }
        whitepieces.getTargetAreas();
        blackpieces.getTargetAreas();
    }
    
    private boolean isInCheck()
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
