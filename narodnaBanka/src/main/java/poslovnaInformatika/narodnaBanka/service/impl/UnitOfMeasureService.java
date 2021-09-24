package poslovnaInformatika.narodnaBanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovnaInformatika.narodnaBanka.model.UnitOfMeasure;
import poslovnaInformatika.narodnaBanka.repository.UnitOfMeasureRepository;
import poslovnaInformatika.narodnaBanka.service.UnitOfMeasureServiceInterface;

@Service
public class UnitOfMeasureService implements UnitOfMeasureServiceInterface{

	@Autowired
	private UnitOfMeasureRepository repository;
	
	@Override
	public UnitOfMeasure findOne(Integer id) {
		return repository.getOne(id);
	}

	@Override
	public UnitOfMeasure save(UnitOfMeasure u) {
		return repository.save(u);
	}

	@Override
	public void remove(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public UnitOfMeasure findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public UnitOfMeasure findByShort_name(String sh_name) {
		return repository.findByShortName(sh_name);
	}

	@Override
	public List<UnitOfMeasure> findAll() {
		return repository.findAll();
	}
	
}
