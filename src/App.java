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
        gameInterface = new GameInterface(this, gameManager, graphics);
        add(gameInterface);
        gameInterface.setVisible(false);

        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g)
    {
  //      graphics.setColor(new Color(79, 121, 66));
  //      if (gameInterface.isVisible())
  //          gameInterface.paint(graphics);

  //      g.drawImage(img, 0, 0, null);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void repaint(Graphics g)
    {
        if (gameInterface.isVisible())
            gameInterface.Paint(graphics);

        g.drawImage(img, 0, 0, null);
    }

    private void Update()
    {
        if (gameInterface.isVisible())
            gameInterface.Update();
    }

    public void run()
    {
        for(;;)
        {
            repaint(graphics);
            try
            {
                thread.sleep(20);

                Update();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
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

    public GameManager GetGameManager()
    {
        return gameManager;
    }
}