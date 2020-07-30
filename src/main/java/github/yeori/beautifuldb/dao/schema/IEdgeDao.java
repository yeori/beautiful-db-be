package github.yeori.beautifuldb.dao.schema;

import org.springframework.data.jpa.repository.JpaRepository;

import github.yeori.beautifuldb.model.schema.Column;
import github.yeori.beautifuldb.model.schema.Edge;

public interface IEdgeDao extends JpaRepository<Edge, Long> {

	Edge findByFromAndTo(Column srcColumn, Column dstColumn);

	/**
	 * find outgoing edge from the given column 
	 * @param column
	 * @return
	 */
	Edge findByFrom(Column column);

}
