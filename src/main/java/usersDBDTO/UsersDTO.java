package usersDBDTO;
//Описовий клас для створення екземплярів класу для наших користувачів,
// які будуть додаватись в БД під час підключення до боту
public class UsersDTO {
    private int ID;
    private String name;
    private String phone;

    public UsersDTO() {
    }

    public UsersDTO(int ID, String name, String phone) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
