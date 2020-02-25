package kg.exam.dao;



import kg.exam.model.Requests;
import kg.exam.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public User createUser(User user) {
        String SQL = "insert into users_exam (name, age,gender) values (?,?,?)";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, user.getName());
            statement.setString(3, user.getGender());
            statement.setInt(2, user.getAge());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int id) {
        String SQL = "select * from users_exam where id = ?";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return User.builder()
                            .id(rs.getInt("ID"))
                            .name(rs.getString("NAME"))
                            .age(rs.getInt("AGE"))
                            .gender(rs.getString("GENDER"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Requests addRequest(int id) {
        String SQL = "insert into requests (users_add,dateofcreated) values (?,now())";
        try (Connection connection = DB.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Requests.builder()
                            .id(resultSet.getInt("ID"))
                            .user(getUserById(resultSet.getInt("USERS_ADD")))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Requests> getAllReq() {
        List<Requests> requests = new ArrayList<>();
        String SQL = "select * from requests";
        try (Connection connection = DB.connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL)) {
            while(rs.next()) {
                requests.add(Requests.builder()
                        .id(rs.getInt("ID"))
                        .user(getUserById(rs.getInt("USERS_ADD")))
                        .date(rs.getTimestamp("dateOfCreated"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public Boolean deleteReqById(int id){
        String SQL = "delete from requests where id = ?";
        try(Connection connection = DB.connect();) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
