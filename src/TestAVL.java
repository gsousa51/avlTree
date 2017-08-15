import static org.junit.Assert.*;

import org.junit.Test;

public class TestAVL {

	@Test
	public void testAVLStuff(){
		AVLTree tree = new AVLTree();
		tree.insert(8);
		tree.insert(5);
		tree.insert(11);
		tree.insert(10);
		tree.insert(3);
		tree.insert(12);
		tree.insert(7);
		tree.insert(9);
		tree.insert(2);
		tree.insert(6);
		tree.insert(4);
		tree.insert(1);
		tree.debug("AVL.dot");
		tree.delete(12);
		tree.debug("AVL2.dot");
		tree.delete(8);
		tree.debug("AVL3.dot");
	}
	
	@Test
	public void testAVL2(){
		AVLTree tree = new AVLTree();
		tree.insert(20);
		tree.insert(10);
		tree.insert(50);
		tree.insert(0);
		tree.insert(30);
		tree.insert(15);
		tree.insert(25);
		tree.insert(60);
		tree.insert(5);
		tree.insert(-5);
		tree.insert(19);
		tree.insert(3);
		tree.debug("AVL21.dot");
		tree.delete(20);
		tree.debug("AVL22.dot");
		tree.delete(30);
		tree.debug("AVL23.dot");
		tree.delete(60);
		tree.delete(25);
		tree.debug("AVL24.dot");
	}
	
	@Test
	public void testAVL3(){
		AVLTree tree = new AVLTree();
		tree.insert(12);
		tree.insert(5);
		tree.insert(18);
		tree.insert(16);
		tree.insert(3);
		tree.insert(19);
		tree.insert(7);
		tree.insert(6);
		tree.insert(20);
		tree.insert(4);
		tree.insert(8);
		tree.insert(10);
		tree.debug("AVL31.dot");
		tree.delete(12);
		tree.debug("AVL32.dot");
	}
	
	@Test
	public void testAVL4(){
		AVLTree tree = new AVLTree();
		tree.insert(50);
		tree.insert(30);
		tree.insert(70);
		tree.insert(60);
		tree.insert(90);
		tree.debug("AVL41.dot");
		tree.insert(65);
		tree.debug("AVL42.dot");
		tree.insert(63);
		tree.insert(80);
		tree.insert(100);
		tree.insert(85);
		tree.debug("AVL43.dot");
		tree.insert(87);
		tree.debug("AVL44.dot");
		tree.delete(100);
		tree.debug("AVL45.dot");
		tree.delete(63);
		tree.debug("AVL46.dot");
		tree.delete(70);
		tree.debug("AVL47.dot");
		tree.delete(60);
		tree.delete(30);
		tree.debug("AVL48.dot");
		
	}
	
	
	@Test
	public void testAVL5(){
		AVLTree tree = new AVLTree();
		tree.insert(50);
		tree.insert(40);
		tree.insert(80);
		tree.insert(30);
		tree.insert(70);
		tree.insert(100);
		tree.insert(105);
		tree.debug("AVL51.dot");
		tree.delete(50);
		tree.debug("AVL52.dot");
		
	}
	
	@Test 
	public void testAVL6(){
		AVLTree tree = new AVLTree();
		tree.insert(50);
		tree.insert(30);
		tree.insert(70);
		tree.insert(20);
		tree.insert(40);
		tree.insert(60);
		tree.insert(100);
		tree.insert(55);
		tree.insert(10);
		tree.insert(90);
		tree.insert(120);
		tree.insert(110);
		tree.debug("AVL61.dot");
		tree.delete(40);
		tree.debug("AVL62.dot");
	}
	@Test
	public void testAVL7(){
		AVLTree tree = new AVLTree();
		tree.modThis(62);
		tree.modThis(6);
		tree.modThis(16);
		tree.modThis(39);
		tree.modThis(11);
		tree.modThis(12);
		tree.modThis(88);
		tree.modThis(20);
		tree.modThis(86);
		tree.modThis(13);
		tree.modThis(2);
		/*
		 * eys 62, 6, 16, 39, 11, 12, 88, 20, 86, 13, and 2
		 */
		
		
	}
//		tree.insert(100);
//		tree.insert(50);
//		tree.insert(150);
//		tree.insert(25);
//		tree.insert(35);
//		tree.insert(175);
//		tree.insert(135);
//		tree.insert(140);
//		tree.insert(180);
//		tree.insert(0);
//		tree.insert(20);
//		tree.insert(38);
//		tree.insert(75);
//		assertTrue(tree.checkHeight());
//		AVLNode root = tree.getRoot();
//		tree.setHeight(tree.getRoot().left, 0);
//		assertFalse(tree.checkHeight());
//		tree.print_InOrderRange(0, 175);
//		tree.print_InOrderRange(99,101);
//		tree.print_InOrderRange(100,175);
//		tree.print_InOrderRange(60,80);
//		tree.print_InOrderRange(50,50);
//		tree.print_InOrderRange(-100, -1);
//	}
	
	

}
