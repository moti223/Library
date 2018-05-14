
// the main program that builds the whole data structure according to the required action.
import java.io.IOException;

public class Library {
	private MaxHeap _mh = new MaxHeap();
	private RBTree _cusTree = new RBTree();
	private RbTreeBook _bookTree = new RbTreeBook();

	// building the customer tree.
	public void newCustomer(String name, int id) {
		_cusTree.insert(name, id);
	}

	// deleting the customer from the tree, also returning all his books and
	// deleting from book tree and max heap.
	public void deleteCustomer(int id, String name) {
		RedBlackNode temp = _cusTree.search(id);
		temp.getCustomer().getList().deleteList();
		_mh.deleteCustomer(temp.getBookHolders());
		_cusTree.delete(id);
		System.out.println(name + " is no longer a customer.");
	}

	// adding a book to a customer, also checks if the book hasn't been taken
	// yet and if the customer hasn't reached the maximum amount of books.
	// adds a new book to the customer, the RB tree that is sorted by books and
	// updates and max heap.
	public void borrow(int id, String book) {
		RedBlackNode temp = _cusTree.search(id);
		if (_bookTree.isEmpty || _bookTree.search(book) == null) {
			if (temp.getCustomer().getList()._size < 10) {
				temp.getCustomer().getList().add(book);
				if (temp.getBookHolders() == null) {
					BookHolders bookTem = new BookHolders(temp.getCustomer());
					_mh.insert(bookTem);
					temp.setBookHolders(bookTem);
				} else {
					_mh.add(temp.getBookHolders());
				}
				RedBlackBook rbBooktem = new RedBlackBook(book, temp.getCustomer());
				_bookTree.insert(rbBooktem);
				temp.getCustomer().getList().setTree(_bookTree);
				System.out.println(temp.getCustomer().getName() + " has borrowed the book with the code: " + book);
			} else
				System.out.println(temp.getCustomer().getName() + " has reached the maximum amount of books");
		} else {
			RedBlackBook tmp = _bookTree.search(book);
			System.out.println(temp.getCustomer().getName() + " can't borrow that book, " + tmp.getCustomer().getName()
					+ " already owns that book.");
		}
	}

	// removing a book from the customer (customer returns a book), also deletes
	// from RB tree of books and updates max heap
	public void returnBook(int id, String book) {
		RedBlackNode temp = _cusTree.search(id);
		temp.getCustomer().getList().delete(book);
		_bookTree.delete(book);
		_mh.deleteFix(temp.getBookHolders());
		System.out.println(temp.getCustomer().getName() + " has returned the with the code: " + book);
	}
	// next 3 methods take care of the 3 inquiries needed.

	// prints which book the customer with the id has.
	public void whichBooks(int id) {
		RedBlackNode temp = _cusTree.search(id);
		System.out.print(temp.getCustomer().getName() + " owns the books with these codes: ");
		temp.getCustomer().getList().printList();
	}

	// prints the owner of the book with the code book.
	public void owner(String book) {
		RedBlackBook temp = _bookTree.search(book);
		System.out.println("The owner of the book with the code " + book + " is: " + temp.getCustomer().getName());
	}

	// returns people with the largest amount of books.
	public void mostBorrowed() {
		_mh.printMaxBooksOwners();
	}

	// a method to decide what should happen according to the input String.
	// the String is split into String array with every word inside 1 cell,
	// than performs checks to understand what should be done.
	public void operate(String line) {
		String[] in = line.split(" ");
		if (in[0].equalsIgnoreCase("?")) {
			if (in[1].equalsIgnoreCase("!"))
				mostBorrowed();
			else if (in[1].length() == 6)
				owner(in[1]);
			else
				whichBooks(Integer.parseInt(in[1]));
		} else if (in[0].equalsIgnoreCase("+")) {
			newCustomer(in[1], Integer.parseInt(in[2]));
		} else if (in[0].equalsIgnoreCase("-")) {
			deleteCustomer(Integer.parseInt(in[2]), in[1]);
		} else if (in[3].equalsIgnoreCase("+")) {
			borrow(Integer.parseInt(in[1]), in[2]);
		} else {
			returnBook(Integer.parseInt(in[1]), in[2]);
		}
	}

	// main program, reads from an external file all the requests and inquiries.
	public static void main(String[] args) {
		Library lib = new Library();
		String file =  "C:/Users/מוטי/Desktop/input 2.txt";
		try {
			ReadFile f = new ReadFile(file);
			String[] lines = f.OpenFile();
			for (int i = 0; i < 25; i++)
				lib.operate(lines[i]);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
