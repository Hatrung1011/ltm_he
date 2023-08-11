package Model;

import java.io.Serializable;

public class StationeryType implements Serializable {
    private static final long serialVersionUID = 2L;


    private int id;
    private String name;
    private String description;

    public StationeryType(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] toArray() {
        String[] result = {String.valueOf(this.getId()), this.getName(), this.getDescription()};
        return result;
    }

    @Override
    public String toString() {
        return "StationeryType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
