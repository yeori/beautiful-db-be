package github.yeori.beautifuldb.service.schema;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.yeori.beautifuldb.dao.schema.IColumnDao;
import github.yeori.beautifuldb.dao.schema.ITableDao;
import github.yeori.beautifuldb.model.schema.Column;
import github.yeori.beautifuldb.model.schema.Table;

@Service
public class ColumnService {

	@Autowired ITableDao tableDao;
	@Autowired IColumnDao columnDao;
	
	@Transactional
	public Column insertColumn(Long tableSeq, Column col) {
		
		Table table = tableDao.getOne(tableSeq);
		Column lastColumn = tableDao.findLastColumn(table);
		int orderNum = lastColumn == null ? 0 : lastColumn.getOrdernum() + 1;
		col.setOrdernum(orderNum);
		col.setTable(table);
//		table.getColumns().add(col);
		columnDao.save(col);
		return col;
	}

	public List<Column> findColumns(int ... colSeqs) {
		List<Column> columns = new ArrayList<>();
		for (long colSeq : colSeqs) {
			columns.add(columnDao.getOne(colSeq));
		}
		return columns;
	}
}