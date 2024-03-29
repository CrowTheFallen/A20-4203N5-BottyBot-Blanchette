package Blanchette.bot;

import java.util.Objects;

public class Web {
    String WebAdresse;

    public Web(String WebAdress) {
        this.WebAdresse = WebAdress;
    }

    @Override
    public String toString() {
        return WebAdresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Web web = (Web) o;
        return Objects.equals(WebAdresse, web.WebAdresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(WebAdresse);
    }
}
