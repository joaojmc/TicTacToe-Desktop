package tictactoe;

import tictactoe.ActionListeners.TicTacToeButtonActionListener;
import tictactoe.ActionListeners.TicTacToePlayerButtonActionListener;
import tictactoe.ActionListeners.TicTacToeStartOrResetButtonActionListener;
import tictactoe.Buttons.TicTacToeButton;
import tictactoe.Buttons.TicTacToePlayerButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

public class TicTacToe extends JFrame {

    //region GUI Elements

    private static final JPanel GAME_BOARD = new JPanel();
    private static final JPanel USER_MENU_BAR = new JPanel(new GridLayout(1, 3));
    private static final JPanel LABEL_BAR = new JPanel(new BorderLayout());
    private static final JMenuBar J_MENU_BAR = new JMenuBar();

    private static final JLabel GAME_STATUS_LABEL = new JLabel();

    private static final ArrayList<TicTacToeButton> GAME_BUTTONS = new ArrayList<>();

    private static final JButton START_RESET_BUTTON = new JButton();
    private static final TicTacToePlayerButton PLAYER_CONTROL_BUTTON_ONE = new TicTacToePlayerButton();
    private static final TicTacToePlayerButton PLAYER_CONTROL_BUTTON_TWO = new TicTacToePlayerButton();

    //endregion

    private static int turn = 0;
    private static boolean gameIsOver = false;

    public TicTacToe() {
        composeMainWindow();
        composeMenuBar();
        composePlayerControlsBar();
        composeGameBoard();
        composeLabelBar();
        assembleLayout();
        setVisible(true);
    }

    //region GUI composers

