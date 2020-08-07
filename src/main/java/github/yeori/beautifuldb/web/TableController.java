package github.yeori.beautifuldb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import github.yeori.beautifuldb.Res;
import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.service.schema.TableService;

@RestController
public class TableController {

	@Autowired TableService tableService;
	@PutMapping("/table/{tableSeq}")
	public Object updateTable(@PathVariable Long tableSeq, @RequestBody TypeMap req) {
		String prop = req.get("prop");
		Object value = req.get("value");
		tableService.updateTable(tableSeq, prop, value);
		System.out.println(value.getClass().getName() + ", " + value);
		return Res.success(true);
	}
}
