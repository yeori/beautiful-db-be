package github.yeori.beautifuldb.dao.config;

import javax.persistence.AttributeConverter;

import github.yeori.beautifuldb.BeautDbException;

public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return attribute ? "Y" : "N";
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		int idx = "YN".indexOf(dbData);
		if (idx < 0) {
			throw new BeautDbException(500, "SERVER_ERROR:INVALID_DB_VALUE", "expected 'Y' or 'N', but [%s]", dbData);
		}
		return idx == 0 ? Boolean.TRUE : Boolean.FALSE;
	}

}
