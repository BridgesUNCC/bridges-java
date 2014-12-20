package bridges_vs2;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.uncc.cs.bridges.AbstractEdge;
import edu.uncc.cs.bridges.AbstractVertex;
import edu.uncc.cs.bridges.Bridge;

public class ADTVisualizer {
	//public LinkedHashMap<Element<Value, T>, String> LList;
	public String visualizerType;
	public Map<String, String> linkProperties =  new HashMap<String, String>(){{
															put("color","black");
															put("opacity","1.0");
															put("weight","1.0");
															put("width","1.0");
															}}; 

	public String getVisualizerType() {
		return visualizerType;
	}

	public void setVisualizerType(String visualizerType) {
		this.visualizerType = visualizerType;
	}
	
	public void setGraph(String visualizerType) {
		this.visualizerType = visualizerType;
	}
	
	
	public void setTree(String visualizerType) {
		this.visualizerType = visualizerType;
	}
	
	public void setStack(String visualizerType) {
		this.visualizerType = visualizerType;
	}
	
	public void setQueue(String visualizerType) {
		this.visualizerType = visualizerType;
	}
	
	protected
	<E> String getSLRepresentation(SLelement<E> e) {
		StringBuilder nodes = new StringBuilder();
		StringBuilder links = new StringBuilder();
		Map<Element<E>, Integer> element_to_index = new HashMap<>();
		
		int i=0;
		SLelement<E> anElement = e;
		do {
			// Manage vertex properties
			// Encapsulate in {}, and remove the trailing comma.
			if (anElement != null){
				nodes.append(anElement.getRepresentation() + ",");
				element_to_index.put(anElement, i);
				i++;
			}
			anElement = anElement.getNext();
		}while(anElement != null);
		
		anElement = e;
			// Manage link properties
			do {
				// Encapsulate in {}, and remove the trailing comma.
				if (anElement.getNext() != null){
					links.append(getLinkRepresentation(anElement, anElement.getNext(), element_to_index) + ",");
				}	
					anElement = anElement.getNext();
				
			}while(anElement!=null);
			
		return "{"
				+ "\"name\": \"edu.uncc.cs.bridges\","
				+ "\"version\": \"0.4.0\","
				+ "\"visual\": \""+visualizerType+"\","
				+ "\"nodes\": [" + Bridge.trimComma(nodes) + "],"
				+ "\"links\": [" + Bridge.trimComma(links) + "]"
				+ "}";
	}
	
	public <E> int compare(Element<E> e1, Element<E> e2){
		return e1.getIdentifier().compareTo(e2.getIdentifier());
	}
	
	<E> String getLinkRepresentation(Element<E>source, Element<E>target, Map<Element<E>, Integer> element_to_index) {
		String json = "{";
		for (Entry<String, String> entry : linkProperties.entrySet()) {
			json += String.format("\"%s\": \"%s\", ", entry.getKey(), entry.getValue());
		}
		json += String.format("\"source\":%s,", element_to_index.get(source));
		json += String.format("\"target\":%s", element_to_index.get(target));
		//System.out.println("json: " + json);
		return json + "}";
	}
	
}
