import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends Container implements ActionListener {
    private Button startGameButton;
    private Button exitGameButton;
    private int buttonWidth = 100;
    private int buttonHeight = 30;
    private App app;
    private Panel panel;

    public Menu(App a)
    {
        panel = new Panel(null);
        add(panel);

        app = a;
        setSize(a.getWidth(), a.getHeight());
        panel.setSize(a.getWidth(), a.getHeight());

        startGameButton = new Button("New game");
        panel.setBackground(new Color(79, 121, 66));
        panel.add(startGameButton);
        startGameButton.setLocation(panel.getWidth() / 2 - buttonWidth / 2, panel.getHeight() / 3);
        startGameButton.setSize(buttonWidth, buttonHeight);
        startGameButton.addActionListener(this);

        exitGameButton = new Button("Exit");
        panel.add(exitGameButton);
        exitGameButton.setLocation(getWidth() / 2 - buttonWidth / 2, 2 * getHeight() / 3);
        exitGameButton.setSize(buttonWidth,buttonHeight);
        exitGameButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == startGameButton)
            app.StartGame();
        else if (e.getSource() == exitGameButton)
            app.ExitApp();
    }
}
