package Blanchette.bot;

import java.util.Objects;

public class Web {
    String WebAdress;

    public Web(String WebAdress) {
        this.WebAdress = WebAdress;
    }

    @Override
    public String toString() {
        return  WebAdress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Web web = (Web) o;
        return Objects.equals(WebAdress, web.WebAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(WebAdress);
    }
}
