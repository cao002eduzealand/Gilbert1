package Application;


import Domain.User;
import Infrastructure.UserRepositoryImpl;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements ServiceInterface<User> {

    private final UserRepositoryImpl repository;

    public UserServiceImpl(UserRepositoryImpl repository){this.repository = repository;}

    @Override
    public User save(User user) {return repository.save(user);}

    @Override
    public List<User> findAll(){return repository.findAll();}

    @Override
    public void update(User user){ repository.update(user);}

    @Override
    public void delete(int id){repository.delete(id);}

    //public List<Product> getProductsByUser(User user){return repository.getProductsByUser(user);}

    public User findById(int id) {return repository.findById(id);}

    //public List<Product> findAllByBrand(Brand brand){return repository.findAllByBrand(brand);}
}
