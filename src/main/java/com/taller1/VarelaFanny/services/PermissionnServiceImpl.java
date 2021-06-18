package com.taller1.VarelaFanny.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.model.Permissionn;
import com.taller1.VarelaFanny.repositories.PermissionnRepository;


@Service
public class PermissionnServiceImpl implements PermissionnService {
	
	public PermissionnRepository permissionnRepository;
	
	@Autowired
	public PermissionnServiceImpl(PermissionnRepository permissionnRepository) {
		this.permissionnRepository = permissionnRepository;
	}

	@Override
	public Permissionn createPermissionn(Permissionn permissionn) {
		
		if(permissionn != null)
			permissionnRepository.save(permissionn);
		return permissionnRepository.findById(permissionn.getPermId()).get();
	}

	@Override
	public Permissionn updatePermissionn(Permissionn permissionn) {
		if(permissionn != null)
			permissionnRepository.save(permissionn);
		return permissionnRepository.findById(permissionn.getPermId()).get();
	}

	@Override
	public void deletePermissionn(long id) {
		permissionnRepository.deleteById(id);
	}
	
	
	
}
