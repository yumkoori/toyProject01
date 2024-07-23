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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String email;

    private String pw;

    private String nickName;

    private Date age;

    private String tel;


    public MemberJpaEntity() {

    }

    public MemberJpaEntity(Long no) {
        this.no = no;
    }
}
