package at.spg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="q_quantity")
public class Quantity extends Element{

    public Quantity() {
    }

    public Quantity(double value, String ComparatorCode, String unit, String system, String code) {
        this.value = value;
        this.comperator = comperator;
        this.unit = unit;
        this.system = system;
        this.code = code;
    }

    public enum ComparatorCode {
        bigger(">"),
        biggerOrEqual(">="),
        smaller("<"),
        smallerOrEqual("<=");

        private String value;

        private ComparatorCode(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value; //will return , or ' instead of COMMA or APOSTROPHE
        }

    }

    @Column(name="value")
    private double value;
    
    @Enumerated(EnumType.STRING)
    private ComparatorCode comperator;
    
    @Column(name="unit")
    private String unit;
    
    @Column(name="system")
    private String system;
    
    @Column(name="code")
    private String code;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ComparatorCode getComperator() {
        return comperator;
    }

    public void setComperator(ComparatorCode comperator) {
        this.comperator = comperator;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
