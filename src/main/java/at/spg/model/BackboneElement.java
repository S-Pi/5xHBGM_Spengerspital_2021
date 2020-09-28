package at.spg.model;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
public class BackboneElement extends Element{
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Extension.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ex_backboneelement_fk", referencedColumnName = "id")
    Set<Extension> modifierExtension;

    public Set<Extension> getModifierExtension() {
        return modifierExtension;
    }

    public void setModifierExtension(Set<Extension> modifierExtension) {
        this.modifierExtension = modifierExtension;
    }
}
