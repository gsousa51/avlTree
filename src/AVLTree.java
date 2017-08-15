
//Author: Gary Sousa
//Assignment: CS345 Fall '17 - Proj03
//Purpose: Creates an AVT tree and stores simple method for modifying the tree.
// Rule of AVT Tree: Each Node's children's height differ by 1 at most. If there is a violation
// of this rule during insertion or deletion, the tree performs rotations until the rule of 
//balance is restored.
//ClassName: AVLTree.java

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/* class AVLTree
 *
 * Models a complete AVLTree.  Individual nodes are modeled by the AVLNode
 * class.
 *
 * The constructor creates an empty tree (root=null).
 *
 * This class has many methods which perform basic operations.  Most of these
 * (non-static) methods have (static) recursive helper methods, which take an
 * AVLNode as the first parameter; these are static because it is valid for the
 * parameter to be null.  Thus, the base case for each of these methods is an
 * empty tree.  (See the method documentation below for details; there are a
 * few exceptions to this rule.)
 *
 * Methods that perform modifications on the tree all use the x=change(x)
 * style.  These include highly visible methods (such as insert and delete),
 * and also utility methods (such as rotateRight).
 */
public class AVLTree {

	// You *must not* add any fields other than this one to this class!
	// This field is sufficient for everything that you need to do.
	//
	// Also, remember that you can't change the AVLNode class *at all* -
	// and so you cannot add fields there, either!
	//
	// However, you can (and should!) add some static helper methods to
	// this class.
	private AVLNode root;

	/*
	 * default constructor
	 *
	 * Initializes this object to represent an empty tree.
	 */
	public AVLTree() {
		// See my comment in the constructor for AVLNode. I like
		// to explicitly set things to null, instead of simply
		// using the default values.
		// - Russ
		root = null;
	}
	public void modThis(int num){
		int numMod = ((2*num)+5)%23;
		System.out.println(num + " -> " + numMod);
	}
	/*
	 * debug(String)
	 *
	 * Opens a file with the given filename, and then fills that file with .dot
	 * file source code, to generate a picture for this tree. This wrapper opens
	 * (and closes) the file, and adds the necessary prefix/suffix text inside
	 * it. However, the actual body of the file should be printed by a recursive
	 * helper method.
	 *
	 * - Note from Russ: I have not yet defined the helper method, since I
	 * assume that different students might use different Java classes for
	 * printing. One option is to pass a FileWriter as a parameter; another is
	 * to *return* a String.
	 */
	public void debug(String filename) {
		// Create a new PrintWriter for writing to file
		PrintWriter writer = null;
		try {
			// try to set PrintWriter to write to the parameter
			writer = new PrintWriter(filename);
		} catch (FileNotFoundException e) {
			// If didn't work, error message.
			e.printStackTrace();
		}
		// Write out start to graph.
		writer.println("Digraph");
		writer.println("{");
		// Use private helper method to create data part of graph
		debug(root, writer);
		// End output with bracket
		writer.println("}");
		// close printwriter
		writer.close();
	}

	public AVLNode getRoot(){
		return root;
	}
	public AVLNode getRight(AVLNode node){
		return node.right;
	}
	public AVLNode getLeft(AVLNode node){
		return node.left;
	}
	public void setHeight(AVLNode node, int height){
		node.height= height;
	}
	public void print_InOrderRange(int min, int max) {
		print_InOrderRange(min, max, root);
		System.out.println(" " );
	}
	private void print_InOrderRange(int min, int max, AVLNode curr) {
		if (curr == null) {
			return;
		}
		if (curr.val>min) {
			print_InOrderRange(min, max, curr.left);
		}
		if (curr.val>= min && curr.val<=max) {
			System.out.print(curr.val + " ");
		}
		if (curr.val < max) {
			print_InOrderRange(min, max, curr.right);
		}

	}
	public boolean checkHeight(){
		return checkHeight(root).balanced;
	}
	private HeightChecker checkHeight(AVLNode curr){
		if (curr == null){
			return new HeightChecker(true,-1);
		}
		HeightChecker left = checkHeight(curr.left);
		HeightChecker right = checkHeight (curr.right);
		if(curr.height == Math.max(left.height,right.height)+1&&left.balanced&&right.balanced){
			return new HeightChecker(true,curr.height);
		}
		else return new HeightChecker(false,curr.height);
	}

