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
 *
 *
 * One does not typically create an object from this type, but rather obtain them by calling bridges::connect::DataSource::getCancerIncidenceData()
 *
 * Refer to tutorial examples on how to use this dataset: https://bridgesuncc.github.io/tutorials/Data_CancerIncidence.html
 *
 *  Data: Courtesy of Corgis Datasets, 2017
 *
 *  Author: Kalpathi Subramanian, April, 2017
 *
 */


public class CancerIncidence {

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


	public CancerIncidence () {
		loc  = new double[] {0.0, 0.0};
		age_adjusted_rate_ci = new double[] {0.0, 0.0};
		crude_rate_ci = new double[] {0.0, 0.0};
	}

	/*
	 * @brief Get the expected cancer rate, adjusted for age of participants.
	 *
	 * @return cancer rate adjusted for age
	 */
	public double getAgeAdjustedRate() {
		return age_adjusted_rate;
	}

	/**
	 * @brief Set age adjusted cancer rate
	 *
	 * @param aar age adjusted rate
	 *
	 */
	public void setAgeAdjustedRate(double aar) {
		age_adjusted_rate = aar;
	}

	/*
	 * @brief Get the expected cancer rate confidence interval(lower),
	 *	adjusted for age of participants.
	 *
	 * @return cancer conf interval (lower) rate
	 *
	 */
	public double getAgeAdjustedCI_Lower() {
		return age_adjusted_rate_ci[0];
	}

	/**
	 * @brief Set age adjusted cancer conf interval (lower)
	 *
	 * @param ci_l lower bound for confidence interval
	 *
	 */
	public void setAgeAdjustedCI_Lower(double ci_l) {
		age_adjusted_rate_ci[0] = ci_l;
	}
	/*
	 * @brief Get the expected cancer rate confidence interval(upper),
	 *	adjusted for age of participants.
	 *
	 * @return cancer conf interval (upper) rate
	 *
	 */
	public double getAgeAdjustedCI_Upper() {
		return age_adjusted_rate_ci[1];
	}

	/**
	 * @brief Set age adjusted cancer conf interval (upper)
	 *
	 * @param ci_u upper bound of the confidence interval to set
	 *
	 */
	public void setAgeAdjustedCI_Upper(double ci_u) {
		age_adjusted_rate_ci[1] = ci_u;
	}

	/**
	* @brief Get the cancer rate, adjusted for population
	 *
	 * @return crude cancer rate
	 */
	public double getCrudeRate() {
		return crude_rate;
	}
	/**
	 * @brief Set cancer rate, adjusted for population
	 *
	 * @param cr crude rate to set
	 */
	public void setCrudeRate(double cr) {
		crude_rate = cr;
	}
	/**
	* @brief Get the expected cancer crude rate confidence interval(lower),
	 * adjusted for age of participants.
	 *
	 * @return cancer conf interval (lower) rate
	 *
	 */
	public double getCrudeRate_CI_Lower() {
		return crude_rate_ci[0];
	}

	/**
	 * @brief Set age adjusted cancer crude conf interval (lower)
	 *
	 * @param cr_l lower bound of the cancer crude rate confidence interval
	 *
	 */
	public void setCrudeRate_CI_Lower(double cr_l) {
		crude_rate_ci[0] = cr_l;
	}
	/**
	 * Get the expected cancer crude rate confidence interval(upper),
	 * adjusted for age of participants.
	 *
	 * @return cancer crude rate CI (upper) rate
	 *
	 **/
	public double getCrudeRate_CI_Upper() {
		return crude_rate_ci[1];
	}

	/**
	 * @brief Set crude rate CI (upper)
	 *
	 * @param cr_u upper bound of crude rate confidence interval
	 *
	 */
	public void setCrudeRate_CI_Upper(double cr_u) {
		crude_rate_ci[1] = cr_u;
	}

	/**
	 * @brief Get the year of this cancer record
	 *
	 * @return year of the cancer record
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @brief Set the year of this cancer record
	 *
	 * @param y  year of the cancer record
	 */
	public void setYear(int y) {
		year = y;
	}
	/*
	 * @brief Get the gender of the group
	 *
	 * @return the gender ("male", "female", "male and female")
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @brief Set gender
	 *
	 * @param g the gender to set ("male", "female", "male and female")
	 */
	public void setGender(String g) {
		gender = g;
	}

	/**
	 * Get the race of the group
	 *
	 * @return race (All Races, etc)
	 */
	public String getRace() {
		return race;
	}
	/**
	 * @brief Set race
	 *
	 * @param r race to set
	 */
	public void setRace(String r) {
		race = r;
	}

	/**
	 * @brief Get the event type (incidence, mortality, etc)
	 *
	 * @return event type
	 */
	public String getEventType() {
		return event_type;
	}
	/**
	 * @brief Set event type
	 *
	 * @param et event type to set
	 */
	public void setEventType(String et) {
		event_type = et;
	}

	/**
	 * @brief Get the population size
	 *
	 * @return population size
	 */
	public int getPopulation() {
		return population;
	}
	/**
	 * @brief Set population size
	 *
	 * @param pop population size
	 */
	public void setPopulation(int pop) {
		population = pop;
	}

	/**
	 * @brief Get the cancer incidence area (state, region, etc)
	 *
	 * @return affected area
	 */
	public String getAffectedArea() {
		return affected_area;
	}
	/**
	 * @brief Set cancer incidence area
	 *
	 * @param area affected area
	 */
	public void setAffectedArea(String area) {
		affected_area = area;
	}

	/**
	 * @brief Get the number of people affected in this group
	 *
	 * @return number of people affected in this group.
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @brief Set cancer incidence count
	 *
	 * @param c incidence count
	 */
	public void setCount(int c) {
		count = c;
	}

	/**
	 * @brief Get the X coordinate of location
	 *
	 * @return x coordinate (longitude?)
	 */
	public double getLocationX() {
		return loc[0];
	}
	/**
	 *	@brief Set location (X coord)
	 *
	 *	@param locX  X coordinate of location
	 *
	 */
	public void setLocationX (double locX) {
		loc[0] = locX;
	}

	/**
	 * @brief Get the Y coordinate of location
	 *
	 * @return y coordinate (latitude?)
	 */
	public double getLocationY() {
		return loc[1];
	}
	/**
	 *	@brief Set location (Y coord)
	 *
	 *	@param locY  Y coordinate of location
	 */
	public void setLocationY (double locY) {
		loc[1] = locY;
	}
};
