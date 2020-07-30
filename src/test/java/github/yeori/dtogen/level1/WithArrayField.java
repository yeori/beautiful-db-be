package github.yeori.dtogen.level1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import github.yeori.dtogen.ObjectStamper;

class WithArrayField {

	@Test
	void test() {
		User src = new User();
		src.setName("jack");
		src.setEmails(new String[] {"a@a.com", "b@b.com"}); 
		
		ObjectStamper gen = new ObjectStamper();
		User cloned = gen.stamp(src);
		assertTrue(src != cloned) ;
		assertTrue (src.getEmails() != cloned.getEmails());
		assertEquals(src.getEmails().length, cloned.getEmails().length);
		assertArrayEquals(src.getEmails(), cloned.getEmails());
		
		
	}
	
	
	public static class User {
		private String name;
		private String [] emails;
		
		public User() {
			super();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String[] getEmails() {
			return emails;
		}
		public void setEmails(String[] emails) {
			this.emails = emails;
		}
		@Override
		public String toString() {
			return "User [name=" + name + ", emails=" + Arrays.toString(emails) + "]";
		}
		
		
	}

}
