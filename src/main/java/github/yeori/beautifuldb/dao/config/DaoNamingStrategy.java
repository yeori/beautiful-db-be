package github.yeori.beautifuldb.dao.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

import github.yeori.beautifuldb.Util;

public class DaoNamingStrategy extends SpringPhysicalNamingStrategy {

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment env) {
		// Identifier id = super.toPhysicalTableName(name, env);
		String tableName = Util.camelToSnake(name.getText(), "s").toLowerCase();
		return Identifier.toIdentifier(tableName, name.isQuoted());
	}
}
