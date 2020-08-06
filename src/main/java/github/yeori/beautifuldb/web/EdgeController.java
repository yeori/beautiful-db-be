package github.yeori.beautifuldb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.yeori.beautifuldb.Res;
import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.model.schema.Edge;
import github.yeori.beautifuldb.service.schema.EdgeService;

@RestController
@RequestMapping("/edges")
public class EdgeController {
	
	@Autowired EdgeService edgeService;
	
	@PostMapping("/new")
	public Object addEdge(@RequestBody TypeMap req) {
		Long referred = req.asLong("parent");
		Long fkColumnSeq = req.asLong("child");
		Long edgeToDel = req.asLong("edgeToDel");
		Edge edge = edgeService.createEdge(fkColumnSeq, referred, edgeToDel);
		return Res.success("edge", edge);
	}
}
