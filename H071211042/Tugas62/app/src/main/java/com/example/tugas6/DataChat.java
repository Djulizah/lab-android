package com.example.tugas6;

import java.util.ArrayList;
import java.util.List;

public class DataChat {

    private static List<List<ModelBubble>>chats(){
        List<List<ModelBubble>>chats = new ArrayList<>();
        //orang
        List<ModelBubble>chat1= new ArrayList<>();
        chat1.add(new ModelBubble("Oii","19.20", true));
        chat1.add(new ModelBubble("Kamu Dimana?","19.21", false));
        chat1.add(new ModelBubble("Jalan yuk","19.21", true));
        chat1.add(new ModelBubble("Lagi Gabut","19.25", false));
        chat1.add(new ModelBubble("Nonton MPL aja di Mall Serpong","19.26", false));

        chats.add(chat1);

        List<ModelBubble>chat2= new ArrayList<>();
        chat2.add(new ModelBubble("Siapa?","19.01", true));
        chat2.add(new ModelBubble("Saya Orang Kedua","19.01", false));

        chats.add(chat2);

        List<ModelBubble>chat3= new ArrayList<>();
        chat3.add(new ModelBubble("Siapa?","18.47", true));
        chat3.add(new ModelBubble("Saya Orang Ketiga","18.47", false));

        chats.add(chat3);

        List<ModelBubble>chat4= new ArrayList<>();
        chat4.add(new ModelBubble("Siapa?","18.46", true));
        chat4.add(new ModelBubble("Saya Orang Keempat","18.46", false));

        chats.add(chat4);

        List<ModelBubble>chat5= new ArrayList<>();
        chat5.add(new ModelBubble("Siapa?","18.45", true));
        chat5.add(new ModelBubble("Saya Orang Kelima","18.45", false));

        chats.add(chat5);

        List<ModelBubble>chat6= new ArrayList<>();
        chat6.add(new ModelBubble("Siapa?","18.30", true));
        chat6.add(new ModelBubble("Saya Orang Keenam","18.31", false));

        chats.add(chat6);

        List<ModelBubble>chat7= new ArrayList<>();
        chat7.add(new ModelBubble("Siapa?","18.22", true));
        chat7.add(new ModelBubble("Saya Orang Ketujuh","18.23", false));

        chats.add(chat7);

        List<ModelBubble>chat8= new ArrayList<>();
        chat8.add(new ModelBubble("Siapa?","18.11", true));
        chat8.add(new ModelBubble("Saya Orang Kedelapan","18.19", false));

        chats.add(chat8);

        List<ModelBubble>chat9= new ArrayList<>();
        chat9.add(new ModelBubble("Siapa?","17.11", true));
        chat9.add(new ModelBubble("Saya Orang Kesembilan","17.08", false));

        chats.add(chat9);

        List<ModelBubble>chat10= new ArrayList<>();
        chat10.add(new ModelBubble("Siapa?","16.43", true));
        chat10.add(new ModelBubble("Saya Orang Kesepuluh","16.52", false));

        chats.add(chat10);


        return chats;
    }

    public static ArrayList<ModelChat>
    ambilDataChat() {
        ArrayList<ModelChat> dataChat = new ArrayList<>();
        dataChat.add(new ModelChat("Unaa",chats().get(0), "https://i.pinimg.com/550x/80/e1/b3/80e1b3a0f8946dcb34e3d05aade2c511.jpg", "081234567890", "Busy", "21.00"));
        dataChat.add(new ModelChat("Alice", chats().get(1), "https://dailyspin.id/wp-content/uploads/2020/09/Alice.jpg", "0812233455677", "Available", "21.30"));
        dataChat.add(new ModelChat("Cathezz", chats().get(2), "https://img.celebrities.id/okz/900/8ztu22/master_wV7988c9eS_895_biodata_profil_dan_fakta_catheez.jpg", "082345678901", "On School", "20.08"));
        dataChat.add(new ModelChat("Listy Chan", chats().get(3), "https://blue.kumparan.com/image/upload/fl_progressive,fl_lossy,c_fill,q_auto:best,w_640/v1594894717/gsufa6nphrcvormcuzeo.jpg", "081234567890", "On Riding", "20.02"));
        dataChat.add(new ModelChat("Jessica Jane", chats().get(4), "https://cdn1-production-images-kly.akamaized.net/V82rkMs3ZMU9po8bQEtyARBUelI=/1200x1200/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3181667/original/019329100_1594890389-Jessica_Jane_0.jpg", "083456789012", "Is Dead", "01.12"));
        dataChat.add(new ModelChat("Jeha", chats().get(5), "https://media.hitekno.com/thumbs/2021/01/05/74979-gamer-jeha-anais-instagram-atjeha-anais/o-img-74979-gamer-jeha-anais-instagram-atjeha-anais.jpg", "085678901234", "Online", "Kemarin 13.20"));
        dataChat.add(new ModelChat("Babyla", chats().get(6), "https://assets.ayobandung.com/crop/0x0:0x0/750x500/webp/photo/2022/07/04/1483090858.jpg", "088901234567", "Happy", "21.20"));
        dataChat.add(new ModelChat("Muthee", chats().get(7), "https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2021/09/15/1770537305.jpg", "081098765432", "Lonely", "30 Feb 2023"));
        dataChat.add(new ModelChat("Gebiann", chats().get(8), "https://i.pinimg.com/originals/3c/ab/e9/3cabe9a190b67a37eda08d8456e1ec8b.jpg", "088123456145", "Turneyy", "10.09"));
        dataChat.add(new ModelChat("Valezzka", chats().get(9), "https://parboaboa.com/data/foto_berita/WhatsApp%20Image%202023-03-22%20at%2015.57.10.webp", "082937123908", "Gaming", "Recently"));
        return dataChat;


    }
}

