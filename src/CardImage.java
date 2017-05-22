import javax.swing.*;
import java.awt.*;

public class CardImage extends Container{
    private float dx = 1;
    private float dy = 1;
    public Image img;
    private int xPos;
    private int yPos;
    private float aprXPos;
    private float aprYPos;
    private int xDest;
    private int yDest;
    private int clocks = 10;
    private boolean isMoving;

    public CardImage(Card card)
    {
        this(card, 0, 0);
    }
    public CardImage(Card card, int x, int y)
    {
        setSize(1000, 600);

        xPos = x;
        yPos = y;

        switch (card.GetSuit())
        {
            case Clubs:
                switch (card.GetRank())
                {
                    case Ace:
                        img = ClubsAce;
                        break;
                    case Six:
                        img = ClubsSix;
                        break;
                    case Seven:
                        img = ClubsSeven;
                        break;
                    case Eight:
                        img = ClubsEight;
                        break;
                    case Nine:
                        img = ClubsNine;
                        break;
                    case Ten:
                        img = ClubsTen;
                        break;
                    case Jack:
                        img = ClubsJack;
                        break;
                    case Queen:
                        img = ClubsQueen;
                        break;
                    case King:
                        img = ClubsKing;
                        break;
                    default:
                        img = null;
                        break;
                }
                break;
            case Diamonds:
                switch (card.GetRank())
                {
                    case Ace:
                        img = DiamondsAce;
                        break;
                    case Six:
                        img = DiamondsSix;
                        break;
                    case Seven:
                        img = DiamondsSeven;
                        break;
                    case Eight:
                        img = DiamondsEight;
                        break;
                    case Nine:
                        img = DiamondsNine;
                        break;
                    case Ten:
                        img = DiamondsTen;
                        break;
                    case Jack:
                        img = DiamondsJack;
                        break;
                    case Queen:
                        img = DiamondsQueen;
                        break;
                    case King:
                        img = DiamondsKing;
                        break;
                    default:
                        img = null;
                        break;
                }
                break;
            case Hearts:
                switch (card.GetRank())
                {
                    case Ace:
                        img = HeartsAce;
                        break;
                    case Six:
                        img = HeartsSix;
                        break;
                    case Seven:
                        img = HeartsSeven;
                        break;
                    case Eight:
                        img = HeartsEight;
                        break;
                    case Nine:
                        img = HeartsNine;
                        break;
                    case Ten:
                        img = HeartsTen;
                        break;
                    case Jack:
                        img = HeartsJack;
                        break;
                    case Queen:
                        img = HeartsQueen;
                        break;
                    case King:
                        img = HeartsKing;
                        break;
                    default:
                        img = null;
                        break;
                }
                break;
            case Spades:
                switch (card.GetRank())
                {
                    case Ace:
                        img = SpadesAce;
                        break;
                    case Six:
                        img = SpadesSix;
                        break;
                    case Seven:
                        img = SpadesSeven;
                        break;
                    case Eight:
                        img = SpadesEight;
                        break;
                    case Nine:
                        img = SpadesNine;
                        break;
                    case Ten:
                        img = SpadesTen;
                        break;
                    case Jack:
                        img = SpadesJack;
                        break;
                    case Queen:
                        img = SpadesQueen;
                        break;
                    case King:
                        img = SpadesKing;
                        break;
                    default:
                        img = null;
                        break;
                }
                break;
        }
    }

    public void SetDestPosition(int x, int y)
    {
        xDest = x;
        yDest = y;

        dx = ((float) xDest - (float) yPos) / clocks;
        dy = ((float) yDest - (float) yPos) / clocks;

        aprXPos = xPos;
        aprYPos = yPos;

        isMoving = true;
    }

    public void SetPosition(int x, int y)
    {
        xPos = x;
        yPos = y;
    }

    public void Update() {
        if (!isMoving)
            return;

        aprXPos += dx;
        aprYPos += dy;

        xPos = Math.round(aprXPos);
        yPos = Math.round(aprYPos);

        boolean xReached = false;
        boolean yReached = false;

        if (dx >= 0 && xPos >= xDest
                || dx < 0 && xPos <= xDest) {
            xPos = xDest;
            xReached = true;
        }

        if (dy >= 0 && yPos >= yDest
                || dy < 0 && yPos <= yDest) {
            yPos = yDest;
            yReached = true;
        }

        isMoving = !(xReached && yReached);
    }

