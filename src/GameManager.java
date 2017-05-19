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
    private int bankMoney;
    private int playerMoney;
    private int bet;
    private int playerScore;
    private int bankScore;
    private Hand playerHand;
    private Hand bankHand;

    public GameManager()
    {
        deck = new Deck();
    }

    public void IncrementBet(int x)
    {
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
    }

    public void BankTurn()
    {

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
    }

    public void DrawActions(EndCase endCase)
    {

    }

    public void WinActions(EndCase endCase)
    {

    }

    public void LoseActions(EndCase endCase)
    {

    }
}
