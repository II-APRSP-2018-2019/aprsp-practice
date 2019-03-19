package aprsppractice.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aprsppractice.jpa.Artikl;
import aprsppractice.reps.ArtiklRepository;

@RestController
public class ArtiklRestController {
	
	@Autowired
	ArtiklRepository artiklRepository;
	
	@GetMapping("artikl")
	public Collection<Artikl> getAll(){
		return artiklRepository.findAll();
	}
	
	@GetMapping("artikl/{id}")
	public Artikl getOne(@PathVariable("id") Integer id) {
		return artiklRepository.getOne(id);
	}
	
	@PostMapping("artikl")
	public ResponseEntity<HttpStatus> addArtikl(@RequestBody Artikl artikl) {
		artiklRepository.save(artikl);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	@PutMapping("artikl/{id}")
	public ResponseEntity<HttpStatus> updateArtikl(@RequestBody Artikl artikl, 
			@PathVariable("id")Integer id){
		if (artiklRepository.existsById(id)) {
			artikl.setId(id);
			artiklRepository.save(artikl);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);			
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		
	}
	
	@DeleteMapping("artikl/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
		if (artiklRepository.existsById(id)) {
			artiklRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		
	}

}
