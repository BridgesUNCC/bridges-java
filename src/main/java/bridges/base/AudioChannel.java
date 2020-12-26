package bridges.base;


/**
 * @brief This class provides support for audio API in bridges; this class
 *  stores the  properties and audio data of a single channel
 */

public class AudioChannel {
	private int data[];
	private int sampleCount;

	/**
	 * @brief constructore 
	 * @param sampleCount  number of samples
	 */
	public AudioChannel(int sampleCount) {
		data = new int[sampleCount];
		this.sampleCount = sampleCount;
	}

	/**
	 * @brief get sample count 
	 * @return number of samples
	 */
	public int getChannelSize() {
		return sampleCount;
	}

	/**
	 * @brief get sample  at an index in the array
	 * @param index to look for sample
	 */
	public int getSample(int index) {
		return data[index];
	}

	/**
	 * @brief set sample  at an index in the array
	 * @param index 
	 * @param sample sample data
	 */
	public void setSample(int index, int sample) {
		data[index] = sample;
	}
}
