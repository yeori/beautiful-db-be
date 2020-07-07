package github.yeori.beatifuldb.model;
/**
 * it represents foreign key (from -> to)
 * @author yeori
 *
 */
public class Edge {

	private Integer seq;
	private Node from;
	private Node to;
	
	public Edge(Integer seq, Integer fromTable, Integer fromCol, Integer toTable, Integer toCol) {
		this(null, new Node(fromTable, fromCol), new Node(toTable, toCol));
	}
	
	public Edge(Integer seq, Node from, Node to) {
		super();
		this.seq = seq;
		this.from = from;
		this.to = to;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Node getFrom() {
		return from;
	}

	public void setFrom(Node from) {
		this.from = from;
	}

	public Node getTo() {
		return to;
	}

	public void setTo(Node to) {
		this.to = to;
	}

	public final static class Node {
		Integer table;
		Integer column;
		Node(Integer table, Integer column) {
			this.table = table;
			this.column = column;
		}
		public Integer getTable() {
			return table;
		}
		public void setTable(Integer table) {
			this.table = table;
		}
		public Integer getColumn() {
			return column;
		}
		public void setColumn(Integer column) {
			this.column = column;
		}
	}
	
}
