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
		String prop = req.getStr("prop");
		Object value = req.get("value");
		Object data = null;
		if ("TYPE".equals(prop.toUpperCase())) {
			data = columnService.updateType(columnSeq, prop, (String)value);
		} else {
			data = columnService.updateColumn(columnSeq, prop, value);
			
		}
		return Res.with(true, "prop", prop, "value", data);
	}
	@PutMapping("/column/{columnSeq}/meta")
	public Object updateColumnMeta(@PathVariable Long columnSeq, @RequestBody TypeMap req) {
		String prop = req.getStr("prop");
		Object value = req.get("value");
		columnService.upateColumnMeta(columnSeq, prop, value);
		return Res.success(true);
	}
}
