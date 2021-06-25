package com.company.game.chesspieces;

import static com.company.Main.chess;
import static com.company.game.Chess.tempChessBoard;

public class Knight{

    private int row, col;

    public Knight(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void showAround(int row, int col) {
        if (tempChessBoard[row][col] == 'n' || tempChessBoard[row][col] == 'N') {
            checkRow(row, col, true);
            checkCol(row, col, true);
        }
    }

    public void dontShowAround(int row, int col){
        if (tempChessBoard[row][col] == 'n'|| tempChessBoard[row][col] == 'N') {
            checkRow(row, col, false);
            checkCol(row, col, false);
        }
    }

    private void checkRow(int row, int col, boolean check){
        if(row - 2 >= 0){
            if (col - 1 >= 0){
                if(tempChessBoard[row - 2][col - 1] == 's'){
                    moveIcon(row - 2, col - 1, check);
                }else {
                    checkEnemy(row, col, row - 2, col - 1, check);
                }
            }
            if(col + 1 <= 7){
                if(tempChessBoard[row - 2][col + 1] == 's'){
                    moveIcon(row - 2, col + 1, check);
                }else {
                    checkEnemy(row, col, row - 2, col + 1, check);
                }
            }
        }
        if(row + 2 <= 7){
            if (col - 1 >= 0){
                if(tempChessBoard[row + 2][col - 1] == 's') {
                    moveIcon(row + 2, col - 1, check);
                }else {
                    checkEnemy(row, col, row + 2, col - 1, check);
                }
            }
            if (col + 1 <= 7){
                if(tempChessBoard[row + 2][col + 1] == 's') {
                    moveIcon(row + 2, col + 1, check);
                }else {
                    checkEnemy(row, col, row + 2, col + 1, check);
                }
            }
        }
    }

    private void checkCol(int row, int col, boolean check){
        if(col - 2 >= 0){
            if (row + 1 <= 7){
                if(tempChessBoard[row + 1][col - 2] == 's'){
                    moveIcon(row + 1, col - 2, check);
                }else {
                    checkEnemy(row, col, row + 1, col - 2, check);
                }
            }
            if (row -1 >= 0){
                if(tempChessBoard[row - 1][col - 2] == 's'){
                    moveIcon(row - 1, col - 2, check);;
                }else {
                    checkEnemy(row, col, row - 1, col - 2, check);
                }
            }
        }
        if(col + 2 <= 7){
            if (row + 1 <= 7){
                if(tempChessBoard[row + 1][col + 2] == 's') {
                    moveIcon(row + 1, col + 2, check);
                }else {
                    checkEnemy(row, col, row + 1, col + 2, check);
                }
            }
            if (row -1 >= 0){
                if(tempChessBoard[row - 1][col + 2] == 's') {
                    moveIcon(row - 1, col + 2, check);
                }else {
                    checkEnemy(row, col, row - 1, col + 2, check);
                }
            }
        }
    }

    private void checkEnemy(int row, int col, int cRow, int cCol, boolean check) {
        if (tempChessBoard[row][col] == 'n'){
            if (tempChessBoard[cRow][cCol] >= 'A'
                    && tempChessBoard[cRow][cCol] <= 'Z'){
                chess.chessFrame.enemy(cRow, cCol, check);
            }
        }else if (tempChessBoard[row][col] == 'N') {
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
        if(tempChessBoard[bRow][bCol] == 'n'){
            changePieces(row, col, bRow, bCol, false);
        }else if (tempChessBoard[bRow][bCol] == 'N'){
            changePieces(row, col, bRow, bCol, true);
        }
    }

    public void killChessPieces(int row, int col, int bRow, int bCol) {
        if (tempChessBoard[bRow][bCol] == 'n'){
            changePieces(row, col, bRow, bCol, false);
        }else if (tempChessBoard[bRow][bCol] == 'N'){
            changePieces(row, col, bRow, bCol, true);
        }
    }

    private void changePieces(int row, int col, int bRow, int bCol, boolean isWhite){
        if(!isWhite & tempChessBoard[row][col] != 'K'){
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'n';
            chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
        }else if (isWhite & tempChessBoard[row][col] != 'k'){
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'N';
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
