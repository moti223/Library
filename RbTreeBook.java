// a class that creates a RB tree that is sorted by the books codes.
public class RbTreeBook {

	public RedBlackBook _root;
	public static RedBlackBook nullNode;
	public boolean isEmpty = true;
	// static initializer for nullNode
	static {
		nullNode = new RedBlackBook(null, null, null, null, null);
		nullNode.setLeft(nullNode);
		nullNode.setRight(nullNode);
	}

	// creates a new tree with no information.
	public RbTreeBook() {

		_root = nullNode;
	}

	// standard insert with the needed adjustments to comply with the
	// RedBlackBook.
	public void insert(RedBlackBook book) {
		if (isEmpty)
			isEmpty = false;
		if (this.search(book.getBook().getCODE()) == null) {
			RedBlackBook z = new RedBlackBook(book);
			RedBlackBook y = nullNode;
			RedBlackBook x = _root;
			while (x != nullNode) {
				y = x;
				if (z.getBook().getCODE().compareTo(x.getBook().getCODE()) < 0)
					x = x.getLeft();
				else
					x = x.getRight();
			}
			z.setParent(y);
			if (y == nullNode)
				_root = z;

			else if (z.getBook().getCODE().compareTo(y.getBook().getCODE()) < 0)
				y.setLeft(z);
			else
				y.setRight(z);
			z.setLeft(nullNode);
			z.setRight(nullNode);
			z.setColor(RedBlackBook.Color.RED);
			insertFixup(z);
		}
	}

	// standard fix-up after insert.
	private void insertFixup(RedBlackBook z) {
		while (z.getParent().getColor() == RedBlackBook.Color.RED) {

			if (z.getParent() == z.getGrandparent().getLeft()) {
				RedBlackBook y = z.getGrandparent().getRight();
				if (y.getColor() == RedBlackBook.Color.RED) {
					z.getParent().setColor(RedBlackBook.Color.BLACK);
					y.setColor(RedBlackBook.Color.BLACK);
					z = z.getGrandparent();
					z.setColor(RedBlackBook.Color.RED);
				} else {
					if (z == z.getParent().getRight()) {
						z = z.getParent();
						leftRotate(z);
					}
					z.getParent().setColor(RedBlackBook.Color.BLACK);
					z.getGrandparent().setColor(RedBlackBook.Color.RED);
					rightRotate(z.getGrandparent());
				}
			} else

			if (z.getParent() == z.getGrandparent().getRight()) {
				RedBlackBook y = z.getGrandparent().getLeft();
				if (y.getColor() == RedBlackBook.Color.RED) {
					z.getParent().setColor(RedBlackBook.Color.BLACK);
					y.setColor(RedBlackBook.Color.BLACK);
					z = z.getGrandparent();
					z.setColor(RedBlackBook.Color.RED);
				} else {
					if (z == z.getParent().getLeft()) {
						z = z.getParent();
						rightRotate(z);
					}
					z.getParent().setColor(RedBlackBook.Color.BLACK);
					z.getGrandparent().setColor(RedBlackBook.Color.RED);
					leftRotate(z.getGrandparent());
				}
			}
		}
		_root.setColor(RedBlackBook.Color.BLACK);
	}

	// standard left rotate.
	private void leftRotate(RedBlackBook redBlackBook) {
		RedBlackBook y = redBlackBook.getRight();
		redBlackBook.setRight(y.getLeft());
		if (y.getLeft() != nullNode)
			y.getLeft().setParent(redBlackBook);
		y.setParent(redBlackBook.getParent());
		if (redBlackBook.getParent() == nullNode)
			_root = y;
		else {
			if (redBlackBook == redBlackBook.getParent().getLeft())
				redBlackBook.getParent().setLeft(y);
			else
				redBlackBook.getParent().setRight(y);
		}
		y.setLeft(redBlackBook);
		redBlackBook.setParent(y);
	}

	// standard right rotate.
	private void rightRotate(RedBlackBook redBlackBook) {
		RedBlackBook y = redBlackBook.getLeft();
		redBlackBook.setLeft(y.getRight());
		if (y.getRight() != nullNode)
			y.getRight().setParent(redBlackBook);
		y.setParent(redBlackBook.getParent());
		if (redBlackBook.getParent() == nullNode)
			_root = y;
		else {
			if (redBlackBook == redBlackBook.getParent().getLeft())
				redBlackBook.getParent().setLeft(y);
			else
				redBlackBook.getParent().setRight(y);
		}
		y.setRight(redBlackBook);
		redBlackBook.setParent(y);
	}

