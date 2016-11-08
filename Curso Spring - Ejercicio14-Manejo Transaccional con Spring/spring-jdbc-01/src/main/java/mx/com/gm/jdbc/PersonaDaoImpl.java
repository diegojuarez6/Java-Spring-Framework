package mx.com.gm.jdbc;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaDaoImpl implements PersonaDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		// No es com�n que se utilicen las 2 plantillas, sin embargo si es
		// posible
		// La diferencia es el manejo de par�metros por indice o por nombre
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	// Query con Parametros por nombre
	// Omitimos la PK ya que es autoincrementable
	private static final String SQL_INSERT_PERSONA = "INSERT INTO PERSONA (nombre, ape_paterno, ape_materno, email) values (:nombre,:apePaterno, :apeMaterno, :email)";
	// Query con Parametros por indice
	// private static final String SQL_INSERT_PERSONA =
	// "insert into persona (username, password, fullname, email,
	// update_by_email) values (?, ?, ?, ?, ?)";
	// Parametros por nombre
	private static final String SQL_UPDATE_PERSONA = "UPDATE PERSONA set nombre = :nombre, ape_paterno = :apePaterno, ape_materno =:apeMaterno, email = :email WHERE id_persona = :idPersona";
	private static final String SQL_DELETE_PERSONA = "DELETE FROM PERSONA WHERE id_persona = :idPersona";
	private static final String SQL_SELECT_PERSONA = "SELECT id_persona, nombre, ape_paterno, ape_materno, email FROM PERSONA";
	// Parametros por indice
	private static final String SQL_SELECT_PERSONA_BY_ID = SQL_SELECT_PERSONA + " WHERE id_persona = ?";

	@Override
	public void insertPersona(Persona persona) {
	}

	@Override
	public void updatePersona(Persona persona) {
	}

	@Override
	public void deletePersona(Persona persona) {
	}

	@Override
	public Persona findPersonaById(long idPersona) {
		return null;
	}

	@Override
	public List<Persona> findAllPersonas() {
		// Esta consulta es equivalente
		// String sql = "SELECT * FROM PERSONA";
		RowMapper<Persona> personaRowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Persona.class);
		return this.jdbcTemplate.query(SQL_SELECT_PERSONA, personaRowMapper);
	}

	@Override
	public int contadorPersonasPorNombre(Persona persona) {
		return 0;
	}

	@Override
	public int contadorPersonas() {
		String sql = "SELECT count(*) FROM PERSONA";
		return this.jdbcTemplate.queryForInt(sql);
		// Esta es otra opcion si no tuvieramos jdbcTemplate
		// return
		// this.namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql);
	}

	@Override
	public Persona getPersonaByEmail(Persona persona) {
		return null;
	}
}