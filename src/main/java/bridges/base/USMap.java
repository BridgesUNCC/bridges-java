package bridges.base;

import java.util.Vector;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;

import bridges.data_src_dependent.State;
import bridges.data_src_dependent.County;

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
 * See the Maps tutorials for examples of the usage of the US Map API
 *
 */

public class USMap extends AbstrMap {

	private Vector<String> state_names;
	private Vector<State>  state_data; // state and county information for each state

	/**
	 * @brief Construtor: creates an object with the given state data
	 *
	 * @param st_data  state and county information  for a set of states
	 */
	public USMap (Vector<State> st_data) {
		state_data = st_data;
	}

	/**
	 *  @brief get JSON representation of the data structure - a dummy string
	 *
	 *  @return JSON string
	 */
	public String getDataStructureRepresentation() {
		return QUOTE + "mapdummy" + COLON + "true" + CLOSE_CURLY + QUOTE;
	}
	/**
	                 * @brief Gets the type of map projection. For US map we
	                 *  currently use albersusa
	                 *
	                 */
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


	public Vector<State> getMapData () {
		return state_data;
	}
	public void setStateData (Vector<State> st_data) {
		state_data = st_data;
	}

	public String getMapRepresentation() {
		// generates a JSON of the states with county information
		String map_str = OPEN_BOX;
		for (State st : state_data) {
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
			for (Map.Entry<String, County> entry : st.accessCounties().entrySet()) {
				// get the county object
				County c = entry.getValue();

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
			if (st.accessCounties().size() > 0) // case where counties are on
				map_str = map_str.substring(0, map_str.length() - 1);
			map_str += CLOSE_BOX + CLOSE_CURLY +  COMMA;
		}
		// close the states array
		map_str = map_str.substring(0, map_str.length() - 1) +  CLOSE_BOX;
		return map_str;
	}
};
