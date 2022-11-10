package kodlamaio.HRMS.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.HRMS.business.abstracts.ResumeService;
import kodlamaio.HRMS.core.utilities.result.DataResult;
import kodlamaio.HRMS.core.utilities.result.Result;
import kodlamaio.HRMS.entities.concretes.Resume;
import kodlamaio.HRMS.entities.dtos.ResumeWithAllRelatedEntitiesDto;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin
public class ResumesController {
	
	
	private ResumeService resumeService;
	
	@Autowired
	public ResumesController(ResumeService resumeService) {
		super();
		this.resumeService = resumeService;
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody Resume resume) {
		return resumeService.add(resume);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody Resume resume) {
		return resumeService.update(resume);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteById(int id){
		return ResponseEntity.ok(this.resumeService.delete(id));
	}
	
	@GetMapping("/getbyid")
	public DataResult<Resume> getById(@RequestParam int id){
		return resumeService.getById(id);
	}
	
	@GetMapping("/getall")
	public DataResult<List<Resume>> getAll(){
		return resumeService.getAll();
	}

	@GetMapping("/getbycandidateid")
	public DataResult<Resume> getByCandidateId(int candidateId){
		return resumeService.getByCandidateId(candidateId);
	}
	
	@PostMapping("/addCoverLetterToResume")
	public Result addacaoverLetterFromResume(@RequestParam int resumeId, @RequestParam int coverLetterId) {
		return resumeService.addCoverLetterToResume(resumeId, coverLetterId);
	}
	
	@DeleteMapping("/deleteCoverLetterFromResume")
	public Result deleteCoverLetterFromResume(@RequestParam int resumeId) {
		return resumeService.deleteCoverLetterFromResume(resumeId);
	}
	
	
	@GetMapping("/getAllResumesDetailsByActivatedCandidate")
	public DataResult<List<ResumeWithAllRelatedEntitiesDto>> getAllResumesDetailsByActivatedCandidate(){
		return resumeService.getAllResumesDetailsByActivatedCandidate();
	}
	
	@GetMapping("/getResumeDetailsByCandidateId")
	public DataResult<ResumeWithAllRelatedEntitiesDto>  getResumeDetailsByCandidateId(@RequestParam int candidateId){
		return resumeService. getResumeDetailsByCandidateId(candidateId);
	}
	
	
	
	

}
