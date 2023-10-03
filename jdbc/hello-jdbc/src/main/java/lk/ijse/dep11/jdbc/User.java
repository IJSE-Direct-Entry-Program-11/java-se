package lk.ijse.dep11.jdbc;

public class User {
    private static User instance;

    private User(){}

    public static User getInstance(){
        return (instance == null) ? instance = new User(): instance;
//        if (instance == null){
//            instance = new User();
//        }
//        return instance;
    }
}
