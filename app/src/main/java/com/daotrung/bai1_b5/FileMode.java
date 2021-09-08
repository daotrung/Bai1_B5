package com.daotrung.bai1_b5;

public class FileMode {
    int img1;
    int img2;
    String txtTitle;

    public FileMode(int img1, int img2, String txtTitle) {
        this.img1 = img1;
        this.img2 = img2;
        this.txtTitle = txtTitle;
    }

    public FileMode(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public FileMode() {
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public int getImg2() {
        return img2;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }
}