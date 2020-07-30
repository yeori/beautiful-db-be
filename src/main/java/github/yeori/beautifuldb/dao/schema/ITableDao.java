package github.yeori.beautifuldb.dao.schema;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import github.yeori.beautifuldb.model.schema.Column;
import github.yeori.beautifuldb.model.schema.Schema;
import github.yeori.beautifuldb.model.schema.Table;

public interface ITableDao extends JpaRepository<Table, Long> {

	@EntityGraph(attributePaths = {"seq", "name", "columns", "encoding"}, type = EntityGraphType.FETCH)
	Table findBySeq(Long tableSeq);
	
	@Query("select max(c) from Column c where c.table = ?1")
	Column findLastColumn(Table table);

}
