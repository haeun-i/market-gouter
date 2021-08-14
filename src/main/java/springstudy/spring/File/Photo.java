package springstudy.spring.File;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "file")
@NoArgsConstructor
// noargsconstructor - 파라미터가 없는 생성자를 생성해준다.
public class Photo {
}
