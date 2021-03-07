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

	/**
	 * @brief create an audio clip
	 *
	 * @param numChannels the number of channels
	 * @param sampleCount the total number of samples
	 * @param sampleBits  the bits per sample
	 * @param sampleRate  the sampling rate (Hz, or cycles per sec)
	 *
	 **/
	public AudioClip(int sampleCount, int numChannels, int sampleBits, int sampleRate) {
		if (sampleCount > 1000000000) {
			throw new IllegalArgumentException("sampleCount must be less than 1 million");
		}

		if (sampleBits != 8 && sampleBits != 16 && sampleBits != 24 && sampleBits != 32) {
			throw new IllegalArgumentException("sampleBits must be either 8, 16, 24, or 32");
		}

		if (numChannels <= 0) {
			throw new IllegalArgumentException("numChannels should be positive");
		}

		if (sampleRate <= 0) {
			throw new IllegalArgumentException("sampleRate should be positive");
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


	/**
	* @brief create an audio clip from a WavFile object
	 *
	 **/
	public AudioClip(WavFile wavFile) throws IOException {
		this((int)wavFile.getNumFrames(), wavFile.getNumChannels(), wavFile.getValidBits(), (int)wavFile.getSampleRate());

		try {

			// // print header to screen
			//wavFile.display();

			// Create a buffer of frames
			double[] buffer = new double[getSampleCount() * getNumChannels()];
			wavFile.readFrames(buffer, getSampleCount() * getNumChannels());

			int count = 0;
			for (int s = 0; s < getSampleCount(); s++) {
				for (int c = 0; c < getNumChannels(); c++) {
					setSample(c, s, (int)(buffer[count] * (Math.pow(2, getSampleBits()) / 2.0 - 1.0)));
					count++;
				}
			}

			// Close the wavFile
			wavFile.close();
		}
		catch (WavFileException wfae) {
			throw new IOException("Not a parsable Wave file", wfae);
		}
	}

	private static WavFile openWavFileRethrow (String filename ) throws IOException {
		WavFile w = null;
		try {
			w = WavFile.openWavFile(new File(filename));
		}
		catch (WavFileException wfae) {
			throw new IOException("Not a parsable Wave file", wfae);
		}
		return w;
	}

	/**
	 * @brief create an audio clip from a File
	 *
	 * @param file name of the file (should be a Wave file)
	 *
	 **/
	public AudioClip(String file) throws IOException {
		this(openWavFileRethrow(file));
	}



	/**
	* @brief create an audio clip
	 *
	 * creates an AudioClip with numChannels channels, sampleCount samples
	 *	at 44100 Hz with a depth of 32 bits
	 * @param numChannels the number of channels
	 * @param sampleCount the total number of samples
	 *
	 **/
	public AudioClip(int sampleCount, int numChannels) {
		this(sampleCount, numChannels, 32, 44100);
	}

	/**
	 * @brief returns the number of channels of the clip
	 * @return  the number of channels of the clip (1 for mono, 2 for stereo, ...)
	 **/
	public int getNumChannels() {
		return this.numChannels;
	}

	/**
	* @brief returns the sampling rate of the clip
	 * @return  the sampling rate of the clip in Hertz. (CD quality is 44100Hz for instance)
	 **/
	public int getSampleRate() {
		return this.sampleRate;
	}

	/**
	* @brief returns the number of samples in the clip
	 *
	 * The length of the clip in second is getSampleCount()/((double) getSampleRate())
	 *
	 * @return  the number of samples in the clip
	 **/
	public int getSampleCount() {
		return this.sampleCount;
	}

	/**
	* @brief returns the sampling depth.
	 *
	 * The sampling depth indicates how many bits are used to
	 * encode each individual samples. The values supported are
	 * only 8, 16, 24, and 32.
	 *
	 * All samples must be in the [-2^(getSampleBits()-1) ;
	 * 2^(getSampleBits()-1)) range. that is to say, for 8-bit, in
	 * the [-256;255] range.
	 *
	 * @return the sampling depth.
	 **/
	public int getSampleBits() {
		return this.sampleBits;
	}

	/**
	 * @brief access a particular sample

	 * @param channelIndex the index of the channel that will be accessed (in the [0;getNumChannels()-1] range).
	 * @param sampleIndex the index of the sample that will be accessed (in the [0;getSampleCount()-1] range).
	 * @return the sample value (in [-2^(getSampleBits()-1) ;  2^(getSampleBits()-1)) range).
	 *
	 **/
	public int getSample(int channelIndex, int sampleIndex) {
		return this.channels[channelIndex].getSample(sampleIndex);
	}

	/**
	* @brief change a particular sample

	 * @param channelIndex the index of the channel that will be accessed (in the [0;getNumChannels()-1] range).
	 * @param sampleIndex the index of the sample that will be accessed (in the [0;getSampleCount()-1] range).
	 * @param value the sample value (in [-2^(getSampleBits()-1) ;  2^(getSampleBits()-1)) range).
	 *
	 **/
	public void setSample(int channelIndex, int sampleIndex, int value) {
		if (value >= Math.pow(2, getSampleBits() - 1) ||
			value <  -Math.pow(2, getSampleBits() - 1))
			throw new IllegalArgumentException("Audio value Out of Bound. Should be in [-2^(getSampleBits()-1) ;  2^(getSampleBits()-1)) range");
		this.channels[channelIndex].setSample(sampleIndex, value);
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
