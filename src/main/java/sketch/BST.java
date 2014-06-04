package sketch;
/**
 * Title: Binary Search Tree ADT with Strings for Bridges
 * @author mihai mehedint
 * @version1.0 modified after the driver provided during 2214 spring 2014 by krs
 * @05.25.2014
 */
class IntBST{
    private String value;

    public IntBST(String val) {
             value = val;	
            }

    public String getVal() {return value;}
    
}

public class BST{

    public static void main(String args[])
    {
    	//--------------------------Testing------------------------------------
    	GraphVisualizer gv = new GraphVisualizer();
    		
    	Vertex bob = new Vertex("actor/Bob", gv);
   		Vertex fred = new Vertex("loafer/Fred", gv);
   		Edge bob_to_fred = new Edge(bob, fred);
   		
   		bob.remove();
    		
   		Vertex steve = new Vertex("actor/Steve", gv);
   		Edge bob_to_steve = new Edge(bob, steve);
    		
   		//gv.vertices.put("actor/Bob", bob);
   		//gv.vertices.put("loafer/Fred", fred);
   		//gv.vertices.put("actor/Steve", steve);    	
   		
   		//---------------------------------------------------------------------
    		BST<String, IntBST> i_tree = new BST<String, IntBST>();

            System.out.println("------ BEGIN -----------")  ;
            System.out.println("Tree Size: " + i_tree.size());

            System.out.println("Inserting 100..")  ;
            i_tree.insert("100", new IntBST("100"));
            System.out.println("Inserting 50..")  ;
            i_tree.insert("50", new IntBST("50"));
            System.out.println("Inserting 8..")  ;
            i_tree.insert("8", new IntBST("8"));
            System.out.println("Inserting 200..")  ;
            i_tree.insert("200", new IntBST("200"));
            System.out.println("Inserting blah..")  ;
            i_tree.insert("blah", new IntBST("blah"));
            System.out.println("Inserting 150..")  ;
            i_tree.insert("150", new IntBST("150"));
            System.out.println("Inserting 1..")  ;
            i_tree.insert("1", new IntBST("1"));
            System.out.println("Tree Size: " + i_tree.size());
            System.out.println("Printing tree..")  ;
            System.out.println("-----------------")  ;
            System.out.println(i_tree.toString());
            System.out.println("-----------------")  ;
            System.out.println("Tree Size: " + i_tree.size());
            System.out.println("Removing 8: ");
            IntBST it = i_tree.remove("8");
            System.out.println("-----------------");
            System.out.println("Printing tree..");
            System.out.println(i_tree.toString());
            System.out.println("-----------------");
            System.out.println("Removing 10.. ");
            it = i_tree.remove("10");
            System.out.println("Printing tree..");
            System.out.println(i_tree.toString());
            System.out.println("Removing 100.. ");
            it = i_tree.remove("100");
            System.out.println("-----------------");
            System.out.println("Printing tree..");
            System.out.println(i_tree.toString());
            System.out.println("-----------------");
            System.out.println("Tree Size: " + i_tree.size());
            System.out.println(i_tree.toString());
            System.out.println("Does  200 exist? ");
            IntBST b = i_tree.find("200");
            if (b!=null)
                      System.out.println("Found Object with val  " + b.getVal());
            else    System.out.println("Not found..");
            System.out.println("Does  8 exist? ");
            b = i_tree.find("8");
            if (b!=null)
                      System.out.println("Found Object with val " + b.getVal());
            else    System.out.println("Not found..");
            i_tree.clear();
            System.out.println("Clearing Tree...");
            System.out.println("Size: " + i_tree.size());
            System.out.println("------ END -----------");

      return ;
    }
}