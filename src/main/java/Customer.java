import com.sun.org.apache.xpath.internal.operations.Bool;

public class Customer {
    private Long id;
    private Double bankAccount;
    private String name, surname;
    private Long faceCode;
    private Double crimeCoef;
    private int age;
    private Boolean isActiveBox;

    public Customer(Long id, Double bankAccount, String name, String surname, Long faceCode, int age, Double crimeCoef, Boolean isActiveBox) {
        this.id = id;
        this.bankAccount = bankAccount;
        this.name = name;
        this.surname = surname;
        this.faceCode = faceCode;
        this.age = age;
        this.crimeCoef = crimeCoef;
        this.isActiveBox = isActiveBox;
    }

    public Double getCrimeCoef() {
        return crimeCoef;
    }

    public void setCrimeCoef(Double crimeCoef) {
        this.crimeCoef = crimeCoef;
    }

    public Boolean getActiveBox() {
        return isActiveBox;
    }

    public void setActiveBox(Boolean activeBox) {
        isActiveBox = activeBox;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Double bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getFaceCode() {
        return faceCode;
    }

    public void setFaceCode(Long faceCode) {
        this.faceCode = faceCode;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
