package roborace.client;

public class EventCardEnd implements Event {

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
