package com.company.game.chesspieces;

import com.company.Main;
import com.company.game.Chess;

import static com.company.game.Chess.tempChessBoard;

public class Rook{

    public Chess chess = Main.chess;
    public King[][] kings = Main.chess.chessFrame.kings;
    private int row, col;
    private boolean firstMove;
    private boolean king;

    public Rook(int row, int col) {
        this.row = row;
        this.col = col;
        this.firstMove = false;
    }

    public void showAround(int row, int col) {
        if (tempChessBoard[row][col] == 'r' || tempChessBoard[row][col] == 'R') {
            checkRow(row, col, true);
            checkCol(row, col, true);
        }
    }

    public void dontShowAround(int row, int col){
        if (tempChessBoard[row][col] == 'r'|| tempChessBoard[row][col] == 'R') {
            checkRow(row, col, false);
            checkCol(row, col, false);
        }
    }

    private void checkRow(int row, int col, boolean check){
        if(row + 1 <= 7){
            for (int r = row + 1; r < 8; r++) {
                if (tempChessBoard[r][col] == 's') {
                    moveIcon(r, col, check);
                }else if (tempChessBoard[row][col] == 'r'){
                    if (tempChessBoard[r][col] >= 'A' && tempChessBoard[r][col] <= 'Z'){
                        chess.chessFrame.enemy(r, col, check);
                    }else if (tempChessBoard[r][col] == 'k'){
                        king = true;
                    }
                    break;
                }else if (tempChessBoard[row][col] == 'R') {
                    if (tempChessBoard[r][col] >= 'a' && tempChessBoard[r][col] <= 'z') {
                        chess.chessFrame.enemy(r, col, check);
                    }else if (tempChessBoard[r][col] == 'K'){
                        king = true;
                    }
                    break;
                }
            }
        }
        if(row - 1 >= 0){
            for (int r = row - 1; r >= 0; r--){
                if (tempChessBoard[r][col] == 's') {
                    moveIcon(r, col, check);
                }else if (tempChessBoard[row][col] == 'r'){
                    if (tempChessBoard[r][col] >= 'A' && tempChessBoard[r][col] <= 'Z'){
                        chess.chessFrame.enemy(r, col, check);
                    }else if (tempChessBoard[r][col] == 'k'){
                        king = true;
                    }
                    break;
                }else if (tempChessBoard[row][col] == 'R') {
                    if (tempChessBoard[r][col] >= 'a' && tempChessBoard[r][col] <= 'z') {
                        chess.chessFrame.enemy(r, col, check);
                    }else if (tempChessBoard[r][col] == 'K'){
                        king = true;
                    }
                    break;
                }
            }
        }
    }

    private void checkCol(int row, int col, boolean check){
        if(col + 1 <= 7){
            for (int c = col + 1; c < 8; c++) {
                if (tempChessBoard[row][c] == 's') {
                    moveIcon(row, c, check);
                }else if (tempChessBoard[row][col] == 'r'){
                    if (tempChessBoard[row][c] >= 'A' && tempChessBoard[row][c] <= 'Z'){
                        chess.chessFrame.enemy(row, c, check);
                    }else if (tempChessBoard[row][c] == 'k'){
                        king = true;
                    }
                    break;
                }else if (tempChessBoard[row][col] == 'R') {
                    if (tempChessBoard[row][c] >= 'a' && tempChessBoard[row][c] <= 'z') {
                        chess.chessFrame.enemy(row, c, check);
                    }else if (tempChessBoard[row][c] == 'K'){
                        king = true;
                    }
                    break;
                }
            }
        }
        if(col - 1 >= 0){
            for (int c = col - 1; c >= 0; c--){
                if (tempChessBoard[row][c] == 's') {
                    moveIcon(row, c, check);
                }else if (tempChessBoard[row][col] == 'r'){
                    if (tempChessBoard[row][c] >= 'A' && tempChessBoard[row][c] <= 'Z'){
                        chess.chessFrame.enemy(row, c, check);
                    }else if (tempChessBoard[row][c] == 'k'){
                        king = true;
                    }
                    break;
                }else if (tempChessBoard[row][col] == 'R') {
                    if (tempChessBoard[row][c] >= 'a' && tempChessBoard[row][c] <= 'z') {
                        chess.chessFrame.enemy(row, c, check);
                    }else if (tempChessBoard[row][c] == 'K'){
                        king = true;
                    }
                    break;
                }
            }
        }
    }

