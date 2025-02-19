package COSC3P91.sound;

public class Sound {

    private final byte[] samples;

    public Sound(byte[] samples) {
        this.samples = samples;
    }

    public byte[] getSamples() {
        return samples;
    }
}
