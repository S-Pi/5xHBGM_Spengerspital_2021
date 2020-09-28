package at.spg.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class HumanName extends Element{
    public enum UseCode{
        usual , official , temp , nickname , anonymous , old , maiden
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "hn_use")
    private UseCode use;
    /**
     *     Text representation of the full name
     */
    @Column(name = "hn_text")
    private String text;
    /**
     * Family name (often called 'Surname')
     */
    @Column(name = "hn_family")
    private String family;
    /**
     * Given names (not always 'first'). Includes middle names
     * This repeating element order: Given Names appear in the correct order for presenting the name
     */
    @Column(name = "hn_given")
    private String given;
    /**
     * Parts that come before the name
     * This repeating element order: Prefixes appear in the correct order for presenting the name
     */
    @ElementCollection
    @CollectionTable(name = "humanname_prefix", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "hn_prefix")
    private Set<String> prefix;
    /**
     * Parts that come after the name
     * This repeating element order: Suffixes appear in the correct order for presenting the name
     */
    @ElementCollection
    @CollectionTable(name = "humanname_surfix", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "hn_surfix")
    private Set<String> surfix = new HashSet<>();
    /**
     * Time period when name was/is in use
     */
    @Embedded
    private Period period;

    public HumanName() {
    }

    public HumanName(UseCode use, String text, String family, String given, Period period) {
        this.use = use;
        this.text = text;
        this.family = family;
        this.given = given;
        this.period = period;
    }

    public UseCode getUse() {
        return use;
    }

    public void setUse(UseCode use) {
        this.use = use;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public Set<String> getPrefix() {
        return prefix;
    }

    public void setPrefix(Set<String> prefix) {
        this.prefix = prefix;
    }

    public Set<String> getSurfix() {
        return surfix;
    }

    public void setSurfix(Set<String> surfix) {
        this.surfix = surfix;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanName humanName = (HumanName) o;
        return use == humanName.use &&
                Objects.equals(text, humanName.text) &&
                Objects.equals(family, humanName.family) &&
                Objects.equals(given, humanName.given) &&
                Objects.equals(prefix, humanName.prefix) &&
                Objects.equals(surfix, humanName.surfix) &&
                Objects.equals(period, humanName.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(use, text, family, given, prefix, surfix, period);
    }
}

