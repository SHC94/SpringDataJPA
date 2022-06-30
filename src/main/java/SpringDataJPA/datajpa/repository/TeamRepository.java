package SpringDataJPA.datajpa.repository;

import SpringDataJPA.datajpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
