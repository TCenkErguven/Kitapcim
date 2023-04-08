package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.TurKitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurKitapRepository extends JpaRepository<TurKitap,Long> {
}
