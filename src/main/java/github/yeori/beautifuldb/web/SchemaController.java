package github.yeori.beautifuldb.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import github.yeori.beautifuldb.BeautDbException;
import github.yeori.beautifuldb.Res;
import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.model.schema.EdgeDto;
import github.yeori.beautifuldb.model.schema.Schema;
import github.yeori.beautifuldb.service.schema.SchemaService;

@RestController
public class SchemaController {

	@Autowired
	SchemaService schemaService;
	/**
	 * README https://www.baeldung.com/get-user-in-spring-security
	 * @param principal
	 * @return
	 */
	@GetMapping("/me")
	public Object listSchema(Principal principal) {
		System.out.println(principal.getName());
		System.out.println(principal.getClass().getName());
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
		System.out.println(token.getPrincipal().getClass().getName());
//		System.out.println(token.getPrincipal().getClass().;
		return Res.success("me", principal);
	}

	@GetMapping("/me2")
	public Object listSchema2(Authentication auth) {
		System.out.println(auth.getName());
		System.out.println(auth.getPrincipal().getClass());
		System.out.println(auth.getPrincipal());
//		System.out.println(token.getPrincipal().getClass().getName());
//		System.out.println(token.getPrincipal().getClass().;
		return Res.success("me", auth);
	}
	
	@GetMapping("/schema/{schemaSeq}")
	public Object loadSchemaFully(@PathVariable Long schemaSeq) {
		Schema schema = schemaService.findSchemaFully(schemaSeq);
		if (schema == null) {
			throw new BeautDbException(404, "NO_SUCH_SCHEMA");
		}
		TypeMap map = new TypeMap();
		return Res.success(
			"schema",
			Res.puts(map,
				"tables", schema.getTables(),
				"edges", EdgeDto.toDto(schema.getEdges()),
				"name", schema.getName(),
				"seq", schema.getSeq()
			)
		);
	}
}
