// a class that creates a list of book nodes to represent all the books the customer has borrowed.
public class BookNodeList {
	private BookNode _head;
	private BookNode _tail;
	public int _size;
	private RbTreeBook _tree;

	// constructor that creates an empty list.
	public BookNodeList() {
		_head = null;
		_tail = null;
		_size = 0;
	}

	public void setTree(RbTreeBook r) {
		_tree = r;
	}

	// adds a new book node to the end of the list (a new book that the customer has borrowed).
	public void add(String book) {
		BookNode _b = new BookNode(book);
		if (_head == null) {
			_head = _b;
			_tail = _b;
			_size = 1;
		} else {
			_tail.setNext(_b);
			_b.setPrev(_tail);
			_tail = _b;
			_size += 1;
		}
	}

	// deletes a book from the list (the customer returned the book to the
	// library).
	public void delete(String book) {
		if (_head == null) {
			System.out.println("no books in list");
		} else {
			BookNode temp = search(book);
			if (temp == null)
				return;
			else {
				_size -= 1;
				if (_tail == _head)
					temp = null;
				else if (_tail == temp) {
					temp.getPrev().setNext(null);
					_tail = temp.getPrev();
				} else if (_head == temp) {
					temp.getNext().setPrev(null);
					_head = temp.getNext();
				} else {
					temp.getNext().setPrev(temp.getPrev());
					temp.getPrev().setNext(temp.getNext());
				}
			}
		}
	}

	// return a bookNode with the code book in the list.
	public BookNode search(String book) {
		BookNode temp = _head;
		while (temp != null && !(temp.getBook().getCODE().equalsIgnoreCase(book)))
			temp = temp.getNext();
		if (temp == null) {
			System.out.println("user doesn't own that book");
			return null;
		} else
			return temp;
	}

	// prints all the book the customer borrowed.
	public void printList() {
		BookNode temp = _head;
		while (temp != null) {
			System.out.print(temp.getBook().getCODE() + ",\t");
			temp = temp.getNext();
		}
		System.out.println();
	}

	// deletes the whole list (in case the customer is leaving the library and
	// is returning all the books at once).
	public void deleteList() {
		while (_head != null) {
			_tree.delete(_head.getBook().getCODE());
			_head = _head.getNext();
		}
	}

}
