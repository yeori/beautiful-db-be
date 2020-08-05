package github.yeori.beautifuldb.model.schema;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class VendorColumnType {

	@Id
	Long seq;
	
	String cate;
	
	String typeName;
	
	String typeAliases;
	
	String typeSpec;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vendor")
	Vendor vendor;
	
	String startVersion;

	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeAliases() {
		return typeAliases;
	}
	public void setTypeAliases(String typeAliases) {
		this.typeAliases = typeAliases;
	}
	public String getTypeSpec() {
		return typeSpec;
	}
	public void setTypeSpec(String typeSpec) {
		this.typeSpec = typeSpec;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public String getStartVersion() {
		return startVersion;
	}
	public void setStartVersion(String startVersion) {
		this.startVersion = startVersion;
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
		VendorColumnType other = (VendorColumnType) obj;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "[seq=" + seq + ", cate=" + cate + ", typeName=" + typeName + ", typeAliases="
				+ typeAliases + ", typeSpec=" + typeSpec + ", vendor=" + vendor.getVendorName() + ", startVersion=" + startVersion
				+ "]";
	}
	
}