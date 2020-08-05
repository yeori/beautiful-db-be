package github.yeori.beautifuldb.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import github.yeori.beautifuldb.dao.schema.IVendorColumnTypeDao;
import github.yeori.beautifuldb.model.schema.Vendor;
import github.yeori.beautifuldb.model.schema.VendorColumnType;
import github.yeori.dtomimic.DtoMimic;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class IVendorColumnTypeDaoTest {

	@Autowired IVendorColumnTypeDao typeDao;

	static DtoMimic mimic ;
	
	@BeforeAll
	static void before() {
		mimic = new DtoMimic();
	}
	@Test
	@Transactional
	void test_컬럼타입로드() throws JsonProcessingException {
		Vendor v = new Vendor();
		v.setSeq(10);
		List<VendorColumnType> types = typeDao.findByVendor(v);
		List<VendorColumnType> dto = mimic.mimic(types, ArrayList.class, "vendor");
		VendorColumnType a = dto.get(0);
		System.out.println(a);
		ObjectMapper om = new ObjectMapper();
		System.out.println(om.writeValueAsString(dto));
		
	}

}
