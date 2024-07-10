package toyProject.toyProject01.old.mapper;

import org.apache.ibatis.annotations.Mapper;
import toyProject.toyProject01.old.domain.OldMember;

@Mapper
public interface OldMemberMapper {
    void save(OldMember member);

    //검증 개발 필요
}
