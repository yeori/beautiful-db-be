package github.yeori.beautifuldb.service.schema;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.yeori.beautifuldb.dao.schema.ITableDao;
import github.yeori.beautifuldb.model.schema.Table;
import github.yeori.dtogen.ObjectStamper;

@Service
@Transactional
public class TableService {

	@Autowired ITableDao tableDao;
	ObjectStamper gen = new ObjectStamper();
	
	public Table findTable(long tableSeq) {
		Table table = tableDao.findBySeq(tableSeq);
		return gen.stamp(
				table,
				Table.class,
				"schema",
				"columns.table");
	}
}
