package roborace.client;

public class CardTurn180 extends Card {
	
    public CardTurn180(int priority, int playerID) {
        super(priority,playerID);
        image = ImageAndAnimationFactory.getInstance().createCardTurn180(priority);
    }

    @Override
    public String toXMLString() {
        return "<CardTurn180 priority=\"" + getPriority() + "\" playerID=\"" + getID() + "\"/>";
    }	
}
