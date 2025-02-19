package roborace.common;

import java.io.Closeable;

public interface Port extends Closeable {
	
	public void send(String message);
	
	public String receive();	
}