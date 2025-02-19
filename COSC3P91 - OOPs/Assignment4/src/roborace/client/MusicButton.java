package roborace.client;

import COSC3P91.midi.MidiManager;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MusicButton extends JButton implements ActionListener {
    
    private boolean paused;
    private final ImageIcon musicOff;
    private final ImageIcon musicOn;
    
    @SuppressWarnings("LeakingThisInConstructor")    
    public MusicButton() {
        super.addActionListener(this);
        paused = false;
        super.setBorderPainted(false);
        super.setBorder(null);
        super.setMargin(new Insets(0, 0, 0, 0));
        super.setContentAreaFilled(false);
        ImageAndAnimationFactory aminFac = ImageAndAnimationFactory.getInstance();
        musicOff = new ImageIcon(aminFac.getMusicOffImage(),"");
        musicOn = new ImageIcon(aminFac.getMusicOnImage(),"");
        super.setIcon(musicOff);
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        paused = !paused;
        MidiManager.getInstance().setPaused(paused);
        if (paused) {
            setIcon(musicOn);
        } else {
            setIcon(musicOff);
        }
    }        
}
