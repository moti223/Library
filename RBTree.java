// a class that creates a RB tree that is sorted by ID.
public class RBTree {
	private RedBlackNode _root;
	private static RedBlackNode nullNode;
	// static initializer for nullNode
	static {
		nullNode = new RedBlackNode(null, null, null, null);
		nullNode.setLeft(nullNode);
		nullNode.setRight(nullNode);
	}

	// constractur creates a new tree with no information.
	public RBTree() {
		_root = nullNode;
	}

	//  constractur creates a new tree with 1 customer already.
	public RBTree(Customer cus) {
		_root = new RedBlackNode(cus);
		_root.setLeft(nullNode);
		_root.setRight(nullNode);
	}

	// standard insert with the needed adjustments to comply with the
	// RedBlackNode.
	public void insert(String name, int id) {
		RedBlackNode z = new RedBlackNode(name, id);
		RedBlackNode y = nullNode;
		RedBlackNode x = _root;
		while (x != nullNode) {
			y = x;
			if (z.getCustomer().getID() < x.getCustomer().getID())
				x = x.getLeft();
			else
				x = x.getRight();
		}
		z.setParent(y);
		if (y == nullNode)
			_root = z;

		else if (z.getCustomer().getID() < y.getCustomer().getID())
			y.setLeft(z);
		else
			y.setRight(z);
		z.setLeft(nullNode);
		z.setRight(nullNode);
		z.setColor(RedBlackNode.Color.RED);
		insertFixup(z);
		System.out.println("new customer " + z.getCustomer().getName() + " was added to the library.");
	}

	// standard fix-up after insert.
	private void insertFixup(RedBlackNode z) {
		while (z.getParent().getColor() == RedBlackNode.Color.RED) {

			if (z.getParent() == z.getGrandparent().getLeft()) {
				RedBlackNode y = z.getGrandparent().getRight();
				if (y.getColor() == RedBlackNode.Color.RED) {
					z.getParent().setColor(RedBlackNode.Color.BLACK);
					y.setColor(RedBlackNode.Color.BLACK);
					z = z.getGrandparent();
					z.setColor(RedBlackNode.Color.RED);
				} else {
					if (z == z.getParent().getRight()) {
						z = z.getParent();
						leftRotate(z);
					}
					z.getParent().setColor(RedBlackNode.Color.BLACK);
					z.getGrandparent().setColor(RedBlackNode.Color.RED);
					rightRotate(z.getGrandparent());
				}
			} else

			if (z.getParent() == z.getGrandparent().getRight()) {
				RedBlackNode y = z.getGrandparent().getLeft();
				if (y.getColor() == RedBlackNode.Color.RED) {
					z.getParent().setColor(RedBlackNode.Color.BLACK);
					y.setColor(RedBlackNode.Color.BLACK);
					z = z.getGrandparent();
					z.setColor(RedBlackNode.Color.RED);
				} else {
					if (z == z.getParent().getLeft()) {
						z = z.getParent();
						rightRotate(z);
					}
					z.getParent().setColor(RedBlackNode.Color.BLACK);
					z.getGrandparent().setColor(RedBlackNode.Color.RED);
					leftRotate(z.getGrandparent());
				}
			}
		}
		_root.setColor(RedBlackNode.Color.BLACK);
	}

	// standard left rotate.
	private void leftRotate(RedBlackNode x) {
		RedBlackNode y = x.getRight();
		x.setRight(y.getLeft());
		if (y.getLeft() != nullNode)
			y.getLeft().setParent(x);
		y.setParent(x.getParent());
		if (x.getParent() == nullNode)
			_root = y;
		else {
			if (x == x.getParent().getLeft())
				x.getParent().setLeft(y);
			else
				x.getParent().setRight(y);
		}
		y.setLeft(x);
		x.setParent(y);
	}

	// standard right rotate.
	private void rightRotate(RedBlackNode x) {
		RedBlackNode y = x.getLeft();
		x.setLeft(y.getRight());
		if (y.getRight() != nullNode)
			y.getRight().setParent(x);
		y.setParent(x.getParent());
		if (x.getParent() == nullNode)
			_root = y;
		else {
			if (x == x.getParent().getLeft())
				x.getParent().setLeft(y);
			else
				x.getParent().setRight(y);
		}
		y.setRight(x);
		x.setParent(y);
	}

