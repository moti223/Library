// a class that creates and object to represent the customer and all hes/hers information.
public class Customer {
	private String _name;
	private int _id;
	private BookNodeList _bookList;

	// constructor that creates a new node with new information.
	public Customer(String name, int id, BookNodeList bl) {
		_name = name;
		_id = id;
		_bookList = bl;
	}

	public String getName() {
		return _name;
	}

	public void setName(String n) {
		_name = n;
	}

	public int getID() {
		return _id;
	}

	public void setID(int id) {
		_id = id;
	}

	public BookNodeList getList() {
		return _bookList;
	}
}