    public void Paint(Graphics g)
    {
        Update();

        if (g != null)
            g.drawImage(img, xPos, yPos, null);
    }

    public void SetXPosition(int x)
    {
        xPos = x;
    }

    public void SetYPosition(int y)
    {
        yPos = y;
    }

    public int GetXPosition()
    {
        return xPos;
    }

    public int GetYPosition()
    {
        return yPos;
    }

    public boolean isMoving()
    {
        return isMoving;
    }

    public static final String path = "C:/Users/TEXHOBEER/Desktop/o4ko/pics/";

    public static Image SpadesAce = new ImageIcon(path + "SpadesAce.gif").getImage();
    public static Image SpadesSix = new ImageIcon(path + "SpadesSix.gif").getImage();
    public static Image SpadesSeven = new ImageIcon(path + "SpadesSeven.gif").getImage();
    public static Image SpadesEight = new ImageIcon(path + "SpadesEight.gif").getImage();
    public static Image SpadesNine = new ImageIcon(path + "SpadesNine.gif").getImage();
    public static Image SpadesTen = new ImageIcon(path + "SpadesTen.gif").getImage();
    public static Image SpadesJack = new ImageIcon(path + "SpadesJack.gif").getImage();
    public static Image SpadesQueen = new ImageIcon(path + "SpadesQueen.gif").getImage();
    public static Image SpadesKing = new ImageIcon(path + "SpadesKing.gif").getImage();

    public static Image HeartsAce = new ImageIcon(path + "HeartsAce.gif").getImage();
    public static Image HeartsSix = new ImageIcon(path + "HeartsSix.gif").getImage();
    public static Image HeartsSeven = new ImageIcon(path + "HeartsSeven.gif").getImage();
    public static Image HeartsEight = new ImageIcon(path + "HeartsEight.gif").getImage();
    public static Image HeartsNine = new ImageIcon(path + "HeartsNine.gif").getImage();
    public static Image HeartsTen = new ImageIcon(path + "HeartsTen.gif").getImage();
    public static Image HeartsJack = new ImageIcon(path + "HeartsJack.gif").getImage();
    public static Image HeartsQueen = new ImageIcon(path + "HeartsQueen.gif").getImage();
    public static Image HeartsKing = new ImageIcon(path + "HeartsKing.gif").getImage();

    public static Image DiamondsAce = new ImageIcon(path + "DiamondsAce.gif").getImage();
    public static Image DiamondsSix = new ImageIcon(path + "DiamondsSix.gif").getImage();
    public static Image DiamondsSeven = new ImageIcon(path + "DiamondsSeven.gif").getImage();
    public static Image DiamondsEight = new ImageIcon(path + "DiamondsEight.gif").getImage();
    public static Image DiamondsNine = new ImageIcon(path + "DiamondsNine.gif").getImage();
    public static Image DiamondsTen = new ImageIcon(path + "DiamondsTen.gif").getImage();
    public static Image DiamondsJack = new ImageIcon(path + "DiamondsJack.gif").getImage();
    public static Image DiamondsQueen = new ImageIcon(path + "DiamondsQueen.gif").getImage();
    public static Image DiamondsKing = new ImageIcon(path + "DiamondsKing.gif").getImage();

    public static Image ClubsAce = new ImageIcon(path + "ClubsAce.gif").getImage();
    public static Image ClubsSix = new ImageIcon(path + "ClubsSix.gif").getImage();
    public static Image ClubsSeven = new ImageIcon(path + "ClubsSeven.gif").getImage();
    public static Image ClubsEight = new ImageIcon(path + "ClubsEight.gif").getImage();
    public static Image ClubsNine = new ImageIcon(path + "ClubsNine.gif").getImage();
    public static Image ClubsTen = new ImageIcon(path + "ClubsTen.gif").getImage();
    public static Image ClubsJack = new ImageIcon(path + "ClubsJack.gif").getImage();
    public static Image ClubsQueen = new ImageIcon(path + "ClubsQueen.gif").getImage();
    public static Image ClubsKing = new ImageIcon(path + "ClubsKing.gif").getImage();

    public static Image CardBack = new ImageIcon(path + "CardBack.gif").getImage();
}
