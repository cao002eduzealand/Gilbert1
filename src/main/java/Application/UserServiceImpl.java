package Application;


import Domain.Role;
import Domain.User;
import Infrastructure.UserRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements ServiceInterface<User> {
    private final Path uploadPath = Paths.get("src/main/resources/static/uploads");


    private final UserRepositoryImpl repository;


   public UserServiceImpl(UserRepositoryImpl repository) {
       this.repository = repository;
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

//    public void updateProfilePicture(int userId, String profilePictureURL) {
//        userRepositoryImpl.updateProfilePicture(userId, profilePictureURL);
//    }

    //public List<Product> findAllByBrand(Brand brand){return repository.findAllByBrand(brand);}

    public String saveProfileImage(User user, MultipartFile image) throws IOException {
        // Ensure uploads folder exists
        if (Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Clean filename
        String originalFilename = StringUtils.cleanPath(image.getOriginalFilename());
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = "profile_" + user.getId() + "_" + UUID.randomUUID() + extension;

        // Save file
        Path filePath = uploadPath.resolve(newFilename);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Generate relative URL
        String imageUrl = "/uploads/" + newFilename;

        // Update user
        user.setProfilePictureURL(imageUrl);

        repository.updateProfilePicture(user.getId(), imageUrl);

        return imageUrl;
    }
}
