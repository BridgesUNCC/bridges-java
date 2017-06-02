package bridges.data_src_dependent;

/**
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


public class CancerIncidence extends DataSource{

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
			
			

	/**
	 * The constructor
	 */
	public CancerIncidence () {
		super.setLabel("Cancer Incidence Data");
	}

	public CancerIncidence(String canc_label){
		super.setLabel(canc_label);
	}
	
	/**
	 * This method returns the string name
	 */
	public String getName(){
		return super.getLabel();
	}
	
	/**
	 * This method sets the string name
	 */
	public void setName(String name){
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
};
