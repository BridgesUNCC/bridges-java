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

  // default domain axes
  protected Float[] domainX = {-100.0f, 100.0f};
  protected Float[] domainY = {-100.0f, 100.0f};

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

  public void updateAxisDomains(Symbol s) {
    Float[] loc = s.getLocation();
    Float[] size = s.getDimensions();

    // check min x
    if(loc[0] - size[0] < domainX[0]) {
      domainX[0] = loc[0]-size[0];
    }
    // check max x
    if(loc[0] + size[2] > domainX[1]) {
      domainX[1] = loc[0]+size[2];
    }
    // check min y
    if(loc[1] - size[1] < domainY[0]) {
      System.out.println(size[1]);
      domainY[0] = loc[1]-size[1];
    }
    // check max y
    if(loc[1] + size[3] > domainY[1]) {
      domainY[1] = loc[1]+size[3];
    }
  }

  /*
   *	Get the JSON representation of the the data structure
   */
  public String getDataStructureRepresentation() {
    JSONArray symbol_json = new JSONArray();
    for(Entry<String, Symbol> symbol : symbols.entrySet()) {

      // assess axis domains
      updateAxisDomains(symbol.getValue());

      symbol_json.add(symbol.getValue().getJSONRepresentation());
    }
    // System.out.println("dimensions: " + domainX[0] + "," + domainX[1] + " -- " + domainY[0] + "," + domainY[1]);

    return "\"domainX\":[" + domainX[0] + "," + domainX[1] + "],\"domainY\":[" + domainY[0] + "," + domainY[1] + "]," + "\"symbols\":" + symbol_json + "}";
  }
}
