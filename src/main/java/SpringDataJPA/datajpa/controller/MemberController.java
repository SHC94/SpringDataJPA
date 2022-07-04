package SpringDataJPA.datajpa.controller;

import SpringDataJPA.datajpa.dto.MemberDTO;
import SpringDataJPA.datajpa.entity.Member;
import SpringDataJPA.datajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }
    
    //도메인 클래스 컨버터
    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    //web 확장 - 페이징과 정렬
    //http://localhost:8080/members?page=1&size=3&sort=id,desc
    //@PageableDefault(size=5, sort="username") <<< paging 글로벌 설정, 우선권을 가짐.
    @GetMapping("/members")
    public Page<MemberDTO> list(@PageableDefault(size=5, sort="username") Pageable pageable) {
        Page<MemberDTO> map = memberRepository.findAll(pageable).map(MemberDTO::new);

//        Page<Member> page = memberRepository.findAll(pageable);
//        Page<MemberDTO> map = page.map(member -> new MemberDTO(member.getId(), member.getUsername(), null));
        return map;
    }

    @PostConstruct
    public void init() {
        for(int i = 0 ; i < 100 ; i++) {
            memberRepository.save(new Member("user" + i, i));
        }
    }

}
