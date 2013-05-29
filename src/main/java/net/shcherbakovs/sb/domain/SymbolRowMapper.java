package net.shcherbakovs.sb.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * A {@link RowMapper} implementation used to map rows from the
 * Bar database table to the {@link Bar} class.
 * 
 * @author Sergey Shcherbakov
 *
 */
public class SymbolRowMapper implements RowMapper<Symbol> {

	@Override
	public Symbol mapRow(ResultSet rs, int rowNum) throws SQLException {
		Symbol result = new Symbol();
		result.setSymbol( rs.getString("symbol") );
		result.setName( rs.getString("name") );
		return result;
	}

}
