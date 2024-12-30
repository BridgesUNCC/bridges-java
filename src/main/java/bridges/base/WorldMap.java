package bridges.base;


/**
 * @brief This class provides an API to displaying  world maps  in BRIDGES
 *
 * See the Maps tutorials for examples of the usage of the US Map API at
 * http://????
 */

public class WorldMap extends DataStruct implements AbstrMap {


	public WorldMap () {
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
    
	public String getProjection() {
		return "equirectangular";
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


	public String getMapRepresentation() {
	  return "\"all\"";
	}
};
