package com.example.tatproject.ForRecyclerView;

public class PackageSingleItem {
    String packageID;
    public String title;
    String price;
    int articleNum;

    public PackageSingleItem(String title, String packID, String price, int num){
        this.packageID = packID;
        this.title = title;
        this.price = price;
        this.articleNum = num;
    }
}
