/*Author: Gary Sousa
 * Assignment: CS345 - Proj03
 * ClassName: Proj03_AVL.java
 * Description: Class holds a main method to create and modify 4 separate AVL Trees.
 * User gives program a file in command line that contains various instructions
 * to modify the AVL trees.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Proj03_AVL {
	
	public static void main(String argv[]) {
		//If file not given, throw an error and stop the program.
		if (argv.length == 0) {
			System.err.println("ERROR: NO FILE GIVEN AS ARGUMENT");
			System.err.println("ENDING PROGRAM");
			return;
		}
		// create array of size 4 to hold different Proj03_AVLs
		AVLTree[] trees = new AVLTree[4];
		// Initialize them all to be empty Proj03_AVLs
		for (int i = 0; i < 4; i++) {
			trees[i] = new AVLTree();
		}
		// variable used to hold which tree we will use a command on.
		int treeToProcess;
		// variable to hold the command we'll be using on a tree
		String command;
		// Scanner we will use to read the file
		Scanner scan;
		// File given from the command line
		File file = new File(argv[0]);
		try {
			// Try to create scanner that reads file in argument
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// If file doesn't exist, report error and stop program.
			System.err.println(argv[0] + " not found ENDING PROGRAM");
			e.printStackTrace();
			return;
		}

		// While we still have anything to process on the file, we keep going
		while (scan.hasNext()) {
			// First integer will be the tree we run a command on
			treeToProcess = scan.nextInt();
			// The next String will be the command we run on the tree
			command = scan.next();

			if (treeToProcess >= 0 && treeToProcess <= 3) {
				// Use a switch case statement on the command given
				switch (command) {
				// If insert, insert the argument given with command
				case "insert":
					// next int will be element to attempt to insert
					int elToInsert = scan.nextInt();
					// we try to insert and catch an IllegalArgumentException
					try {
						// Try to insert the element
						trees[treeToProcess].insert(elToInsert);
						// catch an exception and print an error
					} catch (IllegalArgumentException e) {
						System.err.println(
								"ERROR: Could not insert " + elToInsert + " because it is already in the tree.");
					}
					break;
				// If search, search for the argument given next
				case "search":
					// next element will be the element to search for
					int elToSearch = scan.nextInt();
					// if the returned node is null, we didn't find it.
					if (trees[treeToProcess].search(elToSearch) == null) {
						System.out.println(
								"MISS: The value " + elToSearch + " was *NOT* found in tree " + treeToProcess + ".");
					}
					// else we did.
					else {
						System.out
								.println("HIT: The value " + elToSearch + " was found in tree " + treeToProcess + ".");
					}
					break;

				// If debug, write a dot file to the filename given.
				case "debug":
					trees[treeToProcess].debug(scan.next());
					break;

				// If delete, delete the argument given.
				case "delete":
					// next integer will be the element to try and delete
					int elToDelete = scan.nextInt();
					// try to delete the element, but catch an
					// IllegalArgumentException
					try {
						trees[treeToProcess].delete(elToDelete);
					}
					// if we catch an exception, print out an error.
					catch (IllegalArgumentException e) {
						System.err.println("ERROR: Could not delete " + elToDelete + " because it was not in the tree.");
					}
					break;

				// If preOrder, print out trees[treeToProcess] in preOrder form
				case "preOrder":
					trees[treeToProcess].print_preOrder();
					break;

				// if inOrder, print out trees[treeToProcess] in inOrder form
				case "inOrder":
					trees[treeToProcess].print_inOrder();
					break;

				// Otherwise we didn't recognize the command.
				// Report an error.
				default:
					System.out.println("ERROR: Invalid testcase!  command='" + command + "'");
					return;
				}// end case switch
			} // end if
			else {
				System.err.println("ERROR: TESTCASE CONTAINS ERROR IN TREE SPECIFICATION. ENDING PROGRAM");
				break;
			}
		}
		// close the scanner. end the main.
		scan.close();

	}
}
