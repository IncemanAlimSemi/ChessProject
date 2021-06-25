package com.company.game;

import com.company.Main;
import com.company.game.Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import static com.company.game.Chess.tempChessBoard;

public class ChangePawnFrame implements MouseListener{

    private final int ROWS = 4;
    private final int COLS = 1;

    private int row, col, bRow, bCol;

    private final String imagesPath = "/com/company/game/images/";

    private final ImageIcon bb = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "bb.png")));
    private final ImageIcon bn = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "bh.png")));
    private final ImageIcon br = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "br.png")));
    private final ImageIcon bq = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "bq.png")));
    private final ImageIcon wb = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "wb.png")));
    private final ImageIcon wn = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "wh.png")));
    private final ImageIcon wr = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "wr.png")));
    private final ImageIcon wq = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagesPath + "wq.png")));

    private final Color primaryColor = new Color(180, 135, 102);
    private final Color secondaryColor = new Color(240, 217, 183);

    private JFrame changePawn;
    private Label pawnLabel[][] = new Label[ROWS][COLS];
    private Chess chess = Main.chess;

    public ChangePawnFrame(int row , int col, int bRow, int bCol){
        this.row = row;
        this.col  = col;
        this.bRow = bRow;
        this.bCol = bCol;

        changePawn = new JFrame("Chess Game");
        changePawn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changePawn.setLayout(new GridLayout(ROWS, COLS));
        changePawn.setBounds(710, 290, 100, 500);

        generateLabel();

        if (tempChessBoard[bRow][bCol] == 'p') {
            generateIcon(false);
        }else if (tempChessBoard[bRow][bCol] == 'P'){
            generateIcon(true);
        }

        changePawn.setVisible(true);
    }

    private void generateLabel() {
        boolean isWhite = true;
        for (int labelRow = 0; labelRow < ROWS; labelRow++) {
            for (int labelCol = 0; labelCol < COLS; labelCol++) {
                Label squaresLabel = new Label(labelRow, labelCol);
                if (isWhite) {
                    squaresLabel.setBackground(primaryColor);
                } else {
                    squaresLabel.setBackground(secondaryColor);
                }
                squaresLabel.setOpaque(true);
                squaresLabel.addMouseListener(this);
                changePawn.add(squaresLabel);
                pawnLabel[labelRow][labelCol] = squaresLabel;
                isWhite = !isWhite;
            }
        }
    }

    private void generateIcon(boolean isWhite){
        if(!isWhite){
            pawnLabel[0][0].setIcon(br);
            pawnLabel[0][0].setHorizontalAlignment(SwingConstants.CENTER);
            pawnLabel[1][0].setIcon(bn);
            pawnLabel[1][0].setHorizontalAlignment(SwingConstants.CENTER);
            pawnLabel[2][0].setIcon(bb);
            pawnLabel[2][0].setHorizontalAlignment(SwingConstants.CENTER);
            pawnLabel[3][0].setIcon(bq);
            pawnLabel[3][0].setHorizontalAlignment(SwingConstants.CENTER);
        }else {
            pawnLabel[0][0].setIcon(wr);
            pawnLabel[0][0].setHorizontalAlignment(SwingConstants.CENTER);
            pawnLabel[1][0].setIcon(wn);
            pawnLabel[1][0].setHorizontalAlignment(SwingConstants.CENTER);
            pawnLabel[2][0].setIcon(wb);
            pawnLabel[2][0].setHorizontalAlignment(SwingConstants.CENTER);
            pawnLabel[3][0].setIcon(wq);
            pawnLabel[3][0].setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1){
            Label clickedLabel = (Label) e.getComponent();
            System.out.println(clickedLabel.getRow() + " " + clickedLabel.getCol());
            Icon icon = pawnLabel[clickedLabel.getRow()][clickedLabel.getCol()].getIcon();
            if (br.equals(icon)) {
                changePawn.setVisible(false);
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[row][col] = 'r';
                chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
            } else if (bn.equals(icon)) {
                changePawn.setVisible(false);
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[row][col] = 'n';
                chess.chessFrame.regenerateChessBoard(row , col, bRow, bCol);
            } else if (bb.equals(icon)) {
                changePawn.setVisible(false);
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[row][col] = 'b';
                chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
            } else if (bq.equals(icon)) {
                changePawn.setVisible(false);
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[row][col] = 'q';
                chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
            } else if (wr.equals(icon)) {
                changePawn.setVisible(false);
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[row][col] = 'R';
                chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
            } else if (wn.equals(icon)) {
                changePawn.setVisible(false);
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[row][col] = 'N';
                chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
            } else if (wb.equals(icon)) {
                changePawn.setVisible(false);
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[row][col] = 'B';
                chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
            } else if (wq.equals(icon)) {
                changePawn.setVisible(false);
                tempChessBoard[bRow][bCol] = 's';
                tempChessBoard[row][col] = 'Q';
                chess.chessFrame.regenerateChessBoard(row, col, bRow, bCol);
            }
        }
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
