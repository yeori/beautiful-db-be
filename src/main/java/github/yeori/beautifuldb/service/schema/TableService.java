package github.yeori.beautifuldb.service.schema;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.dao.schema.ITableDao;
import github.yeori.beautifuldb.model.schema.Table;
import github.yeori.beautifuldb.model.schema.Table.View;
import github.yeori.dtomimic.DtoMimic;

@Service
@Transactional
public class TableService {

	@Autowired ITableDao tableDao;
	DtoMimic dtoMimicker = new DtoMimic();
	
	public Table findTable(long tableSeq) {
		Table table = tableDao.findBySeq(tableSeq);
		return dtoMimicker.mimic(
				table,
				Table.class,
				"schema",
				"columns.table");
	}
	
	public Object updateTable(Long tableSeq, String prop, Object value) {
		Table table = tableDao.findBySeq(tableSeq);
		if (prop.equals("view")) {
			TypeMap pos = new TypeMap((Map)value);
			int x = pos.asInt("x");
			int y = pos.asInt("y");
			View v = table.getView();
			v.setX(1.0*x);
			v.setY(1.0*y);
			
		} else if (prop.equals("name")) {
			String name = (String) value;
			// TODO name 값 검증 코드 필요함
			table.setName(name);
		}
		return null;
	}
}
