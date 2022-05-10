package com.example.demo.repository;

import com.example.demo.entity.member.MemberAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MemberAuthRepository extends JpaRepository<MemberAuth, Long> {

    @Transactional
    @Query("SELECT ma FROM MemberAuth ma WHERE member_no = :memberNo")
    Optional<MemberAuth> findByMemberNo(Long memberNo);
}
