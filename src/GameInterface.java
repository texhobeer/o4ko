import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameInterface extends Container implements ActionListener {
    private App app;
    private GameManager gameManager;
    private Panel betPanel;
    private Label playerMoneyText;
    private Label playerMoneyValue;
    private Label betText;
    private Button betDecr;
    private Button betIncr;
    private Label bet;
    private Button startGame;
    private boolean isGameRunning;

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

    private HandInterface playerHandUI;
    private HandInterface bankHandUI;

    private Panel deckPanel;
    private Image cardBack;
    private Panel infoPanel;
    private Label infoText;

    private boolean isStalled;

    public GameInterface(App p, GameManager gMan , Graphics g)
    {
        app = p;
        gameManager = gMan;
        setSize(app.getWidth(), app.getHeight());
        isGameRunning = false;

        isStalled = false;

        int panelWidth = getWidth() / 6;
        int panelHeight = getHeight() / 3;
        int betPanelPos = getWidth() - panelWidth;

        bankHandUI = new HandInterface(0, panelHeight, getWidth() - panelWidth, panelHeight, gameManager.GetBankHand(), this);
        playerHandUI = new HandInterface(0,2 * panelHeight, getWidth() - panelWidth, panelHeight, gameManager.GetPlayerHand(), this);

        deckPanel = new Panel();
        deckPanel.setBackground(new Color(79, 121, 66));
        add(deckPanel);
        deckPanel.setLocation(0, 0);
        deckPanel.setSize(panelWidth, panelHeight);

        cardBack = CardImage.CardBack;

        infoPanel = new Panel();
        infoPanel.setBackground(new Color(79, 121, 66));
        add(infoPanel);
        infoPanel.setLocation(panelWidth, 0);
        infoPanel.setSize(getWidth() - 2 * panelWidth, panelHeight);

        infoText = new Label("", Label.CENTER);
        infoPanel.add(infoText);

        GroupLayout infoLayout = new GroupLayout(infoPanel);
        infoPanel.setLayout(infoLayout);
        infoLayout.setAutoCreateGaps(true);
        infoLayout.setAutoCreateContainerGaps(true);

        Label lab1 = new Label("");
        Label lab2 = new Label("");

        infoLayout.setHorizontalGroup(
                infoLayout.createParallelGroup()
                        .addComponent(lab1)
                        .addComponent(infoText)
                        .addComponent(lab2)
        );

        infoLayout.setVerticalGroup(
                infoLayout.createSequentialGroup()
                    .addComponent(lab1)
                    .addComponent(infoText)
                    .addComponent(lab2)
        );

        betPanel = new Panel();
        betPanel.setBackground(new Color(79, 121, 66));
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
        betDecr.addActionListener(this);
        bet = new Label("0", Label.CENTER);
        betIncr = new Button("+");
        betIncr.addActionListener(this);
        startGame = new Button("Start game");
        startGame.addActionListener(this);

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
        playerPanel.setBackground(new Color(79, 121, 66));
        add(playerPanel);
        playerPanel.setLocation(betPanelPos, 2 * panelHeight);
        playerPanel.setSize(panelWidth, panelHeight);

        GroupLayout playerLayout = new GroupLayout(playerPanel);
        playerPanel.setLayout(playerLayout);
        playerLayout.setAutoCreateGaps(true);
        playerLayout.setAutoCreateContainerGaps(true);

        getCardButton = new Button("Get card");
        getCardButton.addActionListener(this);
        getCardButton.setEnabled(false);
        foldButton = new Button("Fold");
        foldButton.addActionListener(this);
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

    public void Update()
    {
        if (bankHandUI.isMoving() || playerHandUI.isMoving())
            isStalled = true;
        else
            isStalled = false;

        bet.setText(Integer.toString(gameManager.GetBet()));
        playerMoneyValue.setText(Integer.toString(gameManager.GetPlayerMoney()));
        bankMoneyVal.setText(Integer.toString(gameManager.GetBankMoney()));

        if (isGameRunning)
        {
            bankScoreVal.setText(Integer.toString(gameManager.GetBankScore()));
            playerScoreVal.setText(Integer.toString(gameManager.GetPlayerScore()));
        }
        else
        {
            bankScoreVal.setText("");
            playerScoreVal.setText("");
        }

        if (gameManager.IsBankFold() && isGameRunning)
        {
            bankStatus.setText("Fold");
        }
        else
        {
            bankStatus.setText("");
        }

        playerHandUI.Update();
        if (!playerHandUI.isMoving())
            bankHandUI.Update();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (isStalled)
            return;

        if (e.getSource() == betDecr)
            gameManager.DecrementBet();

        if (e.getSource() == betIncr)
            gameManager.IncrementBet();

        if (e.getSource() == startGame)
            StartGame();

        if (e.getSource() == foldButton)
            Fold();

        if (e.getSource() == getCardButton)
            Turn();
    }

    private void StartGame()
    {
        getCardButton.setEnabled(true);
        foldButton.setEnabled(true);
        startGame.setEnabled(false);

        bankHandUI.Clear();
        playerHandUI.Clear();

        playerScoreText.setText("Score:");
        bankScoreText.setText("Bank score:");
        getCardButton.setLabel("Get card");
        infoText.setText("Round started!");

        isGameRunning = true;

        gameManager.StartGame();
        Turn();
    }

    private void Turn()
    {
        gameManager.Turn();

        if (gameManager.GetEndCase() != GameManager.EndCase.Null)
            FinishGame();

        if (gameManager.GetPlayerScore() > 21)
            getCardButton.setLabel("Cheat!");
    }

    private void Fold()
    {
        gameManager.Fold();
        FinishGame();
    }

    private void FinishGame()
    {
        getCardButton.setEnabled(false);
        foldButton.setEnabled(false);
        startGame.setEnabled(true);

        playerScoreText.setText("");
        bankScoreText.setText("");

        isGameRunning = false;

        String data = " Bank score: " + gameManager.GetBankScore() + " Your score: " + gameManager.GetPlayerScore();

        switch(gameManager.GetEndCase())
        {
            case BankGainedO4KO:
                infoText.setText("Bank gained O4KO! You lose!" + data);
                break;
            case BankOverPlayer:
                infoText.setText("Bank gained more score! You lose!" + data);
                break;
            case BankOverScored:
                infoText.setText("Bank gained more then O4KO! You win!" + data);
                break;
            case Draw:
                infoText.setText("Draw!" + data);
                break;
            case PlayerGainedO4KO:
                infoText.setText("You gained O4KO! You win!" + data);
                break;
            case PlayerOverBank:
                infoText.setText("You gained more score! You win!" + data);
                break;
            case PlayerOverScored:
                infoText.setText("You gained more then O4KO! You lose!" + data);
                break;
            default:
                infoText.setText("");
                break;
        }
    }

    public void Paint()
    {
        playerHandUI.Paint();
        bankHandUI.Paint();

        int cardBackX = deckPanel.getX() + deckPanel.getWidth() / 2 - cardBack.getWidth(null) / 2;
        int cardBackY = deckPanel.getY() + deckPanel.getHeight() / 10;

        Image buff = deckPanel.createImage(deckPanel.getWidth(), deckPanel.getHeight());

        if (buff == null)
            return;

        Graphics buffG = buff.getGraphics();

        buffG.drawImage(cardBack, cardBackX + 2, cardBackY + 2, null);
        buffG.drawImage(cardBack, cardBackX + 1, cardBackY + 1, null);
        buffG.drawImage(cardBack, cardBackX, cardBackY, null);

        deckPanel.getGraphics().drawImage(buff, deckPanel.getX(), deckPanel.getY(), null);
    }

    public int GetDeckXPos()
    {
        return deckPanel.getX() + deckPanel.getWidth() / 2 - cardBack.getWidth(null) / 2;
    }

    public int GetDeckYPos()
    {
        return deckPanel.getY() + deckPanel.getHeight() / 10;
    }
}
