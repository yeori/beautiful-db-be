package github.yeori.dtogen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import github.yeori.beautifuldb.model.schema.Column;
import github.yeori.beautifuldb.model.schema.ColumnMeta;
import github.yeori.beautifuldb.model.schema.Schema;
import github.yeori.beautifuldb.model.schema.Table;
import github.yeori.beautifuldb.model.schema.Vendor;
import github.yeori.beautifuldb.model.user.User;
import github.yeori.dtommic.DtoMimic;

class DtoMimicTest {

	@Test
	void test() {
		Schema schema = new Schema("yes", new Vendor(), new User("jack", "jack"));
		
		Table table = new Table("items", "utf-8", null);
		table.setSeq(122L);
		table.setSchema(schema);
		
		Column c0 = new Column("seq", "INT", null);
		c0.setSeq(11L);
		c0.setMeta(new ColumnMeta().setAi(true).setPk(true));
		c0.setTable(table);
		table.getColumns().add(c0);
		
		Column c1 = new Column("item_name", "VARCHAR", "50");
		c1.setSeq(13L);
		c0.setTable(table);
		table.getColumns().add(c1);
		
		DtoMimic gen = new DtoMimic();
		Table cloned = gen.mimic(table, Table.class, "columns.table", "schema");
		System.out.println(cloned);
		for(Column c: cloned.getColumns()) {
			System.out.println("> " + c.toString());
		}
		assertNull(cloned.getSchema());
		for(Column c : cloned.getColumns()) {
			assertNull(c.getTable());
		}
	}
	
	

}
