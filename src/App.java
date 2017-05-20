import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class App extends Applet implements Runnable {
    private int width = 1000;
    private int height = 600;
    private Menu menu;
    private GameManager gameManager;
    private GameInterface gameInterface;
    private Thread thread;
    private Graphics graphics;
    private Image img;

    public void init() {
        setLayout(null);
        resize(width, height);
        img = createImage(width, height);
        graphics = img.getGraphics();

        menu = new Menu(this);
        add(menu);
        menu.setVisible(true);

        gameManager = new GameManager();
        gameInterface = new GameInterface(this, graphics);
        add(gameInterface);
        setVisible(false);

        CardImage kek = new CardImage();

        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g)
    {
//        graphics.setColor(new Color(79, 121, 66));
//        graphics.fillRect(0, 0, width, height);

//        g.drawImage(img, 0, 0, null);
//        g.drawImage(CardImage.SpadesAce, 100, 100, null);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void repaint(Graphics g)
    {
        paint(g);
    }

    public void run()
    {
        for(;;)
            repaint(graphics);
    }

    public void ExitApp()
    {
        System.exit(0);
    }

    public void StartGame()
    {
        menu.setVisible(false);
        gameInterface.setVisible(true);
        gameManager.Init();
    }
}