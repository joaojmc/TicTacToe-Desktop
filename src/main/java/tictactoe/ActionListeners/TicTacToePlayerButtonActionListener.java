package tictactoe.ActionListeners;

import tictactoe.Buttons.TicTacToePlayerButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToePlayerButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        TicTacToePlayerButton.press((TicTacToePlayerButton) event.getSource());
    }
}