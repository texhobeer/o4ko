import java.util.ArrayList;

public class HandInterface {
    private ArrayList<CardImage> cards;
    private CardImage lastCard;
    private int xCenter, yCenter;

    private int offset;
    private int cardWidth;

    public HandInterface(int x, int y)
    {
        xCenter = x;
        yCenter = y;


        cardWidth = CardImage.ClubsAce.getWidth(null);
        offset = cardWidth / 3;
        lastCard = null;
    }

    public void AddCard(CardImage card)
    {
        lastCard = card;
        cards.add(card);

        int length = offset * (cards.size() - 1) + cardWidth;
        int pos = xCenter - length / 2;

        for(CardImage c : cards)
        {
            c.SetDestPosition(pos, yCenter);
            pos += offset;
        }
    }
}
