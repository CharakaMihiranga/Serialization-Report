package dto;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String email;
    private transient String password;
}
