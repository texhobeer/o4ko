public class Card
{
    public enum Suit
    {
        Diamonds,
        Spades,
        Clubs,
        Hearts
    }

    public enum Rank
    {
        Ace,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King
    }

    private Suit suit;
    private Rank rank;
    private int score;

    private void SetScore()
    {
        switch (rank)
        {
            case Ace:
                score = 11;
                break;
            case Six:
                score = 6;
                break;
            case Seven:
                score = 7;
                break;
            case Eight:
                score = 8;
                break;
            case Nine:
                score = 9;
                break;
            case Ten:
                score = 10;
                break;
            case Jack:
                score = 2;
                break;
            case Queen:
                score = 3;
                break;
            case King:
                score = 4;
                break;
            default:
                score = 0;
                break;
        }
    }

    public Card(Suit s, Rank r)
    {
        suit = s;
        rank = r;

        SetScore();
    }

    public int GetScore()
    {
        return score;
    }

    public Rank GetRank()
    {
        return rank;
    }

    public Suit GetSuit()
    {
        return suit;
    }
}
