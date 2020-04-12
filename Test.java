package Lesson3.Lesson3_4;
//Сделать клиен-серверное приложение. Передать по сети сеарилизованный объект.

import java.io.Serializable;

public class Test implements Serializable {
    private String firstName;
    private String lastName;

    public Test(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}