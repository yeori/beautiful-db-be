package github.yeori.beatifuldb.model;
/**
 * pk, uk, fk, etc
 * @author yeori
 *
 */
public class ColumnMeta {

	private boolean pk;
	private boolean uk;
	private boolean fk;
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
	
}
