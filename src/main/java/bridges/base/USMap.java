package bridges.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;

import bridges.data_src_dependent.USState;
import bridges.data_src_dependent.USCounty;

/**
 * @brief This class provides an API to building, displaying and
 * manipulating  US maps and counties in BRIDGES
 *
 * In the current implementation, we can draw a US map  with all state
 * boundaries, a map with all US state and county boundaries, or
 * specify a set of states  and display the state and/or county
 * boundaries.
 *
 * Functions are provided to access each US state or county and color
 * its boundary or its interior using the stroke color  and fill color
 * functions. This lets us build map based applications where the fill
 * fill color can be used to represent different data attributes, such
 * population counts, election statistics or any attribute at the state
 * or county level.
 *
 * See the Maps tutorials for examples of the usage of the US Map API at
 * https://bridgesuncc.github.io/tutorials/Map.html
 */

public class USMap extends DataStruct implements AbstrMap {

	/**
	 * @brief Constructor: creates an object with the given state data
	 *
	 * @param st_data  state and county information  for a set of states
	 */
	private ArrayList<String> state_names;
	private ArrayList<USState>  state_data;
    private boolean all;

    	public USMap () {
	    all = true;
	}
    
	public USMap (ArrayList<USState> st_data) {
	    this.setStateData(st_data);
	}

	/**
	 *  @brief get JSON representation of the data structure - a dummy string
	 *
	 *  @return JSON string
	 */
	public String getDataStructureRepresentation() {
		return QUOTE + "mapdummy" + QUOTE + COLON + QUOTE + "true" + QUOTE + CLOSE_CURLY;
	}

	public String getDataStructType() {
		return "us_map";
	}

	public String getProjection() {
		return "albersusa";
	}

	/**
	             *
	             * @brief Gets the map overlay flag.
	             *
				 * @return boolean
	             */
	public Boolean getOverlay() {
		return true;
	}

	public ArrayList<USState> getMapData () {
		return state_data;
	}
	public void setStateData (ArrayList<USState> st_data) {
	    all = false;
		state_data = st_data;
	}

	public String getMapRepresentation() {
	    if (this.all) {
		return "[\"all\"]";
	    }
		// generates a JSON of the states with county information
		String map_str = OPEN_BOX;
		for (USState st : state_data) {
			map_str += OPEN_CURLY +
				QUOTE + "_state_name" + QUOTE + COLON +
				QUOTE + st.getStateName() + QUOTE + COMMA +
				QUOTE + "_stroke_color" + QUOTE + COLON +
				st.getStrokeColor().getRepresentation() + COMMA +
				QUOTE +	"_stroke_width" + QUOTE + COLON +
				st.getStrokeWidth() + COMMA +
				QUOTE +	"_fill_color" + QUOTE + COLON +
				st.getFillColor().getRepresentation() + COMMA +
				QUOTE +	"_view_counties" + QUOTE + COLON +
				(st.getViewCountiesFlag()) + COMMA +
				QUOTE +	"_counties" + QUOTE + COLON;

			// get all the counties
			map_str += OPEN_BOX;  // array of counties
			for (Map.Entry<String, USCounty> entry : st.getCounties().entrySet()) {
				// get the county object
				USCounty c = entry.getValue();
				// build the JSON
				map_str +=  OPEN_CURLY +
					QUOTE +	"_geoid" + QUOTE + COLON +
					QUOTE + c.getGeoId() + QUOTE + COMMA +
					QUOTE +	"_fips_code" + QUOTE + COLON +
					QUOTE + c.getFipsCode() + QUOTE + COMMA +
					QUOTE +	"_county_name" + QUOTE + COLON +
					QUOTE + c.getCountyName() + QUOTE + COMMA +
					QUOTE +	"_state_name" + QUOTE + COLON +
					QUOTE + c.getStateName() + QUOTE + COMMA +
					QUOTE +	"_stroke_color" + QUOTE + COLON +
					c.getStrokeColor().getRepresentation() + COMMA +
					QUOTE +	"_stroke_width" + QUOTE + COLON +
					c.getStrokeWidth() + COMMA +
					QUOTE +	"_fill_color" + QUOTE + COLON +
					c.getFillColor().getRepresentation() + COMMA +
					QUOTE +	"_hide" + QUOTE + COLON +
					c.getHideFlag() +
					CLOSE_CURLY + COMMA;
			}
			// remove last comma
			if (st.getCounties().size() > 0) // case where counties are on
				map_str = map_str.substring(0, map_str.length() - 1);
			map_str += CLOSE_BOX + CLOSE_CURLY +  COMMA;
		}
		// close the states array
		map_str = map_str.substring(0, map_str.length() - 1) +  CLOSE_BOX;
		return map_str;
	}
};
