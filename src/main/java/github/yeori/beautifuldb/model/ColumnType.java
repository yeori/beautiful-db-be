package github.yeori.beautifuldb.model;

public class ColumnType {
	
	private Integer seq;
	private String name;
	private String mag;
	private Vendor vendor;

	public ColumnType(Integer seq, String name, String mag, Vendor vendor) {
		super();
		this.seq = seq;
		this.name = name;
		this.mag = mag;
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "DataType [seq=" + seq + ", name=" + name + ", mag=" + mag + ", vendor=" + vendor + "]";
	}
	
}
