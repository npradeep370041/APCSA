import java.util.Scanner;
import java.util.ArrayList;

/**
 * Binary Tree for State Objects
 *
 * @author 
 * @version 
 */
public class BinaryTree {

	private final String DEFAULT_FILE_NAME = "states2.txt"; // Default input file
	private Scanner keyboard;
	
	private TreeNode root;
	
	private ArrayList<State> nodes;
	
	public BinaryTree() {
		nodes = new ArrayList<State>();
	}
	
	/**
	 *	Load data from a text file
	 */
	public void loadData(){
		Scanner file = FileUtils.openToRead(DEFAULT_FILE_NAME);
		while(file.hasNext()) {
			String n = file.next();
			String a = file.next();
			int p = file.nextInt();
			int ar = file.nextInt();
			int r = file.nextInt();
			String c = file.next();
			int m = file.nextInt();
			int d = file.nextInt();
			int y = file.nextInt();
			State state = new State(n, a, p, ar, r, c, m, d, y);
			add(state);
		}
	}
	
	/**
	 * Insert State into tree
	 * @param next  State to insert
	 */
	public void add(State next) {
		if(root == null) {
			root = new TreeNode<State>(next);
		}
		else {
			TreeNode<State> curr = root;
			TreeNode<State> prev = root;
			boolean finished = false;
			while(curr != null) {
				if(next.compareTo(curr.getValue()) < 0) {
					if(curr.getLeft() == null) {
						prev = curr;
					}
					curr = curr.getLeft();
				}
				else if(next.compareTo(curr.getValue()) > 0) {
					if(curr.getRight() == null) {
						prev = curr;
					}
					curr = curr.getRight();
				}
			}
			TreeNode<State> node = new TreeNode(next);
			if(next.compareTo(prev.getValue()) < 0) {
				prev.setLeft(node);
			}
			else {
				prev.setRight(node);
			}
		}
	}
	

	/**
	 * Prints the tree as a list in ascending order by state name
	 */
	public void printList() {
		traverse(root);
		for(int i = 0; i < nodes.size(); i++) {
			System.out.println(nodes.get(i).toString());
		}
		while(nodes.size() > 0) {
			nodes.remove(0);
		}
	}
	
	public void traverse(TreeNode<State> node) {
		if(node.getLeft() == null && node.getRight() == null) {
			nodes.add(node.getValue());
			return;
		}
		if(node.getLeft() != null) {
			traverse(node.getLeft());
		}
		nodes.add(node.getValue());
		if(node.getRight() != null) {
			traverse(node.getRight());
		}
	}
	
	
	/**
	 * Prompts user for State name to find, then starts search
	 */
	public void testFind() {
		boolean ended = false;
		while(!ended) {
			String state = Prompt.getString("Enter state name to search for (Q to quit)");
			if(state.equalsIgnoreCase("q")) {
				ended = true;
			}
			else {
				traverse(root);
				boolean printed = false;
				for(int i = 0; i < nodes.size(); i++) {
					if(nodes.get(i).getName().compareTo(state) == 0) {
						System.out.println(nodes.get(i).toString());
						printed = true;
					}
				}
				if(!printed) {
					System.out.println("Name = " + state + "\tNo such state name");
				}
				while(nodes.size() > 0) {
					nodes.remove(0);
				}	
			}
		}
	}
	

	/**
	 * Prompts user for State name to delete
	 * OPTIONAL: Not included in your grade!
	 */
	public void testDelete() {

	}
	
	/**
	 * Finds the number of nodes starting at the root of the tree
	 * @return  the number of nodes in the tree
	 */
	public int size() {
		if(root == null) {
			return 0;
		}
		traverse(root);
		int size = nodes.size();
		while(nodes.size() > 0) {
			nodes.remove(0);
		}
		return size;
	}
	
	
	/**
	 * Clears the tree of all nodes
	 */
	public void clear() {
		root = null;
	}
	
	/**
	 * Prompts user for level of tree to print.
	 * The top level (root node) is level 0.
	 */
	public void printLevel() {
		boolean ended = false;
		while(!ended) {
			int target = Prompt.getInt("Enter level value to print (-1 to quit)");
			if(target == -1) {
				ended = true;
			}
			else {
				traverseLevel(root, 0, target);
				System.out.println("Level\t" + target);
				for(int i = 0; i < nodes.size(); i++) {
					System.out.print(nodes.get(i).getName() + "\t\t");
				}
				System.out.println();
				while(nodes.size() > 0) {
					nodes.remove(0);
				}
			}
		}
	}
	
	public void traverseLevel(TreeNode<State> node, int level, int target) {
		if(level == target) {
			nodes.add(node.getValue());
			return;
		}
		if(node.getLeft() != null) {
			traverseLevel(node.getLeft(), level + 1, target);
		}
		if(node.getRight() != null) {
			traverseLevel(node.getRight(), level + 1, target);
		}
	}
	
	/**
	 * Prints the highest level of the tree (root is level 0),
	 * prints "Tree empty" if empty tree
	 */
	public void testDepth() {
		if(root == null) {
			System.out.println("Tree Empty");
		}
		else {
			ArrayList<Integer> depths = new ArrayList<Integer>();
			traverseDepth(root, 0, depths);
			int max = 0;
			for(int i = 0; i < depths.size(); i++) {
				if(depths.get(i) > max) {
					max = depths.get(i);
				}
			}
			System.out.println("Depth of the tree = " + max);
		}
	}
	
	public void traverseDepth(TreeNode<State> node, int level, ArrayList<Integer> depths) {
		if(node.getLeft() == null && node.getRight() == null) {
			depths.add(level);
			return;
		}
		else if(node.getLeft() != null) {
			traverseDepth(node.getLeft(), level + 1, depths);
		}
		else if(node.getRight() != null) {
			traverseDepth(node.getLeft(), level + 1, depths);
		}
	}
}
