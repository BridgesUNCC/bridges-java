package bridges.data_src_dependent;

import java.lang.String;

public class USCities {

	private String String city, state, country, time_zone;
	private	int elevation, population;
	private  float latit, longit;

	public USCities() {
		this.city = this.state = this.country = this.time_zone = new String();
		this.elevation = this.population = 0;
		this.latit = this.longit = 0.;
	}

	public USCities (String cty, String st, String cntry, String tm_zone,
		int elev, int pop, float lat, float lon) {
		this.city = cty;
		this.state = st;
		this.country = cntry;
		this.time_zone = tm_zone;
		this.latit = lat;
		this.longit = lon;
	}

	public int getCity() {
		return this.city;
	}

	public void setCity(String c) {
		this.city = c;
	}

	public String  getState() {
		return this.state;
	}

	public void  setState(String st) {
		this.state = st;;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String cntry) {
		this.country = cntry;
	}

	public String getTimeZone() {
		return this.time_zone;
	}

	public void setTimeZone(String tz) {
		this.time_zone = tz;
	}

	public float  getLatitude() {
		return this.latit;
	}

	public void setLatitude(float lat) {
		this.latit = lat;
	}

	public float  getLongitude()  {
		return this.longit;
	}

	public void setLongitude(float lon) {
		this.longit = lon;
	}

	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elev) {
		elevation = elev;
	}

	public int getPopulation() {
		return population;
	}   
                
	public void setPopulation(int pop) {
		population = pop;
	}   
};
