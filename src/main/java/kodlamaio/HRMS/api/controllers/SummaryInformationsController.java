package kodlamaio.HRMS.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.HRMS.business.abstracts.SummaryInformationService;
import kodlamaio.HRMS.core.utilities.result.DataResult;
import kodlamaio.HRMS.entities.concretes.SummaryInformation;

@RestController
@RequestMapping("/api/summaryinformations")
@CrossOrigin
public class SummaryInformationsController {

	
	private SummaryInformationService summaryInformationService;

	@Autowired
	public SummaryInformationsController(SummaryInformationService summaryInformationService) {
		super();
		this.summaryInformationService = summaryInformationService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody SummaryInformation summaryInformation){
		return ResponseEntity.ok(this.summaryInformationService.add(summaryInformation));
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> deleteById(int id){
		return ResponseEntity.ok(this.summaryInformationService.delete(id));
	}

	
	@GetMapping("/getAll")
	public DataResult<List<SummaryInformation>> getAll(){
		return this.summaryInformationService.getAll();
	}
	
}
