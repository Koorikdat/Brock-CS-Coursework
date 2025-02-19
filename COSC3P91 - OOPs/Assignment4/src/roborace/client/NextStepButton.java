package roborace.client;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class NextStepButton extends JButton implements ActionListener {
    
    private final InfoPane infoPane;
    
    @SuppressWarnings("LeakingThisInConstructor")       
    public NextStepButton(InfoPane infoPane) {
        super.addActionListener(this);
        this.infoPane = infoPane;
        super.setBorderPainted(false);
        super.setBorder(null);
        super.setMargin(new Insets(0,0,0,0));
        super.setContentAreaFilled(false);
        super.setIcon(new ImageIcon(ImageAndAnimationFactory.getInstance().getNextStepImage(),""));
        super.setDisabledIcon(new ImageIcon(new BufferedImage(74,74,BufferedImage.TYPE_INT_ARGB),""));
        super.setEnabled(false);
        infoPane.setNextStepButton(this);
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        infoPane.nextStep();
        setEnabled(false);
    }          
}