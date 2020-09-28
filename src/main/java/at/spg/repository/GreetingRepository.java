package at.spg.repository;

import java.util.List;
import at.spg.model.Greeting;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface GreetingRepository extends PagingAndSortingRepository<Greeting, Long> {
  List<Greeting> findById(@Param("id") long id);
  List<Greeting> findByContent(@Param("content") String content);
}
