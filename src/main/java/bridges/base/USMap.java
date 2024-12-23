package bridges.base;

import java.util.Vector;
import java.util.HashMap;
import java.util.Map;

import bridges.data_src_dependent.State;
import bridges.data_src_dependent.County;


public class USMap extends AbstrMap {

	private Vector<String> state_names;
	private Vector<State>  state_data;

	public USMap (Vector<State> st_data) {
		state_data = st_data;
	}

	public String getDataStructureRepresentation() {
		return QUOTE + "mapdummy" + COLON + "true" + CLOSE_CURLY + QUOTE;
	}

	public String getProjection() {
		return "albersusa";
	}

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
			st.getStateName() + COMMA +
			QUOTE+ "_stroke_color" + QUOTE + COLON +
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
			for (Map.Entry<String,County> entry : st.accessCounties().entrySet()) {
				// get the county object
				County c = entry.getValue();	

				// build the JSON
				map_str +=  OPEN_CURLY +
				QUOTE +	"_geoid" + QUOTE + COLON + 
				c.getGeoId()+ COMMA +
				QUOTE +	"_fips_code" + QUOTE + COLON + 
				c.getFipsCode()+ COMMA +
				QUOTE +	"_county_name" + QUOTE +COLON + 
				c.getCountyName() + COMMA +
				QUOTE +	"_state_name" + QUOTE +COLON + 
				c.getStateName() + COMMA +
				QUOTE +	"_stroke_color" + QUOTE + COLON + 
				c.getStrokeColor().getRepresentation()+ COMMA +
				QUOTE +	"_stroke_width" + QUOTE +COLON + 
				c.getStrokeWidth() + COMMA +
				QUOTE +	"_fill_color" + QUOTE + COLON + 
				c.getFillColor().getRepresentation() + COMMA +
				QUOTE +	"_hide" + QUOTE + COLON + 
				c.getHideFlag() + 
				CLOSE_CURLY + COMMA;
			}
			// remove last comma
			if (st.accessCounties().size() > 0) // case where counties are on
				map_str = map_str.substring(0, map_str.length()-1);
				map_str += CLOSE_BOX + CLOSE_CURLY +  COMMA;
			}
			// close the states array
			map_str = map_str.substring(0, map_str.length()-1) +  CLOSE_BOX;
			return map_str;
	}
};
