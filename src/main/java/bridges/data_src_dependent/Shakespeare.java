package bridges.data_src_dependent;
import java.lang.String;
import bridges.data_src_dependent.DataSource;
/**
 * @brief  A Shakespeare Data source object containing sonnets, poems and
 *		plays
 *
 * This is a convenience class provided for  users who wish to use this
 * data source as part of their application. It provides an API that makes
 * it easy to access the attributes of this data set.
 *
 * Refer to tutorial examples to using this data source in data structure
 * assignments.
 *
 *  Refer to tutorial examples to using this data source in data structure
 *  assignments.
 *
 * @author Kalpathi Subramanian
 * @date   2/1/17, 12/26/20
 *
 */

public class Shakespeare  extends DataSource {
	private	String title,		// title of sonnet, play or poem
			type,			// Type (sonnet, play or poem
			text;			// full text

	/**
	 * Constructor
	 */
	public	Shakespeare() {
		title = type = text = "";
	}

	/**
	 * Constructor
	 *
	 * @param title title of sonnet, play or poem
	 * @param type (sonnet, play or poem)
	 * @param text full text of entity
	 */
	public Shakespeare(String title, String type, String text) {
		this.title = title;
		this.type = type;
		this.text = text;
	}

	/**
	 *  Get title of sonnet, play or poem
	 *  @return title  (string)
	 */
	public String getTitle() {
		return this.title;
	}
	/**
	 *  set title of sonnet, play or poem
	 *  @param title  (string) to be set
	 */
	public 	void setTitle (String title) {
		this.title = title;
	}

	/**
	 *  Get type of sonnet, play or poem
	 *  @return type  (string)
	 */
	public 	String getType() {
		return this.type;
	}
	/**
	 *  set type to sonnet, play or poem
	 *  @param type  (string) to be set
	 */
	public 	void setType(String type) {
		this.type = type;
	}

	/**
	 *  Get full text of  sonnet, play or poem
	 *  @return full text  (string)
	 */
	public 	String getText() {
		return text;
	}
	/**
	 *  set full text of  sonnet, play or poem
	 *  @param text  full text (string) to be set
	 */
	public 	void setText(String text) {
		this.text = text;
	}
};
