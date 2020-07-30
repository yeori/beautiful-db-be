package github.yeori.beautifuldb.service.schema;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import github.yeori.beautifuldb.model.schema.Edge;
import github.yeori.beautifuldb.model.schema.Schema;
import github.yeori.beautifuldb.model.schema.Table;

@SpringBootTest
class SchemaServiceTest {

	@Autowired SchemaService schemaService;
	
	@Test
	public void test_스키마로드() {
		long t = System.nanoTime();
		Schema schema = schemaService.findSchemaFully(4L);
		t = t  - System.nanoTime();
		System.out.printf("%.3f\n", t/1_000_000_000.0);
		assertNull(schema.getOwner());
		assertNotNull(schema.getName());
		assertNotNull(schema.getSeq());
		assertNotNull(schema.getVendor());
		for(Table table : schema.getTables()) {
			assertNull(table.getSchema());
		}
		for(Edge e : schema.getEdges()) {
			assertNull(e.getSchema());
		}
	}

}
