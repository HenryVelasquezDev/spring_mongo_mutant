package com.example.mutant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mutant.DTO.EstadisticasMutanteDTO;
import com.example.mutant.DTO.MutanteDnaDTO;
import com.example.mutant.exceptions.ErrorDTO;
import com.example.mutant.exceptions.EstructuraNoValidaException;
import com.example.mutant.models.CodigoAdnModel;
import com.example.mutant.services.CodigoAdnService;

@RestController
@RequestMapping("")
public class CodigoAdnController {
	@Autowired
    CodigoAdnService codigoAdnService;

    /*@GetMapping()
    public ArrayList<CodigoAdnModel> obtenerCodigos(){
        return codigoAdnService.obtenerCodigosAdn();
    }*/
    
    @PostMapping(path = "/mutant")
    public CodigoAdnModel validarMutante(@RequestBody MutanteDnaDTO mutanteAdn) {  
    	//agregando comentario
    	//agregando comentario
    	// agrego una funcionalidad nueva
    	//Se agrega segunda funcionalidad
    	return codigoAdnService.validarRegistrarMutante( mutanteAdn );
    	
    }
    
    @GetMapping(path = "/stats")
    public EstadisticasMutanteDTO EstadisticasMutantes() {    	
    	return codigoAdnService.EstadisticasMutantes();
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleException (EstructuraNoValidaException ex) {
    	ErrorDTO error = new ErrorDTO( ex.getMessage());
    	return new ResponseEntity<ErrorDTO>( error,HttpStatus.FORBIDDEN );
    }
}
