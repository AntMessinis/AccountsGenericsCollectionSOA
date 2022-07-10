package dto;

public class UserDTO {
    String firstname;
    String lastname;
    String ssn;

    public UserDTO(UserDTO dto) {
        firstname = dto.getFirstname();
        lastname = dto.getLastname();
        ssn = dto.getSsn();
    }

    public UserDTO(String firstname, String lastname, String ssn) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
