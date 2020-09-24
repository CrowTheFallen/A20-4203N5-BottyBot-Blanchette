package Blanchette.bot;

import java.util.Objects;

public class Email {
    String Email;

    public Email(String Email){
        this.Email = Email;
    }

    @Override
    public String toString() {
        return  Email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(Email, email.Email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Email);
    }
}
