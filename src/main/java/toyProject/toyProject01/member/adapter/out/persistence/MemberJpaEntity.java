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

    @Id @GeneratedValue
    private Long memberNumber;

    private String memberId;        //hash로

    private String pw;

    private String nickName;

    private Date age;

    private String tel;

    private String email;

    public MemberJpaEntity() {

    }

    public MemberJpaEntity(String memberId, String pw, String nickName, Date age, String tel, String email) {
        this.memberId = memberId;
        this.pw = pw;
        this.nickName = nickName;
        this.age = age;
        this.tel = tel;
        this.email = email;
    }
}
