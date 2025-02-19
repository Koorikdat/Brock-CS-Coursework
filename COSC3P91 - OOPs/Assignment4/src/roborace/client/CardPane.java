package roborace.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import roborace.common.GameDialogs;

public class CardPane extends JPanel implements MouseListener {
	
    private final Image noCard;
    private final Image[] selectImages;
    private CardList cards;
    private CardList selectedCards;
    private boolean selecting;
    private final SelectDoneButton okButton;
    private final JFrame parent;

    @SuppressWarnings("LeakingThisInConstructor")
    public CardPane(SelectDoneButton okButton, JFrame parent) {
        this.okButton = okButton;
        this.parent = parent;
        okButton.setOwner(this);
        super.setPreferredSize(new Dimension(644,120));
        ImageAndAnimationFactory aminFac = ImageAndAnimationFactory.getInstance();
        noCard = aminFac.getNoCardImage();
        selectImages = aminFac.getSelectImages();
        cards = new CardList();
        selectedCards = new CardList();
        selecting = false;
        super.addMouseListener(this);
    }

    public CardList selectCards(CardList list) {
        
        cards = list;
        selecting = true;
        okButton.setEnabled(true);
            
        repaint();
        
       synchronized(this){
        
           
           while(selecting){
               
               
                   
        try{   
           wait();
        }
        catch(Exception e){
        }
       
               }
       }
    
      return cards;

    }
    
    

    public void stopSelection() {
        
        if (5 == selectedCards.size()){
        
            cards = selectedCards;
            selectedCards = new CardList();
            selecting = false;
            okButton.setEnabled(false);
            
           repaint();
           synchronized(this) {
               notify();
           } 
        
        } else {
        
         
            
            
        GameDialogs.showMessageDialog("Minimum 5 card selection", "Must choose 5 cards", null);
        }
        
        
        
        
    }

    
    
    
    @Override
    public void paintComponent(Graphics g) {	
        
        
        for (int i=0;i<7;i++)        
            
        {                            
            if (i < cards.size()){ 
                
            Card c = cards.get(i);
            g.drawImage(c.getImage(), i*92, 0, null);
            
            
            int index = selectedCards.indexOf(c);
            if (index != -1){
            Image numeral = selectImages[index];    
            g.drawImage(numeral, i*92 + 32, 52, null);
            
            }
            
                        
            } else {
           
            g.drawImage(noCard, i*92, 0, null);
             
             }
        }
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
	
		int x = me.getX();
		int y = me.getY();
		if (me.getButton() == MouseEvent.BUTTON1) {
                    
                    switch(me.getButton())
                    {
                        case MouseEvent.BUTTON1:
                        if((x/92)>=0 && (x/92)<cards.size())
                        {
                            if(selectedCards.contains(cards.get(x/92)))
                            {
                            selectedCards.remove();
                            }
                            else{
                            
                            if(selectedCards.size()<=5)
                            {
                            selectedCards.add(cards.get(x/92));
                            }
                            }
                                repaint();
                                
                                }
                    
                    }
                    
                    
                    
                    
		};

	}


    

    @Override
    public void mouseReleased(MouseEvent e) {
    }	
}