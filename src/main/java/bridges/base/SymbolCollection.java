package bridges.base;

import bridges.base.DataStruct;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;



/*
 * @brief the ShapeCollection represents a collection of symbols (shapes, polygons, and text) to visualize in Bridges
 * @author David Burlinson
*/
public class SymbolCollection extends DataStruct {
  // keep track of the shape elements; useful
  // to maintain their properties
  private final HashMap<String, Symbol> symbols;

  /**
	 *
	 *	Constructor
	 */
  public SymbolCollection() {
    symbols = new HashMap<String, Symbol>();
  }

  /**
   *	This method gets the data structure type
   *
   *	@return  The date structure type as a string
   *
   */
  public String getDataStructType() {
    return "SymbolCollection";
  }

 /**
  *   This method adds a symbol to the collection
  */
  public void addSymbol(Symbol s) {
    // note: it is the user's responsibility to handle
    //  duplicates where desired
    symbols.put(s.getIdentifier(), s);
  }

  /*
   *	Get the JSON representation of the the data structure
   */
  public String getDataStructureRepresentation() {
    JSONArray symbol_json = new JSONArray();
    for(Entry<String, Symbol> symbol : symbols.entrySet()) {
      symbol_json.add(symbol.getValue().getJSONRepresentation());
    }
    return "\"symbols\":" + symbol_json + "}";
  }
}
