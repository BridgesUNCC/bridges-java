package bridges.data_src_dependent;
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
 * @date   2/1/17
 *
 */
import java.lang.String;

import bridges.data_src_dependent.DataSource;

public class Shakespeare  extends DataSource {
	private	String title,		// title of sonnet, play or poem
			type,			// Type (sonnet, play or poem
			text;			// full text

	public	Shakespeare() {
		title = type = text = "";
	}

	public Shakespeare(String title, String type, String text) {
		this.title = title;
		this.type = type;
		this.text = text;
	}

	public String getTitle() {
		return this.title;
	}
	public 	void setTitle (String title) {
		this.title = title;
	}

	public 	String getType() {
		return this.type;
	}
	public 	void setType(String type) {
		this.type = type;
	}

	public 	String getText() {
		return text;
	}
	public 	void setText(String text) {
		this.text = text;
	}
};
