package poslovnaInformatika.narodnaBanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import poslovnaInformatika.narodnaBanka.model.Enterprise;
import poslovnaInformatika.narodnaBanka.repository.EnterpriseRepository;
import poslovnaInformatika.narodnaBanka.service.EnterpriseServiceInterface;

@Service
public class EnterpriseService implements EnterpriseServiceInterface {
	@Autowired
	EnterpriseRepository enterpriseRepository;

	@Override
	public Enterprise findOne(Long enterprise_id) {
		return enterpriseRepository.getOne(enterprise_id);
	}

	@Override
	public List<Enterprise> findAll() {
		// TODO Auto-generated method stub
		return enterpriseRepository.findAll();
	}

	@Override
	public Enterprise save(Enterprise enterprise) {
		// TODO Auto-generated method stub
		return enterpriseRepository.save(enterprise);
	}

	@Override
	public void remove(Long enterprise_id) {
		enterpriseRepository.deleteById(enterprise_id);
		
	}

	@Override
	public Enterprise findByName(String nameEnterprise) {
		// TODO Auto-generated method stub
		return enterpriseRepository.findByNameEnterprise(nameEnterprise);
	}

	@Override
	public Page<Enterprise> findAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return enterpriseRepository.findAll(PageRequest.of(pageNo, pageSize));
	}
	
	

}

