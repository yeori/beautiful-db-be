package github.yeori.beautifuldb.model.schema;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

import github.yeori.beautifuldb.dao.config.BooleanToYNConverter;

/**
 * pk, uk, fk, etc
 * @author yeori
 *
 */
@Embeddable
public class ColumnMeta {

	@Convert(converter = BooleanToYNConverter.class)
	private boolean pk;
	@Convert(converter = BooleanToYNConverter.class)
	private boolean uk;
	@Convert(converter = BooleanToYNConverter.class)
	private boolean fk;
	@Convert(converter = BooleanToYNConverter.class)
	private boolean ai;
	@Convert(converter = BooleanToYNConverter.class)
	private boolean nullable;
	@Convert(converter = BooleanToYNConverter.class)
	private boolean unsigned;
	
	public boolean isPk() {
		return pk;
	}
	public ColumnMeta setPk(boolean pk) {
		this.pk = pk;
		return this;
	}
	public boolean isUk() {
		return uk;
	}
	public ColumnMeta setUk(boolean uk) {
		this.uk = uk;
		return this;
	}
	public boolean isFk() {
		return fk;
	}
	public ColumnMeta setFk(boolean fk) {
		this.fk = fk;
		return this;
	}
	public boolean isAi() {
		return ai;
	}
	public ColumnMeta setAi(boolean ai) {
		this.ai = ai;
		return this;
	}
	public boolean isNullable() {
		return nullable;
	}
	public ColumnMeta setNullable(boolean nullable) {
		this.nullable = nullable;
		return this;
	}
	public boolean isUnsigned() {
		return unsigned;
	}
	public ColumnMeta setUnsigned(boolean unsigned) {
		this.unsigned = unsigned;
		return this;
	}
	
}
