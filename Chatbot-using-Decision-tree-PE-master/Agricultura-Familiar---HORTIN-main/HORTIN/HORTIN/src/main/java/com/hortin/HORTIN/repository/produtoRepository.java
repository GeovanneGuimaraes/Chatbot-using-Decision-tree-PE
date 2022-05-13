package com.hortin.HORTIN.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hortin.HORTIN.entity.Produto;
import com.hortin.HORTIN.entity.Vendedor;

@Repository
public interface produtoRepository extends JpaRepository<Produto, Long>{
	List<Produto> findByVendedorId(Long id);

}
