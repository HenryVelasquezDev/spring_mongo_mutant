package com.example.mutant.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.mutant.models.CodigoAdnModel;

@Repository
public interface CodigoAdnRepository extends MongoRepository<CodigoAdnModel, String> {
	
	//aregando otro comentario
	List<CodigoAdnModel> findByDna(String dna);
	List<CodigoAdnModel> findByMutante(Boolean mutante);
}
