package toyProject.toyProject01.board.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categorys")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    private String type;


}
