public class GameManager {
    private enum EndCase
    {
        BankGainedO4KO,
        PlayerGainedO4KO,
        BankOverScored,
        PlayerOverScored,
        PlayerOverBank,
        BankOverPlayer,
        Draw
    }

    private Deck deck;
    private int bankMoney = 100;
    private int playerMoney = 100;
    private int bet = 0;
    private int playerScore;
    private int bankScore;
    private Hand playerHand;
    private Hand bankHand;
    private boolean betFreezed;
    private BankAI bankAI;

    public GameManager()
    {
        deck = new Deck();
        playerHand = new Hand();
        bankHand = new Hand();
        bankAI = new BankAI(deck, playerHand, bankHand, 0);
        betFreezed = false;
    }

    public void Init()
    {

    }

    public void IncrementBet(int x)
    {
        if (betFreezed)
            return;

        if (bet + x > playerMoney || bet + x > bankMoney)
        {
            bet = Math.min(playerMoney, bankMoney);
        }
        else
        {
            bet += x;
        }
    }

    public void DecrementBet(int x)
    {
        if (betFreezed)
            return;

        if (bet - x < 0)
        {
            bet = 0;
        }
        else
        {
            bet -= x;
        }
    }

    public void StartGame()
    {
        deck.PutHandToDeck(playerHand);
        deck.PutHandToDeck(bankHand);
        deck.Shuffle();
        betFreezed = true;
    }

    public void BankTurn()
    {
        boolean decision = bankAI.GetDecision();

        if (decision)
        {
            bankHand.AddCard(deck.GetCard());
            bankScore = bankHand.GetScore();

            if (bankScore >= 21)
                FinishGame();
        }
    }

    public void PlayerTurn()
    {
        playerHand.AddCard(deck.GetCard());
        playerScore = playerHand.GetScore();
    }

    public void FinishGame()
    {
        if (bankScore == 21)
            LoseActions(EndCase.BankGainedO4KO);
        else if (bankScore > 21)
            WinActions(EndCase.BankOverScored);
        else if (playerScore == 21)
            WinActions(EndCase.PlayerGainedO4KO);
        else if (playerScore > 21)
            LoseActions(EndCase.PlayerOverScored);
        else if (playerScore > bankScore)
            WinActions(EndCase.PlayerOverBank);
        else if (playerScore < bankScore)
            LoseActions(EndCase.BankOverPlayer);
        else
            DrawActions(EndCase.Draw);

        bet = 0;
        betFreezed = false;
    }

    public void DrawActions(EndCase endCase)
    {

    }

    public void WinActions(EndCase endCase)
    {
        bankMoney -= bet;
        playerMoney += bet;
    }

    public void LoseActions(EndCase endCase)
    {
        if (endCase != EndCase.PlayerOverScored && playerScore > 21)
            bet *= 2;

        bankMoney += bet;
        playerMoney -= bet;

        if (playerMoney <= 0)
            GameOver();
    }

    public void GameOver()
    {

    }
}
