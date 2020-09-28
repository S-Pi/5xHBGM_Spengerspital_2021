package at.spg;

import at.spg.model.*;
import at.spg.repository.PatientRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class PatientRepositoryTest {

    @Autowired
    PatientRepository patientRepository;
    @Test
    @Transactional
     public void testSaveOnePatient(){
    //1. Erstellen einer Patienteninstanz
    Set<Identifier> identifiers = new HashSet<>();
    Set<Coding> codings = new HashSet<>();
    Set<ContactPoint> contactPoints = new HashSet<>();
    Set<HumanName> names = new HashSet<>();
    Set<Attachment> photos = new HashSet<>();
    Set<Address> address = new HashSet<>();
    Set<String> prefixes = null;
    Set<String> suffixes = null;
    codings.add(new Coding("System", "0.1.1", "Code", "<div>...<div>", false));
    Period period = new Period(LocalDateTime.of(2000, 01,01,1,1), LocalDateTime.of(2001,01,01,1,1));
    Period period2 = new Period(LocalDateTime.of(2000, 02,02,2,2), LocalDateTime.of(2002,02,02,2,2));
    CodeableConcept ccType = new CodeableConcept(codings, "Text");
    identifiers.add(new Identifier(Identifier.UseCode.official,ccType, "System", "value", period));
    contactPoints.add(new ContactPoint(ContactPoint.system.phone, "123454321", ContactPoint.use.home, 1, period2));
    //prefixes.add("Haupsache nicht null");
    //suffixes.add("Hauptsache was drin");
    names.add(new HumanName(HumanName.UseCode.usual, "Text", "Mustermann", "given", period));
    address.add(new Address(Address.useEnum.home, Address.typeEnum.physical, "Text", "Line", "Vienna", "1050", "Vienna", "1050", "Austria", period));
    Patient p = new Patient(true, Patient.GenderCode.female, LocalDate.of(2000, 01, 01), identifiers,names , contactPoints, false, address, photos);
    //2. Instanz mit Testdaten befüllen
    // ..erledigt
    //3. Instanz in die DB speichern
    Patient savedP = patientRepository.save(p);
    //4. Gespeicherte Daten aus der DB lesen
    Patient loadedPatient = patientRepository.findById(savedP.getId()).get();
    System.out.println(loadedPatient);
    //5. Vergleich der gespeicherten Daten mit den geladenen


    assertEquals(p, loadedPatient);
    assertTrue(CollectionUtils.isEqualCollection(p.getIdentifier(), loadedPatient.getIdentifier()));
    assertTrue(CollectionUtils.isEqualCollection(p.getName(), loadedPatient.getName()));
    assertTrue(CollectionUtils.isEqualCollection(p.getTelecom(), loadedPatient.getTelecom()));
    assertTrue(CollectionUtils.isEqualCollection(p.getAddresses(), loadedPatient.getAddresses()));
    assertTrue(CollectionUtils.isEqualCollection(p.getPhotos(), loadedPatient.getPhotos()));
    //reicht, da der Patient eine überschriebene Equals-Methode hat

    }
}
