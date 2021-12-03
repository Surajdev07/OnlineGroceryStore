package com.groceryshopapi.service;

import com.groceryshopapi.domain.Admin;
import com.groceryshopapi.exception.AdminIDException;

public interface AdminService {

	public Admin saveOrUpdate(Admin admin) throws AdminIDException;

	public Admin findAdminByUserId(String userId);
	public Admin updateByUserId(String userId , int isloggedIn );

}
