package com.tours.tourisme.service;

import com.tours.tourisme.model.BoundedPageSize;
import com.tours.tourisme.model.PageFromOne;
import com.tours.tourisme.model.exception.NotFoundException;
import com.tours.tourisme.repository.UserRepository;
import com.tours.tourisme.repository.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getAllUser(PageFromOne page, BoundedPageSize pageSize){
        Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
        return repository.findAllUser(pageable);
    }

    public User getUserById(Long uid){
        return repository
                .findById(uid)
                .orElseThrow(() -> new NotFoundException("User with id "+ uid + " not found"));
    }

    public User saveUser(User user){
        return repository.save(user);
    }

    public  User crupdateUser(Long uid, User user){
        Optional<User> optionalUser = repository.findById(uid);
        if(optionalUser.isPresent()){
            User userToUpdate = optionalUser.get();
            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());
            return repository.save(userToUpdate);
        }else {
            return saveUser(user);
        }
    }

    public User deleteUserById(Long uid){
        User userToDelete = getUserById(uid);
        repository.delete(userToDelete);
        return userToDelete;
    }
}
