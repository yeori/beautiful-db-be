package github.yeori.beautifuldb.service.schema;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import github.yeori.beautifuldb.Reflections;
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

	@Transactional(readOnly = true)
	public List<Column> findColumns(int ... colSeqs) {
		List<Column> columns = new ArrayList<>();
		for (long colSeq : colSeqs) {
			columns.add(columnDao.getOne(colSeq));
		}
		return columns;
	}
	
	@Transactional
	public boolean updateOrderNum(Long seqA, Long seqB) {
		if (seqA.equals(seqB)) {
			return false;
		}
		Column ca = columnDao.getOne(seqA);
		Column cb = columnDao.getOne(seqB);
		Integer tmp = ca.getOrdernum();
		ca.setOrdernum(cb.getOrdernum());
		cb.setOrdernum(tmp);
		return true;
	}
	
	@Transactional
	public int updateColumn(Long columnSeq, String prop, Object value) {
		Column column = columnDao.getOne(columnSeq);
		Reflections.callSetter(Column.class, column, prop, value);
		return -1;
	}
}
