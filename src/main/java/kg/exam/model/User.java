package kg.exam.model;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@XmlRootElement(name = "user")
public class User {
    int id;
    String name;
    int age;
    String gender;


    public String alert(User user) throws Exception {
        if (user.getAge() > 2000) throw new Exception("рано");
        if (user.getGender().equals("male")){
            return "уважаемый " + user.getName()
                    + "ваш год " + user.getAge()
                    + "вам " + (2020 - user.getAge());
        }
        else return "уважаемая " + user.getName()
                + "ваш год " + user.getAge()
                + "вам " + (2020 - user.getAge());
    }
}
