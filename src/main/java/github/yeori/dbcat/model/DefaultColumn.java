package github.yeori.dbcat.model;

import java.util.ArrayList;
import java.util.List;

import github.yeori.dbcat.IColumn;

public class DefaultColumn implements IColumn {

	Long seq;
	String name;
	String type;
	List<String> mag;
	ColumnMeta meta;
	boolean nullable;
	String defaultValue;
	
	public DefaultColumn(String columnName) {
		this.name = columnName;
	}
	public DefaultColumn(String columnName, String columnType) {
		this.name = columnName;
		this.type = columnType;
	}
	public void setMag(List<String> mag) {
		this.mag = new ArrayList<>(mag);
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
