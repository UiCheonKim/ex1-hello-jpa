package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPA가 처음 로딩될 때, JPA를 사용하는 애구나 인식, 이거는 내가 관리해야겠다고 인식
//@Table(name = "USER") // 관례상 Member를 호출하는데 db가 USER로 되어 있다면 이렇게 해준다.
public class Member {

    @Id // 최소한 PK는 뭔지 알려줘야함
    private Long id;

//    @Column(name = "username") // column 명이 다를 때
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
