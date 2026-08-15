package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.ReservationDTO;
import lk.ijse.userservice.dto.UserHistoryDTO;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.repo.UserRepository;
import lk.ijse.userservice.util.ParkingSpaceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingSpaceClient parkingSpaceClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        String password = user.get().getPassword() == null ? "" : user.get().getPassword();

        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                password,
                getAuthority(user.orElse(null))
        );
    }

    public User registerUser(User user) {
        // implement registration logic
        return userRepository.save(user);
    }

    public String login(String username, String password) {
        // implement authentication and return token
        return null;
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return authorities;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            // Update fields - only update if new value is not null to avoid overwriting with null
            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getRole() != null) {
                existingUser.setRole(user.getRole());
            }
            if (user.getFullName() != null) {
                existingUser.setFullName(user.getFullName());
            }
            if (user.getPhone() != null) {
                existingUser.setPhone(user.getPhone());
            }
            // Note: createdAt is not updated as it is set on creation

            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserHistoryDTO getUserHistory(Long userId) {
        List<ReservationDTO> reservations = parkingSpaceClient.getReservationsByUser(userId);
        return new UserHistoryDTO(userId, reservations);
    }
}