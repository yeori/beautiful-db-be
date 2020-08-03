package github.yeori.dtogen.level1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import github.yeori.dtomimic.DtoMimic;

class WithMapField {

	@Test
	void test() {
		DtoMimic gen = new DtoMimic();
		Map<Integer, Book> books = new HashMap<>();
		books.put(12, new Book("A", "aaaa"));
		books.put(13, new Book("B", "bbbb"));
		books.put(17, new Book("C", "ccc"));
		Lib lib = new Lib(books);
		lib.names = new ArrayList<>(Arrays.asList("x", "y", "z"));
		Lib copy = gen.mimic(lib, "books.isbn");
		assertTrue(lib.books != copy.books);
		assertEquals(lib.books, copy.books);
		assertEquals(lib.names, copy.names);
		for (Book book : lib.books.values()) {
			assertNull(book.isbn);
		}
		
	}

	public static class Lib {
		Map<Integer, Book> books;
		List<String> names;
		public Lib() {
		}
		public Lib(Map<Integer, Book> books) {
			this.books = books;
		}
		public Map<Integer, Book> getBooks() {
			return books;
		}
		public void setBooks(Map<Integer, Book> books) {
			this.books = books;
		}
		public List<String> getNames() {
			return names;
		}
		public void setNames(List<String> names) {
			this.names = names;
		}
	}
	public static class Book {
		String title;
		String isbn;
		
		public Book() {
		}
		public Book(String title, String isbn) {
			super();
			this.title = title;
			this.isbn = isbn;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getIsbn() {
			return isbn;
		}
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Book other = (Book) obj;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			return true;
		}
		
		
		
	}
}
