package SpringDataJPA.datajpa.repository;

import SpringDataJPA.datajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
