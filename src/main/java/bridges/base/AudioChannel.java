package bridges.base;

public class AudioChannel {
	private int data[];
	private int sampleCount;
	
	public AudioChannel(int sampleCount)
	{
		data = new int[sampleCount];
		this.sampleCount = sampleCount;
	}
	
	public int getChannelSize()
	{
		return sampleCount;
	}
	
	public int getSample(int index)
	{
		return data[index];
	}
	
	public void setSample(int index, int sample)
	{
		data[index] = sample;
	}
}
