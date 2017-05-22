import java.util.ArrayList;

public class Hand {
    private int score;
    public ArrayList<Card> cards;
    public Card lastCard;

    public Hand()
    {
        cards = new ArrayList<>();
        score = 0;
    }

    public void Reset()
    {
        score = 0;
        cards.clear();
        lastCard = null;
    }

    public void AddCard(Card card)
    {
        cards.add(card);
        lastCard = card;
        score += card.GetScore();
    }

    public int GetScore()
    {
        return score;
    }
}
