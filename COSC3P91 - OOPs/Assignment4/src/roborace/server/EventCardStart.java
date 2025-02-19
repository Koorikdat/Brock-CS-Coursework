package roborace.server;

public class EventCardStart implements Event {
    
    private final Card card;
    
    public EventCardStart(Card card) {
        this.card = card;
    }
    
    @Override
    public String toXMLString() {
        return "<EventCardStart>" + card.toXMLString() + "</EventCardStart>";
    }  
}