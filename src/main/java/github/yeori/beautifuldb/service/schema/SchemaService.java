package github.yeori.beautifuldb.service.schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import github.yeori.beautifuldb.dao.schema.ISchemaDao;
import github.yeori.beautifuldb.model.schema.Schema;
import github.yeori.dtomimic.DtoMimic;

@Service
public class SchemaService {

	@Autowired ISchemaDao schemaDao;
	@Autowired DtoMimic dtoMimicker;
	
	@Transactional
	public Schema findSchemaFully(long schemaSeq) {
		Schema schema = schemaDao.getOne(schemaSeq);
		return dtoMimicker.mimic(schema, Schema.class, 
				"owner",
				"tables.schema",
				"tables.columns.table",
				"edges.schema",
				"edges.to.table.schema",
				"edges.to.table.columns",
				"edges.from.table.schema",
				"edges.from.table.columns");
	}

	@Transactional
	public void updateLocation(Long schemaSeq, Double x, Double y) {
		Schema schema = schemaDao.getOne(schemaSeq);
		schema.setX(x);
		schema.setY(y);
	}
}
