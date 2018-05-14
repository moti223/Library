// a max heap that is sorted by the number of books the customer has borrowed.
class MaxHeap {

	private final int SIZE = 8; // initial length of array
	private int next = 0; // limit of elements in array
	private BookHolders[] A = new BookHolders[SIZE];

	// standard resize to avoid overflow
	private void resize() {
		BookHolders[] B = new BookHolders[A.length * 2];
		for (int i = 0; i < A.length; ++i)
			B[i] = A[i];
		A = B;
	}

	// methods to move up and down tree as array

	private int parent(int i) {
		return (int) Math.floor((i - 1) / 2);
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * i + 2;
	}

	private boolean isRoot(int i) {
		return i == 0;
	}

	// standard swap, switches indicators, also switched their _loc attribute.
	private void swap(int i, int j) {
		BookHolders temp = A[i];
		A[i] = A[j];
		A[j] = temp;
		int tmp = A[i].getLoc();
		A[i].setLoc(A[j].getLoc());
		A[j].setLoc(tmp);
	}

	public boolean isEmpty() {
		return (next == 0);
	}

	public int size() {
		return (next);
	}

	// standard Insert with changes to comply with the object BookHolders.
	public void insert(BookHolders k) {
		if (size() == A.length)
			resize();
		A[next] = k;
		k.setLoc(next);

		int i = next;
		int p = parent(i);
		while (!isRoot(i) && A[i].getCustomer().getList()._size > A[p].getCustomer().getList()._size) {
			swap(i, p);
			i = p;
			p = parent(i);
		}
		++next;
	}

	// a method to print customers that has borrowed the largest amount of books
	// currently.
	public void printMaxBooksOwners() {
		System.out.println(
				"Customers with the largest amount of books are (" + A[0].getCustomer().getList()._size + " books): ");
		printMax(0);
	}

	// print root and all the following son that still have the same amount of
	// books.
	private void printMax(int i) {
		// stops if reached end of heap or the current junction in question has
		// less books than the max amount.
		if ((i >= next) || (A[parent(i)].getCustomer().getList()._size > A[i].getCustomer().getList()._size)) {
			return;
		}
		// prints informations.
		System.out.println("Name - " + A[i].getCustomer().getName() + " ID - " + A[i].getCustomer().getID());
		// Recursively keeps searching the heap for more junctions with the
		// maximum amount of books.
		printMax(left(i));
		printMax(right(i));

	}

	// standard max heapify.
	private void maxHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int largest;
		if (l < next && A[l].getCustomer().getList()._size > A[i].getCustomer().getList()._size)
			largest = l;
		else
			largest = i;
		if (r < next && A[r].getCustomer().getList()._size > A[i].getCustomer().getList()._size)
			largest = r;
		if (largest != i) {
			swap(i, largest);
			maxHeapify(largest);
		}

	}

	// a method to delete a customer from the heap (customer finished his
	// library membership).
	public void deleteCustomer(BookHolders b) {
		int largest;
		int l = left(b.getLoc());
		int r = right(b.getLoc());
		// checks which of the customers sons has more books (if they exist) and
		// applies him as the parent.
		if (l < next) {
			if (r < next) {
				largest = (A[l].getCustomer().getList()._size >= A[r].getCustomer().getList()._size ? l : r);
			} else
				largest = l;
			swap(largest, b.getLoc());
			A[b.getLoc()].getCustomer().getList()._size = Integer.MIN_VALUE;
			maxHeapify(b.getLoc());
		}

		next--;
	}

	// a fix-up method when the user returns a book (called upon after the
	// customers book list has been updated).
	public void deleteFix(BookHolders b) {
		maxHeapify(b.getLoc());
	}

	// a method to maintain heap accuracy while an owner already exist and he
	// borrowed another book.
	// this method is called upon after the amount of books as been updated.
	public void add(BookHolders b) {
		int i = b.getLoc();
		int p = parent(i);
		while (!isRoot(i) && A[i].getCustomer().getList()._size > A[p].getCustomer().getList()._size) {
			swap(i, p);
			i = p;
			p = parent(i);
		}
	}
}
