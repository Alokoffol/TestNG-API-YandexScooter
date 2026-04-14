package e2e.api.models;


public class Order {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final Integer rentTime;
    private final String deliveryDate;
    private final String comment;
    private String[] color;

    // Конструктор без цвета
    public Order(String firstName, String lastName, String address, String metroStation,
                 String phone, Integer rentTime, String deliveryDate, String comment) {
        this(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, null);
    }

    // Конструктор с цветом
    public Order(String firstName, String lastName, String address, String metroStation,
                 String phone, Integer rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    // Геттеры
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public String getMetroStation() { return metroStation; }
    public String getPhone() { return phone; }
    public Integer getRentTime() { return rentTime; }
    public String getDeliveryDate() { return deliveryDate; }
    public String getComment() { return comment; }
    public String[] getColor() { return color; }


    public void setColor(String[] color) {
        this.color = color;
    }
}