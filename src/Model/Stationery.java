package Model;

import java.io.Serializable;

public class Stationery implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String type;
    private int age;
    private String img;
    private String price;

    public Stationery(int id, String name, String type, int age, String img, String price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.img = img;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String[] toArray() {
        String[] result = {String.valueOf(this.getId()), this.getName(), this.getType(), String.valueOf(this.getAge()), this.getImg(), this.getPrice()};
        return result;
    }

    @Override
    public String toString() {
        return "Stationery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", img='" + img + '\'' +
                ", price=" + price +
                '}';
    }
}
