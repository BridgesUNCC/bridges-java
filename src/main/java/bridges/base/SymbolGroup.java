package bridges.base;
import bridges.base.DataStruct;

import java.util.ArrayList;
import bridges.base.Color;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/** 	@author Erik Saule
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
