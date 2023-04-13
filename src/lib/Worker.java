package lib;

public class Worker extends Person {
    private String employeeId;
    private String address;

    public Worker(String firstName, String lastName, String idNumber) {
        super(firstName, lastName, idNumber);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