	/*
	 * Created a private class to help with checking height
	 * Rather than just having a return type of boolean or int we return a new HeightChecker
	 * This holds the boolean value of if the node was balanced as well as its stored height.
	 * This allows us to recurse through the entire tree once to check the height of every node.
	 */
	private class HeightChecker{
		int height;
		boolean balanced;
		
		public HeightChecker(boolean balanced, int height){
			this.height = height;
			this.balanced = balanced;
		}
	}

	// Parameters: current node and writer used to write to file.
	// Purpose: Private helper method to write output to .dot file
	private static void debug(AVLNode curr, PrintWriter writer) {
		// if node is null, just return.
		if (curr == null) {
			return;
		}
		// Write out the val from the node to file
		writer.println(curr.val + " [label=\"" + curr.val + "\nh=" + curr.height + "\"]");
		// If it has a left child, write to that it shares an edge with node.
		if (curr.left != null) {
			writer.println(curr.val + " -> " + curr.left.val + " [label=\"left\"]");
		}
		// if it has a right child, write to file that it shares an edge with
		// that node.
		if (curr.right != null) {
			writer.println(curr.val + " -> " + curr.right.val + " [label=\"right\"]");
		}
		// recurse left
		debug(curr.left, writer);
		// recurse right
		debug(curr.right, writer);
	}// end debug

	/*
	 * print_inOrder()
	 *
	 * Prints out an in-order traversal of the tree to stdout (followed by a
	 * newline). The keys are separated by spaces, and the list (if non-empty)
	 * has a trailing space. If the tree is empty, this prints out nothing
	 * except for the newline. Uses a static recursive helper method.
	 *
	 * static print_inOrder(AVLNode)
	 *
	 * Helper method for the public method. Is static; takes an AVLNode
	 * parameter, which might be null. If the parameter is null, it does
	 * nothing. Works recursively.
	 */
	public void print_inOrder() {
		// call a helper method to print tree in inOrder
		print_inOrder(root);
		System.out.println("");
	}

	private static void print_inOrder(AVLNode tree) {
		// if we hit a null node, do nothing.
		if (tree == null) {
			return;
		}
		// else we recurse into left subtree
		print_inOrder(tree.left);
		// then the current node
		System.out.print(tree.val + " ");
		// then the recurse in the right subtree
		print_inOrder(tree.right);

	}

	/*
	 * print_preOrder()
	 *
	 * See print_inOrder() above - works in exactly the same way, except
	 * produces a pre-order traversal instead of an in-order traversal.
	 */
	public void print_preOrder() {
		// call helper method
		print_preOrder(root);
		System.out.println("");
	}

	private static void print_preOrder(AVLNode tree) {
		// if tree is empty, do nothing
		if (tree == null) {
			return;
		}

		// print value of current node
		System.out.print(tree.val + " ");
		// recurse into left subtree
		print_preOrder(tree.left);
		// then recurse into the right subtree.
		print_preOrder(tree.right);
	}

	/*
	 * search(int)
	 *
	 * Searches the tree for a given key. Returns the node where the tree
	 * exists, or null if the key does not exist. May be implemented recursively
	 * (with a helper method), or as a loop.
	 */
	public AVLNode search(int val) {
		// Return the value of the helper method.
		// It returns the node if we find it or null if we don't find it.
		return search(val, root);
	}

