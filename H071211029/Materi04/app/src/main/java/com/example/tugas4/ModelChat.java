package com.example.tugas4;

public class ModelChat {
    String nama;
    String chat;
    String time;

    public ModelChat(String nama, String chat, int foto, String time, String nomor, String status, String statusTime) {
        this.nama = nama;
        this.chat = chat;
        this.time = time;
        this.nomor = nomor;
        this.status = status;
        this.statusTime = statusTime;
        this.foto = foto;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime;
    }

    String nomor;
    String status;
    String statusTime;
    int foto;


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
