package roborace.client;

public class DecorationPusherAnimation extends DecorationAnimation {
    
    private final int phase1;
    private final int phase2;
    private final int phase3;
    
    public DecorationPusherAnimation(int x, int y, int phase1, int phase2, int phase3) {
        super(x,y);
        this.phase1 = phase1;
        this.phase2 = phase2;
        this.phase3 = phase3;
    }
    
    @Override
    public void start() {
        super.start();
    }
    
    @Override
    public synchronized void start(int phase) {
        if (phase==phase1 || phase==phase2 || phase==phase3) {
            this.start();
        }
    } 
}