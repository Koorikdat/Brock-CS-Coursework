package roborace.common;

public class Channel {
	
    private final Queue<String> port1ToPort2;
    private final Queue<String> port2ToPort1;

    public Channel() {
        port1ToPort2 = new Queue<String>();
        port2ToPort1 = new Queue<String>();
    }

    public void pushPort1(String message) { 
        port1ToPort2.push(message);
    }

    public void pushPort2(String message) { 
        port2ToPort1.push(message);
    }

    public String pullPort1() {
        return port2ToPort1.pull();
    }

    public String pullPort2() {
        return port1ToPort2.pull();
    }

    public Port asPort1() {
        return new Port() {
            @Override
            public void send(String message) {
                    pushPort1(message);
            }

            @Override
            public String receive() {
                    return pullPort1();
            }

            @Override
            public void close() {}
        };
    }

    public Port asPort2() {
        return new Port() {
            @Override
            public void send(String message) {
                    pushPort2(message);
            }

            @Override
            public String receive() {
                    return pullPort2();
            }

            @Override
            public void close() {}
        };
    }
}