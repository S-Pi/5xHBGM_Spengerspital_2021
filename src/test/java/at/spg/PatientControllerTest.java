package at.spg;

import at.spg.model.*;
import at.spg.repository.PatientRepository;
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

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@AutoConfigureMockMvc
@SpringBootTest
public class PatientControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper om;
    @Autowired
    PatientRepository patientRepository;

    @Test
    public void getAllPatient() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/patient")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createPatient() throws Exception{
        Patient m1 = PatientRepositoryTest.returnOnePatient();
        var json = om.writeValueAsString(m1);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/patient")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @Transactional
    public void DeleteOnePatient() throws Exception{
        Patient patient = PatientRepositoryTest.returnOnePatient();


        Patient savedPatient = patientRepository.save(patient);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/patient/" + savedPatient.getId())).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    public void UpdateOnePatient() throws Exception {

        List<Patient> patientList = new ArrayList <Patient>();
        patientRepository.findAll().forEach(patientList::add);
        Patient patient = patientList.get(0);
        //var patient = PatientRepositoryTest.returnOnePatient();
        assertNotNull(patient);

        //Patient savedPatientToChange = patientRepository.save(patient);

        patient.setActive(!patient.isActive());
        patient.setGender(Patient.GenderCode.unknown);

        var json = om.writeValueAsString(patient);
        var result = mockMvc.perform(MockMvcRequestBuilders.put("/api/patient/" + patient.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        var resultContent =  result.getResponse().getContentAsString();
        assertTrue( result.getResponse().getStatus() == 200,"Should be 200");
        Patient receivedPatient = om.readValue(resultContent, Patient.class);
        Patient foundPatient = patientRepository.findById(receivedPatient.getId()).get();
        assertNotNull(foundPatient);
    }
}
