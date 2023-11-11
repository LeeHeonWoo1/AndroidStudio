package com.example.tatproject.ForRecyclerView;

import com.example.tatproject.R;

public class SingleItem {
    public String QATitle;
    public String writer;
    public String writeDate;
    public int resID;

    public SingleItem(String title, String writer, String writeDate, int resID){
        this.QATitle = title;
        this.writer = writer;
        this.writeDate = writeDate;
        this.resID = resID;
    }
}
