package lk.ijse.dep11.sqlgame.controller;

import lk.ijse.dep11.sqlgame.db.DbConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ManageStudentsViewControllerTest {

    private final ManageStudentsViewController controller = new ManageStudentsViewController();

    @CsvSource({"2303111001,true",
                "1503111001,false",
                "ijse,false",
                "2403111001,false",
                "2301111001,false",
                "2303101001,true",
                "2303101101,false",
                "2303151001,false"})
    @ParameterizedTest
    void isValidDepStudentId(String id, boolean expectedResult) {
        boolean result = controller.isValidDepStudentId(id);
        assertEquals(expectedResult, result);
    }

    @Nested
    class DbRelatedTest{
        @BeforeEach
        void setUp() throws SQLException {
            System.out.println("Setup");
            DbConnection.getInstance().getConnection().setAutoCommit(false);
        }

        @AfterEach
        void tearDown() throws SQLException {
            System.out.println("Tear Down");
            DbConnection.getInstance().getConnection().rollback();
            DbConnection.getInstance().getConnection().setAutoCommit(true);
        }

        @CsvSource({"2303101001,Kasun Sampath,kasuna,true",
                "2303101002,Supun Malinda,maliya,true"})
        @ParameterizedTest
        void addNewStudent(String id, String name, String card, boolean expectedResult) {
            assertDoesNotThrow(()->{
                boolean result = controller.addNewStudent(id, name, card);
                assertEquals(expectedResult, result);
            });
        }
    }

}