package SpringDataJPA.datajpa.repository;

import SpringDataJPA.datajpa.dto.MemberDTO;
import SpringDataJPA.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //메소드 이름으로 쿼리 생성
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    //JPA NamedQuery
    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    //@Query, 리포지토리 메소드에 쿼리 정의하기 (실무에서 주로 사용.)
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    //@Query 값 조회하기
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //@Query DTO 조회하기
    @Query("select new SpringDataJPA.datajpa.dto.MemberDTO(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDTO> findMemberDTO();

    //컬렉션 파라미터 바인딩
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    List<Member> findListByUsername(String username);           //컬렉션
    Member findMemberByUsername(String username);               //단건
    Optional<Member> findOptionalByUsername(String username);   //단건

    //페이징
    @Query(value = "select m from Member m", countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);
}
