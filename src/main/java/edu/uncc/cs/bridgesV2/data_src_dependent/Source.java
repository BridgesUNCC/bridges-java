package edu.uncc.cs.bridgesV2.data_src_dependent;
/**
 * This interface can be refine later as data sources diversify
 * @author mihai mehedint
 *
 */
public interface Source extends Comparable<DataSource>{
	
	public abstract void setLabel(String label);

	public abstract String getLabel();

	public abstract boolean equals(Object obj);

	public abstract String toString();

}