package sketch;

public class testingBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// TODO insert(node)
		// TODO remove(node)
		// TODO find(node.val)
		TreeVisualizer tv = new TreeVisualizer("BST");
		
		
		tv.insert(new BSTNode("actor/Bob", 2));
		tv.insert(new BSTNode("actor/Steve", 5));
		tv.insert(new BSTNode("actor/Jim", 1));
		
		
		tv.insert(new BSTNode("actor/Jim", 10));		
	}

}
