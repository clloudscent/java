package com.example.crud_test.entity;

public class Members {
    private Long id;
    private String name;
    private String email;
    private String password;

    //Getter, Setter
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String content) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String author) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if(o==null || getClass() != o.getClass()) {
            return false;
        }
        Members members = (Members) o;
        return id == members.id;
    }
}
