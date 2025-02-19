package roborace.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JPanel;

public class InfoPane extends JPanel {
    
    private enum InfoType {None, Card, Effect, Decorations};
    
    private final String playerName;
    private final Font regFont;
    private final Font headingFont;
    private final Image logo;
    
    private NextStepButton nextStepButton;
    private boolean singleStep;
    private int phase;
    private InfoType infoType;
    private Image cardImage;
    private String currentPlayerName;
    
    public InfoPane(int width, int height, String playerName) {
        this.playerName = playerName;
        phase = 1;
        super.setPreferredSize(new Dimension(width,height));
        Font regFontTemp = null;
        Font headingFontTemp = null;
        try {
            Font obelix = Font.createFont(Font.TRUETYPE_FONT,new FileInputStream("./Font/ObelixPro-cyr.ttf"));
            regFontTemp = obelix.deriveFont(Font.PLAIN,12);
            headingFontTemp = obelix.deriveFont(Font.PLAIN,24);
        } catch (FontFormatException ex) {} 
          catch (IOException ex) {}
        regFont = regFontTemp;
        headingFont = headingFontTemp;
        ImageAndAnimationFactory animFac = ImageAndAnimationFactory.getInstance();
        logo = animFac.getLogoImage();
        singleStep = false;
        infoType = InfoType.None;
    }
    
    public void setNextStepButton(NextStepButton nextStepButton) {
        this.nextStepButton = nextStepButton;
    }
    
    public void increasePhase() {
        phase++;
    }
    
    public void resetPhase() {
        phase = 1;
    }
    
    public void toggleSingleStep() {
        singleStep = !singleStep;
        if (!singleStep) {
            nextStepButton.setEnabled(false);
            synchronized(this) {
                notify();
            }
        }
    }
    
    public boolean isSingleStep() {
        return singleStep;
    }
    
    public synchronized void nextStep() {
        notify();
    }
    
    public void displayNoInfo() {
        infoType = InfoType.None;
        repaint();
    }
    
    public void displayCardInfo(Image cardImage, String name) {
        this.cardImage = cardImage;
        this.currentPlayerName = name;
        infoType = InfoType.Card;
        repaint();
    }
    
    public void displayEffectInfo(String name) {
        this.currentPlayerName = name;
        infoType = InfoType.Effect;
        repaint();
    }
    
    public void displayRunDecorationsInfo() {
        infoType = InfoType.Decorations;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0,0,getSize().width,getSize().height);
        g.setColor(Color.RED);
        g.setFont(headingFont);
        g.drawString("RoboRace 2.0",20,40);
        g.drawLine(20,50,219,50);
        g.setFont(regFont);
        g.drawString("Player: " + playerName,10,90);
        g.drawImage(logo,60,getSize().height-120,null);
        switch (infoType) {
            case Card:  
                g.drawString("Phase: " + phase,10,150);
                g.drawString("Action: Executing card",10,170);
                g.drawString("Player: " + currentPlayerName,10,190);
                g.drawImage(cardImage,(getSize().width-cardImage.getWidth(null))/2,210,null);
                break;
            case Effect:
                g.drawString("Phase: " + phase,10,150);
                g.drawString("Action: Location effect",10,170);
                g.drawString("Player: " + currentPlayerName,10,190);
                break;
            case Decorations:
                g.drawString("Phase: " + phase,10,150);
                g.drawString("Action: Running all",10,170);
                g.drawString("location effects",69,190);
                break;
        }
    }
    
    public synchronized void waitOnSingleStep() {
        if (singleStep) {
            nextStepButton.setEnabled(true);
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
    }
}
