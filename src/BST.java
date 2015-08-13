public class BST<T extends Comparable<T>> {

	private Node<T> root;

	public BST() {
		root = null;
	}

	private int compare(T x, T y) {
		return x.compareTo(y);
	}

	private class Node<T> {
		private T data;
		private Node<T> left, right;

		public Node(T data) {
			this(data, null, null);
		}

		public Node(T data, Node<T> left, Node<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return data.toString();
		}

	}

	public void insert(T data) {
		root = insert(data, root);
	}

	private Node<T> insert(T data, Node<T> currentPointer) {

		if (currentPointer == null) {
			return new Node<T>(data);
		}

		if (compare(data, currentPointer.data) == 0) {
			return currentPointer;
		}

		if (compare(data, currentPointer.data) < 0) {
			currentPointer.left = insert(data, currentPointer.left);
		} else {
			currentPointer.right = insert(data, currentPointer.right);
		}

		return currentPointer;
	}

	public void preOrderTraversal() {
		preOrderHelper(root);
	}

	private void preOrderHelper(Node<T> currentPointer) {
		if (currentPointer != null) {
			System.out.print(currentPointer + " ");
			preOrderHelper(currentPointer.left);
			preOrderHelper(currentPointer.right);
		}
	}

	/*****************************************************
	 *
	 * MIN MAX
	 *
	 ******************************************************/
	public T getMinimumValue() {
		return getMinimumHelper(root);
	}

	private T getMinimumHelper(Node<T> currentPointer) {
		while (currentPointer.left != null) {
			currentPointer = currentPointer.left;
		}
		return currentPointer.data;
	}

	public T getMaximumValue() {
		return getMaximumHelper(root);
	}

	private T getMaximumHelper(Node<T> currentPointer) {
		while (currentPointer.right != null) {
			currentPointer = currentPointer.right;
		}
		return currentPointer.data;
	}

	/*****************************************************
	 *
	 * TOTAL NUMBER OF LEAF NODES
	 *
	 ******************************************************/

	public int getTotalNumberOfLeaf() {
		return getTotalNumberofLeafHelper(root);
	}

	private int getTotalNumberofLeafHelper(Node<T> currentPointer) {
		if (currentPointer == null)
			return 0;
		if (currentPointer.left == null && currentPointer.right == null)
			return 1;
		else
			return getTotalNumberofLeafHelper(currentPointer.left)
					+ getTotalNumberofLeafHelper(currentPointer.right);
	}

	/*****************************************************
	 *
	 * TOTAL NUMBER OF NODES
	 *
	 ******************************************************/

	public int totalNumberOfNodes() {
		return totalNumberOfNodesHelper(root);
	}

	private int totalNumberOfNodesHelper(Node<T> currentPointer) {
		if (currentPointer == null)
			return 0;
		else
			return 1 + totalNumberOfNodesHelper(currentPointer.left)
					+ totalNumberOfNodesHelper(currentPointer.right);
	}

	/*****************************************************
	 *
	 * MAX DEPTH OF A NODE
	 *
	 ******************************************************/
	public int maxDepthOfNode() {
		return maxDepthOfNodeHelper(root);
	}

	private int maxDepthOfNodeHelper(Node<T> currentPointer) {
		if (currentPointer == null) {
			return 0;
		} else {

			int leftDepth = maxDepthOfNodeHelper(currentPointer.left);
			int rightDepth = maxDepthOfNodeHelper(currentPointer.right);

			return maxDepth(leftDepth, rightDepth);
		}
	}

	private int maxDepth(int a, int b) {
		if (a > b)
			return (a + 1);
		else
			return (b + 1);
	}

	/*****************************************************
	 *
	 * MIN DEPTH OF A NODE
	 *
	 ******************************************************/

	public int minDepthOfNode() {
		return minDepthOfNodeHelper(root);
	}

	private int minDepthOfNodeHelper(Node<T> currentPointer) {
		if (currentPointer == null) {
			return 0;
		}
		if (currentPointer.left == null && currentPointer.right == null) {
			return 1;
		}
		if (currentPointer.left != null && currentPointer.right == null) {
			return minDepthOfNodeHelper(currentPointer.left) + 1;
		}
		if (currentPointer.left == null && currentPointer.right != null) {
			return minDepthOfNodeHelper(currentPointer.right) + 1;
		}

		return Math.min(minDepthOfNodeHelper(currentPointer.left),
				minDepthOfNodeHelper(currentPointer.right)) + 1;
	}

	public static void main(String[] args) {
		Integer[] a1 = { 7, 1, 0, 3, 2, 5, 4, 6, 9, 8, 10 };
		BST<Integer> bst1 = new BST<Integer>();
		for (Integer n : a1)
			bst1.insert(n);

		System.out.println(bst1.getMinimumValue());
		System.out.println(bst1.getMaximumValue());

		System.out.println(bst1.getTotalNumberOfLeaf());
		System.out.println(bst1.totalNumberOfNodes());

		System.out.println(bst1.minDepthOfNode());

	}
}