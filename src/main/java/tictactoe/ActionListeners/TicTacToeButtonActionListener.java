package tictactoe.ActionListeners;

import tictactoe.Buttons.TicTacToeButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        TicTacToeButton.press((TicTacToeButton) event.getSource());
    }
}