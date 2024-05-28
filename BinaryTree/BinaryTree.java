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
	
	private final int PRINT_SPACES = 3;
	
	public BinaryTree() {
		nodes = new ArrayList<State>();
	}
	
	/**
	 *	Load data from a text file
	 */
	public void loadData(){
		clear();
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
	public void insert(State next) {
		add(next);
	}
	
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
		if(root != null) {
			traverse(root);
			for(int i = 0; i < nodes.size(); i++) {
				System.out.println(nodes.get(i).toString());
			}
			while(nodes.size() > 0) {
				nodes.remove(0);
			}
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
		if(root != null) {
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
						if(nodes.get(i).getName().toLowerCase().compareTo(state.toLowerCase()) == 0) {
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
	}
	

	/**
	 * Prompts user for State name to delete
	 * OPTIONAL: Not included in your grade!
	 */
	public void testDelete() {
		if(root != null) {
			boolean ended = false;
			while(!ended) {
				String state = Prompt.getString("Enter state name to search for (Q to quit)");
				if(state.equalsIgnoreCase("q")) {
					ended = true;
				}
				else {
					traverse(root);
					boolean deleted = false;
					for(int i = 0; i < nodes.size(); i++) {
						if(nodes.get(i).getName().toLowerCase().compareTo(state.toLowerCase()) == 0) {
							remove(nodes.get(i));
							System.out.println("Deleted " + nodes.get(i).getName());
							deleted = true;
						}
					}
					if(!deleted) {
						System.out.println("Name = " + state + "\tNo such state name");
					}
					while(nodes.size() > 0) {
						nodes.remove(0);
					}	
				}
			}
		}
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
		if(root != null) {
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
			System.out.println("Depth of the tree = " + max + "\n\n");
		}
	}
	
	public void traverseDepth(TreeNode<State> node, int level, ArrayList<Integer> depths) {
		if(node.getLeft() == null && node.getRight() == null) {
			depths.add(level);
			return;
		}
		if(node.getLeft() != null) {
			traverseDepth(node.getLeft(), level + 1, depths);
		}
		if(node.getRight() != null) {
			traverseDepth(node.getRight(), level + 1, depths);
		}
	}
	
	/**
	 *	Print Binary Tree Inorder
	 */
	public void printInorder() { 
		traverseInorder(root);
		for(int i = 0; i < nodes.size(); i++) {
			System.out.print(nodes.get(i) + " ");
		}
		while(nodes.size() > 0) {
			nodes.remove(0);
		}
	}
	
	public void traverseInorder(TreeNode<State> node) {
		if(node.getLeft() == null && node.getRight() == null) {
			nodes.add(node.getValue());
			return;
		}
		if(node.getLeft() != null) {
			traverseInorder(node.getLeft());
		}
		nodes.add(node.getValue());
		if(node.getRight() != null) {
			traverseInorder(node.getRight());
		}
	}

	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() { 
		traversePreorder(root);
		for(int i = 0; i < nodes.size(); i++) {
			System.out.print(nodes.get(i) + " ");
		}
		while(nodes.size() > 0) {
			nodes.remove(0);
		}
	}
	
	public void traversePreorder(TreeNode<State> node) {
		if(node.getLeft() == null && node.getRight() == null) {
			nodes.add(node.getValue());
			return;
		}
		nodes.add(node.getValue());
		if(node.getLeft() != null) {
			traversePreorder(node.getLeft());
		}
		if(node.getRight() != null) {
			traversePreorder(node.getRight());
		}
	}
	
	
	/**
	 *	Print Binary Tree Postorder
	 */
	public void printPostorder() { 
		traversePostorder(root);
		for(int i = 0; i < nodes.size(); i++) {
			System.out.print(nodes.get(i) + " ");
		}
		while(nodes.size() > 0) {
			nodes.remove(0);
		}
	}
	
	public void traversePostorder(TreeNode<State> node) {
		if(node.getLeft() == null && node.getRight() == null) {
			nodes.add(node.getValue());
			return;
		}
		if(node.getLeft() != null) {
			traversePreorder(node.getLeft());
		}
		if(node.getRight() != null) {
			traversePreorder(node.getRight());
		}
		nodes.add(node.getValue());
	}
		
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree makeBalancedTree() {
		BinaryTree balancedTree = new BinaryTree();
		traverseInorder(root);
		formTree(nodes, balancedTree);
		while(nodes.size() > 0) {
			nodes.remove(0);
		}
		return balancedTree;
	}
	
	public void formTree(ArrayList<State> list, BinaryTree tree) {
		if(list.size() == 1) {
			tree.add(list.get(0));
			return;
		}
		else if(list.size() != 0) {
			int mid = (list.size() - 1)/2;
			tree.add(list.get(mid));
			ArrayList<State> leftList = new ArrayList<State>();
			for(int i = 0; i < mid; i++) {
				leftList.add(list.get(i));
			}
			ArrayList<State> rightList = new ArrayList<State>();
			for(int j = mid + 1; j < list.size(); j++) {
				rightList.add(list.get(j));
			}
			formTree(leftList, tree);
			formTree(rightList, tree);
		}
	}
	
	/**
	 *	Remove value from Binary Tree
	 *	@param value		the value to remove from the tree
	 *	Precondition: value exists in the tree
	 */
	public void remove(State value) {
		root = remove(root, value);
	}
	/**
	 *	Remove value from Binary Tree
	 *	@param node			the root of the subtree
	 *	@param value		the value to remove from the subtree
	 *	@return				TreeNode that connects to parent
	 */
	public TreeNode<State> remove(TreeNode<State> node, State value) {
		TreeNode<State> curr = root;
		TreeNode<State> prev = curr;
		while(curr.getLeft() != null || curr.getRight() != null) {
			if(value.compareTo(curr.getValue()) == 0) {
				if(curr.getRight() == null) {
					if(curr == root) {
						root = root.getLeft();
					}
					else {
						if(curr.getValue().compareTo(prev.getValue()) < 0) {
							prev.setLeft(curr.getLeft());
						}
						else {
							prev.setRight(curr.getLeft());
						}
					}
				}
				else {
					if(curr.getRight().getLeft() == null) {
						if(curr == root) {
							root = new TreeNode<State>((State)root.getRight().getValue(), root.getLeft(), root.getRight().getRight());
						}
						else {
							if(curr.getValue().compareTo(prev.getValue()) < 0) {
								prev.setLeft(curr.getRight());
							}
							else {
								prev.setRight(curr.getRight());
							}
							if(curr.getRight() != null) {
								curr.getRight().setLeft(curr.getLeft());
							}
						}
					}
					else {
						if(curr == root) {
							curr = curr.getRight();
							while(curr.getLeft() != null) {
								curr = curr.getLeft();
							}
							State newVal = curr.getValue();
							remove(newVal);
							root = new TreeNode<State>(newVal, root.getLeft(), root.getRight());
						}
						else {
							TreeNode<State> saved = curr;
							curr = curr.getRight();
							while(curr.getLeft() != null) {
								curr = curr.getLeft();
							}
							State newVal = curr.getValue();
							remove(newVal);
							saved = new TreeNode<State>(newVal, saved.getLeft(), saved.getRight());
							if(saved.getValue().compareTo(prev.getValue()) < 0) {
								prev.setLeft(saved);
							}
							else {
								prev.setRight(saved);
							}
						}
					}
				}
				return root;
			}
			else {
				if(value.compareTo(curr.getValue()) < 0) {
					prev = curr;
					curr = curr.getLeft();
				}
				else if(value.compareTo(curr.getValue()) > 0) {
					prev = curr;
					curr = curr.getRight();
				}
			}
			
		}
		if(value.compareTo(prev.getValue()) < 0) {
			prev.setLeft(null);
		}
		else {
			prev.setRight(null);
		}
		if(curr == root) {
			root = null;
		}
		return root;
	}
	

	/*******************************************************************************/	
	/********************************* Utilities ***********************************/	
	/*******************************************************************************/	
	/**
	 *	Print binary tree
	 *	@param root		root node of binary tree
	 *
	 *	Prints in vertical order, top of output is right-side of tree,
	 *			bottom is left side of tree,
	 *			left side of output is root, right side is deepest leaf
	 *	Example Integer tree:
	 *			  11
	 *			/	 \
	 *		  /		   \
	 *		5			20
	 *				  /	  \
	 *				14	   32
	 *
	 *	would be output as:
	 *
	 *				 32
	 *			20
	 *				 14
	 *		11
	 *			5
	 ***********************************************************************/
	public void printTree() {
		printLevel(root, 0);
	}
	
	/**
	 *	Recursive node printing method
	 *	Prints reverse order: right subtree, node, left subtree
	 *	Prints the node spaced to the right by level number
	 *	@param node		root of subtree
	 *	@param level	level down from root (root level = 0)
	 */
	private void printLevel(TreeNode<State> node, int level) {
		if (node == null) return;
		// print right subtree
		printLevel(node.getRight(), level + 1);
		// print node: print spaces for level, then print value in node
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue().getName());
		// print left subtree
		printLevel(node.getLeft(), level + 1);
	}
}
