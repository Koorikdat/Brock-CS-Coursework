package roborace.client;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SelectDoneButton extends JButton implements ActionListener {
    
    private CardPane owner;
    
    @SuppressWarnings("LeakingThisInConstructor")   
    public SelectDoneButton() {
        super.addActionListener(this);
        super.setBorderPainted(false);
        super.setBorder(null);
        super.setMargin(new Insets(0, 0, 0, 0));
        super.setContentAreaFilled(false);
        super.setEnabled(false);
        super.setIcon(new ImageIcon(ImageAndAnimationFactory.getInstance().getSelectDoneImage(),""));
        super.setDisabledIcon(new ImageIcon(new BufferedImage(74,74,BufferedImage.TYPE_INT_ARGB),""));
    };
    
    public void setOwner(CardPane owner) {
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        owner.stopSelection();
    }        
}
