package toyProject.toyProject01.bord.mapper;

import org.apache.ibatis.annotations.Mapper;
import toyProject.toyProject01.bord.domain.Member;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);
}
