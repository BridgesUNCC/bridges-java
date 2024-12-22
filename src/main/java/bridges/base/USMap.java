package bridges.base;

import java.util.Vector;

public class USMap extends Map {

	private Vector<String> state_names;
	private Vector<State>  state_data;

	USMap (vector<State> st_data) {
		state_data = st_data;
	}

	private String getDataStructureRepresentation() {
		return QUOTE + "mapdummy" + COLON + "true" + CLOSE_CURLY + QUOTE;
	}

	public String getProjection() {
		return "albersusa";

	public Boolean getOverlay() {
		return true;
	}

	public getMapData () {
		return state_data;
	}
	public setStateData (Vector<State> st_data) {
		state_data = st_data;
	}

	public getMapRepresentation() {
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
			for (auto& c : st.getCounties()) {
				map_str +=  OPEN_CURLY +
				QUOTE +	"_geoid" + QUOTE + COLON + 
				c.second.getGeoId()+ COMMA +
				QUOTE +	"_fips_code" + QUOTE + COLON + 
				c.second.getFipsCode()+ COMMA +
				QUOTE +	"_county_name" + QUOTE +COLON + 
				c.second.getCountyName() + COMMA +
				QUOTE +	"_state_name" + QUOTE +COLON + 
				c.second.getStateName() + COMMA +
				QUOTE +	"_stroke_color" + QUOTE + COLON + 
				c.second.getStrokeColor().getRepresentation()+ COMMA +
				QUOTE +	"_stroke_width" + QUOTE +COLON + 
				c.second.getStrokeWidth() + COMMA +
				QUOTE +	"_fill_color" + QUOTE + COLON + 
				c.second.getFillColor().getRepresentation() + COMMA +
				QUOTE +	"_hide" + QUOTE + COLON + 
				c.second.getHideFlag() + 
				CLOSE_CURLY + COMMA;
			}
			// remove last comma
			if (st.getCounties().size()) // case where counties are on
				map_str = map_str.substr(0, map_str.size()-1);
				map_str += CLOSE_BOX + CLOSE_CURLY +  COMMA;
			}
			// close the states array
			map_str = map_str.substr(0, map_str.size()-1) +  CLOSE_BOX;
			return map_str;
};
