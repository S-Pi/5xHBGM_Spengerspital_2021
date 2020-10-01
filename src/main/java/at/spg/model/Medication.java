package at.spg.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Medication extends DomainResource{

    public Medication() {
    }


    public Medication(List<Identifier> identifier, CodeableConcept code, StatusEnum status, Reference manufacturer, CodeableConcept form, Ratio amount, List<Ingredient> ingredients, Batch batch) {
        this.identifier = identifier;
        this.code = code;
        this.status = status;
        this.manufacturer = manufacturer;
        this.form = form;
        this.amount = amount;
        this.ingredients = ingredients;
        this.batch = batch;
    }

    public enum StatusEnum{
        active, inactive, enteredInError
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Identifier.class)
    @JoinColumn(name = "i_medication_fk", referencedColumnName = "id")
    private List<Identifier> identifier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="med_code_fk", referencedColumnName="id")
    private CodeableConcept code;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @OneToOne(cascade = CascadeType.ALL,targetEntity = Reference.class)
    @JoinColumn(name = "med_manufacturer_reference_fk", referencedColumnName = "id")
    private Reference manufacturer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="med_form_fk", referencedColumnName="id")
    private CodeableConcept form;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="med_ratio_fk", referencedColumnName="id")
    private Ratio amount;

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Ingredient.class)
    @JoinColumn(name = "ind_medication_fk", referencedColumnName = "id")
    private List<Ingredient> ingredients;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="med_bat_fk", referencedColumnName="id")
    private Batch batch;

    public List<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<Identifier> identifier) {
        this.identifier = identifier;
    }

    public CodeableConcept getCode() {
        return code;
    }

    public void setCode(CodeableConcept code) {
        this.code = code;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Reference getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Reference manufacturer) {
        this.manufacturer = manufacturer;
    }

    public CodeableConcept getForm() {
        return form;
    }

    public void setForm(CodeableConcept form) {
        this.form = form;
    }

    public Ratio getAmount() {
        return amount;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setAmount(Ratio amount) {
        this.amount = amount;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }
}
