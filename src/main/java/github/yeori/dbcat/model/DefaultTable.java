package github.yeori.dbcat.model;

import java.util.ArrayList;
import java.util.List;

import github.yeori.dbcat.IColumn;
import github.yeori.dbcat.ITable;

public class DefaultTable implements ITable{

	Long seq;
	String name;
	List<IColumn> columns;
	
	public DefaultTable() {
		this.columns = new ArrayList<>();
	}
	@Override
	public String getName() {
		return null;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addColumn(IColumn column) {
		this.columns.add(column);
	}
	
}
