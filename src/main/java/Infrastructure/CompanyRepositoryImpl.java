package Infrastructure;

import Domain.Company;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CompanyRepositoryImpl implements CrudRepository<Company> {

    private final JdbcTemplate jdbcTemplate;

    public CompanyRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public Company save(Company company){
        String sql = "INSERT INTO company(company_name, VAT_number) VALUES(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

     jdbcTemplate.update(connection ->{
         PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
         preparedStatement.setString(1, company.getCompanyName());
         preparedStatement.setString(2, company.getCompanyCVRNumber());
         return preparedStatement;
     }, keyHolder);
     if (keyHolder.getKey()!=null){
         company.setId(keyHolder.getKey().intValue());

     }
     return company;
    }

    @Override
    public List<Company> findAll(){
        String sql = "SELECT * from company";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Company.class));
    }

    @Override
    public void update(Company company){
        String sql = "UPDATE company SET company_name=?, VAT_number=? WHERE id=?";
        jdbcTemplate.update(sql, company.getCompanyName(), company.getCompanyCVRNumber(), company.getId());
    }

    @Override
    public void delete(int id){
        String sql="DELETE FROM company where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Company findById(int id){
        String sql = "SELECT * FROM company WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Company.class), id);
    }




}
