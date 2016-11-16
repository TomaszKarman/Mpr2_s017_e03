package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.model.EnumDictionary;

public class EnumDictionaryRepository extends RepositoryBase {

	String insertSql = "INSERT INTO enumDictionary(intKey, stringKey, value, enumerationName) VALUES (?,?,?,?)";
	String selectByIdSql = "SELECT * FROM enumDictionary WHERE id=?";
	String deleteSql = "DELETE FROM enumDictionary WHERE id=?";
	String updateSql = "UPDATE enumDictionary SET (intKey, stringKey, value, enumerationName)=(?,?,?,?) WHERE id=?";
	String selectAllSql = "SELECT * FROM enumDictionary";

	PreparedStatement insert;
	PreparedStatement selectById;
	PreparedStatement update;
	PreparedStatement delete;
	PreparedStatement selectAll;

	public EnumDictionaryRepository(Connection connection) {
		super(connection);
		try {
			insert = connection.prepareStatement(insertSql);
			selectById = connection.prepareStatement(selectByIdSql);
			delete = connection.prepareStatement(deleteSql);
			update = connection.prepareStatement(updateSql);
			selectAll = connection.prepareStatement(selectAllSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public EnumDictionary get(int enumDictionaryId) {
		try {
			selectById.setInt(1, enumDictionaryId);
			ResultSet rs = selectById.executeQuery();
			while (rs.next()) {
				EnumDictionary result = new EnumDictionary();
				result.setId(rs.getInt("id"));
				result.setIntKey(rs.getInt("intKey"));
				result.setStringKey(rs.getString("stringKey"));
				result.setValue(rs.getString("value"));
				result.setEnumerationName(rs.getString("enumerationName"));
				return result;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public void add(EnumDictionary enumDictionary) {
		try {
			insert.setInt(1, enumDictionary.getIntKey());
			insert.setString(2, enumDictionary.getStringKey());
			insert.setString(3, enumDictionary.getValue());
			insert.setString(4, enumDictionary.getEnumerationName());
			insert.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void delete(EnumDictionary enumDictionary) {
		try {
			delete.setInt(1, enumDictionary.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(EnumDictionary enumDictionary) {
		try {
			update.setInt(1, enumDictionary.getIntKey());
			update.setString(2, enumDictionary.getStringKey());
			update.setString(3, enumDictionary.getValue());
			update.setString(4, enumDictionary.getEnumerationName());
			update.setInt(5, enumDictionary.getId());
			update.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<EnumDictionary> getAll() {
		try {
			ResultSet rs = selectAll.executeQuery();
			List<EnumDictionary> result = new ArrayList<EnumDictionary>();
			while (rs.next()) {
				EnumDictionary enumDictionary = new EnumDictionary();
				enumDictionary.setId(rs.getInt("id"));
				enumDictionary.setIntKey(rs.getInt("intKey"));
				enumDictionary.setStringKey(rs.getString("stringKey"));
				enumDictionary.setValue(rs.getString("value"));
				enumDictionary.setEnumerationName(rs
						.getString("enumerationName"));
				result.add(enumDictionary);
			}
			return result;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	protected String createTableSql() {
		return "CREATE TABLE enumDictionary("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
				+ "intKey bigint," + "stringKey varchar(50),"
				+ "value varchar(50)," + "enumerationName varchar(50)" + ")";
	}

	@Override
	protected String tableName() {
		return "enumDictionary";
	}

}