package model.user;

import model.Coordinates;

public class User extends Account {

    public User(String username, String email, Coordinates coordinate, String password) {
        super(username, email, coordinate, password);
    }
    @Override
    public String toString(){
        return super.toString();
    }
}
