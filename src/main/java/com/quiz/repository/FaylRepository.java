package net.idrok.tester.repository;

import net.idrok.tester.entity.Fayl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaylRepository extends JpaRepository<Fayl, Long> {
}
