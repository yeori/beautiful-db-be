package github.yeori.beautifuldb.service.schema;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import github.yeori.beautifuldb.model.schema.Column;

@SpringBootTest
class EdgeServiceTest {

	@Autowired ColumnService colService;
	@Autowired EdgeService edgeService;
	
	@Test
	void test_foreignkey_추가() {
//		List<Column> columns = colService.findColumns(11, 4);
		edgeService.createEdge(11, 4);
//		edgeService.createEdge(888, 7);
	}
	
	@Test
	void test_다른스키마에속하는_컬럼을_연결() {
		long from = 4; // airDB 
		long to = 13;  // particleDB
		edgeService.createEdge(from, to);
		
	}

}
