package roborace.client;

public class EventCardStart implements Event {
    
    private final Card card;
    
    public EventCardStart(Card card) {
        this.card = card;
    }
    
    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
        infoPane.displayCardInfo(card.getImage(),board.getRobotByID(card.getID()).getName());
    }
}
