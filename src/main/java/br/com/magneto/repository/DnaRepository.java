package br.com.magneto.repository;


import br.com.magneto.domain.VerifiedDna;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends MongoRepository<VerifiedDna, String> {
    long countByIsMutant(boolean isMutant);
}
