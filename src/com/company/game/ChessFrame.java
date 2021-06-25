package com.company.game;

import com.company.game.chesspieces.*;

import static com.company.game.Chess.tempChessBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class ChessFrame implements MouseListener {

    private final int ROWS = 8;
    private final int COLS = 8;

    private final String imagesPath = "/com/company/game/images/";

    private final ImageIcon gd = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "green_dot.png")));
    private final ImageIcon bp = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "bp.png")));
    private final ImageIcon bb = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "bb.png")));
    private final ImageIcon bn = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "bh.png")));
    private final ImageIcon br = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "br.png")));
    private final ImageIcon bq = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "bq.png")));
    private final ImageIcon bk = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "bk.png")));
    private final ImageIcon wp = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "wp.png")));
    private final ImageIcon wb = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "wb.png")));
    private final ImageIcon wn = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "wh.png")));
    private final ImageIcon wr = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "wr.png")));
    private final ImageIcon wq = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "wq.png")));
    private final ImageIcon wk = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "wk.png")));

    private final Color primaryColor = new Color(180, 135, 102);
    private final Color secondaryColor = new Color(240, 217, 183);

    private JFrame frame;
    private Label[][] label = new Label[ROWS][COLS];

    public Pawn[][] pawns = new Pawn[ROWS][COLS];
    public Rook[][] rooks = new Rook[ROWS][COLS];
    public King[][] kings = new King[ROWS][COLS];

    private boolean checkClick = false, white = true;
    private int bRow, bCol;

    public void runFrame(){
        generateFrame();
    }

    private void generateFrame() {
        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(ROWS, COLS));
        frame.setBounds(710, 290, 500, 500);

        generateLabel();
        generateChessPieces();
        addPieces();

        frame.setVisible(true);
    }

    private void generateLabel() {
        boolean isWhite = true;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Label squaresLabel = new Label(row,col);
                if(isWhite){
                    squaresLabel.setBackground(primaryColor);
                }else {
                    squaresLabel.setBackground(secondaryColor);
                }
                squaresLabel.setOpaque(true);
                squaresLabel.addMouseListener(this);
                frame.add(squaresLabel);
                label[row][col] = squaresLabel;
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }
    }

    private void generateChessPieces() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                switch (tempChessBoard[row][col]){
                    case 'p':
                        label[row][col].setIcon(bp);break;
                    case 'P':
                        label[row][col].setIcon(wp);break;
                    case 'r':
                        label[row][col].setIcon(br);break;
                    case 'R':
                        label[row][col].setIcon(wr);break;
                    case 'n':
                        label[row][col].setIcon(bn);break;
                    case 'N':
                        label[row][col].setIcon(wn);break;
                    case 'b':
                        label[row][col].setIcon(bb);break;
                    case 'B':
                        label[row][col].setIcon(wb);break;
                    case 'q':
                        label[row][col].setIcon(bq);break;
                    case 'Q':
                        label[row][col].setIcon(wq);break;
                    case 'k':
                        label[row][col].setIcon(bk);break;
                    case 'K':
                        label[row][col].setIcon(wk);break;
                }
                label[row][col].setHorizontalAlignment(SwingConstants.CENTER);
            }
        }
    }


    private void addPieces() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                switch (tempChessBoard[row][col]) {
                    case 'p':
                    case 'P':
                        Pawn pawn = new Pawn(row, col);
                        pawns[row][col] = pawn;
                        break;
                    case 'r':
                    case 'R':
                        Rook rook = new Rook(row, col);
                        rooks[row][col] = rook;
                        break;
                    case 'k':
                    case 'K':
                        King king = new King(row, col);
                        kings[row][col] = king;
                        break;
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 1) {
            Label clickedLabel = (Label) e.getComponent();
            if (!checkClick & tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] != 's' & white &
                    (tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] >= 'A' && tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] <= 'Z') ) {
                System.out.println("İlk hareket");
                bRow = clickedLabel.getRow();
                bCol = clickedLabel.getCol();
                setBackground(bRow, bCol, true);
                move(clickedLabel.getRow(), clickedLabel.getCol(), bRow, bCol, 's');
                checkClick = true;
            } else if (bRow == clickedLabel.getRow() & bCol == clickedLabel.col & checkClick) {
                System.out.println("ikinci hareket");
                move(clickedLabel.getRow(), clickedLabel.getCol(), bRow, bCol, 'd');
                setBackground(bRow, bCol, false);
                checkClick = false;
            } else if (checkClick & label[clickedLabel.getRow()][clickedLabel.getCol()].getIcon() == gd) {
                System.out.println("üçüncü hareket");
                move(clickedLabel.getRow(), clickedLabel.getCol(), bRow, bCol, 'm');
                setBackground(bRow, bCol, false);;
                checkClick = false;
                white = !white;
            } else if (checkClick & tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] != 's'
                    & label[clickedLabel.getRow()][clickedLabel.getCol()].getBackground() == Color.GRAY
                    & ((tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] != 'k' & (tempChessBoard[bRow][bCol] >= 'A' && tempChessBoard[bRow][bCol] <= 'Z'))
                    || (tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] != 'K' & (tempChessBoard[bRow][bCol] >= 'a' && tempChessBoard[bRow][bCol] <= 'z')))) {
                System.out.println("dördüncü hareket");
                move(clickedLabel.getRow(), clickedLabel.getCol(), bRow, bCol, 'k');
                setBackground(bRow, bCol, false);
                checkClick = false;
                white = !white;
            }else if (checkClick &
                    ((tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] == 'k' & tempChessBoard[bRow][bCol] == 'r')
                    ||(tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] == 'r' & tempChessBoard[bRow][bCol] == 'k')
                    ||(tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] == 'K' & tempChessBoard[bRow][bCol] == 'R')
                    ||(tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] == 'R' & tempChessBoard[bRow][bCol] == 'K'))){
                System.out.println("Beşinci hareket");
                move(clickedLabel.getRow(), clickedLabel.getCol(), bRow, bCol, 'c');
                if (tempChessBoard[bRow][bCol] == 's'){
                    checkClick = false;
                    white = !white;
                    setBackground(bRow, bCol, false);
                }
            }else if (!checkClick & tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] != 's' & !white
                    & (tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] >= 'a' && tempChessBoard[clickedLabel.getRow()][clickedLabel.getCol()] <= 'z')) {
                System.out.println("İlk hareket");
                bRow = clickedLabel.getRow();
                bCol = clickedLabel.getCol();
                setBackground(bRow, bCol, true);
                move(clickedLabel.getRow(), clickedLabel.getCol(), bRow, bCol, 's');
                checkClick = true;
            }
        }
    }



    private void move(int row, int col, int bRow,int bCol, char key) {
        switch (tempChessBoard[bRow][bCol]) {
            case 'p':
            case 'P':
                Pawn pawn = new Pawn(row, col);
                if(key == 's'){
                    pawn.showAround(row, col);
                }else if (key == 'd'){
                    pawn.dontShowAround(row, col);
                }else if (key == 'm'){
                    pawns[bRow][bCol].chessMove(row, col, bRow, bCol);
                }else if(key == 'k'){
                    pawns[bRow][bCol].killChessPieces(row, col, bRow, bCol);
                }
                break;
            case 'r':
            case 'R':
                Rook rook = new Rook(row, col);
                if(key == 's'){
                    rook.showAround(row, col);
                }else if (key == 'd'){
                    rook.dontShowAround(row, col);
                }else if (key == 'm'){
                    rooks[bRow][bCol].chessMove(row, col, bRow, bCol);
                }else if(key == 'k'){
                    rooks[bRow][bCol].killChessPieces(row, col, bRow, bCol);
                }else if(key == 'c'){
                    rooks[bRow][bCol].castling(row, col, bRow, bCol);
                }
                break;
            case 'n':
            case 'N':
                Knight knight = new Knight(row, col);
                if(key == 's'){
                    knight.showAround(row, col);
                }else if (key == 'd'){
                    knight.dontShowAround(row, col);
                }else if (key == 'm'){
                    knight.chessMove(row, col, bRow, bCol);
                }else if(key == 'k'){
                    knight.killChessPieces(row, col, bRow, bCol);
                }
                break;
            case 'b':
            case 'B':
                Bishop bishop = new Bishop(row, col);
                if(key == 's'){
                    bishop.showAround(row, col);
                }else if (key == 'd'){
                    bishop.dontShowAround(row, col);
                }else if (key == 'm'){
                    bishop.chessMove(row, col, bRow, bCol);
                }else if(key == 'k'){
                    bishop.killChessPieces(row, col, bRow, bCol);
                }
                break;
            case 'q':
            case 'Q':
                Queen queen = new Queen(row, col);
                if(key == 's'){
                    queen.showAround(row, col);
                }else if (key == 'd'){
                    queen.dontShowAround(row, col);
                }else if (key == 'm'){
                    queen.chessMove(row, col, bRow, bCol);
                }else if(key == 'k'){
                    queen.killChessPieces(row, col, bRow, bCol);
                }
                break;
            case 'k':
            case 'K':
                King king = new King(row, col);
                if(key == 's'){
                    king.showAround(row, col);
                }else if (key == 'd'){
                    king.dontShowAround(row, col);
                }else if (key == 'm'){
                    kings[bRow][bCol].chessMove(row, col, bRow, bCol);
                }else if(key == 'k'){
                    kings[bRow][bCol].killChessPieces(row, col, bRow, bCol);
                }
                break;
        }
    }

    public void showLegalMoveIcon(int row, int col) {
        label[row][col].setIcon(gd);
    }

    public void dontShowLegalMoveIcon(int row, int col) {
        label[row][col].setIcon(null);
    }

    public void regenerateChessBoard(int row, int col, int bRow, int bCol){
        switch (tempChessBoard[row][col]){
            case 'p': label[row][col].setIcon(bp); label[bRow][bCol].setIcon(null); break;
            case 'P': label[row][col].setIcon(wp); label[bRow][bCol].setIcon(null); break;
            case 'r': label[row][col].setIcon(br); label[bRow][bCol].setIcon(null); break;
            case 'R': label[row][col].setIcon(wr); label[bRow][bCol].setIcon(null); break;
            case 'n': label[row][col].setIcon(bn); label[bRow][bCol].setIcon(null); break;
            case 'N': label[row][col].setIcon(wn); label[bRow][bCol].setIcon(null); break;
            case 'b': label[row][col].setIcon(bb); label[bRow][bCol].setIcon(null); break;
            case 'B': label[row][col].setIcon(wb); label[bRow][bCol].setIcon(null); break;
            case 'q': label[row][col].setIcon(bq); label[bRow][bCol].setIcon(null); break;
            case 'Q': label[row][col].setIcon(wq); label[bRow][bCol].setIcon(null); break;
            case 'k': label[row][col].setIcon(bk); label[bRow][bCol].setIcon(null); break;
            case 'K': label[row][col].setIcon(wk); label[bRow][bCol].setIcon(null); break;
        }
    }

    public void enemy(int row, int col, boolean isShow){
        if(isShow){
           label[row][col].setBackground(Color.GRAY);
        }else {
            if (col - 1 >= 0){
                if (label[row][col - 1].getBackground() == primaryColor) label[row][col].setBackground(secondaryColor);
                else if (label[row][col - 1].getBackground() == secondaryColor) label[row][col].setBackground(primaryColor);
            }
            if (row - 1 >= 0){
                if (label[row - 1][col].getBackground() == primaryColor) label[row][col].setBackground(secondaryColor);
                else if (label[row - 1][col].getBackground() == secondaryColor) label[row][col].setBackground(primaryColor);
            }
            if (col + 1 <= 7) {
                if (label[row][col + 1].getBackground() == primaryColor) label[row][col].setBackground(secondaryColor);
                else if (label[row][col + 1].getBackground() == secondaryColor)
                    label[row][col].setBackground(primaryColor);
            }
            if (row + 1 <= 7) {
                if (label[row + 1][col].getBackground() == primaryColor) label[row][col].setBackground(secondaryColor);
                else if (label[row + 1][col].getBackground() == secondaryColor)
                    label[row][col].setBackground(primaryColor);
            }
        }
    }

    private void setBackground(int row, int col, boolean set){
        if(set){
            label[row][col].setBackground(Color.GREEN.darker());
        }else {
            if (col - 1 >= 0 ){
                if (label[row][col - 1].getBackground() == primaryColor)
                    label[row][col].setBackground(secondaryColor);
                else if (label[row][col - 1].getBackground() == secondaryColor)
                    label[row][col].setBackground(primaryColor);
            }
            if (row - 1 >= 0){
                if (label[row - 1][col].getBackground() == primaryColor)
                    label[row][col].setBackground(secondaryColor);
                else if (label[row - 1][col].getBackground() == secondaryColor)
                    label[row][col].setBackground(primaryColor);
            }
            if (col + 1 <= 7) {
                if (label[row][col + 1].getBackground() == primaryColor)
                    label[row][col].setBackground(secondaryColor);
                else if (label[row][col + 1].getBackground() == secondaryColor)
                    label[row][col].setBackground(primaryColor);
            }if (row + 1 <= 7) {
                if (label[row + 1][col].getBackground() == primaryColor)
                    label[row][col].setBackground(secondaryColor);
                else if (label[row + 1][col].getBackground() == secondaryColor)
                    label[row][col].setBackground(primaryColor);
            }
        }
    }

    public void changePawn(int row, int col) {
        ChangePawnFrame changePawnFrame = new ChangePawnFrame(row, col, bRow, bCol);
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