    private void moveIcon(int row, int col, boolean check){
        if (check) chess.chessFrame.showLegalMoveIcon(row, col);
        else chess.chessFrame.dontShowLegalMoveIcon(row, col);
    }

    public void chessMove(int row, int col, int bRow,int bCol) {
        if (tempChessBoard[bRow][bCol] == 'r' & (bRow == row || bCol == col)) {
            changePieces(row, col, bRow, bCol, false);
        } else if (tempChessBoard[bRow][bCol] == 'R' & (bRow == row || bCol == col)) {
            changePieces(row, col, bRow, bCol, true);
        }
    }

    private void regenerateBoard(int row, int col, int bRow, int bCol){
        chess.chessFrame.rooks[bRow][bCol].setFirstMove(true);
        chess.chessFrame.rooks[row][col] = chess.chessFrame.rooks[bRow][bCol];
        chess.chessFrame.rooks[bRow][bCol] = null;
        chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
    }

    private void regenerateKing(int row, int col, int bRow, int bCol){
        chess.chessFrame.kings[bRow][bCol].setFirstMove(true);
        chess.chessFrame.kings[row][col] = chess.chessFrame.kings[bRow][bCol];
        chess.chessFrame.kings[bRow][bCol] = null;
        chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
    }

    public void killChessPieces(int row, int col, int bRow, int bCol) {
        if (tempChessBoard[bRow][bCol] == 'r'){
            changePieces(row, col, bRow, bCol, false);
        }else if (tempChessBoard[bRow][bCol] == 'R'){
            changePieces(row, col, bRow, bCol, true);
        }
    }

    private void changePieces(int row, int col, int bRow, int bCol, boolean isWhite){
        if(!isWhite & tempChessBoard[row][col] != 'K'){
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'r';
            regenerateBoard(row, col, bRow, bCol);
        }else if (isWhite & tempChessBoard[row][col] != 'k' ){
            dontShowAround(bRow, bCol);
            tempChessBoard[bRow][bCol] = 's';
            tempChessBoard[row][col] = 'R';
            regenerateBoard(row, col, bRow, bCol);
        }else {
            dontShowAround(bRow, bCol);
        }
    }

    public void castling(int row, int col, int bRow, int bCol){
        showAround(bRow, bCol);
        if(!isFirstMove() & king & !kings[row][col].isFirstMove()){
            if (tempChessBoard[bRow][bCol] == 'r' & bRow == 0 & bCol == 7){
                dontShowAround(bRow, bCol);
                tempChessBoard[row][col] = 's';
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[bRow][bCol - 1] = 'k';
                tempChessBoard[row][col + 1] = 'r';
                regenerateBoard(row, col + 1, bRow, bCol);
                regenerateKing(bRow, bCol - 1, row, col);
                king = false;
            }else if (tempChessBoard[bRow][bCol] == 'R' & bRow == 7 & bCol == 7){
                dontShowAround(bRow, bCol);
                tempChessBoard[row][col] = 's';
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[bRow][bCol - 1] = 'K';
                tempChessBoard[row][col + 1] = 'R';
                regenerateBoard(row, col + 1, bRow, bCol);
                regenerateKing(bRow, bCol - 1, row, col);
                king = false;
            }else if (tempChessBoard[bRow][bCol] == 'r' & bRow == 0 & bCol == 0){
                dontShowAround(bRow, bCol);
                tempChessBoard[row][col] = 's';
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[bRow][bCol + 2] = 'k';
                tempChessBoard[row][col - 1] = 'r';
                regenerateBoard(row, col - 1, bRow, bCol);
                regenerateKing(bRow, bCol + 2, row, col);
                king = false;
            }else if (tempChessBoard[bRow][bCol] == 'R' & bRow == 7 & bCol == 0){
                dontShowAround(bRow, bCol);
                tempChessBoard[row][col] = 's';
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[bRow][bCol + 2] = 'K';
                tempChessBoard[row][col - 1] = 'R';
                regenerateBoard(row, col - 1, bRow, bCol);
                regenerateKing(bRow, bCol + 2, row, col);
                king = false;
            }

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
