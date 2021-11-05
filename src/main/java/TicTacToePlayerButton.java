import javax.swing.*;

public class TicTacToePlayerButton extends JButton {

    public boolean isHuman;

    public TicTacToePlayerButton() {
        this.isHuman = true;
    }

    public static void press(TicTacToePlayerButton button) {
        TicTacToe.pressPlayerButton(button);
    }
}