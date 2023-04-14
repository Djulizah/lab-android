package com.example.tugas6;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ModelChat implements Parcelable {
    private String nama;
    private List<ModelBubble> chat;
    private String foto;

    private String noHP;
    private String status;
    private String time;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<ModelBubble> getChat() {
        return chat;
    }

    public void setChat(List<ModelBubble> chat) {
        this.chat = chat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ModelChat(String nama, List<ModelBubble> chat, String foto, String noHP, String status, String time) {
        this.nama = nama;
        this.chat = chat;
        this.foto = foto;
        this.noHP = noHP;
        this.status = status;
        this.time = time;
    }

    protected ModelChat(Parcel in) {
        nama = in.readString();
        chat = in.createTypedArrayList(ModelBubble.CREATOR);
        foto = in.readString();
        noHP = in.readString();
        status = in.readString();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeTypedList(chat);
        dest.writeString(foto);
        dest.writeString(noHP);
        dest.writeString(status);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelChat> CREATOR = new Creator<ModelChat>() {
        @Override
        public ModelChat createFromParcel(Parcel in) {
            return new ModelChat(in);
        }

        @Override
        public ModelChat[] newArray(int size) {
            return new ModelChat[size];
        }
    };
}
