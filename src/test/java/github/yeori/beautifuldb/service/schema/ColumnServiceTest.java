package github.yeori.beautifuldb.service.schema;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import github.yeori.beautifuldb.dao.DaoTestBase;
import github.yeori.beautifuldb.dao.schema.IColumnDao;
import github.yeori.beautifuldb.dao.schema.ITableDao;
import github.yeori.beautifuldb.model.schema.Column;
import github.yeori.beautifuldb.model.schema.ColumnMeta;


//@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest
class ColumnServiceTest{

	@Autowired ColumnService svc;
	
	
	@Test
	@Rollback(false)
	void test_테이블에_컬럼_추가() {
//		ColumnMeta meta = new ColumnMeta();
		Column col = new Column("seq", "INT", null);
		col.getMeta().setUnsigned(true);
		svc.insertColumn(6L, col);
		System.out.println(col.toString());
		
	}

}
