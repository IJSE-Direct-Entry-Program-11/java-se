package lk.ijse.dep11.jdbc;

public class Earth {
    private static Earth instance;
    private Earth(){

    }

    public static Earth getInstance(){
        return (instance == null)? instance = new Earth(): instance;
    }
}

// Singleton D.P
// Creational
// Objective: Reuse a same instance repeatedly
// Impl in Java
// 1. constructor -> private
// 2. to store the singleton instance -> create encapsulated class variable
// 3. expose an api to return the singleton instance

