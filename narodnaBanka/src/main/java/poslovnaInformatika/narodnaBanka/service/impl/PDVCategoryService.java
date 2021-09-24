package poslovnaInformatika.narodnaBanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovnaInformatika.narodnaBanka.model.PDVCategory;
import poslovnaInformatika.narodnaBanka.repository.PDVCategoryRepository;
import poslovnaInformatika.narodnaBanka.service.PDVCategoryServiceInterface;

@Service
public class PDVCategoryService implements PDVCategoryServiceInterface{

	@Autowired
	private PDVCategoryRepository repository;
	
	@Override
	public PDVCategory findOne(Integer id) {
		return repository.getOne(id);
	}

	@Override
	public PDVCategory save(PDVCategory p) {
		return repository.save(p);
	}

	@Override
	public void remove(Integer id) {
		 repository.deleteById(id);
	}

	@Override
	public PDVCategory findByName(String name) {
		return repository.findByName(name);
	}

//	@Override
//	public PDVCategory findByServiceGroup(ServiceGroup s) {
//		return repository.findByServiceGroup(s);
//	}

//	@Override
//	public PDVCategory findByPDVRate(PDVRate p) {
//		return repository.findByPDVRate(p);
//	}

	@Override
	public List<PDVCategory> findAll() {
		return repository.findAll();
	}
	
	

}