	// standard delete with the needed adjustments to comply with the
	// RedBlackBook, based in the books code.
	public void delete(String key) {
		RedBlackBook z = search(key);
		if (z != null) {
			RedBlackBook y, x;
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
				z.getBook().setCODE(y.getBook().getCODE());
			if (y.getColor() == RedBlackBook.Color.BLACK)
				deleteFixup(x);

		}
	}

	// standard fix-up after delete.
	private void deleteFixup(RedBlackBook x) {
		while (x != _root && x.getColor() == RedBlackBook.Color.BLACK) {
			if (x == x.getParent().getLeft()) {
				RedBlackBook w = x.getParent().getRight();
				if (w.getColor() == RedBlackBook.Color.RED) {
					w.setColor(RedBlackBook.Color.BLACK);
					x.getParent().setColor(RedBlackBook.Color.RED);
					leftRotate(x.getParent());
				}
				if (w.getLeft().getColor() == RedBlackBook.Color.BLACK
						&& w.getRight().getColor() == RedBlackBook.Color.BLACK) {

					w.setColor(RedBlackBook.Color.RED);
					x = x.getParent();
				} else {
					if (w.getRight().getColor() == RedBlackBook.Color.BLACK) {
						w.getLeft().setColor(RedBlackBook.Color.BLACK);
						w.setColor(RedBlackBook.Color.RED);
						rightRotate(w);
						w = x.getParent().getRight();
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(RedBlackBook.Color.BLACK);
					w.getRight().setColor(RedBlackBook.Color.BLACK);
					leftRotate(x.getParent());
					x = _root;
				}

			} else {
				RedBlackBook w = x.getParent().getLeft();
				if (w.getColor() == RedBlackBook.Color.RED) {
					w.setColor(RedBlackBook.Color.BLACK);
					x.getParent().setColor(RedBlackBook.Color.RED);
					rightRotate(x.getParent());
				}
				if (w.getRight().getColor() == RedBlackBook.Color.BLACK
						&& w.getLeft().getColor() == RedBlackBook.Color.BLACK) {

					w.setColor(RedBlackBook.Color.RED);
					x = x.getParent();
				} else {
					if (w.getLeft().getColor() == RedBlackBook.Color.BLACK) {
						w.getRight().setColor(RedBlackBook.Color.BLACK);
						w.setColor(RedBlackBook.Color.RED);
						leftRotate(w);
						w = x.getParent().getLeft();
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(RedBlackBook.Color.BLACK);
					w.getLeft().setColor(RedBlackBook.Color.BLACK);
					rightRotate(x.getParent());
					x = _root;
				}
			}
		}
		x.setColor(RedBlackBook.Color.BLACK);
	}

	// finds the trees minimum.
	public RedBlackBook treeMinimum(RedBlackBook redBlackBook) {
		while (redBlackBook != nullNode && redBlackBook.getLeft() != nullNode)
			redBlackBook = redBlackBook.getLeft();
		return redBlackBook;
	}

	// searches the tree for the books code given.
	public RedBlackBook search(String key) {
		if (_root == nullNode)
			return null;

		return search(key, _root);

	}

	// standard search for a book code.
	private RedBlackBook search(String code, RedBlackBook node) {
		if (code.equals(node.getBook().getCODE()))
			return node;

		if (code.compareTo(node.getBook().getCODE()) < 0) {
			if (node.getLeft() == nullNode)
				return null;
			return search(code, node.getLeft());
		}

		if (code.compareTo(node.getBook().getCODE()) > 0) {
			if (node.getRight() == nullNode)
				return null;
			return search(code, node.getRight());
		}

		return null;
	}

	// finds the successor of the node.
	public RedBlackBook treeSuccessor(RedBlackBook x) {
		if (x.getRight() != nullNode)
			return treeMinimum(x.getRight());
		RedBlackBook y = x.getParent();
		while (y != nullNode && x == y.getRight()) {
			x = y;
			y = x.getParent();
		}
		return y;
	}

}
