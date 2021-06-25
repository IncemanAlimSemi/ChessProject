package com.company.game;


public class Chess {

    public ChessFrame chessFrame = new ChessFrame();

    private static char[][] chessBoard = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}
            , {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'}
            , {'s', 's', 's', 's', 's', 's', 's', 's'}
            , {'s', 's', 's', 's', 's', 's', 's', 's'}
            , {'s', 's', 's', 's', 's', 's', 's', 's'}
            , {'s', 's', 's', 's', 's', 's', 's', 's'}
            , {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'}
            , {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
    };

    public static char[][] tempChessBoard = chessBoard.clone();

    public void run(){
        chessFrame.runFrame();
    }

    public char[][] getTempChessBoard() {
        return tempChessBoard;
    }

    public void setTempChessBoard(char[][] tempChessBoard) {
        this.tempChessBoard = tempChessBoard;
    }

    private void printChessPieces() {

    }
}