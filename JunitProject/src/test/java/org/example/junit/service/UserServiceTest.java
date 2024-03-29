package org.example.junit.service;


import org.example.junit.data.UsersRepository;
import org.example.junit.data.UsersRepositoryImpl;
import org.example.junit.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//enable mockito classes & annotations to use mockito in this class
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UsersRepositoryImpl usersRepository;

    @Mock
    EmailVerificationServiceImpl emailVerificationService;

    //to use @InjectMocks we need to provide it on interface implementation
    @InjectMocks
    UserServiceImpl userService;
    String firstName ;
    String lastName ;
    String email ;
    String password;
    String repeatPassword;
    @BeforeEach
    public void init(){
         firstName = "Sergey";
       lastName  = "Kargopolov";
        email = "test@test.com";
        password = "12345678";
      repeatPassword = "12345678";

    }

    @DisplayName("User object created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject1() {
        // Arrange
        UsersRepositoryImpl usersRepository1 = mock(UsersRepositoryImpl.class);
        EmailVerificationServiceImpl emailVerificationService1 = mock(EmailVerificationServiceImpl.class);
        UserService userService1 = new UserServiceImpl(usersRepository1,emailVerificationService1);

        // UserService userService = new UserServiceImpl(usersRepository);
        //this statement needs to be added first because when mockito will execute userRepository.save(user);
        //method first we need to tell mockito what to do when you want to execute repository save method
        when(usersRepository1.save(any(User.class))).thenReturn(true);
        doCallRealMethod().when(emailVerificationService1).scheduleEmailConfirmation(any(User.class));
        //doNothing().when(emailVerificationService1).scheduleEmailConfirmation(any(User.class));

        // Act
        User user = userService1.createUser(firstName, lastName, email, password, repeatPassword);

        // Assert
        assertNotNull(user, "The createUser() should not have returned null");
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect.");
        assertEquals(lastName, user.getLastName(), "User's last name is incorrect");
        assertEquals(email, user.getEmail(), "User's email is incorrect");
        assertNotNull(user.getId());
        //Mockito.verify will verify that userRepository.save(user) method is exactly called 1 time
        //if due to some reason it is calling more than 1 more than test case fails
        verify(usersRepository1,times(1))
                .save(any(User.class));
        verify(emailVerificationService1,times(1)).scheduleEmailConfirmation(any(User.class));

    }

    @DisplayName("User object created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject() {
        // Arrange
       // UserService userService = new UserServiceImpl(usersRepository);
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
        when(usersRepository.save(any(User.class))).thenThrow(RuntimeException.class);
        // Assert
        assertThrows(UserServiceException.class,()->{
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        });

    }

    @Test
    @DisplayName("EmailNotificationException is handled")
    void testCreateUser_whenEmailNotificationExceptionThrown_throwsUserServiceException() {
        // Arrange
        when(usersRepository.save(any(User.class))).thenReturn(true);

        doThrow(EmailNotificationServiceException.class)
                .when(emailVerificationService)
                        .scheduleEmailConfirmation(any(User.class));


       // doNothing().when(emailVerificationService).scheduleEmailConfirmation(any(User.class));

        // Act & Assert
        assertThrows(UserServiceException.class, ()-> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        }, "Should have thrown UserServiceException instead");

        // Assert
        verify(emailVerificationService, times(1)).
                scheduleEmailConfirmation(any(User.class));

    }

    @DisplayName("Schedule Email Confirmation is executed")
    @Test
    void testCreateUser_whenUserCreated_schedulesEmailConfirmation() {
        // Arrange
        //when(usersRepository.save(any(User.class))).thenReturn(true);
        given(usersRepository.save(any(User.class))).willReturn(true);

        doCallRealMethod().when(emailVerificationService)
                .scheduleEmailConfirmation(any(User.class));

        // Act
        userService.createUser(firstName, lastName, email, password, repeatPassword);

        // Assert
        verify(emailVerificationService, times(1))
                .scheduleEmailConfirmation(any(User.class));
    }

    @Test
    void test_insertUser2(){

        UsersRepository usersRepository1 = mock(UsersRepositoryImpl.class);
        EmailVerificationService emailVerificationService1 = mock(EmailVerificationServiceImpl.class);
        UserService userService1 = new UserServiceImpl(usersRepository1,emailVerificationService1);

        List<User> userList = Arrays.asList(new User(firstName,lastName,email,"user1"));
        System.out.println(userService1);
      //  doNothing().when(usersRepository1).insert(userList.get(0));
        doCallRealMethod().when(usersRepository1).insert(userList.get(0));
        userService1.insertUser(userList.get(0));
        verify(usersRepository1,times(1)).insert(any(User.class));
       // verify(userService1,times(1)).insertUser(any(User.class));
      //  verify(userService1).insertUser(any(User.class));
    }
    @Test
    void test_insertUser(){

        //Arrange
        List<User> userList = Arrays.asList(new User(firstName,lastName,email,"user1"));
        UsersRepositoryImpl usersRepository1 = mock(UsersRepositoryImpl.class);
        UserService userService = mock(UserServiceImpl.class);

        //usersRepository1,mock(emailVerificationService.getClass()));
        doNothing().when(usersRepository1).insert(any(User.class));
     //   doCallRealMethod().when(usersRepository1).insert(any(User.class));
        doCallRealMethod().when(userService).insertUser(any(User.class));


        userService.insertUser(userList.get(0));

        verify(userService,times(1))
                .insertUser(any(User.class));

        verify(usersRepository,times(1)).insert(any(User.class));

    }
}
