package roborace.client;

public class EventRunDecorations implements Event {
    
    private final int phase;
    
    public EventRunDecorations(int phase) {
        this.phase = phase;
    }
    
    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
        infoPane.displayRunDecorationsInfo();
        board.runDecorations(phase);
        board.waitOnAnimations();
        infoPane.waitOnSingleStep();
        infoPane.displayNoInfo();
        infoPane.increasePhase();
    }
}
