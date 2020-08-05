package github.yeori.beautifuldb.service.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import github.yeori.beautifuldb.BeautDbException;
import github.yeori.beautifuldb.Error;
import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.Util;
import github.yeori.beautifuldb.model.schema.Vendor;
import github.yeori.beautifuldb.model.schema.VendorColumnType;

class MariaDbParser implements IDbParser{

	static ObjectMapper om = new ObjectMapper();
	
	
	@Override
	public boolean canParse(Vendor vendor) {
		return vendor.getVendorName().equals("mariadb");
	}
	
	@Override
	public String[] parseTypeComponents(VendorColumnType columnType, String value) {
		/*
		 * VARCHAR(23) => ["VARCHAR", "23"]
		 * 
		 * ENUM('Y','N' => ["VARCHAR", "'Y','N'" ]
		 */
		int [] mag = magPos(value);
		String typeNamePart = parseTypeName(value, mag).toUpperCase();
		String specPart = parseSpecPart(value, mag);
		boolean isEnum = "ENUM".equals(typeNamePart);
		if (isEnum) {
			specPart = compact(specPart);
		} else {
			List<Map<String, Object>> spec = conv(columnType.getTypeSpec());
			specPart = resolveSpecPart(typeNamePart, specPart, spec, mag);
		}
		
		return new String[] {typeNamePart, specPart};
	}
	
	String compact(String src) {
		String [] tokens = Util.split(src, ",", s -> s.trim());
		return String.join(",", tokens);
	}

	String resolveSpecPart(String typeName, String specPart, List<Map<String, Object>> specs, int[] mag) {
		String [] vs = "".equals(specPart) ? new String[0] : specPart.split(",");
		if (vs.length != specs.size()) {
			// type spec mismatch
			throw new BeautDbException(
					400,
					Error.INVALID_COLUMN_TYPE.name(),
					"type value [%s] is not matched", specPart);
		}
		for (int i = 0; i < vs.length; i++) {
			TypeMap spec = new TypeMap(specs.get(i));
			int min = spec.getInt("min");
			int max = spec.getInt("max");
			int v = parseInt(vs[i]);
			if (v < min || v > max) {
				throw new BeautDbException(
					400,
					Error.INVALID_MAG_FORMAT.name(),
					"out of range %s(%s)", typeName, specPart);
			}
			vs[i] = "" + v;
		}
		return String.join(",", vs);
	}

	int parseInt(String s) {
		try {
			return Integer.parseInt(s.trim());
		} catch (NumberFormatException e) {
			throw new BeautDbException(
				400,
				Error.INVALID_MAG_FORMAT.name(), "not a number format [%s]", s);
		}
	}

	String parseTypeName(String value, int[] mag) {
		return  mag.length == 0 ? value.trim() : value.substring(0, mag[0]).trim();
	}
	String parseSpecPart(String value, int[] mag) {
		return mag.length == 0 ? "" : value.substring(mag[0]+1, mag[1]);
	}

	int[] magPos(String value) {
		int p0 = value.indexOf('(');
		int p1 = value.indexOf(')', Math.max(p0, 0));
		if (p0 == -1 || p1 == -1) {
			return new int[0];
		} else {
			return new int[] {p0, p1};			
		}
	}

	static List<Map<String, Object>> conv(String typeSpec) {
		String specStr = typeSpec.trim();
		if (specStr.equals("\"*\"")) {
			return Collections.emptyList();
		}
		try {
			return om.readValue(specStr, ArrayList.class);
		} catch (JsonProcessingException e) {
			throw new BeautDbException(500, "SERVER_ERROR", "MySqlParser error");
		}
	}
}
