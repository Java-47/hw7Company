package telran.employee.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employee.dao.Company;
import telran.employee.dao.CompanyImpl;
import telran.employee.model.Employee;
import telran.employee.model.Manager;
import telran.employee.model.SalesManager;
import telran.employee.model.WageEmployee;

class CompanyTest {
	Company company;
	Employee[] firm;

	@BeforeEach
	void setUp() throws Exception {
		company = new CompanyImpl(5);
		firm = new Employee[4];
		firm[0] = new Manager(1000, "John", "Smith", 182, 20000, 20);
		firm[1] = new WageEmployee(2000, "Ann", "Smith", 182, 40);
		firm[2] = new SalesManager(3000, "Peter", "Jackson", 182, 40000, 0.1);
		firm[3] = new SalesManager(4000, "Tigran", "Petrosyan", 91, 80000, 0.1);
		for (int i = 0; i < firm.length; i++) {
			company.addEmployee(firm[i]);
		}
	}

	@Test
	void testAddEmployee() {
		assertFalse(company.addEmployee(firm[1]));
		Employee empl = new SalesManager(5000, "Tigran", "Petrosyan", 91, 80000, 0.1);
		assertTrue(company.addEmployee(empl));
		assertEquals(5, company.quantity());
		assertEquals(empl, company.findEmployee(5000));
		assertFalse(company.addEmployee(new SalesManager(6000, "Tigran", "Petrosyan", 91, 80000, 0.1)));
	}

	@Test
	void testRemoveEmployee() {

		assertEquals(4, company.quantity());
		company.removeEmployee(1000);
		assertEquals(3, company.quantity());
		assertEquals(null, company.findEmployee(1000));

	}

	@Test
	void testFindEmployee() {
		assertEquals(null, company.findEmployee(6000));
		assertEquals(firm[0], company.findEmployee(1000));

	}

	@Test
	void testTotalSalary() {
		assertEquals(42920,company.totalSalary());
	}

	@Test
	void testTotalSales() {
		assertEquals(120000, company.totalSales());
	}

	@Test
	void testQuantity() {
		assertEquals(4, company.quantity());
	}

	@Test
	void testAverageSalary() {
		assertEquals(company.totalSalary()/4, company.averageSalary());
	}

	@Test
	void testPrintEmployees() {
		company.printEmployees();
		company.removeEmployee(3000);
		System.out.println("3000 deleted --");
		company.printEmployees();
	}

}
