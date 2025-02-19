package roborace.client;

public class EventWait implements Event {
    
    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
        board.waitOnAnimations();
    }
}
