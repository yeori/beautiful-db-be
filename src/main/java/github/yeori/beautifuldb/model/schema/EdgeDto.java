package github.yeori.beautifuldb.model.schema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class EdgeDto {

	private Long seq;
	private Node from;
	private Node to;
	
	public EdgeDto() {
	}
	public Long getSeq() {
		return seq;
	}
	public Node getFrom() {
		return from;
	}
	public Node getTo() {
		return to;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgeDto other = (EdgeDto) obj;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}

	public static EdgeDto toDto(Edge edge) {
		EdgeDto dto = new EdgeDto();
		dto.seq = edge.getSeq();
		dto.from = Node.init(edge.getFrom());
		dto.to = Node.init(edge.getTo());
		return dto;
	}
	public static Collection<EdgeDto> toDto(Collection<Edge> edges) {
		List<EdgeDto> dtos = new ArrayList<>(edges.size());
		for (Edge edge : edges) {
			dtos.add(EdgeDto.toDto(edge));
		}
		return dtos;
	}
	
	public static class Node  {
		private Long table;
		private Long column;
		public Long getTable() {
			return table;
		}
		public void setTable(Long table) {
			this.table = table;
		}
		public Long getColumn() {
			return column;
		}
		public void setColumn(Long column) {
			this.column = column;
		}
		public static Node init(Column column) {
			Node n = new Node();
			n.table = column.getTable().getSeq();
			n.column = column.getSeq();
			return n;
		}
	}
}
