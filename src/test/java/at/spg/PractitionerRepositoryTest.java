package at.spg;

import at.spg.model.*;
import at.spg.repository.PractitionerRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PractitionerRepositoryTest {
    @Autowired
    PractitionerRepository practitionerRepository;
    @Test
    @Transactional
    public void testSaveOnePractitioner(){
        Set<Identifier> identifiers = new HashSet<>();
        Set<HumanName> names = new HashSet<>();
        Set<Address> address = new HashSet<>();
        Set<Attachment> photos = new HashSet<>();
        Set<CodeableConcept> cc = new HashSet<>();
        Set<Coding> codings = new HashSet<>();
        Set<ContactPoint> contactPoints = new HashSet<>();
        Set<Qualification> qualifications = new HashSet<>();
        Period period = new Period(LocalDateTime.of(2000, 01,01,1,1), LocalDateTime.of(2001,01,01,1,1));
        Period period2 = new Period(LocalDateTime.of(2000, 02,02,2,2), LocalDateTime.of(2002,02,02,2,2));
        codings.add(new Coding("System", "0.1.1", "Code", "<div>...<div>", false));
        CodeableConcept ccType = new CodeableConcept(codings, "Text");
        contactPoints.add(new ContactPoint(ContactPoint.system.phone, "123454321", ContactPoint.use.home, 1, period2));
        identifiers.add(new Identifier(Identifier.UseCode.official,ccType, "System", "value", period));
        names.add(new HumanName(HumanName.UseCode.usual, "Text", "Mustermann", "given", period));
        cc.add(new CodeableConcept(codings, "CodCoc"));
        qualifications.add(new Qualification(identifiers, new CodeableConcept(codings, "CodCoc"), period2));

        Practitioner p1 = new Practitioner(identifiers, true, names, contactPoints, address, Practitioner.GenderCode.female, LocalDate.of(1987, 3, 7), photos, cc);
        p1.setQualifications(qualifications);
        Practitioner savedP1 = practitionerRepository.save(p1);
        Practitioner loadedPractitioner = practitionerRepository.findById(savedP1.getId()).get();

        assertEquals(p1, savedP1);
        assertTrue(CollectionUtils.isEqualCollection(p1.getIdentifier(), loadedPractitioner.getIdentifier()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getName(), loadedPractitioner.getName()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getTelecom(), loadedPractitioner.getTelecom()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getAddress(), loadedPractitioner.getAddress()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getPhoto(), loadedPractitioner.getPhoto()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getCommunication(), loadedPractitioner.getCommunication()));
        assertTrue(CollectionUtils.isEqualCollection(p1.getQualifications(), loadedPractitioner.getQualifications()));
        //Reich, weil Practitioner eine überschriebene Equals-Methode hat
    }

}
