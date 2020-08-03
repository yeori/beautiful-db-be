package github.yeori.beautifuldb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import github.yeori.beautifuldb.Res;
import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.service.schema.ColumnService;

@RestController
public class ColumnController {

	@Autowired
	ColumnService columnService;
	
	@PutMapping("/columns/position/{seqA}/{seqB}")
	public Object exchangePosition(@PathVariable Long seqA, @PathVariable Long seqB) {
		boolean success = columnService.updateOrderNum(seqA, seqB);
		return Res.success("a", seqA, "b", seqB);
	}
	
	@PutMapping("/column/{columnSeq}")
	public Object updateColumn(@PathVariable Long columnSeq, @RequestBody TypeMap req) {
		int s = columnService.updateColumn(columnSeq, req.getStr("prop"), req.get("value"));
		return Res.with(true, "data", s);
	}
}
