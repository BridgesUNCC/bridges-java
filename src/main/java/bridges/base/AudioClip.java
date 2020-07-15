package bridges.base;

import bridges.base.DataStruct;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.codec.binary.Base64;
import bridges_external.*;

/**
 * @brief This class provides support for reading, modifying, and playing, audio waveform.
 *
 * This class provides a way to represent an AudioClip (think of a
 * .WAV file) in Bridges as waveforms.
 *
 * An AudioClip can be composed of multiple channels: a stereo sound
 * would be composed of 2 channels (Left and Right), a mono sound
 * would be composed of a single channel. A 5.1 sound would be
 * composed of 6 channels. When building an AudioClip from a file, the
 * number of channels is taken from the file; some constructors have a
 * numChannels that enables to pass the number of channels
 * explicitly. If unsure, one can know how many channels are in an
 * audio clip using getNumChannels().
 *
 * Each channel is essentially a 1D signal. That is to say, it is an
 * array of values that represent how far the membrane of a speaker
 * should be from its resting position. The quality of the sound is
 * controlled by two parameters: sampling rate and sampling depth.
 *
 * Sampling rate tells how many positions per second are encoded by
 * the AudioClip. It is expressed in Hertz. CD quality is 44100Hz;
 * while walkie-talkies use 8000Hz. It is set automatically if read
 * from a file; or it can be passed as the sampleRate parameter to
 * some of the constructors. The sampling rate can be obtained from an
 * AudioClip using getSampleRate().
 *
 * The length of an AudioClip is expressed in number of samples. So if
 * an AudioClip is composed of 16,000 samples with a sampling rate of
 * 8000Hz, the clip would be 2 seconds long. The number of samples
 * can obtained with getSampleCount(); it is set from a file or can be
 * passed as the sampleCount parameter of some of the constructor.
 * 
 * The sampling depth indicates how many different positions the
 * membrane can take. It is typically expressed in bits with supported
 * values being 8-bit, 16-bit, 24-bit, and 32-bit. If a clip is
 * encoded with a depth of 8 bits, the membrane can take 2^8 different
 * position ranging from -128 to +127, with 0 being the resting
 * position. The sampling depth is read from files or passed as the
 * sampleBits parameter of the constructor. The sampling depth of an
 * existing clip can be obtained with getSampleBits().
 *
 * The individual samples are accessed with the getSample() and
 * setSample() functions. The samples are integer values in the
 * [-2^(getSampleBits()-1) ; 2^(getSampleBits()-1)[ range. The
 * functions allow to specify for channel and sample index.
 *
 **/

public class AudioClip extends DataStruct {
	private int sampleCount;
	private int sampleRate;
	private int numChannels;
	private int sampleBits;
	private AudioChannel channels[];

	public AudioClip(int sampleCount, int numChannels, int sampleBits, int sampleRate) {
		if (sampleCount > 1000000000) {
			throw new IllegalArgumentException("sampleCount must be less than 1 million");
		}

		this.sampleCount = sampleCount;
		this.numChannels = numChannels;
		this.channels = new AudioChannel[numChannels];
		for (int i = 0; i < numChannels; i++) {
			this.channels[i] = new AudioChannel(sampleCount);

			for (int j = 0; j < sampleCount; j++) {
				this.channels[i].setSample(j, 0);
			}
		}

		this.sampleRate = sampleRate;
		this.sampleBits = sampleBits;
	}

	public AudioClip(WavFile wavFile) throws IOException, WavFileException {
		this((int)wavFile.getNumFrames(), wavFile.getNumChannels(), wavFile.getValidBits(), (int)wavFile.getSampleRate());

		wavFile.display();

		// Create a buffer of frames
		double[] buffer = new double[getSampleCount() * getNumChannels()];
		wavFile.readFrames(buffer, getSampleCount() * getNumChannels());

		int count = 0;
		for (int s = 0; s < getSampleCount(); s++) {
			for (int c = 0; c < getNumChannels(); c++) {
				setSample(c, s, (int)(buffer[count] * (Math.pow(2, 16) / 2.0 - 1.0)));
				count++;
			}
		}

		// Close the wavFile
		wavFile.close();
	}

