package com.groceryshopapi.serviceimpl;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceryshopapi.domain.Admin;
import com.groceryshopapi.exception.AdminIDException;
import com.groceryshopapi.repository.AdminRepository;
import com.groceryshopapi.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin saveOrUpdate(Admin admin) throws AdminIDException {
		try {
			admin.setUserId(admin.getUserId().toUpperCase());
			return adminRepository.save(admin);
		} catch (Exception ex) {
			throw new AdminIDException("UserId" + admin.getUserId() + "is already available");
		}
	}

	@Override
	public Admin findAdminByUserId(String userId) {
		Admin admin = adminRepository.findByuserId(userId);
//		Admin admin = new Admin();
		return admin;

	}

	@Override
	public Admin updateByUserId(String userId, int isloggedIn) {
	
	Admin admin =	findAdminByUserId(userId);
	admin.setIsloggedIn(isloggedIn);
		return adminRepository.save(admin);
	}
}
