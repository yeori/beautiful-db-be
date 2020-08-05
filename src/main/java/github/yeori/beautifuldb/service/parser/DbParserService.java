package github.yeori.beautifuldb.service.parser;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import github.yeori.beautifuldb.dao.schema.IVendorColumnTypeDao;
import github.yeori.beautifuldb.model.schema.Vendor;
import github.yeori.beautifuldb.model.schema.VendorColumnType;

@Service
public class DbParserService {

	@Autowired
	IVendorColumnTypeDao columnTypeDao;
	
	private List<IDbParser> parsers;
	
	public DbParserService() {
		this.parsers = Arrays.asList(new MariaDbParser());
	}
	
	public IDbParser findParser(Vendor vendor) {
		for (IDbParser parser : parsers) {
			if (parser.canParse(vendor)) {
				return parser;
			}
		}
		return null;
	}

	@Transactional(readOnly = true)
	public String[] parseTypeComponent(Vendor vendor, String value) {
		List<VendorColumnType> types = columnTypeDao.findByVendor(vendor);
		String columnType = typePart(value);
		VendorColumnType type = types.stream().filter(t -> t.getTypeName().equals(columnType)).findFirst().get();
		IDbParser parser = findParser(vendor);
		return parser.parseTypeComponents(type, value);
	}

	private String typePart(String value) {
		int p = value.indexOf('(');
		return p == -1 ? value.trim() : value.substring(0, p).trim(); 
	}
}
