package br.edu.principal.Operands;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;


public class TicTacToe implements ActionListener{
    final private static int BOARD_WIDTH = 670;
    final private static int BOARD_HEIGHT = 650; // 50px for the text panel on the top

    protected String currentPlayer = "X";
    protected Result winner = Result.UNDEFINED;

    private ArrayList<JButton> line1 = new ArrayList<>(Arrays.asList(null, null, null));
    private ArrayList<JButton> line2 = new ArrayList<>(Arrays.asList(null, null, null));
    private ArrayList<JButton> line3 = new ArrayList<>(Arrays.asList(null, null, null));
    private ArrayList<ArrayList<JButton>> board = new ArrayList<>(Arrays.asList(line1, line2, line3));

    private int qttOfMoves = 0;
    private boolean onGame = true;

    JLabel textLabel = new JLabel();
    public TicTacToe() {
        JFrame frame = new JFrame("TicTacToe");
        frame.setVisible(true);
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(new Color(0x1F0164));
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("TicTacToe");
        textLabel.setOpaque(true);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel);

        for (int line = 0; line < 3; line++) {
            for (int column = 0; column < 3; column++) {
                JButton tile = new JButton();
                board.get(line).set(column, tile);
                boardPanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (onGame) {
            JButton tile = null;
            boolean checker = true;
            for (ArrayList<JButton> line : board) {
                if (checker) {
                    for (JButton button : line) {
                        if (e.getSource().equals(button)) {
                            tile = button;
                            checker = false;
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
            if (tile.getText().isEmpty()) {
                tile.setText(currentPlayer.equals("X") ? "X" : "O");
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                qttOfMoves += 1;
                if (qttOfMoves >= 5) {
                    ResultChecker.verify(this);
                    if (!winner.equals(Result.UNDEFINED)){
                        onGame = false;
                    }
                }
                if (qttOfMoves == 9 && winner.equals(Result.UNDEFINED)) {
                    winner = Result.DRAW;
                    onGame = false;
                    textLabel.setText("EMPATE");
                }
            }
        } else {
            textLabel.setText("XXX");
        }
    }

    protected ArrayList<ArrayList<JButton>> getBoard() {
        return board;
    }

}
