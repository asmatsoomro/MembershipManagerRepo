package com.membermanager.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional

public interface MemberRepository extends JpaRepository<Member, String> {

    List<Member> findByFirstName(String firstName);

    @Modifying
    @Query("UPDATE Member m SET m.dateOfBirth = :dateOfBirth, m.zipCode = :zipCode WHERE m.firstName = :firstName AND m.lastName = :lastName")
    int updateMember(@Param("firstName") String firstName, @Param("lastName") String lastName,
                      @Param("dateOfBirth") Date dateOfBirth, @Param("zipCode") int zipCode );
}