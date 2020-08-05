package github.yeori.beautifuldb.service.parser;

import github.yeori.beautifuldb.model.schema.Vendor;
import github.yeori.beautifuldb.model.schema.VendorColumnType;

public interface IDbParser {

	boolean canParse(Vendor vendor);

	/**
	 * "INT(23)"과 같은 입력을 ["INT", "23"] 으로 분해해서 반환함
	 * 
	 * @param columnType
	 * @param typeValue
	 * @return
	 */
	String[] parseTypeComponents(VendorColumnType columnType, String typeValue);
}
