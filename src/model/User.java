package model;


public class User {

    private long id;
    private String fullName;
    private int birthDay;
    private String phoneNumber;
    private String address;
    private UserStatus status;
    private Role role;

    public User(){

    }

    public User(long id, String fullName, int birthDay, String phoneNumber, String address) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User(long id, String fullName, int birthDay, String phoneNumber, String address, UserStatus status, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", role=" + role +
                '}';
    }

    public static void  transferFields(User oldUser, User newUser){
    oldUser.id = newUser.id;
    oldUser.fullName = newUser.fullName;
    oldUser.address = newUser.address;
    oldUser.birthDay = newUser.birthDay;
    oldUser.phoneNumber = newUser.phoneNumber;
    oldUser.status = newUser.status;
    }
}
