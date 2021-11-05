package tictactoe.ActionListeners;

import tictactoe.TicTacToe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeStartOrResetButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        TicTacToe.startOrReset();
    }
}