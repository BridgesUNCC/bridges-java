package bridges.data_src_dependent;
/**
 * This interface can be refine later as data sources diversify
 * @author mihai mehedint
 *
 */
abstract interface Source extends Comparable<DataSource>{
	
	abstract void setLabel(String label);

	abstract String getLabel();

	abstract boolean equals(Object obj);

	abstract String toString();
	
	abstract int compareTo(DataSource src);

}
