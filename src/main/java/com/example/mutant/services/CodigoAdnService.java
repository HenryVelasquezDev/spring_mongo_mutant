package com.example.mutant.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mutant.DTO.EstadisticasMutanteDTO;
import com.example.mutant.DTO.MutanteDnaDTO;
import com.example.mutant.models.CodigoAdnModel;
import com.example.mutant.repositories.CodigoAdnRepository;
import com.example.mutant.evaluation.MutantEvaluation;

@Service
public class CodigoAdnService {

	@Autowired
	CodigoAdnRepository codigoAdnRepo;
	
	public CodigoAdnModel validarRegistrarMutante( MutanteDnaDTO mutanteAdn ) {
		
		Boolean esMutante = false;
		
		//Validar Mutante
		esMutante = MutantEvaluation.isMutant( mutanteAdn.getDna());
		
		CodigoAdnModel nuevoMutante = new CodigoAdnModel(mutanteAdn.getDna());
		//Buscar si existe ya el ADN registrado
		List<CodigoAdnModel> mutantesGuardados = new ArrayList<CodigoAdnModel>();
		mutantesGuardados = codigoAdnRepo.findByDna(nuevoMutante.getDna());
		
		if( mutantesGuardados.size() > 0 ) {
			nuevoMutante = mutantesGuardados.get(0);
		}
		
		nuevoMutante.setMutante(esMutante);	
		return guardarUsuario(nuevoMutante);
	}
	
	
	
	public CodigoAdnModel guardarUsuario( CodigoAdnModel mutanteAdn ){
		return codigoAdnRepo.save(mutanteAdn);
    }
	
	public EstadisticasMutanteDTO EstadisticasMutantes(  ) {
		List<CodigoAdnModel> listMutantes = new ArrayList<CodigoAdnModel>();
		List<CodigoAdnModel> listHumanos = new ArrayList<CodigoAdnModel>();
		
		listMutantes = codigoAdnRepo.findByMutante(true);
		listHumanos = codigoAdnRepo.findByMutante(false);
		
		return new EstadisticasMutanteDTO(listMutantes.size(),listHumanos.size());
	}
	
	public ArrayList<CodigoAdnModel> obtenerCodigosAdn(){
        return (ArrayList<CodigoAdnModel>) codigoAdnRepo.findAll();
    }
	
	
	
	
}
