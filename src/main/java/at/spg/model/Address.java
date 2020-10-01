package at.spg.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@Entity
@Table(name="a_address")
public class Address extends Element {

    public Address() {
    }

    public Address(UseCode use, TypeCode type, String text, String line, String city, String district, String state, String postalcode, String country, Period period) {
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

    
    
    public enum UseCode{
        home, work, temp, old, billing
    }

    public enum TypeCode{
        postal, physical, both
    }

    @Enumerated(EnumType.STRING)
    @Column(name="a_use")
    private UseCode use;
    
    @Enumerated(EnumType.STRING)
    @Column(name="a_type")
    private TypeCode type;
    
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

    @Embedded
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
