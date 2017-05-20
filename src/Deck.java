import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;
    private int fullDeckScore;
    private int fullDeckSize;
    final private Random random = new Random();

    public Deck()
    {
        cards = new ArrayList<>();

        fullDeckScore = 0;
        fullDeckSize = 0;

        for(Card.Suit suit : Card.Suit.values())
        {
            for(Card.Rank rank : Card.Rank.values())
            {
                Card card = new Card(suit, rank);
                cards.add(card);
                fullDeckScore += card.GetScore();
                fullDeckSize++;
            }
        }
    }

    public void Shuffle()
    {
        ArrayList<Card> shuffledCards = new ArrayList<>();

        while (!cards.isEmpty())
        {
            int randomCard = random.nextInt(cards.size());
            shuffledCards.add(cards.get(randomCard));
            cards.remove(randomCard);
        }

        cards = shuffledCards;
    }

    public void PrintDeck()
    {
        for (Card card: cards)
        {
            System.out.print(card.GetSuit() + " " + card.GetRank() + " ");
        }

        System.out.println();
    }

    public Card GetCard()
    {
        Card top = cards.get(0);
        cards.remove(0);
        return top;
    }

    public int GetSize()
    {
        return cards.size();
    }

    public void PutHandToDeck(Hand hand)
    {
        cards.addAll(hand.cards);
        hand.Reset();
    }

    public int GetFullDeckScore()
    {
        return fullDeckScore;
    }

    public int GetFullDeckSize()
    {
        return fullDeckSize;
    }
}
