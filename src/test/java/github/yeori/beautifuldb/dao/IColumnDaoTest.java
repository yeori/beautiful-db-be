package github.yeori.beautifuldb.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import github.yeori.beautifuldb.dao.schema.IColumnDao;
import github.yeori.beautifuldb.dao.schema.ITableDao;
import github.yeori.beautifuldb.model.schema.Column;
import github.yeori.beautifuldb.model.schema.ColumnMeta;
import github.yeori.beautifuldb.model.schema.Table;

class IColumnDaoTest extends DaoTestBase{

	@Autowired ITableDao tableDao;
	@Autowired IColumnDao columnDao;
	
	@Test
	@Rollback(false)
	void test_컬럼_추가() {
		Table table = tableDao.getOne(4L);
		ColumnMeta meta = new ColumnMeta();
		Column col = new Column("bookmarker", meta, "INT", null);
		columnDao.save(col);
		
		List<Column> columns = table.getColumns();
		columns.stream().forEach((column) -> {
			System.out.println(column.toString());
		});
	}

}
