package springstudy.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {

}
