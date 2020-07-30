package github.yeori.beautifuldb.dao.schema;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import github.yeori.beautifuldb.model.schema.Schema;

public interface ISchemaDao extends JpaRepository<Schema, Long>{

	@EntityGraph("full-schema-graph")
	Schema findBySeq(long schemaSeq);
}
