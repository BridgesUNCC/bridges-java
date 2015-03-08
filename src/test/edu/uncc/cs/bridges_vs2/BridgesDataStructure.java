
public enum BridgesDataStructure {
	GRAPH("graph"),
	SINGLE_LINKED_LIST("llist"),
	DOUBLE_LINKED_LIST("dlist");
	
	private final String structure;
	
	private BridgesDataStructure(String s){
		structure = s;
	}

	@Override
	public String toString() {
		return structure;
	}
	
}
