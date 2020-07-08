package github.yeori.beautifuldb.dao.naming;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class DaoNamingStrategy extends SpringPhysicalNamingStrategy {

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		Identifier id = super.toPhysicalTableName(name, jdbcEnvironment);
		String tableName = id.getText().toLowerCase();
		return Identifier.toIdentifier(tableName + 's');
	}
}
