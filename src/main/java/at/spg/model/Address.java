package at.spg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Address extends Element {

    public Address() {
    }

    public Address(useEnum use, typeEnum type, String text, String line, String city, String district, String state, String postalcode, String country, Period period) {
        this.use = use;
        this.type = type;
        this.text = text;
        this.line = line;
        this.city = city;
        this.district = district;
        this.state = state;
        this.postalcode = postalcode;
        this.country = country;
        this.period = period;
    }

    public enum useEnum{
        home, work, temp, old, billing
    }

    public enum typeEnum{
        postal, physical, both
    }

    @Column(name="a_use")
    private useEnum use;
    @Column(name="a_type")
    private typeEnum type;
    @Column(name="a_text")
    private String text;
    @Column(name="a_line")
    private String line;
    @Column(name="a_city")
    private String city;
    @Column(name="a_district")
    private String district;
    @Column(name="a_state")
    private String state;
    @Column(name="a_postalcode")
    private String postalcode;
    @Column(name="a_country")
    private String country;
    @Column(name="a_period")
    private Period period;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return use == address.use &&
                type == address.type &&
                Objects.equals(text, address.text) &&
                Objects.equals(line, address.line) &&
                Objects.equals(city, address.city) &&
                Objects.equals(district, address.district) &&
                Objects.equals(state, address.state) &&
                Objects.equals(postalcode, address.postalcode) &&
                Objects.equals(country, address.country) &&
                Objects.equals(period, address.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(use, type, text, line, city, district, state, postalcode, country, period);
    }
}
