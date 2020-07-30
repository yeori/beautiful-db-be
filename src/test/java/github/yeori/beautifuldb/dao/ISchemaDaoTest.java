package github.yeori.beautifuldb.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import github.yeori.beautifuldb.dao.schema.ISchemaDao;
import github.yeori.beautifuldb.dao.schema.IVendorDao;
import github.yeori.beautifuldb.dao.user.IUserDao;
import github.yeori.beautifuldb.model.schema.Schema;
import github.yeori.beautifuldb.model.schema.Vendor;
import github.yeori.beautifuldb.model.user.User;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ISchemaDaoTest {

	@Autowired
	ISchemaDao dao;
	@Autowired
	IVendorDao vendorDao;
	@Autowired
	IUserDao userDao;
	
	@Rollback(false)
	@Test
	void test_스키마추가() {
		User user = userDao.getOne(119L);
		Vendor mysql = vendorDao.getOne(10);
		Schema schema = new Schema("AirDB", mysql, user);
		dao.save(schema);
	}

}
