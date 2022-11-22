package telran.employee.dao;

import telran.employee.model.Employee;
import telran.employee.model.SalesManager;

public class CompanyImpl implements Company {
	private Employee[] employees;
	private int size;

	public CompanyImpl(int capacity) {
		employees = new Employee[capacity];
	}

	@Override
	public boolean addEmployee(Employee employee) {
		if (size == employees.length || findEmployee(employee.getId()) != null) {
			return false;
		}
		employees[size++] = employee;
		return true;

	}

	@Override
	public Employee removeEmployee(int id) {
		for (int i = 0; i < size; i++) {
			if (employees[i].getId() == id) {
				Employee removedEmployee;
				removedEmployee = employees[i];
				employees[i] = null;

				for (int j = i; j < size; j++) {
					employees[j] = employees[j + 1];
					employees[j+1] = null;
				}
				size--;
				return removedEmployee;
			}
		}

		return null;
	}

	@Override
	public Employee findEmployee(int id) {
		for (int i = 0; i < size; i++) {
			if (employees[i].getId() == id) {
				return employees[i];
			}
		}
		return null;
	}

	@Override
	public double totalSalary() {
		double totalSalary=0;
		for(int i=0; i<size; i++)
		{
			totalSalary += employees[i].calcSalary();
		}
		return totalSalary;
	}

	@Override
	public double totalSales() {
		double totalSales=0;
		for (int i = 0; i < employees.length; i++) {
			if(employees[i] instanceof SalesManager)
			{
				totalSales += ((SalesManager) employees[i]).getSalesValue();
			}
		}
		return totalSales;
	}

	@Override
	public int quantity() {
		return size;
	}

	@Override
	public double averageSalary() {
		double totalSalary=0;
		for(int i=0; i<size; i++)
		{
			totalSalary += employees[i].calcSalary();
		}
		return totalSalary/size;
	}

	@Override
	public void printEmployees() {
		for (int i = 0; i < employees.length; i++) {
			if (employees[i] != null) {
				System.out.println(employees[i]);
			}
		}

	}

}
