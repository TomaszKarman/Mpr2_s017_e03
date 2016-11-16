package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.PDLOverrideSupported;

import domain.model.Person;

public class PersonRepository extends RepositoryBase<Person> {

	public PersonRepository(Connection connection) {
		super(connection);
	}

	public List<Person> getAll() {
		try {
			ResultSet rs = selectAll.executeQuery();
			List<Person> result = new ArrayList<Person>();
			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setName(rs.getString("name"));
				person.setSurname(rs.getString("surname"));
				result.add(person);
			}
			return result;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Person get(int personId) {
		try {
			selectById.setInt(1, personId);
			ResultSet rs = selectById.executeQuery();
			while (rs.next()) {
				Person result = new Person();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setSurname(rs.getString("surname"));
				return result;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;

	}

	@Override
	protected String createTableSql() {
		return "" + "CREATE TABLE people("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
				+ "name varchar(20)," + "surname varchar(50)" + ")";
	}

	@Override
	protected String tableName() {
		return "people";
	}

	protected String insertSql() {
		return "INSERT INTO people(name, surname) VALUES (?,?)";
	}

	protected String updateSql() {
		return "UPDATE people SET (name, surname)=(?,?) WHERE id=?";
	}


	@Override
	protected void setUpdate(Person entity) throws SQLException {
		update.setString(1, entity.getName());
		update.setString(2, entity.getSurname());
		
	}

	@Override
	protected void setInsert(Person entity) throws SQLException {
		insert.setString(1, entity.getName());
		insert.setString(2, entity.getSurname());
	}
	
}