	// Parameters: val==value we're searching for
	// curr== the current AVLNode the method is on.
	// Purpose: Searches for an AVLNode that contains the val sent in as
	// parameter
	// Return values: AVLNode contains val if found
	// null AVLNode if tree doesn't contain Node that contains "val"
	private AVLNode search(int val, AVLNode curr) {
		// if the node is null, we just return the null node
		// This means we never found the node we were looking for
		if (curr == null) {
			return curr;
		}
		// if the value of the current node is less than the value we're
		// searching for
		// Recurse to the right.
		if (curr.val < val) {
			return search(val, curr.right);
		}
		// If the value of the current node is greater than the value we're
		// searching for
		// Recurse to the left.
		if (curr.val > val) {
			return search(val, curr.left);
		}
		// if we get here, this was the node we were looking for
		// Return curr
		else
			return curr;
	}

	/*
	 * insert(int)
	 *
	 * Inserts a new key into the tree; throws IllegalArgumentException if the
	 * key is a duplicate.
	 *
	 * Uses the x=change(x) style to modify the tree. Uses a static, recursive
	 * helper method to implement the modification. The helper method is
	 * responsible for *all* of the changes associated with inserting a new
	 * value, including all rebalances.
	 *
	 * static insert(AVLNode,int)
	 *
	 * Static, recursive helper method for insert(int). Takes an AVLNode
	 * parameter, which might be null; if the parameter is null, then it creates
	 * a new node and returns it.
	 *
	 * Always returns the root of the new tree; does all of the rebalancing work
	 * associated with the insert.
	 */
	public void insert(int val) throws IllegalArgumentException {
		// using x=change(x)
		// We set root equal to the value returns by the helper method.
		root = insert(root, val);
	}

	// Parameters: subtree==current node we're on
	// val== value we're inserting into the tree
	// Purpose: Method recurses through tree until we find the correct spot
	// to insert a new node containing the integer parameter
	// Return value: Returns the modified of each node after insertion of node
	// OR if node already exists containing the value we throw an EXCEPTION
	private static AVLNode insert(AVLNode subtree, int val) throws IllegalArgumentException {
		// If we find a null node, we hit the spot to insert our new node
		if (subtree == null) {
			subtree = new AVLNode(val);
			return subtree;
		}
		// if the subtree's value is larger than the value to insert we go left
		if (subtree.val > val) {
			subtree.left = insert(subtree.left, val);
			// Reset the height after inserting
			subtree.height = Math.max(getHeight(subtree.left), getHeight(subtree.right)) + 1;
			// send the subtree to be rebalanced if needed
			subtree = rebalance(subtree);
		}
		// if subtree's value is smaller than value to insert, go right
		else if (subtree.val < val) {
			subtree.right = insert(subtree.right, val);
			// reset height
			subtree.height = Math.max(getHeight(subtree.left), getHeight(subtree.right)) + 1;
			// rebalance as needed
			subtree = rebalance(subtree);
		} else
			// else, the subtree we're on is equal to the value.
			// we throw an illegal argument exception and return the node.
			throw new IllegalArgumentException();
		return subtree;
	}

	/*
	 * delete(int)
	 *
	 * Deletes a key from the tree; throws IllegalArgumentException if the key
	 * does not exist.
	 *
	 * Uses the x=change(x) style to modify the tree. Uses a static, recursive
	 * helper method to implement the modification. The helper method is
	 * responsible for *all* of the changes associated with deleting the value,
	 * including all rebalances.
	 *
	 * static delete(AVLNode,int)
	 *
	 * Static, recursive helper method for delete(int). Takes an AVLNode
	 * parameter, which might be null; if the parameter is null, then we are
	 * attempting to delete from an empty tree, and thus the delete fails. On
	 * the other hand, this method can *return* null quite normally; this
	 * happens when we delete the last node from the tree.
	 *
	 * Always returns the root of the new tree (which might be null); does all
	 * of the rebalancing work associated with the delete. (But see note in the
	 * spec about possibly skipping rebalance-on-delete.)
	 */
	public void delete(int val) throws IllegalArgumentException {
		// Use helper method for deletion
		root = delete(val, root);
	}

