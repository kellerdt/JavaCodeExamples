package dustin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class DataStructures {

	public TreeNode buildTree() {
		TreeNode root = new TreeNode(0);
		TreeNode one = new TreeNode(1);
		TreeNode two = new TreeNode(2);
		TreeNode three = new TreeNode(3);
		TreeNode four = new TreeNode(4);
		TreeNode five = new TreeNode(5);
		TreeNode six = new TreeNode(6);
		TreeNode seven = new TreeNode(7);
		
		six.addChild(seven);
		four.addChild(six);
		four.addChild(five);
		one.addChild(two);
		one.addChild(four);
		one.addChild(three);
		root.addChild(one);
		return root;
	}
	
	public void depthFirstSearchWithStackPreVisit() {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode root = this.buildTree();
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode top = stack.pop();
			top.visit();
			top.output();
			if(top.children.size() > 0) {
				for(TreeNode child : top.children) {
					stack.push(child);
				}
			}
		}
	}
	
	public void depthFirstSearchWithStackPostVisit() {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode root = this.buildTree();
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode top = stack.peek();
			if(!top.visited && top.children.size() > 0) {
				for(TreeNode child : top.children) {
					stack.push(child);
				}
			} else {
				TreeNode leaf = stack.pop();
				leaf.output();
			}
			top.visit();
		}
	}
	
	public void breadthFirstSearchWithQueuePreVisit() {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode root = this.buildTree();
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode first = queue.poll();
			first.visit();
			first.output();
			if(first.children.size() > 0) {
				for(TreeNode child : first.children) {
					queue.offer(child);
				}
			}
		}
	}
	
	public void breadthFirstSearchWithQueuePostVisit() {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode root = this.buildTree();
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode first = queue.peek();
			if(!first.visited && first.children.size() > 0) {
				for(TreeNode child : first.children) {
					queue.offer(child);
				}
			} else {
				TreeNode leaf = queue.poll();
				leaf.output();
			}
			first.visit();
		}
	}
	
	private class TreeNode {
		private int id;
		private boolean visited = false;
		private List<TreeNode> children;
		public TreeNode(int id) {
			this.id = id;
			this.children = new ArrayList<TreeNode>();
		}
		public void addChild(TreeNode node) {
			this.children.add(node);
		}
		public void visit() {
			this.visited = true;
		}
		public void output() {
			System.out.println("Node id = " + this.id);
		}
	}
	
	public static void main(String[] args) {
		DataStructures data = new DataStructures();
		data.depthFirstSearchWithStackPreVisit();
		System.out.println("-----------------------");
		data.depthFirstSearchWithStackPostVisit();
		System.out.println("-----------------------");
		data.breadthFirstSearchWithQueuePreVisit();
		System.out.println("-----------------------");
		data.breadthFirstSearchWithQueuePostVisit();
	}
}
