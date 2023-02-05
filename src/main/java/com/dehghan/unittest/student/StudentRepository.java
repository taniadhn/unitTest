package com.dehghan.unittest.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {


    //?
   @Query("" +
           "SELECT CASE WHEN COUNT(s) > 0 THEN " +
           "TRUE ELSE FALSE END " +
           "FROM StudentEntity s " +
           "WHERE s.email = ?1"
   )

    Boolean selectExistEmail(String email);

}
