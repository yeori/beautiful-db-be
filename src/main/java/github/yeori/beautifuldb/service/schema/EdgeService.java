package github.yeori.beautifuldb.service.schema;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.yeori.beautifuldb.BeautDbException;
import github.yeori.beautifuldb.Error;
import github.yeori.beautifuldb.dao.schema.IColumnDao;
import github.yeori.beautifuldb.dao.schema.IEdgeDao;
import github.yeori.beautifuldb.model.schema.Column;
import github.yeori.beautifuldb.model.schema.Edge;
import github.yeori.beautifuldb.model.schema.Schema;
import github.yeori.dtomimic.DtoMimic;

@Service
public class EdgeService {

	@Autowired IEdgeDao edgeDao;
	@Autowired IColumnDao columnDao;
	@Autowired DtoMimic dtoMimicker;
	
	@Transactional
	public Edge createEdge(Long fkColumnSeq, Long referredColumnSeq, Long edgeToDel) {
		if (edgeToDel != null) {
			Edge edge = edgeDao.findBySeq(edgeToDel);
			edgeDao.delete(edge);
		}
		return createEdge(fkColumnSeq, referredColumnSeq);
	}
	@Transactional
	public Edge createEdge(Long fkColumnSeq, Long referredColumnSeq) {
		Column fkColumn = columnDao.findById(fkColumnSeq).orElse(null);
		checkExist(fkColumn, fkColumnSeq);
		
		Column referredColumn = columnDao.findById(referredColumnSeq).orElse(null);
		checkExist(referredColumn, referredColumnSeq);
		
		checkSameSchema(fkColumn, referredColumn);
		
		Edge exsting = edgeDao.findByFromAndTo(referredColumn, fkColumn);
		if (exsting != null) {
			throw new BeautDbException(409, Error.EXISTING_REVERSE_EDGE.name());
		}
		Edge outgoingEdge = edgeDao.findByFrom(fkColumn);
		if (outgoingEdge != null) {
			Column referred = outgoingEdge.getTo();
			throw new BeautDbException(409,
				Error.EXISTING_FOREIGN_KEY.name(),
				"already referencing column %s at table %s", 
				referred.getName(), referred.getTable().getName());			
		}
		
		Edge edge = new Edge(fkColumn, referredColumn);
		edge.setSchema(fkColumn.getTable().getSchema());
		edgeDao.save(edge);
		return dtoMimicker.mimic(edge, Edge.class,
				"from",
				"to",
				"schema");
	}

	private void checkExist(Column col, long seq) {
		if (col == null) {
			throw new BeautDbException(404, Error.NOT_FOUND.name() , "no such column: [%d]", seq);
		}
	}
	private void checkSameSchema(Column col, Column ... cols) {
		Schema s = col.getTable().getSchema();
		for (Column c : cols) {
			Schema s0 = c.getTable().getSchema();
			if (!s0.equals(s)) {
				throw new BeautDbException(
					409, 
					"DIFFERENT_SCHEMA",
					"column %s belongs to different schema", c.getName());
			}
		}
	}
}
