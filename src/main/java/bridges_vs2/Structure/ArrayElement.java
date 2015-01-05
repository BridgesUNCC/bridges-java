package bridges_vs2.Structure;


public class ArrayElement<E> extends Element<E>{
	public static int index;
	
	public ArrayElement(String identifier, E type){
		super(identifier, type);
	}
	
	public void setIndex(int i){
		this.index = i;
	}
	
	public int getIndex(){
		return index;
	}
	
	public int nextIndex(){
		return index+1;
	}
	
	public int prevIndex(){
		if (index-1 < 0)
			return -1;
		else 
			return index-1;
	}
}
