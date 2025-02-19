package COSC3P91.sound;

import COSC3P91.io.LoopingByteInputStream;
import COSC3P91.util.ThreadPool;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager extends ThreadPool {

    private AudioFormat playbackFormat;
    private ThreadLocal<SourceDataLine> localLine;		
    private ThreadLocal<byte[]> localBuffer;			
    private final Object pausedLock;
    private boolean paused;
    private String soundPath;							
    
    public static int getMaxSimultaneousSounds(AudioFormat playbackFormat) {
        DataLine.Info lineInfo = new DataLine.Info(SourceDataLine.class, playbackFormat);
        Mixer mixer = AudioSystem.getMixer(null);
        int maxLines = mixer.getMaxLines(lineInfo);   
        if (maxLines == AudioSystem.NOT_SPECIFIED) {
            maxLines = 32;
        }
        return maxLines;
    }
    
    public SoundManager(AudioFormat playbackFormat) {
        this(playbackFormat,getMaxSimultaneousSounds(playbackFormat),"");
    }
    
    public SoundManager(AudioFormat playbackFormat, int maxSimultaneousSounds) {
        this(playbackFormat,maxSimultaneousSounds,"");
    }
    
    public SoundManager(AudioFormat playbackFormat, String path) {
        this(playbackFormat,getMaxSimultaneousSounds(playbackFormat),path);
    }
    
    public SoundManager(AudioFormat playbackFormat, int maxSimultaneousSounds, String path) {
        super(Math.min(maxSimultaneousSounds,getMaxSimultaneousSounds(playbackFormat)));
        soundPath = path;
        if (!soundPath.endsWith("/")) soundPath += "/";
        this.playbackFormat = playbackFormat;
        localLine = new ThreadLocal<SourceDataLine>();	
        localBuffer = new ThreadLocal<byte[]>();		
        pausedLock = new Object();
        synchronized (this) {
            notifyAll();
        }
    }
    
    public void setSoundPath(String path) {
        soundPath = path;
        if (!soundPath.endsWith("/")) soundPath += "/";
    } 
	
    public String getSoundPath() {
        return soundPath;
    } 

    @Override
    public void close() {
        cleanUp();
        super.close();
    }

    @Override
    public void join() {
        cleanUp();
        super.join();
    }

    public void setPaused(boolean paused) {
        if (this.paused != paused) {
            synchronized (pausedLock) {
                this.paused = paused;
                if (!paused) {
                    pausedLock.notifyAll();
                }
            }
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public Sound getSound(String filename) {
        return getSound(getAudioInputStream(filename));
    }
    
    public Sound getSound(InputStream is) {
        return getSound(getAudioInputStream(is));
    }

    public Sound getSound(AudioInputStream audioStream) {
        Sound result = null;
        if (audioStream != null) {       
            int length = (int)(audioStream.getFrameLength() * audioStream.getFormat().getFrameSize());
            byte[] samples = new byte[length];
            DataInputStream is = new DataInputStream(audioStream);
            try {
                is.readFully(samples);
                is.close();
            }
            catch (IOException ex) {}
            result = new Sound(samples);
        }
        return result;
    }

    public InputStream play(Sound sound) {
        return play(sound, null, false);
    }

    public InputStream play(Sound sound, SoundFilter filter, boolean loop) {
        InputStream is = null;
        if (sound != null) {
            if (loop) {
                is = new LoopingByteInputStream(sound.getSamples());
            } else {
                is = new ByteArrayInputStream(sound.getSamples());
            }
            is = play(is, filter);
        }
        return is;
    }
    
    private InputStream play(InputStream is, SoundFilter filter) {
        if (is != null) {
            if (filter != null) {
                is = new FilteredSoundStream(is, filter);
            }
            runTask(new SoundPlayer(is));
        }
        return is;
    }

    private void cleanUp() {
        setPaused(false);
        Mixer mixer = AudioSystem.getMixer(null);
        if (mixer.isOpen()) {
            mixer.close();
        }
    }
    
    private AudioInputStream getAudioInputStream(String filename) {
        AudioInputStream result = null;
        try {
            result = getAudioInputStream(new FileInputStream(soundPath + filename));
        }
        catch (IOException ex) {}
        return result;
    }

    private AudioInputStream getAudioInputStream(InputStream is) {
        AudioInputStream result = null;
        try {
            if (!is.markSupported()) {
                is = new BufferedInputStream(is);
            }
            AudioInputStream source = AudioSystem.getAudioInputStream(is);
            result = AudioSystem.getAudioInputStream(playbackFormat, source);
        }
        catch (UnsupportedAudioFileException ex) {}
        catch (IOException ex) {}
        catch (IllegalArgumentException ex) {}
        return result;
    }

    @Override
    protected void threadStarted() {
        synchronized (this) {
            try {
                wait();
            }
            catch (InterruptedException ex) {}
        }
        int bufferSize = playbackFormat.getFrameSize() * Math.round(playbackFormat.getSampleRate() / 10);
        SourceDataLine line;
        DataLine.Info lineInfo = new DataLine.Info(SourceDataLine.class,playbackFormat);
        try {
            line = (SourceDataLine)AudioSystem.getLine(lineInfo);
            line.open(playbackFormat, bufferSize);
            line.start();
            byte[] buffer = new byte[bufferSize];
            localLine.set(line);
            localBuffer.set(buffer);
        }
        catch (LineUnavailableException ex) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected void threadStopped() {
        SourceDataLine line = localLine.get();			
        if (line != null) {
            line.drain();
            line.close();
        }
    }

    private class SoundPlayer implements Runnable {

        private final InputStream source;

        public SoundPlayer(InputStream source) {
            this.source = source;
        }

        @Override
        public void run() {
            SourceDataLine line = localLine.get();		
            byte[] buffer = localBuffer.get();			
            if (line != null && buffer != null) {   
                try {
                    int numBytesRead = 0;
                    while (numBytesRead != -1) {
                        synchronized (pausedLock) {
                            if (paused) {
                                pausedLock.wait();    
                            }
                        }
                        numBytesRead = source.read(buffer,0,buffer.length);
                        if (numBytesRead != -1) {
                            line.write(buffer, 0, numBytesRead);
                        }
                    }
                }
                catch (IOException ex) {}
                catch (InterruptedException ex) {}
            }
        }
    }
}
