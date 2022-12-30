package com.azissulaiman.eder;

public class Buku {
    private int cover;
    private String judul;
    private String penulis;
    private String rating;

    public Buku(int cover, String judul, String penulis, String rating) {
        this.cover = cover;
        this.judul = judul;
        this.penulis = penulis;
        this.rating = rating;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
