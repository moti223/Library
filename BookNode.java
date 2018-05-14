// a class that creates a node if a node list (books that the customer borrowed).
public class BookNode {
	private Book _bk = new Book();
	private BookNode _prev;
	private BookNode _next;

	// constructor that builds a new node.
	public BookNode(String code) {
		_bk.setCODE(code);
		_prev = null;
		_next = null;
	}

	// constuctor that builds an empty node.
	public BookNode() {
		_bk = null;
		_prev = null;
		_next = null;
	}

	public Book getBook() {
		return _bk;
	}

	public BookNode getNext() {
		return _next;
	}

	public BookNode getPrev() {
		return _prev;
	}

	public void setNext(BookNode next) {
		_next = next;
	}

	public void setPrev(BookNode prev) {
		_prev = prev;
	}
}
