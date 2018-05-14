// a class that creates a node for the max heap.
public class BookHolders {
	private Customer _cus;
	private int _loc;

	// constructor that builds a new node for the max heap.
	public BookHolders(Customer cus) {
		_cus = cus;
	}

	public Customer getCustomer() {
		return _cus;
	}

	public int getLoc() {
		return _loc;
	}

	public void setLoc(int l) {
		_loc = l;
	}
}
