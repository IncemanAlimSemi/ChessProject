package com.company.game.chesspieces;

import static com.company.Main.chess;
import static com.company.game.Chess.tempChessBoard;

public class Queen{

    private int row, col;

    public Queen(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void showAround(int row, int col) {
        if (tempChessBoard[row][col] == 'q' || tempChessBoard[row][col] == 'Q') {
            checkRow(row, col, true);
            checkCol(row, col, true);
            checkTopRight(row, col, true);
            checkTopLeft(row, col, true);
            checkBottomRight(row, col, true);
            checkBottomLeft(row, col, true);
        }
    }

    public void dontShowAround(int row, int col){
        if (tempChessBoard[row][col] == 'q'|| tempChessBoard[row][col] == 'Q') {
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
            for (int r = row + 1; r < 8; r++) {
                if (chess.tempChessBoard[r][col] == 's') {
                    moveIcon(r, col, check);
                }else {checkEnemy(row, col, r, col, check); break;}
            }
        }
        if(row - 1 >= 0){
            for (int r = row - 1; r >= 0; r--){
                if (chess.tempChessBoard[r][col] == 's') {
                    moveIcon(r, col, check);
                }else {checkEnemy(row, col, r, col, check); break;}
            }
        }
    }

    private void checkCol(int row, int col, boolean check){
        if(col + 1 <= 7){
            for (int c = col + 1; c < 8; c++) {
                if (chess.tempChessBoard[row][c] == 's') {
                    moveIcon(row, c, check);
                } else {checkEnemy(row, col, row, c, check); break;}
            }
        }
        if(col - 1 >= 0){
            for (int c = col - 1; c >= 0; c--){
                if (chess.tempChessBoard[row][c] == 's') {
                        moveIcon(row, c, check);
                }else {checkEnemy(row, col, row, c, check); break;}
            }
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
        if (tempChessBoard[row][col] == 'q'){
            if (tempChessBoard[cRow][cCol] >= 'A'
                    && tempChessBoard[cRow][cCol] <= 'Z'){
                chess.chessFrame.enemy(cRow, cCol, check);
            }
        }else if (tempChessBoard[row][col] == 'Q') {
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
        if(tempChessBoard[bRow][bCol] == 'q'){
            changePieces(row, col, bRow, bCol, false);
        }else if (tempChessBoard[bRow][bCol] == 'Q'){
            changePieces(row, col, bRow, bCol, true);
        }
    }

    public void killChessPieces(int row, int col, int bRow, int bCol) {
        if (tempChessBoard[bRow][bCol] == 'q'){
            changePieces(row, col, bRow, bCol, false);
        }else if (tempChessBoard[bRow][bCol] == 'Q'){
            changePieces(row, col, bRow, bCol, true);
        }
    }

    private void changePieces(int row, int col, int bRow, int bCol, boolean isWhite){
        if(!isWhite & tempChessBoard[row][col] != 'K'){
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'q';
            chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
        }else if (isWhite & tempChessBoard[row][col] != 'k'){
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'Q';
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
