package com.application.careerserviceapplication.repositories;

import com.application.careerserviceapplication.models.UserLogin;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<UserLogin,String> {

    @Query("SELECT us from user_login where us.user_id=?1")
    public UserLogin getUserLoginByuid(String userid);

}
