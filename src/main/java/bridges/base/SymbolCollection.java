package bridges.base;

import bridges.base.DataStruct;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;



/**
 * @brief the ShapeCollection represents a collection of symbols to visualize in Bridges
 *
 * @sa See tutorial at http://bridgesuncc.github.io/tutorials/Symbol_Collection.html
 *
 * @author David Burlinson
 * @date 2018, 7/15/19
 */
public class SymbolCollection extends DataStruct {
	// keep track of the shape elements; useful
	// to maintain their properties
	private final HashMap<String, Symbol> symbols;

	// default domain (assuming square coordinate space)
	//  domian emanates in x and y directions, both positive and negative, from 0,0
	protected Float domainxmin = -100.0f;
    	protected Float domainxmax = 100.0f;
    	protected Float domainymin = -100.0f;
    	protected Float domainymax = 100.0f;
    protected boolean autoscaledomain = true;
    

    public void setViewport(float xmin, float xmax,
			    float ymin, float ymax) {
	domainxmin = xmin;
	domainxmax = xmax;
	domainymin = ymin;
	domainymax = ymax;
	autoscaledomain = false;
    }
    
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
	 *	 @param s  symbol to be added
	 */
	public void addSymbol(Symbol s) {
		// note: it is the user's responsibility to handle
		//  duplicates where desired
		symbols.put(s.getIdentifier(), s);
	}
	/**
	 *   This method examines whether the axes should be expanded to ensure all shapes are shown
	 *	 @param s  symbol to be updated
	 */
	private void updateAxisDomains(Symbol s) {
		float[] dims = s.getDimensions();

		// check x axis
		if (dims[0] < domainxmin) {
			domainxmin = dims[0];
		}
		if (dims[1] > domainxmax) {
			domainxmax = dims[1];
		}

		// check y axis
		if (dims[2] < domainymin) {
			domainymin = dims[2];
		}
		if (dims[3] > domainymax) {
			domainymax = dims[3];
		}
	}

	/**
	 *	Get the JSON representation of the the data structure
	 *	@return JSON of symbol collection (string)
	 */

	public String getDataStructureRepresentation() {
		JSONArray symbol_json = new JSONArray();
		for (Entry<String, Symbol> symbol : symbols.entrySet()) {

		    if (autoscaledomain)
			updateAxisDomains(symbol.getValue());

			symbol_json.add(symbol.getValue().getJSONRepresentation());
		}

		return "\"domainX\":[" + domainxmin + "," + domainxmax + "],\"domainY\":[" + domainymin + "," + domainymax + "]," + "\"symbols\":" + symbol_json + "}";
	}
}
