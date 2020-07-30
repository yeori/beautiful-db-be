package github.yeori.beautifuldb.model;

import github.yeori.beautifuldb.model.schema.Vendor;

public class ColumnType {
	
	private Integer seq;
	private String typeName;
	private String mag;
	private Vendor vendor;

	public ColumnType(Integer seq, String name, String mag, Vendor vendor) {
		super();
		this.seq = seq;
		this.typeName = name;
		this.mag = mag;
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "DataType [seq=" + seq + ", name=" + typeName + ", mag=" + mag + ", vendor=" + vendor + "]";
	}
	
}
