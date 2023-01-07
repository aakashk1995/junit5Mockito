package org.example.service;


import org.example.data.UsersRepository;
import org.example.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//enable mockito classes & annotations to use mockito in this class
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UsersRepository usersRepository;

    //to use @InjectMocks we need to provide it on interface implementation
    @InjectMocks
    UserServiceImpl userService;

    @DisplayName("User object created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
        // Arrange
       // UserService userService = new UserServiceImpl(usersRepository);
        String firstName = "Sergey";
        String lastName  = "Kargopolov";
        String email = "test@test.com";
        String password = "12345678";
        String repeatPassword = "12345678";
        //this statement needs to be added first because when mockito will execute userRepository.save(user);
        //method first we need to tell mockito what to do when you want to execute repository save method
        when(usersRepository.save(any(User.class))).thenReturn(true);

        // Act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        // Assert
        assertNotNull(user, "The createUser() should not have returned null");
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect.");
        assertEquals(lastName, user.getLastName(), "User's last name is incorrect");
        assertEquals(email, user.getEmail(), "User's email is incorrect");
        assertNotNull(user.getId());
        //Mockito.verify will verify that userRepository.save(user) method is exactly called 1 time
        //if due to some reason it is calling more than 1 more than test case fails
        verify(usersRepository,times(1))
                .save(any(User.class));




    }

    @DisplayName("save method return exception")
    @Test
    void testCreateUser_whenSaveMethodThrowsException_returnsUserServiceException() {
        // Arrange
        // UserService userService = new UserServiceImpl(usersRepository);
        String firstName = "Sergey";
        String lastName  = "Kargopolov";
        String email = "test@test.com";
        String password = "12345678";
        String repeatPassword = "12345678";

        when(usersRepository.save(any(User.class))).thenThrow(RuntimeException.class);



        // Assert
        assertThrows(UserServiceException.class,()->{
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        });





    }
}
