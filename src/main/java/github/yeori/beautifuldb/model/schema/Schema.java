package github.yeori.beautifuldb.model.schema;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;

import github.yeori.beautifuldb.model.user.User;

/**
 * 
 * @author yeori
 *
 */
@NamedEntityGraph(
	name = "full-schema-graph",
//	includeAllAttributes = true
	
	attributeNodes = {
		@NamedAttributeNode("seq"),
		@NamedAttributeNode("name"),
		@NamedAttributeNode(value = "tables", subgraph = "full-table-graph")
	},
	subgraphs = {
		@NamedSubgraph(
			name = "full-table-graph",
			attributeNodes = {
//				@NamedAttributeNode(value="columns")
			}
		)
	}
)
@Entity
public class Schema {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seq;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="vendor")
	private Vendor vendor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner")
	private User owner;

	@OneToMany(mappedBy = "schema",
		cascade = CascadeType.ALL,
		orphanRemoval = true,
		fetch = FetchType.EAGER)
	private Set<Table> tables;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schema", orphanRemoval = true)
	private Set<Edge> edges;
	
	public Schema() {}
	public Schema(String name, Vendor vendor, User owner) {
		super();
		this.name = name;
		this.vendor = vendor;
		this.owner = owner;
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

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public User getOwnerSeq() {
		return owner;
	}

	public void setOwnerSeq(User owner) {
		this.owner = owner;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Set<Table> getTables() {
		return tables;
	}
	public void setTables(Set<Table> tables) {
		this.tables = tables;
	}
	public Set<Edge> getEdges() {
		return edges;
	}
	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
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
		Schema other = (Schema) obj;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}
	
}
