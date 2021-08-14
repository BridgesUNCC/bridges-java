package bridges.base;
import bridges.base.DataStruct;

import java.util.ArrayList;
import bridges.base.Color;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 *  @brief This class defines a group of Symbols and is part of the bridges::base::SymbolCollection.
 *
 * One would use SymbolGroup to bundle together a set of Symbol that can be used and placed at one. For instance, one could create a group representing a house and then place that house in a scene.
 *
 * Setting properties of groups may have surprising consequences. For instance setting the opacity of a SymbolGroup will apply a multiplicative opacity to all the objects in the group, but setting an fill color will only impact the Symbol in the SymbolGroup that do not have a defined fill color.
 *
 * @sa An example tutorial can be found at
 * 		https://bridgesuncc.github.io/tutorials/Symbol_Collection.html
 *
 * 	@author Erik Saule
 *
 *	@date 6/22/21
 **/
public class SymbolGroup extends Symbol{

    ArrayList<Symbol> al = new ArrayList<Symbol>();
    
	/**
	 *	Create a default symbol object
	 */
	public SymbolGroup() {
		super();
	}

    public void addSymbol(Symbol s) {
	al.add(s);
    }

    	public String getShapeType() {
		return "group";
	}

    
    public void addAllJSON (JSONArray symbol_json, Integer parent) {
	int id = symbol_json.size();

	JSONObject obj = this.getJSONRepresentation();

	obj.put("ID", id);
	
	if (parent != null) {
	    obj.put("parentID", parent);	    
	}
	
	symbol_json.add(obj);

	for (Symbol s : al) {
	    s.addAllJSON(symbol_json, id);
	}
    }
}
