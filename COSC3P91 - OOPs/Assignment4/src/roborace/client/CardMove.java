package roborace.client;

public class CardMove extends Card {

    private final int steps;

    public CardMove(int priority, int playerID, int steps) {
        super(priority,playerID);
        this.steps = steps;
        image = ImageAndAnimationFactory.getInstance().createCardMove(priority,steps);
    }

    @Override
    public String toXMLString() {
        return "<CardMove priority=\"" + getPriority() + "\" playerID=\"" + getID() + "\" steps=\"" + steps + "\"/>";
    }
}
