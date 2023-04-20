/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class Bishop extends Piece{
    
    public Bishop(ChessPieceColour colour,int row, int col)
    {
        super(colour, col, row);
    }
    
    @Override
    public char getSymbol()
    {
        return 'B';
    }
}