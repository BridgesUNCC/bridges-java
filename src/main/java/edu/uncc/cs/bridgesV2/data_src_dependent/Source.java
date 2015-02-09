package edu.uncc.cs.bridgesV2.data_src_dependent;

public interface Source {

	public abstract void setLabel(String label);

	public abstract String getLabel();

	public abstract boolean equals(Object obj);

	public abstract String toString();

}