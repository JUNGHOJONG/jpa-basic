package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
public class Member {

    @Id
    private Long id;

    @Column(unique = true, length = 10)
    private String name;

}
