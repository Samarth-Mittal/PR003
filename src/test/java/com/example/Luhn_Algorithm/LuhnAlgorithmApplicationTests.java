package com.example.Luhn_Algorithm;

import com.example.Luhn_Algorithm.service.CreditCardValidation;
import com.example.Luhn_Algorithm.service.CreditCardValidationImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LuhnAlgorithmApplication.class)
@RunWith(SpringRunner.class)
class LuhnAlgorithmApplicationTests {

	/*@Test
	void contextLoads() {
	}*/

	CreditCardValidationImpl creditCardValidationImpl=new CreditCardValidationImpl();

	@Test
	public void testValid() throws Exception {
		Assert.assertEquals(true, creditCardValidationImpl.isCardValid("5575542004376575", "Sam Mittal", "123", "05/24"));
	}

	@Test
	public void testInvalidCardNo() throws Exception {
		Assert.assertEquals(false, creditCardValidationImpl.isCardValid("5675542004376575", "Sam Mittal", "123", "05/24"));
	}

	@Test
	public void testInvalidName() throws Exception {
		Assert.assertEquals(false, creditCardValidationImpl.isCardValid("5575542004376575", "Sam 213Mittal", "123", "05/24"));
	}

	@Test
	public void testInvalidCVV() throws Exception {
		Assert.assertEquals(false, creditCardValidationImpl.isCardValid("5575542004376575", "Sam Mittal", "1s3", "05/24"));
	}

	@Test
	public void testInvalidDate() throws Exception {
		Assert.assertEquals(false, creditCardValidationImpl.isCardValid("5575542004376575", "Sam Mittal", "123", "05/13"));
	}
}
