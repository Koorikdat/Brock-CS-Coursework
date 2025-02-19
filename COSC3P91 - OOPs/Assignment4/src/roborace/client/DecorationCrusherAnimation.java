package roborace.client;

public class DecorationCrusherAnimation extends DecorationAnimation {
    
    private final int phase1;
    private final int phase2;
    
    public DecorationCrusherAnimation(int x, int y, int phase1, int phase2) {
        super(x,y);
        this.phase1 = phase1;
        this.phase2 = phase2;
    }
    
    @Override
    public void start() {
        super.start();
    }
    
    @Override
    public synchronized void start(int phase) {
        if (phase==phase1 || phase==phase2) {
            this.start();
        }
    } 
}