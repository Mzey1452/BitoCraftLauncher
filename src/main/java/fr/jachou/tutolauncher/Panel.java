package fr.jachou.tutolauncher;

import fr.jachou.tutolauncher.utils.MicrosoftThread;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static fr.jachou.tutolauncher.Frame.*;

public class Panel extends JPanel implements SwingerEventListener {
    private Image background = getImage("Launcher.png");
    private STexturedButton play = new STexturedButton(getBufferedImage("bouton.png"), getBufferedImage("bouton.png"));
    private STexturedButton microsoft = new STexturedButton(getBufferedImage("microsoft.png"), getBufferedImage("microsoft.png"));

    public Panel() throws IOException {
        this.setLayout(null);

        play.setBounds(109, 109);
        play.setLocation(200, 490);
        play.addEventListener(this);
        this.add(play);

        microsoft.setBounds(100, 100);
        microsoft.setLocation(200, 300);
        microsoft.addEventListener(this);
        this.add(microsoft);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void onEvent(SwingerEvent swingerEvent) {
        if (swingerEvent.getSource() == microsoft) {
            Launcher.crack();
        } else if (swingerEvent.getSource() == play) {

            try {
                Launcher.update();
            } catch (Exception e) {
                Launcher.getReporter().catchError(e, "Impossible de mettre à jour le launcher.");
            }

            try {
                Launcher.launch();
            } catch (Exception e) {
                Launcher.getReporter().catchError(e, "Impossible de lancer le jeu.");
            }
        }
    }
}
