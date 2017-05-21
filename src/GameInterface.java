import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameInterface extends Container implements ActionListener {
    private App app;
    private Panel betPanel;
    private Label playerMoneyText;
    private Label playerMoneyValue;
    private Label betText;
    private Button betDecr;
    private Button betIncr;
    private Label bet;
    private Button startGame;

    private Panel bankPanel;
    private Label bankStatus;
    private Label bankMoneyText;
    private Label bankMoneyVal;
    private Label bankScoreText;
    private Label bankScoreVal;

    private Panel playerPanel;
    private Button getCardButton;
    private Button foldButton;
    private Label playerScoreText;
    private Label playerScoreVal;

    public GameInterface(App p, Graphics g)
    {
        app = p;
        setSize(app.getWidth(), app.getHeight());

        int panelWidth = getWidth() / 6;
        int panelHeight = getHeight() / 3;
        int betPanelPos = getWidth() - panelWidth;

        betPanel = new Panel();
        betPanel.setBackground( new Color(79, 121, 66));
        add(betPanel);
        betPanel.setLocation(betPanelPos, 0);
        betPanel.setSize(panelWidth, panelHeight);

        GroupLayout betLayout = new GroupLayout(betPanel);
        betPanel.setLayout(betLayout);
        betLayout.setAutoCreateGaps(true);
        betLayout.setAutoCreateContainerGaps(true);

        playerMoneyText = new Label("Money:", Label.LEFT);
        playerMoneyValue = new Label("0", Label.RIGHT);
        betText = new Label("Bet:", Label.LEFT);
        betDecr = new Button("-");
        bet = new Label("0", Label.CENTER);
        betIncr = new Button("+");
        startGame = new Button("Start game");

        betLayout.setHorizontalGroup(
                betLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(betLayout.createSequentialGroup()
                        .addComponent(playerMoneyText, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(playerMoneyValue, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(betLayout.createSequentialGroup()
                        .addComponent(betText, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(betDecr, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bet, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(betIncr, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(startGame, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );

        betLayout.setVerticalGroup(
                betLayout.createSequentialGroup()
                        .addGroup(betLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(playerMoneyText)
                                .addComponent(playerMoneyValue))
                        .addGroup(betLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(betText)
                                .addComponent(betDecr)
                                .addComponent(bet)
                                .addComponent(betIncr))
                        .addComponent(startGame)
        );

        bankPanel = new Panel();
        bankPanel.setBackground( new Color(79, 121, 66));
        add(bankPanel);
        bankPanel.setLocation(betPanelPos, panelHeight);
        bankPanel.setSize(panelWidth, panelHeight);

        GroupLayout bankLayout = new GroupLayout(bankPanel);
        bankPanel.setLayout(bankLayout);
        bankLayout.setAutoCreateGaps(true);
        bankLayout.setAutoCreateContainerGaps(true);

        bankMoneyText = new Label("Money:", Label.LEFT);
        bankMoneyVal = new Label("0", Label.RIGHT);
        bankStatus = new Label("", Label.CENTER);
        bankScoreText = new Label("", Label.LEFT);
        bankScoreVal = new Label("", Label.RIGHT);

        bankLayout.setHorizontalGroup(
                bankLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(bankLayout.createSequentialGroup()
                        .addComponent(bankMoneyText, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bankMoneyVal, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(bankStatus, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bankLayout.createSequentialGroup()
                        .addComponent(bankScoreText, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bankScoreVal, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bankLayout.setVerticalGroup(
                bankLayout.createSequentialGroup()
                    .addGroup(bankLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bankMoneyText)
                        .addComponent(bankMoneyVal))
                    .addComponent(bankStatus)
                    .addGroup(bankLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bankScoreText)
                        .addComponent(bankScoreVal))
        );

        playerPanel = new Panel();
        playerPanel.setBackground( new Color(79, 121, 66));
        add(playerPanel);
        playerPanel.setLocation(betPanelPos, 2 * panelHeight);
        playerPanel.setSize(panelWidth, panelHeight);

        GroupLayout playerLayout = new GroupLayout(playerPanel);
        playerPanel.setLayout(playerLayout);
        playerLayout.setAutoCreateGaps(true);
        playerLayout.setAutoCreateContainerGaps(true);

        getCardButton = new Button("Get card");
        getCardButton.setEnabled(false);
        foldButton = new Button("Fold");
        foldButton.setEnabled(false);
        playerScoreText = new Label("", Label.LEFT);
        playerScoreVal = new Label("", Label.RIGHT);

        playerLayout.setHorizontalGroup(
                playerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(getCardButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(foldButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(playerLayout.createSequentialGroup()
                                .addComponent(playerScoreText, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(playerScoreVal, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        playerLayout.setVerticalGroup(
                playerLayout.createSequentialGroup()
                        .addComponent(getCardButton)
                        .addComponent(foldButton)
                        .addGroup(playerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(playerScoreText)
                                .addComponent(playerScoreVal))
        );
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == betDecr)
            app.getGameManager().DecrementBet(5);
    }
}
