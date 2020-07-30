package github.yeori.dbcat.model;

public class ColumnMeta {

	boolean pk;
	boolean uk;
	boolean fk;
	boolean ai; // auto increment
	boolean unsigned;
	
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	public boolean isUk() {
		return uk;
	}
	public void setUk(boolean uk) {
		this.uk = uk;
	}
	public boolean isFk() {
		return fk;
	}
	public void setFk(boolean fk) {
		this.fk = fk;
	}
	public boolean isAi() {
		return ai;
	}
	public void setAi(boolean ai) {
		this.ai = ai;
	}
	public boolean isUnsigned() {
		return unsigned;
	}
	public void setUnsigned(boolean unsigned) {
		this.unsigned = unsigned;
	}
	
}
