package com.company.game.chesspieces;

import static com.company.Main.chess;
import static com.company.game.Chess.tempChessBoard;

public class Bishop{

    private int row, col;

    public Bishop(int row, int col){
    this.row = row;
    this.col = col;
    }

    public void showAround(int row, int col) {
        if (tempChessBoard[row][col] == 'b' || tempChessBoard[row][col] == 'B') {
            checkTopRight(row, col, true);
            checkTopLeft(row, col, true);
            checkBottomRight(row, col, true);
            checkBottomLeft(row, col, true);
        }
    }

    public void dontShowAround(int row, int col){
        if (tempChessBoard[row][col] == 'b'|| tempChessBoard[row][col] == 'B') {
            checkTopRight(row, col, false);
            checkTopLeft(row, col, false);
            checkBottomRight(row, col, false);
            checkBottomLeft(row, col, false);
        }
    }

    private void checkTopRight(int row, int col, boolean check){
        int r = row - 1;
        int c = col + 1;
        while (r >= 0){
            if(c <= 7) {
                if (tempChessBoard[r][c] == 's') {
                    moveIcon(r, c, check);
                }else {checkEnemy(row, col, r, c, check); break;}
            }
            r--;
            c++;
        }
    }

    private void checkTopLeft(int row, int col, boolean check){
        int r = row - 1;
        int c = col - 1;
        while (r >= 0){
            if(c >= 0) {
                if (tempChessBoard[r][c] == 's') {
                    moveIcon(r, c, check);
                }else {checkEnemy(row, col, r, c, check); break;}
            }
            r--;
            c--;
        }
    }

    private void checkBottomRight(int row, int col, boolean check){
        int r = row + 1;
        int c = col + 1;
        while (r <= 7){
            if(c <= 7) {
                if (tempChessBoard[r][c] == 's') {
                    moveIcon(r, c, check);
                }else {checkEnemy(row, col, r, c, check); break;}
            }
            r++;
            c++;
        }
    }

    private void checkBottomLeft(int row, int col, boolean check){
        int r = row + 1;
        int c = col - 1;
        while (r <= 7){
            if(c >= 0) {
                if (tempChessBoard[r][c] == 's') {
                    moveIcon(r, c, check);
                }else {checkEnemy(row, col, r, c, check); break;}
            }
            r++;
            c--;
        }
    }

    private void checkEnemy(int row, int col, int cRow, int cCol, boolean check) {
        if (tempChessBoard[row][col] == 'b'){
            if (tempChessBoard[cRow][cCol] >= 'A'
                    && tempChessBoard[cRow][cCol] <= 'Z'){
                chess.chessFrame.enemy(cRow, cCol, check);
            }
        }else if (tempChessBoard[row][col] == 'B') {
            if (tempChessBoard[cRow][cCol] >= 'a'
                    && tempChessBoard[cRow][cCol] <= 'z') {
                chess.chessFrame.enemy(cRow, cCol, check);
            }
        }
    }

    private void moveIcon(int row, int col, boolean check){
        if (check) chess.chessFrame.showLegalMoveIcon(row, col);
        else chess.chessFrame.dontShowLegalMoveIcon(row, col);
    }

    public void chessMove(int row, int col, int bRow, int bCol) {
        if (tempChessBoard[bRow][bCol] == 'b'){
            changePieces(row, col, bRow, bCol, false);
        }else if (tempChessBoard[bRow][bCol] == 'B'){
            changePieces(row, col, bRow, bCol, true);
        }
    }

    public void killChessPieces(int row, int col, int bRow, int bCol) {
        if (tempChessBoard[bRow][bCol] == 'b'){
            changePieces(row, col, bRow, bCol, false);
        }else if (tempChessBoard[bRow][bCol] == 'B'){
            changePieces(row, col, bRow, bCol, true);
        }
    }

    private void changePieces(int row, int col, int bRow, int bCol, boolean isWhite){
        if(!isWhite & tempChessBoard[row][col] != 'K'){
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'b';
            chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
        }else if (isWhite & tempChessBoard[row][col] != 'k'){
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'B';
            chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
        }else {
            dontShowAround(bRow, bCol);
        }

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

}
