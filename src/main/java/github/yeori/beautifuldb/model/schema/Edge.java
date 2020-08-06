package github.yeori.beautifuldb.model.schema;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * it represents foreign key (from -> to)
 * @author yeori
 *
 */
@Entity
public class Edge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;
	
	@ManyToOne
	@JoinColumn(name="src_column")
	private Column from;
	
	@ManyToOne
	@JoinColumn(name="dst_column")
	private Column to;
	
	@ManyToOne
	@JoinColumn(name="schema")
	private Schema schema;
	
	@javax.persistence.Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private Cascading onDelete;
	
	public Edge() {}
	public Edge(Column from, Column to) {
		this.from = from;
		this.to = to;
		this.onDelete = Cascading.R;
	}

	public Edge(Long seq) {
		this.seq = seq;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public Column getFrom() {
		return from;
	}
	public void setFrom(Column from) {
		this.from = from;
	}
	public Column getTo() {
		return to;
	}
	public void setTo(Column to) {
		this.to = to;
	}
	public Cascading getOnDelete() {
		return onDelete;
	}
	public void setOnDelete(Cascading onDelete) {
		this.onDelete = onDelete;
	}
	public Schema getSchema() {
		return schema;
	}
	public void setSchema(Schema schema) {
		this.schema = schema;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getSeq() == null) ? 0 : getSeq().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Edge))
			return false;
		Edge other = (Edge) obj;
		if (getSeq() == null) {
			if (other.getSeq() != null)
				return false;
		} else if (!getSeq().equals(other.getSeq()))
			return false;
		return true;
	}
	
	
}
