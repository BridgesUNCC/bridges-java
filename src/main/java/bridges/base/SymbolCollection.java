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

  // default domain (assuming square coordinate space)
  //  domian emanates in x and y directions, both positive and negative, from 0,0
  protected Float domain = 100.0f;

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
   *   This method examines whether the axes should be expanded to ensure all shapes are shown
   */
  private void updateAxisDomains(Symbol s) {
    Float[] dims = s.getDimensions();

    // check x axis
    if(Math.abs(dims[0]) > domain) {
      domain = Math.abs(dims[0]);
    }
    if(Math.abs(dims[1]) > domain) {
      domain = Math.abs(dims[1]);
    }

    // check y axis
    if(Math.abs(dims[2]) > domain) {
      domain = Math.abs(dims[2]);
    }
    if(Math.abs(dims[3]) > domain) {
      domain = Math.abs(dims[3]);
    }
  }

  /*
   *	Get the JSON representation of the the data structure
   */
  public String getDataStructureRepresentation() {
    JSONArray symbol_json = new JSONArray();
    for(Entry<String, Symbol> symbol : symbols.entrySet()) {

      // update axis domains where appropriate for each shape
      updateAxisDomains(symbol.getValue());

      symbol_json.add(symbol.getValue().getJSONRepresentation());
    }

    return "\"domainX\":[" + -domain + "," + domain + "],\"domainY\":[" + -domain + "," + domain + "]," + "\"symbols\":" + symbol_json + "}";
  }
}
