## UserDetailsManager

In Spring Security, the `UserDetailsManager` interface is used for managing user account details within an application. It provides operations for creating, updating, deleting, and retrieving user account information.

The `UserDetailsManager` interface extends the `UserDetailsService` interface, which is responsible for loading user-specific data during the authentication process. However, the `UserDetailsManager` offers additional functionality for managing user accounts.

Key methods provided by the `UserDetailsManager` interface include:

- `createUser(UserDetails user)`: Creates a new user account.
- `updateUser(UserDetails user)`: Updates an existing user account.
- `deleteUser(String username)`: Deletes a user account.
- `changePassword(String oldPassword, String newPassword)`: Changes the password for the currently authenticated user.
- `userExists(String username)`: Checks if a user account with the specified username exists.
- `loadUserByUsername(String username)`: Loads the user details for the specified username.

By implementing the `UserDetailsManager` interface, you can perform user management operations within your application. It provides a convenient way to create, update, and delete user accounts, as well as retrieve user details when needed.

You can utilize various implementations for the `UserDetailsManager` interface provided by Spring Security, such as `InMemoryUserDetailsManager` or `JdbcUserDetailsManager`, or create a custom implementation to suit your requirements.

The `UserDetailsManager` interface plays a crucial role in managing user accounts and offers a high-level abstraction for user management operations within your Spring Security-enabled application.

For more information, please refer to the [official Spring Security documentation](https://docs.spring.io/spring-security/site/docs/current/reference/html5/#userdetailsmanager).

In this project we used in memory h2 database. We first created UserEntity.

```java
package com.jawwad.ss_l3.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;
}
```
As this entity stores user's information, we can use this table
to load user details to authenticate.

We will implement the UserDetailsManager. 

```java
package com.jawwad.ss_l3.config;

import com.jawwad.ss_l3.domain.User;
import com.jawwad.ss_l3.entity.UserEntity;
import com.jawwad.ss_l3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public void createUser(UserDetails user) {
        var userEntity = new UserEntity().setPassword(user.getPassword())
                .setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserDetails user) {
        var existingUser = this.userRepository.findByUsernameAndEnabled(user.getUsername(), 1)
                .orElseThrow();
        existingUser.setPassword(user.getPassword())
                .setUsername(user.getUsername());
        this.userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String username) {
        this.userRepository.deleteByUsername(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userRepository.findByUsernameAndEnabled(username, 1)
                .orElseThrow();
        return new User(user);
    }
}

```
We've loaded all the user from the database using the username
and returning it as userDetails object. We are using our
custom User class for this wrapper class.

The User class we've crated implements the  UserDetails interface.

After creating the MyUserDetailsService, we've created the bean in 
`ProjectConfig` class. 
A controller class has been created to create new user and authenticate
it from our own custom user class. You can find it in the controller
class.
