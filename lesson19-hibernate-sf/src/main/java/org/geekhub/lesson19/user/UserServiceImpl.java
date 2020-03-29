package org.geekhub.lesson19.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(int userId) {
        final Optional<User> user = userRepository.findBy(userId);
        user.ifPresent(userRepository::delete);
    }
}
