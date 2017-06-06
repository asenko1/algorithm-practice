package algorithms;

import java.util.Random;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BinarySearchTree {
	private Node root;
	
	//Constructors: w/ or w/o starting data
	public BinarySearchTree(int startData){
		root =  new Node(startData);
	}
	public BinarySearchTree(){
		root = null;
	}
	
	//populate the BST with random data from 0 to range
	public void popRandData(int size, int range){
		 Random randInt = new Random();
		 ArrayList<Integer> intArr = new ArrayList<Integer>(size);
		 for(int i = 0; i < size; i++){
			 intArr.add(randInt.nextInt(range));
		 }
		 for(int i : intArr){
			add(i); 
		 }
		 
	}
	
	//add new data to the BST
	public boolean add(int newInt){
		if(root == null){ //needed if BST wasn't initialized with starting data
			root = new Node(newInt);
			return true;
		}
		return add(newInt, root); //start recursion to add data
	}
	//Using recursion to add data
	private boolean add(int newInt, Node root){
		if(root.data > newInt){
			if(root.left == null){
				root.left = new Node(newInt);
				return true;
			}
			add(newInt, root.left);
		}
		else if(root.data <= newInt){
			if(root.right == null){
				root.right = new Node(newInt);
				return true;
			}
			add(newInt, root.right);
		}
		
		return false;
	}
	
	public void BFS(PrintStream out){
		Queue<Node> lvlOrder = new ArrayDeque<Node>();
		lvlOrder.add(root);
		Node node;
		while(!lvlOrder.isEmpty()){
			node = lvlOrder.peek();
			if(node.left != null)
				lvlOrder.add(node.left);
			if(node.right != null)
				lvlOrder.add(node.right);
			out.println(lvlOrder.remove() + " ");
		}
		
	}
}



class Node{
	public int data;
	public Node left;
	public Node right;
	
	public Node(int newData){
		data = newData;
		left = null;
		right = null;
	}
	
	@Override
	public String toString(){
		return Integer.toString(data);
	}

}
