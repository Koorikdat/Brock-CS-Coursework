package roborace.client;

public class CardTurn90 extends Card {
	
    private final boolean clockwise;

    public CardTurn90(int priority, int playerID, boolean clockwise) {
        super(priority,playerID);
        this.clockwise = clockwise;
        image = ImageAndAnimationFactory.getInstance().createCardTurn90(priority,clockwise);
    }

    @Override
    public String toXMLString() {
        return "<CardTurn90 priority=\"" + getPriority() + "\" playerID=\"" + getID() + "\" clockwise=\"" + clockwise + "\"/>";
    }	
}
