package com.ganghuang.mlc2_android.TestModules.TestGason;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TestProduct implements Serializable {//定义的类，可能包含商品的相关信息，例如图片、名称、价格等

    /**
     * @SerializedName 是一个注解，用于指定在将 JSON 数据解析为 Java 对象时，JSON 字段与 Java 字段之间的映射关系。
     * 它通常用于 JSON 字段名和 Java 字段名不一致的情况。
     *
     * @SerializedName("item_pic") 注解告诉 JSON 解析库（如 Gson），
     * 在解析 JSON 数据时，JSON 字段 item_pic 对应的是 Java 字段 image。
     *
     * @SerializedName 注解是由 Gson 库提供的。
     * Gson 是一个用于在 Java 和 JSON 之间进行转换的开源库。它不是系统自带的库，而是一个第三方库。
     * **/
    @SerializedName("item_pic")
    private String image;

    @SerializedName("item_name")
    private String name;

    @SerializedName("item_id")
    private int itemId;

    @SerializedName("market_price")
    private int marketPrice;

    @SerializedName("current_price")
    private int currentPrice;

    // Getters and setters
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(int marketPrice) {
        this.marketPrice = marketPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }
}


class Page<T> {//Page 是一个用于分页的数据结构，通常用于处理分页加载的数据。
    private List<T> items;
    private int currentPage;
    private int totalPages;

    // Getters and setters
    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

/**
 * Resource 是一种常见的包装类，用于封装资源的状态、数据和错误信息。
 * 通常在网络请求或数据库查询时使用，以便更好地管理加载状态和错误处理。
 */
class Resource<T> {
    public enum Status {
        SUCCESS, ERROR, LOADING
    }

    private Status status;
    private T data;
    private String message;

    public Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String message, T data) {
        return new Resource<>(Status.ERROR, data, message);
    }

    public static <T> Resource<T> loading(T data) {
        return new Resource<>(Status.LOADING, data, null);
    }
}

