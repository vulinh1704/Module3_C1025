package service;

import entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService<Product> {
    private List<Product> products;

    public ProductService() {
        this.products = new ArrayList<>();
        this.add(new Product(1, "Test", 4.5, "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcSjfe0TS6hbkcaKVyU1TSg589BvvBfplZEjjd4Vy98H7JiRA1dh3zuc5qFRYqdpJxEep5Bc-kdNDLNfiQh2aJuvkU_UWh9akB6M2vQiigJW86KaEzQXQoTDhLVFd30Fb9DvXKWsYw&usqp=CAc"));
    }

    @Override
    public void add(Product product) {
        this.products.add(product);
    }

    @Override
    public List<Product> getAll() {
        return this.products;
    }

    @Override
    public Product getById(int id) {
        int index = this.getIndexById(id);
        return this.products.get(index);
    }

    private int getIndexById(int id) {
        for (int i = 0; i < this.products.size(); i++) {
            if(this.products.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void update(int id, Product product) {
        int index = this.getIndexById(id);
        this.products.set(index, product);
    }

    @Override
    public void deleted(int id) {
        int index = this.getIndexById(id);
        this.products.remove(index);
    }
}
