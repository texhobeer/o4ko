import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;
    final private Random random = new Random();

    public Deck()
    {
        cards = new ArrayList<>();

        for(Card.Suit suit : Card.Suit.values())
        {
            for(Card.Rank rank : Card.Rank.values())
            {
                cards.add(new Card(suit, rank));
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
}
