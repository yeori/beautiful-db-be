package github.yeori.beautifuldb.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EncTest {

	@Test
	void test() {
		assertEquals("03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", Enc.sha256("1234"));
	}
}
