/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chess_Project_1;

/**
 *
 * @author rh200
 */
public class Piece {
    
    private int row;
    private int column;
    private ChessPieceColour colour;
    
    public Piece(ChessPieceColour colour, int col, int row)
    {
        this.colour = colour;
        this.row = row;
        this.column = col;
    }
    
    public ChessPieceColour getColour()
    {
        return colour;
    }
    
    public void setColour(ChessPieceColour colour)
    {
        this.colour = colour;
    }
    
    public int getColumn()
    {
        return this.column;
    }
    
    public int getRow()
    {
        return this.row;
    }
    
    public void setColumn(int col)
    {
        this.column = col;
    }
    
    public void setRow(int row)
    {
        this.row = row;
    }
    
    public char getSymbol()
    {
        return '?';
    }
}
