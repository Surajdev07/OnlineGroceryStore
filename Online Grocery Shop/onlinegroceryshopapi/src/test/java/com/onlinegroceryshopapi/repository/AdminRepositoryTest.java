package com.onlinegroceryshopapi.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.groceryshopapi.repository.AdminRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AdminRepositoryTest {

	@Autowired
	private AdminRepository adminRepository;
	
	@Test
	void test_findByAdminId() throws Exception {
		System.out.println("----test_findByAdminId------"+adminRepository);
	}
	
}
