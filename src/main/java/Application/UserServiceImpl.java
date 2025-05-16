package Application;


import Domain.User;
import Infrastructure.UserRepositoryImpl;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements ServiceInterface<User> {

    private final UserRepositoryImpl repository;
    private final UserRepositoryImpl userRepositoryImpl;

    public UserServiceImpl(UserRepositoryImpl repository, UserRepositoryImpl userRepositoryImpl){this.repository = repository;
        this.userRepositoryImpl = userRepositoryImpl;
    }

    @Override
    public User save(User user) {return repository.save(user);}

    @Override
    public List<User> findAll(){return repository.findAll();}

    @Override
    public void update(User user){ repository.update(user);}

    @Override
    public void delete(int id){repository.delete(id);}


    @Override
    public User findById(int id) {return repository.findById(id);}

    public User authenticeUser(String username, String password){return repository.authenticateUser(username, password);}

    public void updateProfilePicture(int userId, String profilePictureURL) {
        userRepositoryImpl.updateProfilePicture(userId, profilePictureURL);
    }

    //public List<Product> findAllByBrand(Brand brand){return repository.findAllByBrand(brand);}
}
