import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeResetButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        TicTacToe.startOrReset();
    }
}