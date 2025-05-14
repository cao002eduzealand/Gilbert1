package Infrastructure;

import Domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class UserRepositoryImpl implements CrudRepository<User> {


    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @Override
    public User save(User user) {

        String sql = "INSERT INTO user (fName, lName, display_name, user_name, email, password, profile_picture, created_at, last_login," +
                " shipping_full_name, shipping_address, shipping_zip, shipping_city, shipping_country, role_id, company_id)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            preparedStatement.setString(1, user.getfName());
            preparedStatement.setString(2, user.getlName());
            preparedStatement.setString(3, user.getDisplayName());
            preparedStatement.setString(4, user.getUserName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, hashedPassword);
            preparedStatement.setString(7, user.getProfilePictureURL());
            preparedStatement.setTimestamp(8, user.getCreatedAt());
            preparedStatement.setTimestamp(9, user.getLastLogin());
            preparedStatement.setString(10, user.getShippingName());
            preparedStatement.setString(11, user.getAddress());
            preparedStatement.setString(12, user.getZIP());
            preparedStatement.setString(13, user.getCity());
            preparedStatement.setString(14, user.getCountry());
            preparedStatement.setInt(15, user.getRole().getId());
            preparedStatement.setInt(16, user.getCompany().getId());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> findAll(){
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));

    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET fName=?, lName=?, display_name=?, user_name=?, email=?, password=?, profile_picture=?, created_at=?, last_login=?, " +
                "shipping_full_name=?, shipping_address=?, shipping_zip=?, shipping_city=?, shipping_country=?, role_id=?, company_id=? WHERE user=?";
        jdbcTemplate.update(sql, user.getfName(), user.getlName(), user.getDisplayName(), user.getUserName(), user.getEmail(), user.getPassword(),
                user.getProfilePictureURL(), user.getCreatedAt(), user.getLastLogin(), user.getAddress(), user.getZIP(), user.getCity(), user.getCountry(), user.getRole().getId(), user.getCompany().getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM User WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM user WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }




}
