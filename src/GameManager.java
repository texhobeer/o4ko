import java.util.ArrayList;

public class GameManager {
    public enum EndCase
    {
        BankGainedO4KO,
        PlayerGainedO4KO,
        BankOverScored,
        PlayerOverScored,
        PlayerOverBank,
        BankOverPlayer,
        Draw,
        Null
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
    private boolean isPlayerFold;
    private boolean isBankFold;
    private EndCase endCase;

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

    public void IncrementBet()
    {
        IncrementBet(5);
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

    public void DecrementBet()
    {
        DecrementBet(5);
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

        playerScore = bankScore = 0;

        isBankFold = false;
        isPlayerFold = false;

        betFreezed = true;

        endCase = EndCase.Null;
    }

    public void BankTurn()
    {
        boolean decision = bankAI.GetDecision();

        if (decision)
        {
            Card toBank = deck.GetCard();

            bankHand.AddCard(toBank);
            bankScore = bankHand.GetScore();

            if (bankScore >= 21)
                FinishGame();

            return;
        }

        isBankFold = true;
    }

    public void PlayerTurn()
    {
        Card toPlayer = deck.GetCard();

        playerHand.AddCard(toPlayer);
        playerScore = playerHand.GetScore();

        if (playerScore == 21)
            FinishGame();
    }

    public void Turn()
    {
        if (!isPlayerFold)
            PlayerTurn();

        if (deck.GetSize() == 0)
        {
            FinishGame();
            return;
        }

        if (endCase != EndCase.Null)
            return;

        if (!isBankFold)
            BankTurn();

        if (deck.GetSize() == 0)
        {
            FinishGame();
        }
    }

    public void Fold()
    {
        isPlayerFold = true;

        while (!isBankFold)
        {
            BankTurn();
        }

        FinishGame();
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

    private void DrawActions(EndCase e)
    {
        endCase = e;
    }

    private void WinActions(EndCase e)
    {
        endCase = e;

        bankMoney -= bet;
        playerMoney += bet;
    }

    private void LoseActions(EndCase e)
    {
        endCase = e;

        if (playerHand.lastCard != null && playerScore - playerHand.lastCard.GetScore() > 21)
            bet *= 2;

        if (playerMoney - bet < 0)
        {
            bankMoney += playerMoney;
            playerMoney = 0;
        }
        else
        {
            bankMoney += bet;
            playerMoney -= bet;
        }
    }

    public int GetBet()
    {
        return bet;
    }

    public int GetPlayerScore()
    {
        return playerScore;
    }

    public int GetBankScore()
    {
        return bankScore;
    }

    public int GetPlayerMoney()
    {
        return playerMoney;
    }

    public int GetBankMoney()
    {
        return bankMoney;
    }

    public EndCase GetEndCase()
    {
        return endCase;
    }

    public boolean IsBankFold()
    {
        return isBankFold;
    }

    public Hand GetPlayerHand()
    {
        return playerHand;
    }

    public Hand GetBankHand()
    {
        return bankHand;
    }
}
