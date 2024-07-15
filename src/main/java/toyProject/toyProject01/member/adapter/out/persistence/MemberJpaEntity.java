package toyProject.toyProject01.member.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "Member")
@Data
@AllArgsConstructor
public class MemberJpaEntity {

    @Id
    private String email;

    private String pw;

    private String nickName;

    private Date age;

    private String tel;


    public MemberJpaEntity() {

    }

}
