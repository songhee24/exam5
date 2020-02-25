package kg.exam.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@XmlRootElement(name = "requests")
public class Requests {
    int id;
    User user;
    Date date;
}
