package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import soa.entity.Tourist;

@Repository
public interface TouristRepository extends JpaRepository<Tourist, Long> {
}
