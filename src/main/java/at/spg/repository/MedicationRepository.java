package at.spg.repository;

import at.spg.model.Medication;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicationRepository extends PagingAndSortingRepository<Medication, String>{

}
