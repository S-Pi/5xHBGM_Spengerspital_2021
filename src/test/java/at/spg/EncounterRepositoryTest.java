package at.spg;

import at.spg.model.*;
import at.spg.repository.EncounterRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EncounterRepositoryTest {
    @Autowired
    EncounterRepository encounterRepository;
    @Test
    @Transactional
    public void TestSaveOneEncounter(){
        List<Identifier> identifiers = new ArrayList<>();
        List<StatusHistory> statusHistory = new ArrayList<>();
        List<Reference> episodeOfCare = new ArrayList<>();
        List<Participant> participants = new ArrayList<>();
        List<Reference> appointment = new ArrayList<>();
        List<Reference> reasonReference = new ArrayList<>();
        List<Reference> conditions = new ArrayList<>();
        List<Diagnosis> diagnosis = new ArrayList<>();
        List<Coding> codings = new ArrayList<>();
        List<CodeableConcept> cc = new ArrayList<>();

        Period period = new Period(LocalDateTime.of(2000, 01,01,1,1), LocalDateTime.of(2001,01,01,1,1));
        Reference r1 = new Reference("/api/practitioner/p1", "Practitioner", identifiers, "behandelnder Arzt");
        Reference subjectReference = new Reference("/api/patient/p1", "Patient", identifiers, "Betroffener Patient");
        Reference partOfReference = new Reference("/api/encounter/enc1", "Encounter", identifiers, "Mitglieder");
        conditions.add(new Reference("/api/conditions/cond1", "Condition", identifiers, "Zustand"));
        CodeableConcept ccType = new CodeableConcept(codings, "Text");
        codings.add(new Coding("System", "0.1.1", "Code", "<div>...<div>", false));
        cc.add(new CodeableConcept(codings, "CodCoc"));
        diagnosis.add(new Diagnosis(conditions, ccType, 5));

        identifiers.add(new Identifier(Identifier.UseCode.official,ccType, "System", "value", period));
        statusHistory.add(new StatusHistory(StatusHistory.EncounterStatus.finished, period));
        episodeOfCare.add(new Reference("/api/episode", "EpisodeOfCare", identifiers, "alternative"));
        participants.add(new Participant(cc, period, r1));
        appointment.add(new Reference("/api/references/ref1", "Appointment", identifiers, "Liste der bisherigen Termine"));
        reasonReference.add(new Reference("/api/procedures/proc1", "Procedure", identifiers, "Grund"));

        Encounter enc2 = new Encounter(identifiers, Encounter.StatusCode.finished, statusHistory, cc, subjectReference, episodeOfCare, participants, appointment, period, reasonReference, diagnosis, partOfReference);
        Encounter savedEnc = encounterRepository.save(enc2);
        Encounter loadedEnc = encounterRepository.findById(enc2.getId()).get();


        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getIdentifier(), loadedEnc.getIdentifier()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getStatusHistory(), loadedEnc.getStatusHistory()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getEpisodeOfCare(), loadedEnc.getEpisodeOfCare()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getParticipant(), loadedEnc.getParticipant()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getAppointment(), loadedEnc.getAppointment()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getReasonReference(), loadedEnc.getReasonReference()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getDiagnosis(), loadedEnc.getDiagnosis()));
        assertTrue(CollectionUtils.isEqualCollection(savedEnc.getType(), loadedEnc.getType()));
        assertEquals(savedEnc.getPeriod(), loadedEnc.getPeriod());
        assertEquals(enc2.getPartOf(), loadedEnc.getPartOf());
        assertEquals(savedEnc, loadedEnc);

    }
}
