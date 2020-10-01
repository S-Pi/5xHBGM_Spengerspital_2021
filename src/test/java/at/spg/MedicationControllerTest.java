package at.spg;

import at.spg.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
public class MedicationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper om;

    @Test
    @Transactional
    public void getAllMedication() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/medication")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    public void createMedication() throws Exception{
        List<Identifier> identifiers = new ArrayList<>();
        List<Coding> codings = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();

        codings.add(new Coding("System", "0.1.2", "Code", "<div>...<div>", false));
        Period period = new Period(LocalDateTime.of(2000, 01,01,1,1), LocalDateTime.of(2001,01,01,1,1));
        CodeableConcept ccType = new CodeableConcept(codings, "Text");
        identifiers.add(new Identifier(Identifier.UseCode.official,ccType, "System", "value", period));
        Reference r1 = new Reference("/api/practitioner/manufacturer1", "Manufacturer", identifiers, "Bayer");
        Quantity q1 = new Quantity(10, "<", "mg", "System", "Code");
        Quantity q2 = new Quantity(20, "<=", "kg", "System", "Code");
        Ratio rat1 = new Ratio(q1, q2);
        Ingredient ing1 = new Ingredient(r1, true, rat1);
        ingredients.add(ing1);
        LocalDateTime ldt = LocalDateTime.of(2010, 01,01,1,1);
        Batch b1 = new Batch("123a", ldt);

        Medication m1 = new Medication(identifiers, ccType, Medication.StatusEnum.active, r1, ccType, rat1, ingredients, b1);
        var json = om.writeValueAsString(m1);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/medication")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
