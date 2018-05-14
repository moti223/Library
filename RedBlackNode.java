// an object that creates a node for the RB tree that is sorted by the customer's ID.
class RedBlackNode {
	private RedBlackNode _left, _right, _parent;
	private RedBlackBook _rbBook;
	private BookHolders _bH;
	private Customer _cus;
	private Color _color;
	private Book _bk;

	// constructor that builds a new node with pre-existing customer
	// information.
	public RedBlackNode(Customer cus) {
		this(cus, null, null, null);
	}

	// constructor that builds a new node with new customer information.
	public RedBlackNode(String name, int id) {
		_left = null;
		_right = null;
		_color = Color.BLACK;
		_parent = null;
		_cus = new Customer(name, id, new BookNodeList());
	}

	// constructor that build a new node with a pre-existing customer and all
	// its tree pointers.
	public RedBlackNode(Customer cus, RedBlackNode lt, RedBlackNode rt, RedBlackNode pr) {
		_left = lt;
		_right = rt;
		_cus = cus;
		_parent = pr;
		_color = Color.BLACK;
	}

	// constructor that build a new node with a new customer and all its tree
	// pointers.
	public RedBlackNode(String name, int id, BookNodeList bl, RedBlackNode lt, RedBlackNode rt) {
		Customer cus = new Customer(name, id, bl);
		_left = lt;
		_right = rt;
		_cus = cus;
		_color = Color.BLACK;
	}

	public RedBlackBook getRBBook() {
		return _rbBook;
	}

	public void setRBBook(RedBlackBook b) {
		_rbBook = b;
	}

	public Book getBook() {
		return _bk;
	}

	public void setBook(Book b) {
		_bk = b;
	}

	public RedBlackNode getLeft() {
		return _left;
	}

	public BookHolders getBookHolders() {
		return _bH;
	}

	public void setBookHolders(BookHolders b) {
		_bH = b;
	}

	public RedBlackNode getRight() {
		return _right;
	}

	public void setLeft(RedBlackNode l) {
		_left = l;
	}

	public RedBlackNode getParent() {
		return _parent;
	}

	public RedBlackNode getGrandparent() {
		if (getParent() != null && getParent().getParent() != null) {
			return getParent().getParent();
		}
		return null;
	}

	public void setParent(RedBlackNode p) {
		_parent = p;
	}

	public void setRight(RedBlackNode r) {
		_right = r;
	}

	public Customer getCustomer() {
		return _cus;
	}

	public Color getColor() {
		return _color;
	}

	public void setColor(Color color) {
		_color = color;
	}

	// creates an eNum data for the node color.
	public enum Color {
		BLACK, RED
	}
}