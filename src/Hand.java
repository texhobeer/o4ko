import java.util.ArrayList;

public class Hand {
    public ArrayList<Card> cards;
    public int score;

    public Hand()
    {
        cards = new ArrayList<>();
        score = 0;
    }

    public void Reset()
    {
        score = 0;
        cards.clear();
    }

    public void AddCard(Card card)
    {
        cards.add(card);
        score += card.GetScore();
    }

    public int GetScore()
    {
        return score;
    }
}
