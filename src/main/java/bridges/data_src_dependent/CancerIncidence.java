package bridges.data_src_dependent;

/**
 *
 *  @brief United States Cancer Statistics from the U.S. Center for
 *  Disease Control
 *
 * 	From the United States Cancer Statistics as part of the U.S. Center for
 *	Disease Control, the following data set focuses on the crude rate for
 *	all types of cancer reported for different demograpic groups.
 *	Significant groupings include age, gender, race and geographical area.
 *
 *	http://www.cdc.gov/cancer/npcr/uscs/download_data.htm
 *  Data: Courtesy of Corgis Datasets, 2017
 *
 *  Author: Kalpathi Subramanian, April, 2017
 *
 */


public class CancerIncidence extends DataSource {

	private double
	age_adjusted_rate,		 // expected cancer rate, adjusted for age
	age_adjusted_rate_ci[], // confidence interval-lower,upper
	crude_rate,				 // cancer rate adjusted by population
	crude_rate_ci[];		 // confidence interval

	private int
	count,					 // incidence count
	year,					 // reporting year
	population;				 // population of this area

	private String
	gender, 				 // gender (male, female, male and female
	race,
	event_type,				 // incidence, mortality
	affected_area;			 // location, typically, state

	private double loc[];		 	// location (cartesian coords


	/**
	 * The constructor
	 */
	public CancerIncidence () {
		super.setLabel("Cancer Incidence Data");
		loc  = new double[] {0.0, 0.0};
		age_adjusted_rate_ci = new double[] {0.0, 0.0};
		crude_rate_ci = new double[] {0.0, 0.0};
	}

	public CancerIncidence(String canc_label) {
		super.setLabel(canc_label);
	}

	/**
	 * This method returns the string name
	 */
	public String getName() {
		return super.getLabel();
	}

	/**
	 * This method sets the string name
	 */
	public void setName(String name) {
		super.setLabel(name);
	}
	/*
	 * Get the expected cancer rate, adjusted for age of participants.
	 *
	 * @return cancer rate
	 *
	 */
	public double getAgeAdjustedRate() {
		return age_adjusted_rate;
	}

	/**
	 * Set age adjusted cancer rate
	 *
	 * @param double aar
	 *
	 */
	public void setAgeAdjustedRate(double aar) {
		age_adjusted_rate = aar;
	}

	/*
	 * Get the expected cancer rate confidence interval(lower),
	 *	adjusted for age of participants.
	 *
	 * @return cancer conf interval (lower) rate
	 *
	 */
	public double getAgeAdjustedCI_Lower() {
		return age_adjusted_rate_ci[0];
	}

	/**
	 * Set age adjusted cancer conf interval (lower)
	 *
	 * @param double ci_l
	 *
	 */
	public void setAgeAdjustedCI_Lower(double ci_l) {
		age_adjusted_rate_ci[0] = ci_l;
	}
	/*
	 * Get the expected cancer rate confidence interval(upper),
	 *	adjusted for age of participants.
	 *
	 * @return cancer conf interval (lower) rate
	 *
	 */
	public double getAgeAdjustedCI_Upper() {
		return age_adjusted_rate_ci[1];
	}

	/**
	 * Set age adjusted cancer conf interval (upper)
	 *
	 * @param double ci_u
	 *
	 */
	public void setAgeAdjustedCI_Upper(double ci_u) {
		age_adjusted_rate_ci[1] = ci_u;
	}

	/*
	 * Get the cancer rate, adjusted for population
	 *
	 * @return crude cancer rate
	 */
	public double getCrudeRate() {
		return crude_rate;
	}
	/**
	 * Set cancer rate, adjusted for population
	 *
	 * @param double cr
	 */
	public void setCrudeRate(double cr) {
		crude_rate = cr;
	}
	/*
	 * Get the expected cancer crude rate confidence interval(lower),
	 * adjusted for age of participants.
	 *
	 * @return cancer conf interval (lower) rate
	 *
	 */
	public double getCrudeRate_CI_Lower() {
		return crude_rate_ci[0];
	}

	/**
	 * Set age adjusted cancer crude conf interval (lower)
	 *
	 * @param double cr_l
	 *
	 */
	public void setCrudeRate_CI_Lower(double cr_l) {
		crude_rate_ci[0] = cr_l;
	}
	/*
	 * Get the expected cancer crude rate confidence interval(upper),
	 * adjusted for age of participants.
	 *
	 * @return cancer crude rate CI (upper) rate
	 *
	 */
	public double getCrudeRate_CI_Upper() {
		return crude_rate_ci[1];
	}

	/**
	 * Set crude rate CI (upper)
	 *
	 * @param double cr_u
	 *
	 */
	public void setCrudeRate_CI_Upper(double cr_u) {
		crude_rate_ci[1] = cr_u;
	}

	/*
	 * Get the year of this cancer record
	 *
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/*
	 * Set the year of this cancer record
	 *
	 * @param y  year
	 */
	public void setYear(int y) {
		year = y;
	}
	/*
	 * Get the gender of the group
	 *
	 * @return gender (male, female, male and female)
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * Set gender
	 *
	 * @param g
	 */
	public void setGender(String g) {
		gender = g;
	}

	/*
	 * Get the race of the group
	 *
	 * @return race (All Races, etc)
	 */
	public String getRace() {
		return race;
	}
	/**
	 * Set race
	 *
	 * @param String r
	 */
	public void setRace(String r) {
		race = r;
	}

	/*
	 * Get the event type (incidence, mortality, etc)
	 *
	 * @return event (String)
	 */
	public String getEventType() {
		return event_type;
	}
	/**
	 * Set event type
	 *
	 * @param event (String)
	 */
	public void setEventType(String et) {
		event_type = et;
	}

	/*
	 * Get the population size
	 *
	 * @return population (int)
	 */
	public int getPopulation() {
		return population;
	}
	/**
	 * Set population size
	 *
	 * @param pop (int)
	 */
	public void setPopulation(int pop) {
		population = pop;
	}

	/*
	 * Get the cancer incidence area (state, region, etc)
	 *
	 * @return area (String)
	 */
	public String getAffectedArea() {
		return affected_area;
	}
	/**
	 * Set cancer incidenc area
	 *
	 * @param area (String)
	 */
	public void setAffectedArea(String area) {
		affected_area = area;
	}

	/*
	 * Get the number of people affected in this group
	 *
	 * @return incidence count (int)
	 */
	public int getCount() {
		return count;
	}
	/**
	 * Set cancer incidence count
	 *
	 * @param c (int)
	 */
	public void setCount(int c) {
		count = c;
	}

	/*
	 * Get the X coordinate of location
	 *
	 * @return x coordinate
	 */
	public double getLocationX() {
		return loc[0];
	}
	/**
	 *	Set location (X coord)
	 *
	 *	@param locX  X coordinate of location
	 *
	 */
	public void setLocationX (double locX) {
		loc[0] = locX;
	}

	/*
	 * Get the Y coordinate of location
	 *
	 * @return y coordinate
	 */
	public double getLocationY() {
		return loc[1];
	}
	/**
	 *	Set location (Y coord)
	 *
	 *	@param locX  Y coordinate of location
	 *
	 */
	public void setLocationY (double locY) {
		loc[1] = locY;
	}
};
