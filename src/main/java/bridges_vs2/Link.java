/**
 * 
 */
package bridges_vs2;

/**
 * @author mihai
 *
 */
public class Link<K> {
	private K type;
	private Element<?, ?> e;
	
	public Link(K type, Element<?, ?> e){
		this.type = type;
		this.e = e;
	}
	
	public Link (Link<K> aLink){
		this.type = aLink.getType();
		this.e = aLink.getElement();
	}
	
	public K getType(){
		return this.type;
	}
	
	public void setNext(Element<?, ?> e){
		this.e = e;
	}
	
	public void removeNext(Element<?, ?> e){
		this.e = null;
	}
	
	public Element getElement(){
		return e;
	}
}
