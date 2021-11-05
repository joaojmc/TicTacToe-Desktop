package tictactoe.Buttons;

import tictactoe.TicTacToe;

import javax.swing.*;

public class TicTacToeButton extends JButton {

    public boolean pressed;

    public static void press(TicTacToeButton button) {
        TicTacToe.pressBoardButton(button);
    }
}