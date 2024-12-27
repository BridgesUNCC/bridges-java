package bridges.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;

import bridges.data_src_dependent.USState;
import bridges.data_src_dependent.USCounty;


public class USMap extends DataStruct implements AbstrMap {

	private ArrayList<String> state_names;
	private ArrayList<USState>  state_data;

	public USMap (ArrayList<USState> st_data) {
		state_data = st_data;
	}

	public String getDataStructureRepresentation() {
		return QUOTE + "mapdummy" + QUOTE + COLON + QUOTE + "true" + QUOTE + CLOSE_CURLY;
	}

    public String getDataStructType() {
	return "us_map";
    }
    
	public String getProjection() {
		return "albersusa";
	}

	public Boolean getOverlay() {
		return true;
	}

	public ArrayList<USState> getMapData () {
		return state_data;
	}
	public void setStateData (ArrayList<USState> st_data) {
		state_data = st_data;
	}

	public String getMapRepresentation() {
		// generates a JSON of the states with county information
		String map_str = OPEN_BOX;
		for (USState st : state_data) {
			map_str += OPEN_CURLY + 
			QUOTE + "_state_name" + QUOTE + COLON + 
			QUOTE + st.getStateName() + QUOTE + COMMA +
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
			for (Map.Entry<String,USCounty> entry : st.getCounties().entrySet()) {
				// get the county object
				USCounty c = entry.getValue();

				// build the JSON
				map_str +=  OPEN_CURLY +
				QUOTE +	"_geoid" + QUOTE + COLON + 
				QUOTE + c.getGeoId()+ QUOTE + COMMA +
				QUOTE +	"_fips_code" + QUOTE + COLON + 
				QUOTE + c.getFipsCode()+ QUOTE + COMMA +
				QUOTE +	"_county_name" + QUOTE + COLON + 
				QUOTE + c.getCountyName() + QUOTE + COMMA +
				QUOTE +	"_state_name" + QUOTE + COLON + 
				QUOTE + c.getStateName() + QUOTE + COMMA +
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
			if (st.getCounties().size() > 0) // case where counties are on
				map_str = map_str.substring(0, map_str.length()-1);
				map_str += CLOSE_BOX + CLOSE_CURLY +  COMMA;
			}
			// close the states array
			map_str = map_str.substring(0, map_str.length()-1) +  CLOSE_BOX;
			return map_str;
	}
};
