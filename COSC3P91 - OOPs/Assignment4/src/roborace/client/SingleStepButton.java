package roborace.client;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SingleStepButton extends JButton implements ActionListener {
    
    private final InfoPane infoPane;
    private final ImageIcon singleStepOnImage;
    private final ImageIcon singleStepOffImage;
        
    @SuppressWarnings("LeakingThisInConstructor")   
    public SingleStepButton(InfoPane infoPane) {
        super.addActionListener(this);
        this.infoPane = infoPane;
        super.setBorderPainted(false);
        super.setBorder(null);
        super.setMargin(new Insets(0,0,0,0));
        super.setContentAreaFilled(false);
        ImageAndAnimationFactory aminFac = ImageAndAnimationFactory.getInstance();
        singleStepOnImage = new ImageIcon(aminFac.getSingleStepOnImage(),"");
        singleStepOffImage = new ImageIcon(aminFac.getSingleStepOffImage(),"");
        super.setIcon(singleStepOnImage);
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        infoPane.toggleSingleStep();
        if (infoPane.isSingleStep()) {
            setIcon(singleStepOffImage);
        } else {
            setIcon(singleStepOnImage);
        }
    }          
}
