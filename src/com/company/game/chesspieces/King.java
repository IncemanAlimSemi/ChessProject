package com.company.game.chesspieces;

import static com.company.Main.chess;
import static com.company.game.Chess.tempChessBoard;

public class King{

    private int row, col;
    private boolean firstMove;

    public King(int row, int col) {
        this.row = row;
        this.col = col;
        this.firstMove = false;
    }

    public void showAround(int row, int col) {
        if (tempChessBoard[row][col] == 'k' || tempChessBoard[row][col] == 'K') {
            checkRow(row, col, true);
            checkCol(row, col, true);
            checkTopRight(row, col, true);
            checkTopLeft(row, col, true);
            checkBottomRight(row, col, true);
            checkBottomLeft(row, col, true);
        }
    }

    public void dontShowAround(int row, int col){
        if (tempChessBoard[row][col] == 'k'|| tempChessBoard[row][col] == 'K') {
            checkRow(row, col, false);
            checkCol(row, col, false);
            checkTopRight(row, col, false);
            checkTopLeft(row, col, false);
            checkBottomRight(row, col, false);
            checkBottomLeft(row, col, false);
        }
    }

    private void checkRow(int row, int col, boolean check){
        if(row + 1 <= 7){
            if (tempChessBoard[row + 1][col] == 's') {
                moveIcon(row + 1, col, check);
            }else checkEnemy(row, col, row + 1, col, check);
        }
        if(row - 1 >= 0){
            if (tempChessBoard[row - 1][col] == 's') {
                moveIcon(row - 1, col, check);
            }else checkEnemy(row, col, row - 1, col, check);
        }
    }

    private void checkCol(int row, int col, boolean check){
        if(col + 1 <= 7){
            if (tempChessBoard[row][col + 1] == 's') {
                moveIcon(row, col + 1, check);
            }else checkEnemy(row, col, row, col + 1, check);
        }
        if(col - 1 >= 0){
            if (tempChessBoard[row][col - 1] == 's') {
                moveIcon(row, col - 1, check);
            }else checkEnemy(row, col, row, col - 1, check);
        }
    }

    private void checkTopRight(int row, int col, boolean check){
        if(row - 1 >= 0 & col + 1 <= 7) {
            if (tempChessBoard[row - 1][col + 1] == 's') {
                moveIcon(row - 1, col + 1, check);
            }else checkEnemy(row, col, row - 1, col + 1, check);
        }
    }

    private void checkTopLeft(int row, int col, boolean check){
        if(row - 1 >= 0 & col - 1 >= 0) {
            if (tempChessBoard[row - 1][col - 1] == 's') {
                moveIcon(row - 1, col - 1, check);
            }else checkEnemy(row, col, row - 1, col - 1, check);
        }
    }

    private void checkBottomRight(int row, int col, boolean check){
        if(row + 1 <= 7 & col + 1 <= 7) {
            if (tempChessBoard[row + 1][col + 1] == 's') {
                moveIcon(row + 1, col + 1, check);
            }else checkEnemy(row, col, row + 1, col + 1, check);
        }
    }

    private void checkBottomLeft(int row, int col, boolean check){
        if(row + 1 <= 7 & col - 1 >= 0) {
            if (tempChessBoard[row + 1][col - 1] == 's') {
                moveIcon(row + 1, col - 1, check);
            }else checkEnemy(row, col, row + 1, col - 1, check);
        }
    }

    private void checkEnemy(int row, int col, int cRow, int cCol, boolean check) {
        if (tempChessBoard[row][col] == 'k'){
            if (tempChessBoard[cRow][cCol] >= 'A'
                    && tempChessBoard[cRow][cCol] <= 'Z'){
                chess.chessFrame.enemy(cRow, cCol, check);
            }
        }else if (tempChessBoard[row][col] == 'K') {
            if (tempChessBoard[cRow][cCol] >= 'a'
                    && tempChessBoard[cRow][cCol] <= 'z') {
                chess.chessFrame.enemy(cRow, cCol, check);
            }
        }
    }

    private void regenerateBoard(int row, int col, int bRow, int bCol){
        chess.chessFrame.kings[bRow][bCol].setFirstMove(true);
        chess.chessFrame.kings[row][col] = chess.chessFrame.kings[bRow][bCol];
        chess.chessFrame.kings[bRow][bCol] = null;
        chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
    }

    private void moveIcon(int row, int col, boolean check){
        if (check) chess.chessFrame.showLegalMoveIcon(row, col);
        else chess.chessFrame.dontShowLegalMoveIcon(row, col);
    }

    public void chessMove(int row, int col, int bRow, int bCol) {
        if (tempChessBoard[bRow][bCol] == 'k'){
            changePieces(row, col, bRow, bCol, false);
        }else if(tempChessBoard[bRow][bCol] == 'K'){
            changePieces(row, col, bRow, bCol, true);
        }
    }

    public void killChessPieces(int row, int col, int bRow, int bCol) {
        if (tempChessBoard[bRow][bCol] == 'k'){
            changePieces(row, col, bRow, bCol, false);
        }else if (tempChessBoard[bRow][bCol] == 'K'){
            changePieces(row, col, bRow, bCol, true);
        }
    }

    private void changePieces(int row, int col, int bRow, int bCol, boolean isWhite){
        if(!isWhite){
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'k';
        }else {
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'K';
        }
        regenerateBoard(row, col, bRow, bCol);
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

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
}
