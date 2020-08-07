package github.yeori.beautifuldb.model.schema;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@NamedEntityGraph(
	name="table-based-graph",
	
	attributeNodes = {
		@NamedAttributeNode("seq"),
		@NamedAttributeNode("name"),
		@NamedAttributeNode("encoding"),
		@NamedAttributeNode("columns")
	},
	includeAllAttributes = false
)
@Entity
public class Table {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;
	private String name;
	private String encoding;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="schema",updatable = false, nullable = false)
	private Schema schema;
	
	@OneToMany(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("ordernum")
	private List<Column> columns = new ArrayList<>();
	
	@Embedded
	private View view;
	
	public Table() {}
	public Table(String name, String encoding, Schema schema) {
		this.name = name;
		this.encoding = encoding;
		this.schema = schema;
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
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public Schema getSchema() {
		return schema;
	}
	public void setSchema(Schema schema) {
		this.schema = schema;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[seq=" + seq + ", name=" + name + ", encoding=" + encoding + ", schema=" + schema + "]");
		this.columns.stream().forEach(col -> sb.append("  " + col.toString() + "\n"));
		return sb.toString();
	}
	
	@Embeddable
	public static class View {
		Double x;
		Double y;
		public View() {}
		public Double getX() {
			return x;
		}
		public void setX(Double x) {
			this.x = x;
		}
		public Double getY() {
			return y;
		}
		public void setY(Double y) {
			this.y = y;
		}
		
	}
}