    void composeMainWindow() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());
    }

    private void composeMenuBar() {
        JMenu menu = new JMenu("Game");
        menu.setName("MenuGame");

        JMenuItem humanVsHuman = new JMenuItem("Human vs Human");
        humanVsHuman.setName("MenuHumanHuman");

        JMenuItem humanVsRobot = new JMenuItem("Human vs Robot");
        humanVsRobot.setName("MenuHumanRobot");

        JMenuItem robotVsHuman = new JMenuItem("Robot vs Human");
        robotVsHuman.setName("MenuRobotHuman");

        JMenuItem robotVsRobot = new JMenuItem("Robot vs Robot");
        robotVsRobot.setName("MenuRobotRobot");

        JMenuItem exit = new JMenuItem("Exit");
        exit.setName("MenuExit");

        humanVsHuman.addActionListener(e -> setGamePlayersAndStart(true, true));
        humanVsRobot.addActionListener(e -> setGamePlayersAndStart(true, false));
        robotVsHuman.addActionListener(e -> setGamePlayersAndStart(false, true));
        robotVsRobot.addActionListener(e -> setGamePlayersAndStart(false, false));
        exit.addActionListener(e -> System.exit(0));

        menu.add(humanVsHuman);
        menu.add(humanVsRobot);
        menu.add(robotVsHuman);
        menu.add(robotVsRobot);

        menu.addSeparator();
        menu.add(exit);
        J_MENU_BAR.add(menu);
        setJMenuBar(J_MENU_BAR);
    }

    void composePlayerControlsBar() {
        composePlayerButton1();
        composePlayerButton2();
        composeResetButton();

        USER_MENU_BAR.add(PLAYER_CONTROL_BUTTON_ONE);
        USER_MENU_BAR.add(START_RESET_BUTTON);
        USER_MENU_BAR.add(PLAYER_CONTROL_BUTTON_TWO);
        USER_MENU_BAR.setPreferredSize(new Dimension(800, 50));
    }

    void composePlayerButton1() {
        PLAYER_CONTROL_BUTTON_ONE.setText("Human");
        PLAYER_CONTROL_BUTTON_ONE.setName("ButtonPlayer1");
        PLAYER_CONTROL_BUTTON_ONE.addActionListener(new TicTacToePlayerButtonActionListener());
    }

    void composePlayerButton2() {
        PLAYER_CONTROL_BUTTON_TWO.setText("Human");
        PLAYER_CONTROL_BUTTON_TWO.setName("ButtonPlayer2");
        PLAYER_CONTROL_BUTTON_TWO.addActionListener(new TicTacToePlayerButtonActionListener());
    }

    void composeResetButton() {
        START_RESET_BUTTON.setText("Start");
        START_RESET_BUTTON.setName("ButtonStartReset");
        START_RESET_BUTTON.addActionListener(new TicTacToeStartOrResetButtonActionListener());
    }

    void composeGameBoard() {
        GAME_BOARD.setLayout(new GridLayout(3, 3));
        fillGameBoard();
    }

    private void fillGameBoard() {
        for (int row = 3; row > 0; row--) {
            for (char letter = 'A'; letter <= 'C'; letter++) {
                composeGameBoardButton(row, letter);
            }
        }
    }

    void composeGameBoardButton(int row, char letter) {
        var button = new TicTacToeButton();

        button.setText(" ");
        button.setName("Button" + letter + row);
        button.addActionListener(new TicTacToeButtonActionListener());
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 80));
        button.setEnabled(false);

        GAME_BUTTONS.add(button);
        GAME_BOARD.add(button);
    }

    void composeLabelBar() {
        composeGameLabel();
        LABEL_BAR.add(GAME_STATUS_LABEL, BorderLayout.WEST);
        LABEL_BAR.setBorder(new EmptyBorder(10, 15, 10, 15));
    }

    void composeGameLabel() {
        GAME_STATUS_LABEL.setText("Game is not started");
        GAME_STATUS_LABEL.setName("LabelStatus");
    }

    void assembleLayout() {
        add(GAME_BOARD, BorderLayout.CENTER);
        add(USER_MENU_BAR, BorderLayout.NORTH);
        add(LABEL_BAR, BorderLayout.SOUTH);
    }

    //endregion

    //region Button control

    private static void enableControlButtons() {
        PLAYER_CONTROL_BUTTON_ONE.setEnabled(true);
        PLAYER_CONTROL_BUTTON_TWO.setEnabled(true);
    }

    private static void disablePlayerControlButtons() {
        PLAYER_CONTROL_BUTTON_ONE.setEnabled(false);
        PLAYER_CONTROL_BUTTON_TWO.setEnabled(false);
    }

    private static void enableGameBoardButtons() {
        for (JButton button : GAME_BUTTONS) {
            button.setEnabled(true);
        }
    }

    private static void disableBoardButtons() {
        for (JButton button : GAME_BUTTONS) {
            button.setEnabled(false);
        }
        setVictoryLabel();
    }

    private static void setStartResetButtonAsStart() {
        START_RESET_BUTTON.setText("Start");
    }

    private static void setStartResetButtonAsReset() {
        START_RESET_BUTTON.setText("Reset");
    }

    public static void pressBoardButton(TicTacToeButton button) {
        if (button.pressed || gameIsOver) return;
        if (turn == 0) startGame();

        button.pressed = true;
        button.setText(getCurrentPlayerSymbol());

        if (isGameOver()) {
            disableBoardButtons();
            setGameOverLabel(isVictory());
        } else {
            advanceTurn();
        }
    }

    public static void pressPlayerButton(TicTacToePlayerButton button) {
        button.isHuman = !button.isHuman;
        button.setText(button.isHuman ? "Human" : "Robot");
    }

    private static boolean isGameOver() {
        return isVictory() || isDraw();
    }

    private static boolean isDraw() {
        return !isVictory() && turn == 9;
    }

    private static void resetBoardButtons() {
        for (var button : GAME_BUTTONS) {
            button.setText(" ");
            button.pressed = false;
            button.setEnabled(false);
        }
    }

    //endregion

    //region Game control

    public static void startOrReset() {
        if (turn == 0) {
            startGame();
        } else {
            resetGame();
        }
    }

    static void startGame() {
        setStartResetButtonAsReset();
        advanceTurn();
        disablePlayerControlButtons();
        enableGameBoardButtons();
    }

    static void advanceTurn() {
        incrementTurn();
        setCurrentPlayerLabel();
        if (isRobotTurn()) {
            playRobotTurn();
        }
    }

    static void resetGame() {
        setStartResetButtonAsStart();
        setGameNotStartedLabel();
        setTurnToZero();
        setGameAsNotOver();
        enableControlButtons();
        resetBoardButtons();
    }

    static void playRobotTurn() {
        boolean played = false;

        do {
            int randomIndex = new Random().nextInt(9);
            var randomButton = GAME_BUTTONS.get(randomIndex);
            if (randomButton.getText().equals(" ")) {
                TicTacToeButton.press(randomButton);
                played = true;
            }
        } while (!played);
    }

    //endregion

    //region Label control

    private static void setGameNotStartedLabel() {
        GAME_STATUS_LABEL.setText("Game is not started");
    }

    private static void setCurrentPlayerLabel() {
        GAME_STATUS_LABEL.setText(MessageFormat.format("The turn of {0} Player ({1})",
                getCurrentPlayerName(),
                getCurrentPlayerSymbol()));
    }

    private static void setGameOverLabel(boolean isVictory) {
        if (isVictory) {
            setVictoryLabel();
        } else {
            setDrawLabel();
        }
    }

    private static void setVictoryLabel() {
        GAME_STATUS_LABEL.setText(MessageFormat.format("The {0} Player ({1}) wins",
                getCurrentPlayerName(),
                getCurrentPlayerSymbol()));
    }

    private static void setDrawLabel() {
        GAME_STATUS_LABEL.setText("Draw");
    }

    //endregion

    //region Auxiliary routines

    private static void setGameAsNotOver() {
        gameIsOver = false;
    }

    private static void setTurnToZero() {
        turn = 0;
    }

    private static void incrementTurn() {
        turn++;
    }

    private static String getCurrentPlayerName() {
        return turn % 2 != 0 ? PLAYER_CONTROL_BUTTON_ONE.getText() : PLAYER_CONTROL_BUTTON_TWO.getText();
    }

    private static String getCurrentPlayerSymbol() {
        return turn % 2 != 0 ? "X" : "O";
    }

    private static boolean isRobotTurn() {
        if (bothPlayersAreHuman()) return false;
        return turn % 2 != 0 && !PLAYER_CONTROL_BUTTON_ONE.isHuman || turn % 2 == 0 && !PLAYER_CONTROL_BUTTON_TWO.isHuman;
    }

    private static boolean isVictory() {
        boolean victory = false;

        for (int i = 0; i <= 2; i++) {
            if (!GAME_BUTTONS.get(i).getText().equals(" ") &&
                    GAME_BUTTONS.get(i).getText().equals(GAME_BUTTONS.get(i + 3).getText()) &&
                    GAME_BUTTONS.get(i).getText().equals(GAME_BUTTONS.get(i + 6).getText())) {
                victory = true;
            }
        }

        for (int i = 0; i <= 6; i = i + 3) {
            if (!GAME_BUTTONS.get(i).getText().equals(" ") &&
                    GAME_BUTTONS.get(i).getText().equals(GAME_BUTTONS.get(i + 1).getText()) &&
                    GAME_BUTTONS.get(i).getText().equals(GAME_BUTTONS.get(i + 2).getText())) {
                victory = true;
            }
        }

        if (!GAME_BUTTONS.get(0).getText().equals(" ") &&
                GAME_BUTTONS.get(0).getText().equals(GAME_BUTTONS.get(4).getText()) &&
                GAME_BUTTONS.get(0).getText().equals(GAME_BUTTONS.get(8).getText())) {
            victory = true;
        }

        if (!GAME_BUTTONS.get(2).getText().equals(" ") &&
                GAME_BUTTONS.get(2).getText().equals(GAME_BUTTONS.get(4).getText()) &&
                GAME_BUTTONS.get(2).getText().equals(GAME_BUTTONS.get(6).getText())) {
            victory = true;
        }

        return victory;
    }

    private static boolean bothPlayersAreHuman() {
        return PLAYER_CONTROL_BUTTON_ONE.isHuman && PLAYER_CONTROL_BUTTON_TWO.isHuman;
    }

    private void setGamePlayersAndStart(boolean isPlayerOneHuman, boolean isPlayerTwoHuman) {
        PLAYER_CONTROL_BUTTON_ONE.isHuman = isPlayerOneHuman;
        PLAYER_CONTROL_BUTTON_ONE.setText(PLAYER_CONTROL_BUTTON_ONE.isHuman ? "Human" : "Robot");
        PLAYER_CONTROL_BUTTON_TWO.isHuman = isPlayerTwoHuman;
        PLAYER_CONTROL_BUTTON_TWO.setText(PLAYER_CONTROL_BUTTON_TWO.isHuman ? "Human" : "Robot");
        startGame();
    }

    //endregion

}