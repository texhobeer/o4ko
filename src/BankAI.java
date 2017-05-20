public class BankAI {
    private Hand bankHand;
    private Hand playerHand;

    private float sensitivity;
    private int fullDeckScore;
    private int fullDeckSize;

    public BankAI(Deck deck, Hand playerH, Hand bankH, float s) {
        playerHand = playerH;
        bankHand = bankH;
        sensitivity = s;
        fullDeckSize = deck.GetFullDeckSize();
        fullDeckScore = deck.GetFullDeckScore();
    }

    public boolean GetDecision() {
        if (21 - CalcTurnMathEx() < sensitivity)
            return false;
        return true;
    }

    public void SetSensitivity(float s)
    {
        sensitivity = s;
    }

    private float CalcTurnMathEx()
    {
        float mathEx = 0;
        int deckSize = fullDeckSize;
        float deckScore = fullDeckScore;

        for (int i = 0; i < playerHand.cards.size(); i++)
        {
            deckScore -= bankHand.cards.get(i).GetScore();
            deckSize--;
            deckScore -= deckScore / deckSize;
            deckSize--;
        }

        mathEx = deckScore / deckSize;

        return mathEx;
    }
}
