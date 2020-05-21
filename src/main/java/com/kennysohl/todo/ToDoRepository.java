package com.kennysohl.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ToDoRepository {
    private Logger logger = LoggerFactory.getLogger(ToDoRepository.class);
    private DataSource dataSource;

    public ToDoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //The save message method.
    public ToDo saveToDos(ToDo toDo) {
        Connection c = DataSourceUtils.getConnection(dataSource);
        try {
            String insertSql = "INSERT INTO todos (`id`, `text`) VALUE (null, ?)";
            PreparedStatement ps = c.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            // Prepare the parameters for the SQL
            ps.setString(1, toDo.getText());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                // Getting the newly saved message id
                ResultSet result = ps.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    return new ToDo(id, toDo.getText());
                } else {
                    logger.error("Failed to retrieve id. No row in resultset");
                    return null;
                }
            } else {
                // Insert did not succeed
                return null;
            }
            } catch (SQLException ex) {
                logger.error("Failed to save message", ex);
                try {
                    c.close();
                    } catch (SQLException e) {
                    logger.error("Failed to close connection", e);
                    }
                } finally {
                DataSourceUtils.releaseConnection(c, dataSource);
                }
            return null;
            }

    //The select all to do list method
    public List<ToDo> getToDos() {
        Connection c = DataSourceUtils.getConnection(dataSource);

        String insertSql = "SELECT * FROM todos";
        try {
            PreparedStatement ps = c.prepareStatement(insertSql);
            ResultSet resultSet = ps.executeQuery();

            List<ToDo> todos = new ArrayList<ToDo>();
            int indexId = resultSet.findColumn("id");
            int indexText = resultSet.findColumn("text");
            while(resultSet.next()){
                int id = resultSet.getInt(indexId);
                String message = resultSet.getString(indexText);
                todos.add(new ToDo(id, message));
            }
            return todos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //The delete a list item from the databas method.
    public boolean deleteToDos(int id) {
        try {
            Connection c = DataSourceUtils.getConnection(dataSource);
            String insertSql = "DELETE FROM todos WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            // Prepare the parameters for the SQL
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            if(rowsDeleted <= 0) return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