	// Parameters: el == element to insert into the tree
	// curr == the current AVLNode we're on
	// Purpose: Recurses through tree looking for node containing "el" to delete
	// Return value: The modified version of subtrees after deletion.
	// METHOD WILL THROW EXCEPTION IF WE CAN'T FIND NODE CONTAINING "el"
	private static AVLNode delete(int el, AVLNode curr) throws IllegalArgumentException {
		// If curr is null, we didn't find element.
		if (curr == null) {
			throw new IllegalArgumentException("ERROR: Could not delete " + el + " because it is already in the tree.");
		}
		// If el is less than curr.data go to the left.
		if (el < curr.val) {
			// left subtree = curr.left after deletion of el
			curr.left = delete(el, curr.left);
			// reset the height of curr node
			curr.height = Math.max(getHeight(curr.left), getHeight(curr.right)) + 1;
			// check to see if we need to rebalance
			curr = rebalance(curr);
			return curr;
		}
		// if el is greater than curr.data, go to the right.
		if (el > curr.val) {
			// right subtree = curr.left after deletion of el
			curr.right = delete(el, curr.right);
			// reset the height of current node
			curr.height = Math.max(getHeight(curr.left), getHeight(curr.right)) + 1;
			// rebalance the subtree if necessary
			curr = rebalance(curr);
			return curr;
		} else {
			// If we get here, we found the node containing the element.

			// if left is null, we set the current node equal to its right
			// child.
			if (curr.left == null) {
				AVLNode temp = curr.right;
				curr.right = null;
				curr = temp;
				return curr;
			}
			// If right is null, we set current node equal to its left child
			else if (curr.right == null) {
				AVLNode temp = curr.left;
				curr.left = null;
				curr = temp;
				return curr;
			}
			// if we reach here, node has 2 children. Requires a more
			// complicated process.
			else {
				// create a temp node, this will be used to traverse
				// The node's left child's tree to find the node with the next
				// lowest value
				AVLNode temp = curr.left;
				// traverse right until we reach the leaf.
				// This will be the node whose data is next in order to curr's
				while (temp.right != null) {
					temp = temp.right;
				}
				// set curr's data equal to the temp's data.
				curr.val = temp.val;
				// now remove the node who we got the data from
				curr.left = delete(curr.val, curr.left);
				// reset the height
				curr.height = Math.max(getHeight(curr.left), getHeight(curr.right)) + 1;
				// check if we need to rebalance
				curr = rebalance(curr);

				// return curr as usual.
				return curr;
			}
		}

	}

	/*
	 * static rebalance(AVLNode)
	 *
	 * Performs a rebalance (if required) on the current node. This method
	 * assumes that the parameter is non-null; if it is passed a null parameter,
	 * it will hit NullPointerException. However, it is valid for either (or
	 * both) of the children of this node to be null; to be clear, it is OK to
	 * call this on a leaf node.
	 *
	 * This method assumes that the height is correct, in the current node and
	 * all its descendants; thus, after an insert or delete, the *CALLER* must
	 * update the height before calling this method.
	 *
	 * This method returns the root of the new tree. If no rotations were
	 * required, then this is simply the parameter; if rotations were required,
	 * then this returns the root after the rotation.
	 *
	 * This method is NON-RECURSIVE. It runs in O(1) time. As such, it only
	 * checks for imbalances at THIS PARTICULAR NODE, never at any descendants.
	 */
	// Parameter: Node to check if needs rebalancing
	// Purpose: Rebalances the node if necessary and returns the rebalances
	// tree.
	// Return value: The exact same node if no rebalancing was necessary
	// else we return the rebalanced subtree.
	public static AVLNode rebalance(AVLNode node) {
		// If the difference in height between the node's children is greater
		// than one
		// we begin searching for the proper rotation to rebalance the subtree
		if (Math.abs(getHeight(node.left) - getHeight(node.right)) > 1) {
			// if the left child is the "taller" child we know the rotaion
			// occurs there
			if (getHeight(node.left) >= getHeight(node.right)) {
				// if the leftleft granchild is tallest
				if (getHeight(node.left.left) >= getHeight(node.left.right)) {
					// we rotate right at root of subtree
					return rotateRight(node);
				} else {
					// else we perform a double rotation
					node.left = rotateLeft(node.left);
					return rotateRight(node);
				}
			}
			// else the right child is taller
			else {
				// if rightright grandchild is taller, perform a left rotation
				// of root of subtree
				if (getHeight(node.right.right) >= getHeight(node.right.left)) {
					return rotateLeft(node);
				} else {
					// else we perform a double rotation
					node.right = rotateRight(node.right);
					return rotateLeft(node);
				}
			}
		}
		// else Node's children differ in height by one or less
		// and we didn't need to modify
		else
			return node;
	}

