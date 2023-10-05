package lk.ijse.dep11.sqlgame.controller.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    private String id;
    private String name;
    private String card;
    private String challengeStatus;
}
