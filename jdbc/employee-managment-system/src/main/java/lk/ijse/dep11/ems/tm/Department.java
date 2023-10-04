package lk.ijse.dep11.ems.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {
    private int id;
    private String name;
    private int numOfEmployees;
}
