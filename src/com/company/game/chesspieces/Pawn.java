package com.company.game.chesspieces;

import com.company.Main;
import com.company.game.Chess;

import static com.company.game.Chess.tempChessBoard;

public class Pawn{

    public Chess chess = Main.chess;
    private int row, col;
    private boolean firstMove;

    public Pawn(int row, int col){
        this.row = row;
        this.col = col;
        this.firstMove = false;
    }

    public void showAround(int row, int col) {
        if (chess.tempChessBoard[row][col] == 'p') {
            checkBlackRow(row + 1, col, row + 2, true, chess.chessFrame.pawns[row][col].isFirstMove());
            checkBlackBottomRight(row, col, true);
            checkBlackBottomLeft(row, col, true);
        }else if(chess.tempChessBoard[row][col] == 'P') {
            checkWhiteRow(row - 1, col, row - 2, true, chess.chessFrame.pawns[row][col].isFirstMove());
            checkWhiteTopRight(row, col, true);
            checkWhiteTopLeft(row, col, true);
        }
    }

    public void dontShowAround(int row, int col) {
        if (chess.tempChessBoard[row][col] == 'p') {
            checkBlackRow(row + 1, col, row + 2, false, chess.chessFrame.pawns[row][col].isFirstMove());
            checkBlackBottomRight(row, col, false);
            checkBlackBottomLeft(row, col, false);
        }else if(chess.tempChessBoard[row][col] == 'P') {
            checkWhiteRow(row - 1, col, row - 2, false, chess.chessFrame.pawns[row][col].isFirstMove());
            checkWhiteTopRight(row, col, false);
            checkWhiteTopLeft(row, col, false);
        }
    }

    private void checkWhiteRow(int row, int col, int sRow, boolean check, boolean firstMove) {
        if (row >= 0) {
            if (chess.tempChessBoard[row][col] == 's') {
                moveIcon(row, col, check);
                if (sRow >= 0) {
                    if (chess.tempChessBoard[sRow][col] == 's' & firstMove == false) {
                        moveIcon(sRow, col, check);
                    }
                }
            }
        }
    }

    private void checkBlackRow(int row, int col, int sRow, boolean check, boolean firstMove){
        if(row <= 7){
            if(chess.tempChessBoard[row][col] == 's'){
                moveIcon(row, col, check);
                if (sRow <= 7) {
                    if (chess.tempChessBoard[sRow][col] == 's' & firstMove == false) {
                        moveIcon(sRow, col, check);
                    }
                }
            }
        }
    }

    private void checkWhiteTopRight(int row, int col, boolean isShow){
        if (row - 1 >= 0 & col + 1 <= 7){
            if (tempChessBoard[row - 1][col + 1] != 's' & (tempChessBoard[row - 1][col + 1] >= 'a' && tempChessBoard[row - 1][col + 1] <= 'z')){
                chess.chessFrame.enemy(row - 1, col + 1, isShow);
            }
        }
    }

    private void checkWhiteTopLeft(int row, int col, boolean isShow){
        if (row - 1 >= 0 & col - 1 >= 0){
            if (tempChessBoard[row - 1][col - 1] != 's' & (tempChessBoard[row - 1][col - 1] >= 'a' && tempChessBoard[row - 1][col - 1] <= 'z')){
                chess.chessFrame.enemy(row - 1, col - 1, isShow);
            }
        }
    }

    private void checkBlackBottomRight(int row, int col, boolean isShow){
        if (row + 1 <= 7 & col + 1 <= 7){
            if (tempChessBoard[row + 1][col + 1] != 's' & (tempChessBoard[row + 1][col + 1] >= 'A' && tempChessBoard[row + 1][col + 1] <= 'Z')){
                chess.chessFrame.enemy(row + 1, col + 1, isShow);
            }
        }
    }

    private void checkBlackBottomLeft(int row, int col, boolean isShow){
        if (row + 1 <= 7 & col - 1 >= 0){
            if (tempChessBoard[row + 1][col - 1] != 's' & (tempChessBoard[row + 1][col - 1] >= 'A' && tempChessBoard[row + 1][col - 1] <= 'Z')){
                chess.chessFrame.enemy(row + 1, col - 1, isShow);
            }
        }
    }

    private void moveIcon(int row, int col, boolean check){
        if (check) chess.chessFrame.showLegalMoveIcon(row, col);
        else chess.chessFrame.dontShowLegalMoveIcon(row, col);
    }

    public void chessMove(int row, int col, int bRow,int bCol){
        if(!isFirstMove()) {
            if (tempChessBoard[bRow][bCol] == 'p' & (bRow + 1 == row || bRow + 2 == row) & bCol == col
                    & tempChessBoard[row][col] == 's') {
                if (bRow + 1 == row){
                    changePieces(row, col, bRow, bCol, false);
                }else if (bRow + 2 == row & tempChessBoard[bRow + 1][col] == 's'){
                    changePieces(row, col, bRow, bCol, false);
                }
            } else if (tempChessBoard[bRow][bCol] == 'P' & (bRow - 1 == row || bRow - 2 == row) & bCol == col
                    & tempChessBoard[row][col] == 's') {
                if (bRow - 1 == row){
                    changePieces(row, col, bRow, bCol, true);
                }else if (bRow - 2 == row & tempChessBoard[bRow - 1][col] == 's'){
                    changePieces(row, col, bRow, bCol, true);
                }
            }
        }else {
            if (tempChessBoard[bRow][bCol] == 'p' & bRow + 1 == row & bCol == col & tempChessBoard[row][col] == 's'){
                if (bRow + 1 == 7){
                    dontShowAround(bRow, bCol);
                    chess.chessFrame.changePawn(row, col);
                }else {
                    changePieces(row, col, bRow, bCol, false);
                }
            }else if (tempChessBoard[bRow][bCol] == 'P' & bRow - 1 == row & bCol == col & tempChessBoard[row][col] == 's'){
                if (bRow - 1 == 0){
                    dontShowAround(bRow, bCol);
                    chess.chessFrame.changePawn(row, col);
                }else {
                    changePieces(row, col, bRow, bCol, true);
                }
            }
        }
    }

    private void regenerateBoard(int row, int col, int bRow, int bCol){
        chess.chessFrame.pawns[bRow][bCol].setFirstMove(true);
        chess.chessFrame.pawns[row][col] = chess.chessFrame.pawns[bRow][bCol];
        chess.chessFrame.pawns[bRow][bCol] = null;
        chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
    }

    public void killChessPieces(int row, int col, int bRow, int bCol) {
        if (tempChessBoard[bRow][bCol] == 'p'){
            if (row == 7){
                dontShowAround(bRow, bCol);
                chess.chessFrame.changePawn(row, col);
            }else {
                changePieces(row, col, bRow, bCol, false);
            }
        }else if (tempChessBoard[bRow][bCol] == 'P'){
            if (row == 0){
                dontShowAround(bRow, bCol);
                chess.chessFrame.changePawn(row, col);
            }else {
                changePieces(row, col, bRow, bCol, true);
            }
        }
    }

    private void changePieces(int row, int col, int bRow, int bCol, boolean isWhite){
        if(!isWhite & tempChessBoard[row][col] != 'K'){
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'p';
            regenerateBoard(row, col, bRow, bCol);
        }else if (isWhite & tempChessBoard[row][col] != 'k'){
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'P';
            regenerateBoard(row, col, bRow, bCol);
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

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

}
