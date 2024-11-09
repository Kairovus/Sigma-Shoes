package Model;

import javax.validation.constraints.*;
import java.io.InputStream;

public class Product {
    @NotBlank(message = "Name cannot be null")
    @NotEmpty       
    private String name;
    
    @NotBlank(message = "Name cannot be null")
    private String desc;
    
    @NotNull
    @Positive
    private int category;
    
    @NotBlank
    private String brand;
    
    private String color;
    
    private String sex;
    
    private String b64Image;
    
    @NotNull
    private float price;
    
    private int id;
    private InputStream ISImage;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public InputStream getISImage() {
        return ISImage;
    }

    public void setISImage(InputStream ISImage) {
        this.ISImage = ISImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getB64Image() {
        return b64Image;
    }

    public void setB64Image(String b64Image) {
        this.b64Image = b64Image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
