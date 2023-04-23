package com.inditex.logandotest.api.repository;

import com.inditex.logandotest.api.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Long> {
    List<Size> findAll();
}
