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
    private static boolean whiteIsInCheck = false;
    private static boolean blackIsInCheck = false;
    
    //Board Constructor
    public PiecesOnBoard()
    {
        refreshBoard();
    }
    
    //move a piece on the board from square to square
    public boolean movePiece(int fromCol, int fromRow, int toCol, int toRow)
    {
        Piece selectedPiece = board[fromCol][fromRow];
        if(selectedPiece != null)
        {
            refreshPiecesStatus();
            //white piece selected
            if(selectedPiece.getColour() == ChessPieceColour.WHITE)
            {
                //king move
                if(selectedPiece.getSymbol().equals("wK"))
                {
                    //normal move
                    if(!blackpieces.getTargetAreas()[toCol][toRow] 
                            && whitepieces.getAvailableMoves(selectedPiece)[toCol][toRow])
                    {
                        moveCounter++;
                        whitepieces.getPiece(fromCol, fromRow).setLastMoveNum(moveCounter);
                        whitepieces.getPiece(fromCol, fromRow).setMove();
                        whitepieces.getPiece(fromCol, fromRow).setColAndRow(toCol, toRow);
                        blackpieces.removePiece(toCol, toRow);
                        refreshBoard();
                    }
                    //castle move
                    else if(isCastling(selectedPiece, toCol) && toRow == 0)
                    {
                        castle(selectedPiece, toCol);
                    }
                    //unavailable move
                    else
                    {
                        System.out.println("That was a Illegal move. Please enter a new one!");
                        return false;
                    }
                }
                //en passant move
                else if(isEnPassant(selectedPiece, toCol) && toRow == 5)
                {
                    enPassant(selectedPiece, toCol);
                }
                //other pieces move
                else
                {
                    if(whitepieces.getAvailableMoves(selectedPiece)[toCol][toRow] && checkPath[toCol][toRow])
                    {
                        moveCounter++;
                        whitepieces.getPiece(fromCol, fromRow).setLastMoveNum(moveCounter);
                        whitepieces.getPiece(fromCol, fromRow).setMove();
                        whitepieces.getPiece(fromCol, fromRow).setColAndRow(toCol, toRow);
                        blackpieces.removePiece(toCol, toRow);
                        refreshBoard();
                    }
                    //unavailable move
                    else
                    {
                        System.out.println("That was a Illegal move. Please enter a new one!");
                        return false;
                    }
                }
            }
            
            //black piece selected
            else if(selectedPiece.getColour() == ChessPieceColour.BLACK)
            {
                //black king move
                if(selectedPiece.getSymbol().equals("bK"))
                {
                    //normal move
                    if(!whitepieces.getTargetAreas()[toCol][toRow] 
                            && blackpieces.getAvailableMoves(selectedPiece)[toCol][toRow])
                    {
                        moveCounter++;
                        blackpieces.getPiece(fromCol, fromRow).setLastMoveNum(moveCounter);
                        blackpieces.getPiece(fromCol, fromRow).setMove();
                        blackpieces.getPiece(fromCol, fromRow).setColAndRow(toCol, toRow);
                        whitepieces.removePiece(toCol, toRow);
                        refreshBoard();
                    }
                    //castle move
                    else if(isCastling(selectedPiece, toCol) && toRow == 7)
                    {
                        castle(selectedPiece, toCol);
                    }
                    //unavailable move
                    else
                    {
                        System.out.println("That was a Illegal move. Please enter a new one!");
                        return false;
                    }
                }
                //en passant move
                else if(isEnPassant(selectedPiece, toCol) && toRow == 2)
                {
                    enPassant(selectedPiece, toCol);
                }
                //other pieces move
                else
                {
                    if(blackpieces.getAvailableMoves(selectedPiece)[toCol][toRow] && checkPath[toCol][toRow])
                    {
                        moveCounter++;
                        blackpieces.getPiece(fromCol, fromRow).setLastMoveNum(moveCounter);
                        blackpieces.getPiece(fromCol, fromRow).setMove();
                        blackpieces.getPiece(fromCol, fromRow).setColAndRow(toCol, toRow);
                        whitepieces.removePiece(toCol, toRow);
                        refreshBoard();
                    }
                    //unavailable move
                    else
                    {
                        System.out.println("That was a Illegal move. Please enter a new one!");
                        return false;
                    }
                }
            }
            //pawn promotion
            if(board[toCol][toRow] != null)
            {
                promote(board[toCol][toRow]);
            }
        }
        else
        {
            //invalid selection
        }
        return true;
    }
    
    //return the board
    public Piece[][] getBoard()
    {
        return this.board;
    }
    
    //return piece from a selected square on the board
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
    
    //add a piece to white or black pieces list
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
    
    //return an area on the board of a piece checking a king (check path)
    public boolean[][] getCheckPath()
    {
        refreshPiecesStatus();
        return this.checkPath;
    }
    
    //set white or black in check to true, and save the check path.
    public void setInCheck(ChessPieceColour colour, boolean[][] checkPath)
    {
        this.checkPath = checkPath;
        if(colour == ChessPieceColour.WHITE)
        {
            this.whiteIsInCheck = true;
        }
        else if(colour == ChessPieceColour.BLACK)
        {
            this.blackIsInCheck = true;
        }
    }
    
    //return true if white is in check, else false
    public boolean whiteIsInCheck()
    {
        return whiteIsInCheck;
    }
    
    //return true if black is in check, else false
    public boolean blackIsInCheck()
    {
        return blackIsInCheck;
    }
    
    //clear all pieces from the board
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
    
    //clear all pieces from all the pieces lists
    public void clearAllPieces()
    {
        whitepieces.clearPieces();
        blackpieces.clearPieces();
    }
    
    //reset the board and the pieces lists
    public void resetBoard()
    {
        this.moveCounter = 0;
        whitepieces = new WhitePieces();
        blackpieces = new BlackPieces();
        refreshBoard();
    }
    
    //update the board to the pieces lists
    public void refreshBoard()
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
    
    //return true if a selected colour piece is checkmated, else false
    public boolean isCheckmate(ChessPieceColour colour)
    {
        refreshPiecesStatus();
        boolean isCheckmate = false;
        //if white colour is selected and is in check
        if(whiteIsInCheck && colour == ChessPieceColour.WHITE)
        {
            isCheckmate = true;
            //no checkmate if any piece other than king can stop the check
            for(Piece i : whitepieces.getAllPieces())
            {
                for(int col = 0; col < 8; col++)
                {
                    for(int row = 0; row < 8; row++)
                    {
                        if(i.getAvailableMoves()[col][row] && checkPath[col][row] && !i.getSymbol().equals("wK"))
                        {
                            isCheckmate = false;
                        }
                    }
                }
            }
            //no checkmate if king still have available move
            for(int col = 0; col < 8; col++)
            {
                for(int row = 0; row < 8; row++)
                {
                    if(!blackpieces.getTargetAreas()[col][row] 
                            && whitepieces.getKing().getAvailableMoves()[col][row])
                    {
                        isCheckmate = false;
                    }
                }
            }
        }
        //if black colour is selected and is in check
        else if(blackIsInCheck && colour == ChessPieceColour.BLACK)
        {
            isCheckmate = true;
            //no checkmate if any piece other than king can stop the check
            for(Piece i : blackpieces.getAllPieces())
            {
                for(int col = 0; col < 8; col++)
                {
                    for(int row = 0; row < 8; row++)
                    {
                        if(i.getAvailableMoves()[col][row] && checkPath[col][row] && !i.getSymbol().equals("bK"))
                        {
                            isCheckmate = false;
                        }
                    }
                }
            }
            //no checkmate if king still have available move
            for(int col = 0; col < 8; col++)
            {
                for(int row = 0; row < 8; row++)
                {
                    if(!whitepieces.getTargetAreas()[col][row] 
                            && blackpieces.getKing().getAvailableMoves()[col][row])
                    {
                        isCheckmate = false;
                    }
                }
            }
        }
        return isCheckmate;
    }
    
    //return true if a selected colour piece is stalemated, else false
    public boolean isStalemate(ChessPieceColour colour)
    {
        refreshPiecesStatus();
        boolean isStalemate = false;
        //if white colour is selected and is not in check
        if(!whiteIsInCheck && colour == ChessPieceColour.WHITE)
        {
            isStalemate = true;
            //no stalemate if any piece other than king can still move
            for(Piece i : whitepieces.getAllPieces())
            {
                for(int col = 0; col < 8; col++)
                {
                    for(int row = 0; row < 8; row++)
                    {
                        if(i.getAvailableMoves()[col][row] && !i.getSymbol().equals("wK"))
                        {
                            isStalemate = false;
                        }
                    }
                }
            }
            //no stalemate if king can still move
            for(int col = 0; col < 8; col++)
            {
                for(int row = 0; row < 8; row++)
                {
                    if(!blackpieces.getTargetAreas()[col][row] 
                            && whitepieces.getKing().getAvailableMoves()[col][row])
                    {
                        isStalemate = false;
                    }
                }
            }
        }
        //if black colour is selected and is not in check
        else if(!blackIsInCheck && colour == ChessPieceColour.BLACK)
        {
            isStalemate = true;
            //no stalemate if any piece other than king can still move
            for(Piece i : blackpieces.getAllPieces())
            {
                for(int col = 0; col < 8; col++)
                {
                    for(int row = 0; row < 8; row++)
                    {
                        if(i.getAvailableMoves()[col][row] && !i.getSymbol().equals("bK"))
                        {
                            isStalemate = false;
                        }
                    }
                }
            }
            //no stalemate if king can still move
            for(int col = 0; col < 8; col++)
            {
                for(int row = 0; row < 8; row++)
                {
                    if(!whitepieces.getTargetAreas()[col][row] 
                            && blackpieces.getKing().getAvailableMoves()[col][row])
                    {
                        isStalemate = false;
                    }
                }
            }
        }
        return isStalemate;
    }
    
    //return true is castling is avaialable, else false
    private boolean isCastling(Piece king, int toCol)
    {
        boolean availability = false;
        //if king has not moved yet
        if(king.hasNotMoved())
        {
            //long castle: if the rook has not moved yet and the castle path has no other pieces
            if(toCol == 2 && board[0][king.getRow()].hasNotMoved() 
                    && board[1][king.getRow()] == null && board[2][king.getRow()] == null && board[3][king.getRow()] == null)
            {
                availability = true;
                //if the selected piece is white
                if(king.getColour() == ChessPieceColour.WHITE)
                {
                    //check if king, rook, and their path are not being targeted
                    for(int col = 0; col <= 4; col++)
                    {
                        if(blackpieces.getTargetAreas()[col][0])
                        {
                            availability = false;
                        }
                    }
                }
                //if the selected piece is black
                else if(king.getColour() == ChessPieceColour.BLACK)
                {
                    for(int col = 0; col <= 4; col++)
                    {
                        //check if king, rook, and their path are not being targeted
                        if(whitepieces.getTargetAreas()[col][7])
                        {
                            availability = false;
                        }
                    }
                }
            }
            //short castle: if the rook has not moved yet and the castle path has no other pieces
            else if(toCol == 6 && board[7][king.getRow()].hasNotMoved() 
                    && board[5][king.getRow()] == null && board[6][king.getRow()] == null)
            {
                availability = true;
                //if the selected piece is white
                if(king.getColour() == ChessPieceColour.WHITE)
                {
                    for(int col = 7; col >= 4; col--)
                    {
                        //check if king, rook, and their path are not being targeted
                        if(blackpieces.getTargetAreas()[col][0])
                        {
                            availability = false;
                        }
                    }
                }
                //if the selected piece is black
                else if(king.getColour() == ChessPieceColour.BLACK)
                {
                    for(int col = 7; col >= 4; col--)
                    {
                        //check if king, rook, and their path are not being targeted
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
    
    //perform castling
    private void castle(Piece king, int toCol)
    {
        int col = king.getColumn();
        int row = king.getRow();
        
        //white king selected
        if(king.getColour() == ChessPieceColour.WHITE)
        {
            moveCounter++;
            whitepieces.getPiece(col, row).setLastMoveNum(moveCounter);
            whitepieces.getPiece(col, row).setMove();
            whitepieces.getPiece(col, row).setColAndRow(toCol, 0);

            if(toCol == 2) //long castle
            {
                whitepieces.getPiece(0, 0).setMove();
                whitepieces.getPiece(0, 0).setColAndRow(3, 0);
            }
            else if(toCol == 6) //short castle
            {
                whitepieces.getPiece(7, 0).setMove();
                whitepieces.getPiece(7, 0).setColAndRow(5, 0);
            }
        }
        //black king selected
        else if(king.getColour() == ChessPieceColour.BLACK)
        {
            moveCounter++;
            blackpieces.getPiece(col, row).setLastMoveNum(moveCounter);
            blackpieces.getPiece(col, row).setMove();
            blackpieces.getPiece(col, row).setColAndRow(toCol, 7);

            if(toCol == 2) //long castle
            {
                blackpieces.getPiece(0, 7).setMove();
                blackpieces.getPiece(0, 7).setColAndRow(3, 7);
            }
            else if(toCol == 6) //short castle
            {
                blackpieces.getPiece(7, 7).setMove();
                blackpieces.getPiece(7, 7).setColAndRow(5, 7);
            }
        }
        refreshBoard();
    }
    
    //return true if en passant move is available, else false
    private boolean isEnPassant(Piece pawn, int toCol)
    {
        boolean availability = false;
        //if the target square if not empty
        if(board[toCol][pawn.getRow()] != null)
        {
            /* if selected piece is white pawn at row 5
             * and the target piece is black pawn
             * and that black pawn advanced two squares in the previous move
             */
            if(pawn.getSymbol().equals("wP") && pawn.getRow() == 4 
                    && board[toCol][pawn.getRow()].getSymbol().equals("bP") 
                    && board[toCol][pawn.getRow()].getLastMoveNum() == this.moveCounter 
                    && board[toCol][pawn.getRow()].hasMovedOnce())
            {
                availability = true;
            }
            /* if selected piece is black pawn at row 4
             * and the target piece is white pawn
             * and that white pawn advanced two squares in the previous move
             */
            else if(pawn.getSymbol().equals("bP") && pawn.getRow() == 3 
                    && board[toCol][pawn.getRow()].getSymbol().equals("wP") 
                    && board[toCol][pawn.getRow()].getLastMoveNum() == this.moveCounter 
                    && board[toCol][pawn.getRow()].hasMovedOnce())
            {
                availability = true;
            }
        }
        
        return availability;
    }
    
    //perform en passant move
    private void enPassant(Piece pawn, int toCol)
    {
        int col = pawn.getColumn();
        
        //if selected pawn is white
        if(pawn.getColour() == ChessPieceColour.WHITE)
        {
            moveCounter++;
            whitepieces.getPiece(col, 4).setLastMoveNum(moveCounter);
            whitepieces.getPiece(col, 4).setMove();
            whitepieces.getPiece(col, 4).setColAndRow(toCol, 5);
            blackpieces.removePiece(toCol, 4);
            refreshBoard();
        }
        //if selected pawn is black
        else if(pawn.getColour() == ChessPieceColour.BLACK)
        {
            moveCounter++;
            blackpieces.getPiece(col, 3).setLastMoveNum(moveCounter);
            blackpieces.getPiece(col, 3).setMove();
            blackpieces.getPiece(col, 3).setColAndRow(toCol, 2);
            whitepieces.removePiece(toCol, 3);
            refreshBoard();
        }
    }
    
    //promote pawn to other piece
    private void promote(Piece pawn)
    {
        int col = pawn.getColumn();
        int row = pawn.getRow();
        Scanner scanner = new Scanner(System.in);
        
        //if white pawn reach the end of the board
        if(pawn.getSymbol().equals("wP") && pawn.getColour() == ChessPieceColour.WHITE && pawn.getRow() == 7)
        {
            Piece promotion;
            System.out.println("Please enter your promotion: ");
            String userInput = scanner.nextLine();
            
            //player select promotion piece
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
        //if black pawn reach the end of the board
        else if(pawn.getSymbol().equals("bP") && pawn.getColour() == ChessPieceColour.BLACK && pawn.getRow() == 0)
        {
            Piece promotion;
            System.out.println("Please enter your promotion: ");
            String userInput = scanner.nextLine();
            
            //player select promotion piece
            if(userInput.equalsIgnoreCase("queen"))
            {
                promotion = new Queen(ChessPieceColour.BLACK, col, row);
                blackpieces.replacePiece(promotion, col, row);
            }
            else if(userInput.equalsIgnoreCase("bishop"))
            {
                promotion = new Bishop(ChessPieceColour.BLACK, col, row);
                blackpieces.replacePiece(promotion, col, row);
            }
            else if(userInput.equalsIgnoreCase("Knight"))
            {
                promotion = new Knight(ChessPieceColour.BLACK, col, row);
                blackpieces.replacePiece(promotion, col, row);
            }
            else if(userInput.equalsIgnoreCase("Rook"))
            {
                promotion = new Rook(ChessPieceColour.BLACK, col, row);
                blackpieces.replacePiece(promotion, col, row);
            }
        }
        refreshBoard();
    }
    
    //set isInCheck, isUnderPin, and checkPath back to default then update again
    private void refreshPiecesStatus()
    {
        whiteIsInCheck = false;
        blackIsInCheck = false;
        for(Piece i : allPieces)
        {
            i.setUnderPin(false);
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
}
