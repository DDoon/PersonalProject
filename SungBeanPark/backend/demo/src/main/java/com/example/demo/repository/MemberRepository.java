package com.example.demo.repository;


import com.example.demo.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Transactional
    @Query("SELECT m FROM Member m where m.id = :id")
    Optional<Member> findByUserId(String id);

    List<Member> findByUserName(String keyWord);
}
