package github.yeori.beautifuldb.dao;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import github.yeori.beautifuldb.dao.schema.ISchemaDao;
import github.yeori.beautifuldb.dao.schema.ITableDao;
import github.yeori.beautifuldb.model.schema.Column;
import github.yeori.beautifuldb.model.schema.Schema;
import github.yeori.beautifuldb.model.schema.Table;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ITableDaoTest {

	@Autowired ISchemaDao schemaDao;
	@Autowired ITableDao tableDao;
	
	@Test
	@Rollback(false)
	void test_테이블추가() {
		Schema schema = schemaDao.getOne(5L);
		Table table = new Table("PARTICLES", null, schema);
		tableDao.save(table);
	}

	@Test
	public void test_ordernum_검색() {
		Table t = tableDao.getOne(3L);
		Column c = tableDao.findLastColumn(t);
		System.out.println(c.toString());
	}
}
