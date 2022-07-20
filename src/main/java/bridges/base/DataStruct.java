package bridges.base;

/*! \mainpage BRIDGES
 *	@author   Kalpathi Subramanian, Jamie Payton, Erik Saule (and previously Mihai Mehedint, David Burlinson, Dakota Carmer, Michael Youngblood, Robert Kosara)
 *	@date  7/18/16, 7/20/22
 *
 * \section overview_sec Overview
 *	The Bridging Real-world Infrastructure Designed to Goal-align, Engage, and
 * 	Stimulate (BRIDGES) project is directed at improving the retention of sophomore
 *	students in Computer Science by (1) introducing real-world datasets (Facebook,
 *	Twitter, IMDB (Actor/Movie), Earthquake, Stock Charts, etc) into course projects
 *	involving computer science data structures,
 *	and (2) facilitating peer mentoring of sophomores by senior CS students in
 *	shared lab experiences that involve the use of the BRIDGES infrastructure in
 *	software development.
 *
 *	\section br_client BRIDGES Client Design
 *	BRIDGES client side design loosely follows the basic data structure elements
 *	implemented and described in ``A Practical Introduction to Data Structures and
 *	Algorithm Analysis" by C.A. Shaffer (http://people.cs.vt.edu/shaffer/Book/).
 *	These elements are augmented to contain visual properties that are controlled
 *	by the user to customize the visual representation of the constructed data structure.
 *	Once a a data structure is ready to be visualized, related BRIDGES server calls are
 *	made to send a reprsentation of the data structure to BRIDGES server.
 *
 *	\section br_server BRIDGES Server Design.
 *	BRIDGES server implements a combination of technologies (MongoDB, Node,
 *	d3JS(visualization) to receive a data structure representation for visualization.
 *	These are largely transparent to the user and involves the user being directed to a
 *	web page for viewing the data structure. Attention has been paid to provide
 *	meaningful error messages to the user in case problems are encountered in the process.
 *
 *	\section api_sec API Descriptions.
 *	See the accompanying pages for detailed description of the BRIDGES classes
 *
 *	\section sponsor_sec Sponsorship/Funding.
 * 	BRIDGES is funded by the National Science Foundation through NSF TUES Project DUE-1245841 and IUSE Projects DUE-1726809, DUE-214381
 *
 *	\section contacts_sec Contacts:
 *	Kalpathi Subramanian, krs@uncc.edu, Jamie Payton, payton@temple.edu, Erik Saule, esaule@uncc.edu,  	Paula Goolkasian, pagoolka@uncc.edu (and previously 
 *	Michael Youngblood, gmyoungblood@gmail.com, Robert Kosara, rkosara@tableau.com)


 *	Department of Computer Science, The University of North Carolina at Charlotte,
 *	Charlotte, NC.
 **/

/**
 *	@brief This is an abstract super class that is extended by all Bridges
 *	subclasses and provides some methods that are used universally across
 *  BRIDGES.
 *
 */

public abstract class DataStruct {
	// used by subclasses in JSON construction
	protected String
	QUOTE = "\"",
	COMMA = ",",
	COLON = ":",
	OPEN_CURLY = "{",
	CLOSE_CURLY = "}",
	OPEN_PAREN = "(",
	CLOSE_PAREN = ")",
	OPEN_BOX = "[",
	CLOSE_BOX = "]";

	public  abstract  String getDataStructType();
	public abstract String getDataStructureRepresentation();
};

