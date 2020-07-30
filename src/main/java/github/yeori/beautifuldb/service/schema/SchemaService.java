package github.yeori.beautifuldb.service.schema;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.yeori.beautifuldb.dao.schema.ISchemaDao;
import github.yeori.beautifuldb.model.schema.Schema;
import github.yeori.dtogen.ObjectStamper;

@Service
public class SchemaService {

	@Autowired ISchemaDao schemaDao;
	@Autowired ObjectStamper stamper;
	
	@Transactional
	public Schema findSchemaFully(long schemaSeq) {
		Schema schema = schemaDao.getOne(schemaSeq);
		return stamper.stamp(schema, Schema.class, 
				"owner",
				"tables.schema",
				"tables.columns.table",
				"edges.schema",
				"edges.to.table.schema",
				"edges.to.table.columns",
				"edges.from.table.schema",
				"edges.from.table.columns");
	}
	
}