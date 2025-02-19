package COSC3P91.midi;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class MidiManager implements MetaEventListener {
	
    public static final int END_OF_TRACK_MESSAGE = 47;
    
    private static MidiManager instance = null;
    
    public static MidiManager getInstance() {
    	if (instance == null) instance = new MidiManager();
    	return instance;
    }

    private Sequencer sequencer;
    private boolean loop;
    private boolean paused;
    private String midiPath;							

    @SuppressWarnings("LeakingThisInConstructor")
    private MidiManager() {
    	try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addMetaEventListener(this);
        }
        catch (MidiUnavailableException ex) {
            sequencer = null;
        }
    }
    
    public void setMidiPath(String path) {
    	midiPath = path;
    	if (!path.endsWith("/")) path += "/";
    }
    
    public String getMidiPath() {
    	return midiPath;
    }

    public Sequence getSequence(String filename) {
        Sequence result = null;
        try {
            result = getSequence(new FileInputStream(midiPath + filename));
        }
        catch (IOException ex) {}
        return result;
    }

    public Sequence getSequence(InputStream is) {
        Sequence result = null;
        try {
            if (!is.markSupported()) {
                is = new BufferedInputStream(is);
            }
            result = MidiSystem.getSequence(is);
            is.close();
        }
        catch (InvalidMidiDataException ex) {}
        catch (IOException ex) {}
        return result;
    }

	
    public void play(Sequence sequence) {
        play(sequence,false);
    }
	
    public void play(Sequence sequence, boolean loop) {
        if (sequencer != null && sequence != null && sequencer.isOpen()) {
            try {
                sequencer.setSequence(sequence);
                sequencer.start();
                this.loop = loop;
            }
            catch (InvalidMidiDataException ex) {}
        }
    }

    @Override
    public void meta(MetaMessage event) {
        if (event.getType() == END_OF_TRACK_MESSAGE) {
            if (sequencer != null && sequencer.isOpen() && loop) {
            	sequencer.setTickPosition(0);			
                sequencer.start();
            }
        }
    }

    public void stop() {
        if (sequencer != null && sequencer.isOpen()) {
            sequencer.stop();
            sequencer.setMicrosecondPosition(0);
        }
    }

    public void close() {
         if (sequencer != null && sequencer.isOpen()) {
             sequencer.close();
         }
    }

    public Sequencer getSequencer() {
        return sequencer;
    }

    public void setPaused(boolean paused) {
        if (this.paused != paused && sequencer != null && sequencer.isOpen()) {
            this.paused = paused;
            if (paused) {
                sequencer.stop();
            }
            else {
                sequencer.start();
            }
        }
    }

    public boolean isPaused() {
        return paused;
    }
}
