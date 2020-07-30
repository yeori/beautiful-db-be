package github.yeori.beautifuldb.service.schema;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import github.yeori.beautifuldb.model.schema.Column;
import github.yeori.beautifuldb.model.schema.Table;

@SpringBootTest
class TableServiceTest {
	
	@Autowired TableService tableService;
	
	@Test
	void test() {
		long t = System.nanoTime();
		Table table = tableService.findTable(3);
		t = t  - System.nanoTime();
		System.out.printf("%.3f\n", t/1_000_000_000.0);
		System.out.println(table);
		assertNull(table.getSchema());
		for(Column c : table.getColumns()) {
			assertNull(c.getTable());
		}
	}
	
	@Test
	public void test_테이블_로드() {
		Table table = tableService.findTable(3L);
		assertNull(table.getSchema());
	}

}
