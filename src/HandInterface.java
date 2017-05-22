import java.awt.*;
import java.util.ArrayList;

public class HandInterface {
    private ArrayList<CardImage> cards;
    private Hand hand;
    private CardImage lastCard;
    private int xCenter, yCenter;

    private int offset;
    private int cardWidth;
    public Panel background;
    private GameInterface gui;

    public HandInterface(int x, int y, int sizeX, int sizeY, Hand h, GameInterface gI)
    {
        xCenter = sizeX / 2;
        yCenter = sizeY / 10;

        gui = gI;

        background = new Panel();
        background.setBackground(new Color(79, 121, 66));
        gui.add(background);
        background.setLocation(x, y);
        background.setSize(sizeX, sizeY);

        hand = h;

        cardWidth = CardImage.ClubsAce.getWidth(null);
        offset = cardWidth / 3;
        lastCard = null;

        cards = new ArrayList<>();
    }

    public void AddCard(CardImage card)
    {
        lastCard = card;
        cards.add(card);
        background.add(card);

        int length = offset * (cards.size() - 1) + cardWidth;
        int pos = xCenter - length / 2;

        for(CardImage c : cards)
        {
            //c.SetDestPosition(pos, yCenter);
            c.SetPosition(pos, yCenter);
            pos += offset;
        }
    }

    public void Update()
    {
        if (hand.cards.size() > cards.size())
        {
            for (int i = cards.size(); i < hand.cards.size(); i++)
            {
                AddCard(new CardImage(hand.cards.get(i)));
                Paint();
            }
        }
    }

    public void Paint()
    {
        for(CardImage c : cards)
        {
            c.Paint(background.getGraphics());
        }
    }

    public void Clear()
    {
        // init moving
        background.removeAll();
        background.setVisible(false);
        background.setVisible(true);
        cards.clear();
    }
}
