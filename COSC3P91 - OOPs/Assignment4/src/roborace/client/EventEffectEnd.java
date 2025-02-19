package roborace.client;

public class EventEffectEnd implements Event {

    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
        board.waitOnAnimations();
        try {
            Thread.sleep(1000);
	} catch(InterruptedException e) {}
        infoPane.waitOnSingleStep();
        infoPane.displayNoInfo();
    } 
}
