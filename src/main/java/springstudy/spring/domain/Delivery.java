package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="delivery")
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    private Order order;

    //@Embedded
    //private Address address;

    @Enumerated(EnumType.STRING) // enum 타입 생성. 타입은 꼭 string으로 지정해야한다.
    private DeliveryStatus deliveryStatus; //ENUM [READY(준비), COMP(배송)]

}
