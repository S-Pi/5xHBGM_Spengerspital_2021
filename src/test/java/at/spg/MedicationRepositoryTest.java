package at.spg;

import at.spg.model.*;
import at.spg.repository.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MedicationRepositoryTest {
    @Autowired
    MedicationRepository medicationRepository;
    
    @Test
    @Transactional
    public void TestSaveOneMedication(){
        List<Identifier> identifiers = new ArrayList<>();
        List<Coding> codings = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();

        codings.add(new Coding("System", "0.1.1", "Code", "<div>...<div>", false));
        Period period = new Period(LocalDateTime.of(2000, 01,01,1,1), LocalDateTime.of(2001,01,01,1,1));
        CodeableConcept ccType = new CodeableConcept(codings, "Text");
        identifiers.add(new Identifier(Identifier.UseCode.official,ccType, "System", "value", period));
        Reference r1 = new Reference("/api/practitioner/manufacturer1", "Manufacturer", identifiers, "Bayer");
        Quantity q1 = new Quantity(10, "<", "mg", "System", "Code");
        Quantity q2 = new Quantity(20, "<=", "kg", "System", "Code");
        Ratio rat1 = new Ratio(q1, q2);
        Ingredient ing1 = new Ingredient(ccType, true, rat1);
        ingredients.add(ing1);
        LocalDateTime ldt = LocalDateTime.of(2010, 01,01,1,1);
        Batch b1 = new Batch("123a", ldt);

        Medication m1 = new Medication(identifiers, ccType, Medication.StatusEnum.active, r1, ccType, rat1, ingredients, b1);
        Medication savedMedication = medicationRepository.save(m1);
        Medication loadedMedication = medicationRepository.findById(m1.getId()).get();

        assertTrue(CollectionUtils.isEqualCollection(savedMedication.getIdentifier(), loadedMedication.getIdentifier()));
        assertEquals(savedMedication.getCode(), loadedMedication.getCode());
        assertEquals(savedMedication.getStatus(), loadedMedication.getStatus());
        assertEquals(savedMedication.getManufacturer(), loadedMedication.getManufacturer());
        assertEquals(savedMedication.getForm(), loadedMedication.getForm());
        assertEquals(savedMedication.getAmount(), loadedMedication.getAmount());
        assertTrue(CollectionUtils.isEqualCollection(savedMedication.getIngredients(), loadedMedication.getIngredients()));
        assertEquals(savedMedication.getBatch(), loadedMedication.getBatch());
    }
}
