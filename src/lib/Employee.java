package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee extends Worker {

	private enum Gender {
		LAKILAKI,
		PEREMPUAN
	}

	private final int yearJoined;
	private final int monthJoined;
	private final int dayJoined;
	private int monthWorkingInYear;

	private final boolean isForeigner;
	private final Gender gender;

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private final Person spouse;
	private final Person child;

	private Object spouseIdNumber;

	private int childIdNumbers() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public Employee(Worker worker, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, Gender gender) {
		this.setEmployeeId(worker.getEmployeeId());
		this.setAddress(worker.getAddress());
		this.setFirstName(worker.getFirstName());
		this.setLastName(worker.getLastName());
		this.setIdNumber(worker.getIdNumber());

		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {
		switch (grade) {
			case 1 -> monthlySalary = 3000000;
			case 2 -> monthlySalary = 5000000;
			case 3 -> monthlySalary = 7000000;
			default -> throw new IllegalArgumentException("Invalid grade");
		}

		if (isForeigner) {
			monthlySalary *= 1.5;
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setOtherMonthlyIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers());
	}
}