	/*
	 * static getHeight(AVLNode)
	 *
	 * Helper method that returns the height of a subtree. The parameter may be
	 * null.
	 *
	 * Since a single node has height=0, this should return -1 if passed a null
	 * parameter.
	 *
	 * This method is NON-RECURSIVE. It runs in O(1) time. As such, it may only
	 * check the height field in the current node; it does not look at any other
	 * nodes.
	 */
	public static int getHeight(AVLNode subtree) {
		// Through our defintion of height, a node with no children has height 0
		// so a null node would have height of -1
		if (subtree == null) {
			return -1;
		} else {
			return subtree.height;
		}
	}

	/*
	 * static rotateRight(AVLNode)
	 *
	 * Performs a right-rotation at the current node. This method assumes that
	 * both the parameter, and its left child, are non-null; if either is null,
	 * then a NullPointerException will result.
	 *
	 * Returns the root of the new tree, which is always the node which (used to
	 * be) the left child of the old root.
	 *
	 * This method works by changing REFERENCES NOT VALUES. That is, it does not
	 * inspect (or change) the .val field of any node; instead, it changes the
	 * left and right pointers.
	 *
	 * This method updates the height of all appropriate nodes after rearranging
	 * the references.
	 *
	 * This method is NON-RECURSIVE. It runs in O(1) time. As such, it may only
	 * check the fields in a fixed number of nodes.
	 */
	public static AVLNode rotateRight(AVLNode subtree) {
		// Create two placeholder variables
		// One for the left subtree of the AVLNode
		AVLNode temp = subtree.left;
		// One for the right node of the other placeholder.
		AVLNode temp2 = temp.right;
		// Set the left child's right node to be the subtree
		temp.right = subtree;
		// Set the subtree's left child to be the 2nd placeholder
		subtree.left = temp2;
		// reset the height of our nodes
		subtree.height = Math.max(getHeight(subtree.right), getHeight(subtree.left)) + 1;
		temp.height = Math.max(getHeight(temp.left), getHeight(temp.right)) + 1;
		// Return the new rotated tree.
		return temp;

	}

	/*
	 * static rotateLeft(AVLNode)
	 *
	 * See the documentation for rotateRight() above. This is simply its mirror
	 * image.
	 */
	public static AVLNode rotateLeft(AVLNode subtree) {
		// Create two placeholder nodes
		// One for the right child of the subtree
		AVLNode temp = subtree.right;
		// One for the left child of our temp node
		AVLNode temp2 = temp.left;
		// Set temp's left child to be the subtree in the parameter
		temp.left = subtree;
		// Set the right child of the subtree to be the old left child of the
		// temp node.
		subtree.right = temp2;
		// Reset the heights
		subtree.height = Math.max(getHeight(subtree.right), getHeight(subtree.left)) + 1;
		temp.height = Math.max(getHeight(temp.left), getHeight(temp.right)) + 1;
		// Return the new top node
		return temp;
	}
}
