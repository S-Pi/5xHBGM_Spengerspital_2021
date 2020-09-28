package at.spg.controller;

import at.spg.model.Medication;
import at.spg.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping(path = "/api/medication")
@RestController
public class MedicationController {

    @Autowired
    private MedicationRepository medicationRepository;

    @GetMapping
    public @ResponseBody Iterable<Medication> getAllMeication(){
        return medicationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> getPatient(@PathVariable String id){
        return medicationRepository
                .findById(id)
                .map(medication -> ResponseEntity.ok().body(medication))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Medication> createMedication(@Valid @RequestBody Medication medication){
        medication.setId(null);
        var saved = medicationRepository.save(medication);
        return ResponseEntity.created(URI.create("/api/medication/"+saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medication> updateMedication(
            @PathVariable(value = "id") String medicationId, @Valid @RequestBody Medication medicationDetails){
            return medicationRepository.findById(medicationId)
                    .map(
                            medication -> {
                                medication.setIdentifier(medicationDetails.getIdentifier());
                                medication.setCode(medicationDetails.getCode());
                                medication.setStatus(medicationDetails.getStatus());
                                medication.setManufacturer(medicationDetails.getManufacturer());
                                medication.setForm(medicationDetails.getForm());
                                medication.setAmount(medicationDetails.getAmount());
                                medication.setBatch(medicationDetails.getBatch());

                                Medication updatedMedication = medicationRepository.save(medication);
                                return ResponseEntity.ok(updatedMedication);
                            })
                    .orElse(createMedication(medicationDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Medication> deleteMedication (@PathVariable(value="id") String medicationId){
        return medicationRepository
                .findById(medicationId)
                .map(
                        medication -> {
                            medicationRepository.delete(medication);
                            return ResponseEntity.ok().<Medication>build();
                        })
                .orElse(ResponseEntity.notFound().build());
    }
}
