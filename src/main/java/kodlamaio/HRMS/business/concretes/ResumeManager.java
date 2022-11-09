package kodlamaio.HRMS.business.concretes;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.HRMS.business.abstracts.CoverLetterService;
import kodlamaio.HRMS.business.abstracts.EducationService;
import kodlamaio.HRMS.business.abstracts.ImageService;
import kodlamaio.HRMS.business.abstracts.LanguageService;
import kodlamaio.HRMS.business.abstracts.ResumeService;
import kodlamaio.HRMS.business.abstracts.WorkExperienceService;
import kodlamaio.HRMS.core.utilities.result.DataResult;
import kodlamaio.HRMS.core.utilities.result.Result;
import kodlamaio.HRMS.core.utilities.result.SuccessDataResult;
import kodlamaio.HRMS.core.utilities.result.SuccessResult;
import kodlamaio.HRMS.dataAccess.abstracts.ResumeDao;
import kodlamaio.HRMS.entities.concretes.Resume;
import kodlamaio.HRMS.entities.dtos.ResumeWithAllRelatedEntitiesDto;

@Service
public class ResumeManager implements ResumeService {
	
	
	private ResumeDao resumeDao;
	private CoverLetterService coverLetterService;
	private EducationService educationService;
	private WorkExperienceService workExperienceService;
	private ImageService imageService;

	@Autowired
	public ResumeManager(
			ResumeDao resumeDao,
			CoverLetterService coverLetterService,
			EducationService educationService,
			WorkExperienceService workExperienceService,
			ImageService imageService
			
			) {
		super();
		this.resumeDao = resumeDao;
		this.coverLetterService = coverLetterService;
		this.educationService = educationService;
		this.workExperienceService = workExperienceService;
		this.imageService = imageService;
	}

	@Override
	public Result add(Resume resume) {
		resume.setCreationDate(LocalDateTime.now());
		
		resumeDao.save(resume);
		return new SuccessResult(
				"Özgeçmiş Başarılı İle Oluşturuldu.");
	}

	@Override
	public Result update(Resume resume) {
		resume = getById(resume.getId()).getData();
		resume.setCreationDate(LocalDateTime.now());
		
		resumeDao.save(resume);
		return new SuccessResult(
				"Özgeçmiş Başarılı Bir Şekilde Güncellendi");
	}

	@Override
	public Result delete(int id) {
		resumeDao.deleteById(id);
		return new SuccessResult("Silme İşlemi Başarılı");
	}

	@Override
	public DataResult<List<Resume>> getAll() {
		Sort sort  = Sort.by(Sort.Direction.DESC,"creationDate");
		 return new SuccessDataResult<>(this.resumeDao.findAll(sort));
	}

	@Override
	public DataResult<Resume> getById(int id) {
		return new SuccessDataResult<>(this.resumeDao.getById(id));
	}

	@Override
	public DataResult<Resume> getByCandidateId(int candidateId) {
		return new SuccessDataResult<>(this.resumeDao.getByCandidate_Id(candidateId));
	}

	@Override
	public Result deleteCoverLetterFromResume(int resumeId) {
		Resume resume =getById(resumeId).getData();
		resume.setCoverLetter(null);
		
		update(resume);
		return new SuccessResult("Ön Yazı Kaldırıldı.");
				
	}

	@Override
	public DataResult<ResumeWithAllRelatedEntitiesDto> getResumeDetailsByCandidateId(int candidateId) {
		
		Resume resume = getByCandidateId(candidateId).getData();
		
		ResumeWithAllRelatedEntitiesDto resumeWithAllRelatedEntitiesDto = new ResumeWithAllRelatedEntitiesDto(
				
				resume.getId(),
				resume.getCreationDate(),
				resume.getCandidate(),
				resume.getCoverLetter(),
				educationService.getAllByResumeIdSortedByGraduationDate(resume.getId()).getData(),
				workExperienceService.getAllByResumeIdSortedByTerminationDate(resume.getId()).getData(),
				resume.getLanguages(),
				resume.getSkills()
			
			
				
				);
				
				return new SuccessDataResult<ResumeWithAllRelatedEntitiesDto>(resumeWithAllRelatedEntitiesDto);
	
	}

	@Override
	public DataResult<List<ResumeWithAllRelatedEntitiesDto>> getAllResumeDetailsByCandidate() {
		List<ResumeWithAllRelatedEntitiesDto> resumes = new ArrayList<ResumeWithAllRelatedEntitiesDto>();
		
		for (Resume resume : getAll().getData()){
			if(resume.getCandidate().getUserActivation().isActivated()==true) {
				resumes.add(getResumeDetailsByCandidateId(resume.getCandidate().getId()).getData());
			}
		};
		return new SuccessDataResult<List<ResumeWithAllRelatedEntitiesDto>>(resumes);
	}

}
