package github.yeori.beautifuldb.dao.schema;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import github.yeori.beautifuldb.model.schema.Column;
import github.yeori.beautifuldb.model.schema.Edge;

public interface IEdgeDao extends JpaRepository<Edge, Long> {

	@EntityGraph(attributePaths ={"seq"}, type=EntityGraphType.FETCH)
	Edge findByFromAndTo(Column srcColumn, Column dstColumn);

	/**
	 * find outgoing edge from the given column 
	 * @param column
	 * @return
	 */
	@EntityGraph(attributePaths ={"seq", "to"}, type=EntityGraphType.FETCH)
	Edge findByFrom(Column column);
	
	@EntityGraph(attributePaths ={"seq"}, type=EntityGraphType.FETCH)
	Edge findBySeq(Long seq);

}
