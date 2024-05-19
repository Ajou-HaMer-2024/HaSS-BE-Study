package hello.hellospring.domain;

public class Member {

    private Long id; //고객이 정하는 값이 아니라, 시스템이 정하는 값(데이터 구분위해)
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
