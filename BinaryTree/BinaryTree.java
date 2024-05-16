/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	
 *	@author	Naithik Pradeep
 *	@since	
 */
 
import java.util.ArrayList;

public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root;		// the root of the tree
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
										// used by printTree()
	private ArrayList<E> printOrder;
	
	/**	constructor for BinaryTree */
	public BinaryTree() { 
		printOrder = new ArrayList<E>();
	}
	
	/**	Field accessors and modifiers */
	
	/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public void add(E value) {
		if(root == null) {
			root = new TreeNode<E>(value);
		}
		else {
			TreeNode<E> curr = root;
			TreeNode<E> branch = root;
			boolean finished = false;
			while(curr != null) {
				if(value.compareTo(curr.getValue()) < 0) {
					if(curr.getLeft() == null) {
						branch = curr;
					}
					curr = curr.getLeft();
				}
				else if(value.compareTo(curr.getValue()) > 0) {
					if(curr.getRight() == null) {
						branch = curr;
					}
					curr = curr.getRight();
				}
				
			}
			TreeNode<E> node = new TreeNode(value);
			if(value.compareTo(branch.getValue()) < 0) {
				branch.setLeft(node);
			}
			else {
				branch.setRight(node);
			}
		}
	}
	
	/**
	 *	Print Binary Tree Inorder
	 */
	public void printInorder() { 
		traverseInorder(root);
		for(int i = 0; i < printOrder.size(); i++) {
			System.out.print(printOrder.get(i) + " ");
		}
		while(printOrder.size() > 0) {
			printOrder.remove(0);
		}
	}
	
	public void traverseInorder(TreeNode<E> node) {
		if(node.getLeft() == null && node.getRight() == null) {
			printOrder.add(node.getValue());
			return;
		}
		if(node.getLeft() != null) {
			traverseInorder(node.getLeft());
		}
		printOrder.add(node.getValue());
		if(node.getRight() != null) {
			traverseInorder(node.getRight());
		}
	}

	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() { 
		traversePreorder(root);
		for(int i = 0; i < printOrder.size(); i++) {
			System.out.print(printOrder.get(i) + " ");
		}
		while(printOrder.size() > 0) {
			printOrder.remove(0);
		}
	}
	
	public void traversePreorder(TreeNode<E> node) {
		if(node.getLeft() == null && node.getRight() == null) {
			printOrder.add(node.getValue());
			return;
		}
		printOrder.add(node.getValue());
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
		for(int i = 0; i < printOrder.size(); i++) {
			System.out.print(printOrder.get(i) + " ");
		}
		while(printOrder.size() > 0) {
			printOrder.remove(0);
		}
	}
	
	public void traversePostorder(TreeNode<E> node) {
		if(node.getLeft() == null && node.getRight() == null) {
			printOrder.add(node.getValue());
			return;
		}
		if(node.getLeft() != null) {
			traversePreorder(node.getLeft());
		}
		if(node.getRight() != null) {
			traversePreorder(node.getRight());
		}
		printOrder.add(node.getValue());
	}
		
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree<E> makeBalancedTree() {
		BinaryTree<E> balancedTree = new BinaryTree<E>();
		traverseInorder(root);
		formTree(printOrder, balancedTree);
		while(printOrder.size() > 0) {
			printOrder.remove(0);
		}
		return balancedTree;
	}
	
	public void formTree(ArrayList<E> list, BinaryTree<E> tree) {
		if(list.size() == 1) {
			tree.add(list.get(0));
			return;
		}
		else if(list.size() != 0) {
			int mid = (list.size() - 1)/2;
			tree.add(list.get(mid));
			ArrayList<E> leftList = new ArrayList<E>();
			for(int i = 0; i < mid; i++) {
				leftList.add(list.get(i));
			}
			ArrayList<E> rightList = new ArrayList<E>();
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
	public void remove(E value) {
		root = remove(root, value);
	}
	/**
	 *	Remove value from Binary Tree
	 *	@param node			the root of the subtree
	 *	@param value		the value to remove from the subtree
	 *	@return				TreeNode that connects to parent
	 */
	public TreeNode<E> remove(TreeNode<E> node, E value) {
		return null;
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
	private void printLevel(TreeNode<E> node, int level) {
		if (node == null) return;
		// print right subtree
		printLevel(node.getRight(), level + 1);
		// print node: print spaces for level, then print value in node
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue());
		// print left subtree
		printLevel(node.getLeft(), level + 1);
	}
	
	
}