	// searches the tree for the id given.
	public RedBlackNode search(int id) {
		if (_root == nullNode)
			return null;

		return search(id, _root);

	}

	// standard search for an id.
	private RedBlackNode search(int id, RedBlackNode node) {
		if (id == node.getCustomer().getID())
			return node;

		if (id < node.getCustomer().getID()) {
			if (node.getLeft() == nullNode)
				return null;
			return search(id, node.getLeft());
		}

		if (id > node.getCustomer().getID()) {
			if (node.getRight() == nullNode)
				return null;
			return search(id, node.getRight());
		}

		return null;
	}

	// standard delete with the needed adjustments to comply with the
	// RedBlackNode, based on the customers ID.
	public void delete(int id) {
		RedBlackNode z = search(id);
		if (z != null) {
			RedBlackNode y, x;
			if (z.getLeft() == nullNode || z.getRight() == nullNode)
				y = z;
			else
				y = treeSuccessor(z);
			if (y.getLeft() != nullNode)
				x = y.getLeft();
			else

				x = y.getRight();
			x.setParent(y.getParent());
			if (y.getParent() == nullNode)
				_root = x;
			else if (y == y.getParent().getLeft())
				y.getParent().setLeft(x);
			else
				y.getParent().setRight(x);

			if (y != z)
				z.getCustomer().setID(y.getCustomer().getID());
			if (y.getColor() == RedBlackNode.Color.BLACK)
				deleteFixup(x);

		}
	}

	// standard fix-up after delete.
	private void deleteFixup(RedBlackNode x) {
		while (x != _root && x.getColor() == RedBlackNode.Color.BLACK) {
			if (x == x.getParent().getLeft()) {
				RedBlackNode w = x.getParent().getRight();
				if (w.getColor() == RedBlackNode.Color.RED) {
					w.setColor(RedBlackNode.Color.BLACK);
					x.getParent().setColor(RedBlackNode.Color.RED);
					leftRotate(x.getParent());
				}
				if (w.getLeft().getColor() == RedBlackNode.Color.BLACK
						&& w.getRight().getColor() == RedBlackNode.Color.BLACK) {

					w.setColor(RedBlackNode.Color.RED);
					x = x.getParent();
				} else {
					if (w.getRight().getColor() == RedBlackNode.Color.BLACK) {
						w.getLeft().setColor(RedBlackNode.Color.BLACK);
						w.setColor(RedBlackNode.Color.RED);
						rightRotate(w);
						w = x.getParent().getRight();
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(RedBlackNode.Color.BLACK);
					w.getRight().setColor(RedBlackNode.Color.BLACK);
					leftRotate(x.getParent());
					x = _root;
				}

			} else {
				RedBlackNode w = x.getParent().getLeft();
				if (w.getColor() == RedBlackNode.Color.RED) {
					w.setColor(RedBlackNode.Color.BLACK);
					x.getParent().setColor(RedBlackNode.Color.RED);
					rightRotate(x.getParent());
				}
				if (w.getRight().getColor() == RedBlackNode.Color.BLACK
						&& w.getLeft().getColor() == RedBlackNode.Color.BLACK) {

					w.setColor(RedBlackNode.Color.RED);
					x = x.getParent();
				} else {
					if (w.getLeft().getColor() == RedBlackNode.Color.BLACK) {
						w.getRight().setColor(RedBlackNode.Color.BLACK);
						w.setColor(RedBlackNode.Color.RED);
						leftRotate(w);
						w = x.getParent().getLeft();
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(RedBlackNode.Color.BLACK);
					w.getLeft().setColor(RedBlackNode.Color.BLACK);
					rightRotate(x.getParent());
					x = _root;
				}
			}
		}
		x.setColor(RedBlackNode.Color.BLACK);
	}

	// finds the successor of the node.
	public RedBlackNode treeSuccessor(RedBlackNode x) {
		if (x.getRight() != nullNode)
			return treeMinimum(x.getRight());
		RedBlackNode y = x.getParent();
		while (y != nullNode && x == y.getRight()) {
			x = y;
			y = x.getParent();
		}
		return y;
	}

	// finds the trees minimum.
	public RedBlackNode treeMinimum(RedBlackNode node) {
		while (node != nullNode && node.getLeft() != nullNode)
			node = node.getLeft();
		return node;
	}

}
