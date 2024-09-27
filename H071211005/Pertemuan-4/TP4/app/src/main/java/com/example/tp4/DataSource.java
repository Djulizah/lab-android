package com.example.tp4;

import java.util.ArrayList;

public class DataSource {

        private static ArrayList<Room> getRoomChat() {
            ArrayList<Room> roomChat = new ArrayList<>();
            // chat2
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Firaa"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Firaa"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Firaa"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Firaa"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Firaa"));
            // chat1
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Oh fira"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Oh fira"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Oh fira"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Oh fira"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Oh fira"));
            // chat2
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Fira Cans"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Fira Cans"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Fira Cans"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Fira Cans"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Fira Cans"));
            // chat1
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Raafi"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Raafi"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Raafi"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Raafi"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Raafi"));
            // chat1
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Inzani"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Inzani"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Inzani"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Inzani"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Inzani"));
            // chat2
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Mage"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Mage"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Mage"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Mage"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Mage"));
            // chat1
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Andi"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Andi"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Andi"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Andi"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Andi"));
            // chat2
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Saputra"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Saputra"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Saputra"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Saputra"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Saputra"));
            // chat1
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Maghfirah"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Maghfirah"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Maghfirah"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Maghfirah"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Maghfirah"));
            // chat2
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Zani"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Zani"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Zani"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Zani"));
            roomChat.add(new Room("Hai", "21.30", "hai juga", "12.34", "Zani"));
            return roomChat;
        }

        private static ArrayList<Chat> getOnlyChat() {
            ArrayList<Chat> chat = new ArrayList<>();
        chat.add(new Chat("Firaa",  "01.44", R.drawable.im1, "+62 123 123 1234", "Busy", "April 12,2023"));
        chat.add(new Chat("Oh fira",  "02.46", R.drawable.im2, "+62 123 123 1234", "Busy", "April 12,2023"));
        chat.add(new Chat("Fira Cans",  "03.46", R.drawable.im3, "+62 123 123 1234", "Busy", "April 12,2023"));
        chat.add(new Chat("Raafi",  "04.46", R.drawable.im4,"+62 123 123 1234", "Busy", "April 12,2023"  ));
        chat.add(new Chat("Inzani",  "05.46", R.drawable.im5, "+62 123 123 1234", "Busy", "April 12,2023"));
        chat.add(new Chat("Mage",  "06.46", R.drawable.img10, "+62 123 123 1234", "Busy", "April 12,2023"));
        chat.add(new Chat("Andi",  "17.46", R.drawable.img7, "+62 123 123 1234", "Busy", "April 12,2023"));
        chat.add(new Chat("Saputra",  "18.46", R.drawable.imglo, "+62 123 123 1234", "Busy", "April 12,2023"));
        chat.add(new Chat("Maghfirah",  "19.46", R.drawable.imgpe, "+62 123 123 1234", "Busy", "April 12,2023" ));
        chat.add(new Chat("Zani",  "23.46", R.drawable.tanjiro, "+62 123 123 1234", "Busy", "April 12,2023"));
        return chat;
    }
    public static ArrayList<Chat> getChatWithRoomChat(){
        ArrayList<Chat> listChat = new ArrayList<>();
        //gabungkan chat dn roomchat
        for(Chat chat: getOnlyChat()){
            for(Room roomChat: getRoomChat()){
                // jika nama kontak sama dengan nama di roomchat
                if(roomChat.getTvName().equals(chat.getTvName())){
                    // dipasangkan
                    chat.addChat(roomChat);
                }
            }
            listChat.add(chat);
        }
        return listChat;
    }
}
