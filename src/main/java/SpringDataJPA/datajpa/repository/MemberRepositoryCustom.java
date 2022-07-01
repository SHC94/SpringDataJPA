package SpringDataJPA.datajpa.repository;

import SpringDataJPA.datajpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findMemberCustom();

}