	public AudioClip(String file) throws IOException, WavFileException {
		this(WavFile.openWavFile(new File(file)));
	}

	public AudioClip(int sampleCount, int numChannels) {
		this(sampleCount, numChannels, 32, 44100);
	}

	public int getNumChannels() {
		return this.numChannels;
	}

	public int getSampleRate() {
		return this.sampleRate;
	}

	public int getSampleCount() {
		return this.sampleCount;
	}

	public int getSampleBits() {
		return this.sampleBits;
	}

	public int getSample(int channel, int index) {
		return this.channels[channel].getSample(index);
	}

	public void setSample(int channel, int index, int value) {
	    if (value >= Math.pow(2, getSampleBits()-1) ||
		value <  -Math.pow(2, getSampleBits()-1))
		throw new IllegalArgumentException("Audio value Out of Bound. Should be in [-2^(getSampleBits()-1) ;  2^(getSampleBits()-1)) range");
		this.channels[channel].setSample(index, value);
	}

	public String getDataStructType() {
		return "Audio";
	}

	public String getDataStructureRepresentation() {
		ByteBuffer byteBuff = ByteBuffer.allocate(numChannels * sampleCount * (this.sampleBits / 8));
		byteBuff.order(ByteOrder.LITTLE_ENDIAN);

		int checkSampleBits = sampleBits;

		for (int i = 0; i < sampleCount; i++) {
			for (int c = 0; c < numChannels; c++) {
				if (this.sampleBits == 8) {
					byteBuff.put((byte)getSample(c, i));
				}
				else if (this.sampleBits == 16) {
					byteBuff.putShort((short)getSample(c, i));
				}
				else if (this.sampleBits == 32 || this.sampleBits == 24) {
					// Some browsers cannot play 32 bit audio files so use 16
					int s = getSample(c, i);

					int minmax = (int) ((Math.pow(2, this.sampleBits) / 2) - 1);
					int minmax16 = (int) ((Math.pow(2, 16) / 2) - 1);

					s = (int)((s / (float)minmax) * minmax16);

					byteBuff.putShort((short)s);
					checkSampleBits = 16;
				}
			}
		}

		String jsonString = QUOTE + "encoding" + QUOTE + COLON + QUOTE + "RAW" + QUOTE + COMMA +
			QUOTE + "numChannels" + QUOTE + COLON + Integer.toString(this.numChannels) + COMMA +
			QUOTE + "sampleRate" + QUOTE + COLON + Integer.toString(this.sampleRate) + COMMA +
			QUOTE + "bitsPerSample" + QUOTE + COLON + Integer.toString(checkSampleBits) + COMMA +
			QUOTE + "numSamples" + QUOTE + COLON + Integer.toString(this.sampleCount) + COMMA +
			QUOTE + "samples" + QUOTE + COLON + QUOTE;

		StringWriter b64List = new StringWriter();

		byte[] byteArr = byteBuff.array();
		int numBits = 3;
		int count = 0;
		for (int i = 0; i < numChannels * sampleCount * (checkSampleBits / 8); i += numBits) {
			// Overshot
			if (((count) * numBits) + numBits > numChannels * sampleCount * (checkSampleBits / 8)) {
				break;
			}

			byte[] subArr = new byte[numBits];
			for (int j = 0; j < numBits; j++) {
				subArr[j] = byteArr[((count) * numBits) + j];
			}

			b64List.write(Base64.encodeBase64String(subArr));
			count++;
		}

		jsonString += b64List.toString();
		jsonString += QUOTE + CLOSE_CURLY;

		return jsonString;
	}
}
