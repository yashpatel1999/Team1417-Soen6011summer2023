package com.application.careerserviceapplication.repositories;

import com.application.careerserviceapplication.models.UserLogin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LoginRepositoryTest {

   @Autowired
   private LoginRepository loginRepositoryTest;

    @Test
    void getUserLoginByuid() {
        UserLogin userLogin =new UserLogin();
        userLogin.setUid("1@gmail.com");
        userLogin.setPassword("12121212");
        userLogin.setType("C");
        userLogin.setApproved("Y");
        loginRepositoryTest.save(userLogin);

        UserLogin userLogin1 = loginRepositoryTest.getUserLoginByuid(userLogin.getUid());

        assertEquals(userLogin.getUid(),userLogin1.getUid());
    }
}