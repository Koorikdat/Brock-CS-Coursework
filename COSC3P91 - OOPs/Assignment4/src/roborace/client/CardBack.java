package roborace.client;

public class CardBack extends Card {

    public CardBack(int priority, int playerID) {
        super(priority,playerID);
        image = ImageAndAnimationFactory.getInstance().createCardBack(priority);
    }

    @Override
    public String toXMLString() {
        return "<CardBack priority=\"" + getPriority() + "\" playerID=\"" + getID() + "\"/>";
    }
}
