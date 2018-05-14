// a class that build an object for the RB tree that is sorted by books codes.
class RedBlackBook {
	private RedBlackBook _left, _right, _parent;
	private Book _b = new Book();
	private Customer _cus;
	private Color _color;

	// constructor that builds a new node with new information.
	public RedBlackBook(String book, Customer cus) {
		this(book, cus, null, null, null);
	}

	// copy constructor.
	public RedBlackBook(RedBlackBook r) {
		_left = r.getLeft();
		_right = r.getRight();
		_color = r.getColor();
		_parent = r.getParent();
		_cus = r.getCustomer();
		_b = r.getBook();
	}

	// constructor that builds a new node with new information and all the trees
	// needed pointers.
	public RedBlackBook(String book, Customer cus, RedBlackBook lt, RedBlackBook rt, RedBlackBook pr) {
		_left = lt;
		_right = rt;
		if (book == null)
			_b = null;
		else
			_b.setCODE(book);
		_parent = pr;
		_cus = cus;
		_color = Color.BLACK;
	}
	// Getter and setter
	public Customer getCustomer() {
		return _cus;
	}

	public void setCustomer(Customer c) {
		_cus = c;
	}

	public RedBlackBook getLeft() {
		return _left;
	}

	public RedBlackBook getRight() {
		return _right;
	}

	public void setLeft(RedBlackBook l) {
		_left = l;
	}

	public RedBlackBook getParent() {
		return _parent;
	}

	public RedBlackBook getGrandparent() {
		if (getParent() != null && getParent().getParent() != null) {
			return getParent().getParent();
		}
		return null;
	}

	public void setParent(RedBlackBook p) {
		_parent = p;
	}

	public void setRight(RedBlackBook r) {
		_right = r;
	}

	public Book getBook() {
		return _b;
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
