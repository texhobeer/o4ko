import java.awt.*;
import java.util.ArrayList;

public class HandInterface extends Container {
    private ArrayList<CardImage> cards;
    private Hand hand;
    private CardImage lastCard;
    private int xCenter, yCenter;

    private int offset;
    private int cardWidth;
    public Panel background;
    private GameInterface gui;
    private boolean isMoving;

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

        int length = offset * (cards.size() - 1) + cardWidth;
        int pos = xCenter - length / 2;

        for(CardImage c : cards)
        {
            c.SetDestPosition(pos, yCenter);
            //c.SetPosition(pos, yCenter);
            pos += offset;
        }
    }

    public void Update()
    {
        if (hand.cards.size() > cards.size())
        {
            for (int i = cards.size(); i < hand.cards.size(); i++)
            {
                AddCard(new CardImage(hand.cards.get(i), gui.GetDeckXPos(), gui.GetDeckYPos()));
            }
        }

        checkOnMoving();
    }

    private void checkOnMoving()
    {
        for(CardImage c : cards)
        {
            if (c.isMoving())
            {
                isMoving = true;
                return;
            }
        }

        isMoving = false;
    }

    public boolean isMoving()
    {
        return isMoving;
    }

    public void Paint()
    {
        Image buffer = background.createImage(background.getWidth(), background.getHeight());

        if (buffer == null)
            return;

        Graphics bufferGr = buffer.getGraphics();

        for(CardImage c : cards)
        {
            c.Paint(bufferGr);
        }

        background.getGraphics().drawImage(buffer, getX(), getY(), null);
    }

    public void Clear()
    {
        cards.clear();
    }
}
