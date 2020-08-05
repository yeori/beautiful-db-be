package github.yeori.beautifuldb.service.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.dao.DaoTestBase;
import github.yeori.beautifuldb.dao.schema.IVendorColumnTypeDao;
import github.yeori.beautifuldb.model.schema.Vendor;
import github.yeori.beautifuldb.model.schema.VendorColumnType;

class MariadbParserTest extends DaoTestBase{

	@Autowired IVendorColumnTypeDao columnTypeDao;
	
	private List<VendorColumnType> types;
	MariaDbParser parser = new MariaDbParser();
	
	@BeforeEach
	public void prepare() {
		if ( types == null) {
			Vendor vendor = new Vendor();
			vendor.setSeq(10); // mariadb
			types = columnTypeDao.findByVendor(vendor);			
		}
	}
	@Test
	void test_empty_spec() {
		List<Map<String, Object>> specs = MariaDbParser.conv("[]");
		assertEquals(0, specs.size());
		
		specs = MariaDbParser.conv("*");
		assertEquals(0, specs.size());
	}
	
	@Test
	void test_decimal_spec() {
		String spec = "[{\"min\":0,\"max\":65,\"default\":10},{\"min\":0,\"max\":30,\"default\":0}]";
		List<Map<String, Object>> specs = MariaDbParser.conv(spec);
		assertEquals(2, specs.size());
		TypeMap first = new TypeMap(specs.get(0));
		assertEquals(0, first.getInt("min").intValue());
		assertEquals(65, first.getInt("max"));
		assertEquals(10, first.getInt("default"));
	}
	@Test
	void test_valid_varchar_type() {
		VendorColumnType varchar = types.stream().filter(t -> t.getTypeName().equals("VARCHAR")).findFirst().get();
		String [] type;
		
		type = parser.parseTypeComponents(varchar, "VARCHAR (23)");
		assertArrayEquals(new String[] {"VARCHAR", "23"}, type);

		type = parser.parseTypeComponents(varchar, "   VARCHAR (  23 )   ");
		assertArrayEquals(new String[] {"VARCHAR", "23"}, type);
	}
	
	@Test
	public void test_varchar_범위벗어난케이스() {
		VendorColumnType varchar = types.stream().filter(t -> t.getTypeName().equals("VARCHAR")).findFirst().get();
		
		String [] invalidCases = "VARCHAR(-1), VARCHAR(23.2), VARCHAR(65536)".split(",");
		Arrays.stream(invalidCases)
			.forEach(v -> {
				try {
					parser.parseTypeComponents(varchar, v);
					fail("실패해야하는 케이스 통과함: [" + v + "]");
				} catch(Exception e) {
					
				}
			});
	}
	
	@Test
	public void test_enum타입() {
		VendorColumnType enumType = types.stream().filter(t -> t.getTypeName().equals("ENUM")).findFirst().get();
		String [] type;
		
		type = parser.parseTypeComponents(enumType, "ENUM ('YES', 'NO' )");
		assertArrayEquals(new String[] {"ENUM", "'YES','NO'"}, type);
		
		type = parser.parseTypeComponents(enumType, "ENUM(  'TOP', 'MIDDLE', 'BOTTOM')");
		assertArrayEquals(new String[] {"ENUM", "'TOP','MIDDLE','BOTTOM'"}, type);
	}

}
