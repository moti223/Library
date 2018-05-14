// an object to represent a book.
// hold the code of the book.
public class Book {
	private String _code;

	// consrtactur
	public Book() {
		_code = null;
	}

	public Book(String code) {
		_code = code;
	}

	public String getCODE() {
		return _code;
	}

	public void setCODE(String code) {
		_code = code;
	}

}
