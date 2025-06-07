package bridges.base;

import java.util.ArrayList;
import java.lang.String;

import bridges.data_src_dependent.Country;


/**
  * @brief This class provides an API to building, displaying and
  * manipulating  World maps and countries in BRIDGES
  *
  * In the current implementation, we can draw a World map  with all country
  * boundaries, or specify a set of countries  and display the country
  * boundaries.
  *
  * Functions are provided to access each country county and color
  * its boundary, its interior using stroke and fill color
  * functions. This lets us build map based applications where the 
  * fill color can be used to represent different data attributes, such
  * as population counts, election statistics or any attribute at the country
  * level. We stop at the country level as each country has its own subdivisions,
  * such as discticts, states, regions, counties, etc.
  *
  * See the Maps tutorials for examples of the usage of the World Map API
  *  at https://bridgesuncc.github.io/tutorials/Map.html
  *
  * Authors: Kalpathi Subramanian, Erik Saule
  *
  * Last modified : June 6, 2025
  */


public class WorldMap extends DataStruct implements AbstrMap {

	private ArrayList<String> country_names;
	private ArrayList<Country> country_data;

	public WorldMap () {
	}

	public WorldMap (ArrayList<Country> country_data) {
		this.country_data = country_data;
	}
	public String getProjection() {
		return "equirectangular";
	}

	/**
	 *
	 * @brief Gets the map overlay flag.
	 *
	 */
	public Boolean getOverlay() {
		return true;
	}

	/**
	 * @brief Generates the JSON representation of the World map
	 *
	 * @returns string
	 */
	public String getMapRepresentation() {
		// generates a JSON of the country information
		String map_str = OPEN_BOX;
		for (Country cntry : country_data) {
			map_str += OPEN_CURLY +
				QUOTE + "_country_name" + QUOTE + COLON +
				QUOTE + cntry.getCountryName() + QUOTE + COMMA +
				QUOTE + "_alpha2" + QUOTE + COLON +
				QUOTE + cntry.getAlpha2Id() + QUOTE + COMMA +
				QUOTE + "_alpha3" + QUOTE + COLON + 
				QUOTE + cntry.getAlpha3Id() + QUOTE + COMMA +
				QUOTE + "_numeric" + QUOTE + COLON + 
				cntry.getNumeric3Id() + COMMA +
				QUOTE + "_fill_color" + QUOTE + COLON +
				cntry.getFillColor().getRepresentation() + COMMA +
				QUOTE + "_stroke_color" + QUOTE + COLON +
				cntry.getStrokeColor().getRepresentation() + COMMA +
				QUOTE + "_stroke_width" + QUOTE + COLON +
				cntry.getStrokeWidth() + COMMA;

			// remove last comma
			if (country_data.size() > 0) // case where countries are on
				map_str = map_str.substring(0, map_str.length() - 1);
				map_str += CLOSE_CURLY +  COMMA;
		}
		// close the countries array
		map_str = map_str.substring(0, map_str.length() - 1) +  CLOSE_BOX;
		System.out.println("World Map JSON: " + map_str);
		return map_str;
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
		return "world_map";
	}

//	public String getMapRepresentation() {
//		return "\"all\"";
//	}
};
