/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_project_1_chess;

/**
 *
 * @author rh200
 */
public class WhitePieces extends ChessPieces {
    
    public WhitePieces()
    {
        this.king = new King('e',1);
        this.queen = new Queen('d',1);
        this.bishop1 = new Bishop('c',1);
        this.bishop2 = new Bishop('f',1);
        this.knight1 = new Knight('b',1);
        this.knight2 = new Knight('g',1);
        this.rook1 = new Rook('a',1);
        this.rook2 = new Rook('h',1);
        this.pawn1 = new Pawn('a',2);
        this.pawn2 = new Pawn('b',2);
        this.pawn3 = new Pawn('c',2);
        this.pawn4 = new Pawn('d',2);
        this.pawn5 = new Pawn('e',2);
        this.pawn6 = new Pawn('f',2);
        this.pawn7 = new Pawn('g',2);
        this.pawn8 = new Pawn('h',2);
    }
}
