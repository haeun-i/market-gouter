package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="payment")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "payment_name")
    private String name;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private Order order;
}
