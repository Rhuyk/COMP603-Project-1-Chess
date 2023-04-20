/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_project_1_chess;

/**
 *
 * @author rh200
 */
public class Piece {
    private char column;
    private int row;
    
    public Piece(char col, int row)
    {
        this.column = col;
        this.row = row;
    }
    
    public char getColumn()
    {
        return this.column;
    }
    
    public int getRow()
    {
        return this.row;
    }
    
    public void setColumn(char col)
    {
        this.column = col;
    }
    
    public void setRow(int row)
    {
        this.row = row;
    }
}
