package com.katatest.producttrial.model;

import com.katatest.producttrial.dto.ProductDto;
import com.katatest.producttrial.model.abstracts.AuditMetaData;
import com.katatest.producttrial.util.enums.InventoryStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "products")
public class Product extends AuditMetaData {
    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private int shellId;
    private InventoryStatus inventoryStatus;
    private int rating;

    public Product() {
    }

    public Product(ProductDto productDto) {
        this.code = productDto.getCode();
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.image = productDto.getImage();
        this.category = productDto.getCategory();
        this.price = productDto.getPrice();
        this.quantity = productDto.getQuantity();
        this.internalReference = productDto.getInternalReference();
        this.shellId = productDto.getShellId();
        this.inventoryStatus = productDto.getInventoryStatus();
        this.rating = productDto.getRating();
    }

    public Product(String code, String name, String description, String image, String category, double price, int quantity, String internalReference, int shellId, InventoryStatus inventoryStatus, int rating) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.internalReference = internalReference;
        this.shellId = shellId;
        this.inventoryStatus = inventoryStatus;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInternalReference() {
        return internalReference;
    }

    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }

    public int getShellId() {
        return shellId;
    }

    public void setShellId(int shellId) {
        this.shellId = shellId;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
