package roborace.server;

public class EventRunDecorations implements Event {
    
    private final int phase;
    
    public EventRunDecorations(int phase) {
        this.phase = phase;
    }
    
    @Override
    public String toXMLString() {
        return "<EventRunDecorations phase=\"" + phase + "\"/>";
    }
}
