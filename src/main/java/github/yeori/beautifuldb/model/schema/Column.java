package github.yeori.beautifuldb.model.schema;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Column {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;
	private String name;
	
	@Embedded
	private ColumnMeta meta;
	
	@javax.persistence.Column(nullable = true)
	private String defaultValue;
	private String type;
	private String mag;
	private Integer ordernum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="table")
	private Table table;

	public Column() {}
	public Column(String name, String type, String mag) {
		this(name, new ColumnMeta(), type, mag, null, null);
	}
	public Column(String name, ColumnMeta meta, String type, String mag) {
		this(name, meta, type, mag, null, null);
	}
	public Column(String name, ColumnMeta meta, String type, String mag, Integer ordernum, Table table) {
		super();
		this.name = name;
		this.meta = meta;
		this.type = type;
		this.mag = mag;
		this.ordernum = ordernum;
		this.table = table;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ColumnMeta getMeta() {
		return meta;
	}
	public void setMeta(ColumnMeta meta) {
		this.meta = meta;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMag() {
		return mag;
	}
	public void setMag(String mag) {
		this.mag = mag;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	@Override
	public String toString() {
		return "[seq=" + seq + ", name=" + name + ", meta=" + meta + ", defaultValue=" + defaultValue + ", type="
				+ type + ", mag=" + mag + ", ordernum=" + ordernum + "]";
	}
	
	
	
}
