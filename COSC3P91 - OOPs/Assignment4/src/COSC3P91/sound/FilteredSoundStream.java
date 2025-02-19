package COSC3P91.sound;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;

public class FilteredSoundStream extends FilterInputStream {

    private static final int REMAINING_SIZE_UNKNOWN = -1;

    private final SoundFilter soundFilter;
    private int remainingSize;

    public FilteredSoundStream(InputStream in, SoundFilter soundFilter) {
        super(in);
        this.soundFilter = soundFilter;
        remainingSize = REMAINING_SIZE_UNKNOWN;
    }

    @Override
    public int read(byte[] samples, int offset, int length) throws IOException {
        int bytesRead = super.read(samples, offset, length);
        if (bytesRead > 0) {
            soundFilter.filter(samples, offset, bytesRead);
        } else {
            if (remainingSize == REMAINING_SIZE_UNKNOWN) {
                remainingSize = soundFilter.getRemainingSize();
                remainingSize = remainingSize / 4 * 4;
            }
            if (remainingSize > 0) {
                length = Math.min(length, remainingSize);
                for (int i=offset; i<offset+length; i++) {
                    samples[i] = 0;
                }
                soundFilter.filter(samples, offset, length);
                remainingSize -= length;
                bytesRead = length;
            } else {
                bytesRead = -1;
            }
        }
        return bytesRead;
    }
}
