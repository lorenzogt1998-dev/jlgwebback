// src/main/java/JustoLamasGroup/DTO/ContactLeadRequest.java
package JustoLamasGroup.DTO;

public class ContactLeadRequest {

    private String name;
    private String email;
    private String enquiry;

    public ContactLeadRequest() {}

    public ContactLeadRequest(String name, String email, String enquiry) {
        this.name = name;
        this.email = email;
        this.enquiry = enquiry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }
}
