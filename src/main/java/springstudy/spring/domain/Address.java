package springstudy.spring.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //1) 내장타입. 2) 어딘가에 내장이 될 수 있다.
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //임베디드 타입이나 JPA 스펙상 엔티티들은, 자바 기본 생성자를 public, protected로 설정해줘야 함.
    //이유- jpa구현 라이브러리가 객체를 생성할때 '리플렉션'같은 기술을 쓸 수 있도록 지원해주려고.
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
