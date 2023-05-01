package com.example.tuprak4chat;

import java.util.ArrayList;
import java.util.Arrays;

public class DataSource {
    private static ArrayList<RoomChat> getRoomChat() {
        ArrayList<RoomChat> roomChats = new ArrayList<>();

        //chat1
        roomChats.add(new RoomChat("Doi 1", "Cantikk", "iyaaw", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 1", "Jalann yukk", "Hayukk", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 1", "otww", "gazz", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 1", "okeii", "sippp", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 1", "lalala", "nanana", "20.00", "21.30"));
        //chat2
        roomChats.add(new RoomChat("Rony", "Jalann yukk", "Hayukk", "20.00", "21.30"));
        roomChats.add(new RoomChat("Rony", "Jalann yukk", "Hayukk", "20.00", "21.30"));
        roomChats.add(new RoomChat("Rony", "otww", "gazz", "20.00", "21.30"));
        roomChats.add(new RoomChat("Rony", "okeii", "sippp", "20.00", "21.30"));
        roomChats.add(new RoomChat("Rony", "lalala", "nanana", "20.00", "21.30"));

        //chat3
        roomChats.add(new RoomChat("Paul", "mowninggg", "iyaaw", "20.00", "21.30"));
        roomChats.add(new RoomChat("Paul", "Jalann yukk", "Hayukk", "20.00", "21.30"));
        roomChats.add(new RoomChat("Paul", "otww", "gazz", "20.00", "21.30"));
        roomChats.add(new RoomChat("Paul", "okeii", "sippp", "20.00", "21.30"));
        roomChats.add(new RoomChat("Paul", "lalala", "nanana", "20.00", "21.30"));

        //chat4
        roomChats.add(new RoomChat("Doi 2", "haloow cantikk", "iyaaw", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 2", "Jalann yukk", "Hayukk", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 2", "otww", "gazz", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 2", "okeii", "sippp", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 2", "lalala", "nanana", "20.00", "21.30"));

        //chat5
        roomChats.add(new RoomChat("Doi 3", "dell cantikkk", "iyaaw", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 3", "Jalann yukk", "Hayukk", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 3", "otww", "gazz", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 3", "okeii", "sippp", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 3", "lalala", "nanana", "20.00", "21.30"));

        //chat6
        roomChats.add(new RoomChat("Doi 5", "makann yukk", "iyaaw", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 5", "Jalann yukk", "Hayukk", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 5", "otww", "gazz", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 5", "okeii", "sippp", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 5", "lalala", "nanana", "20.00", "21.30"));

        //chat7
        roomChats.add(new RoomChat("Doi 4", "wru", "iyaaw", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 4", "Jalann yukk", "Hayukk", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 4", "otww", "gazz", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 4", "okeii", "sippp", "20.00", "21.30"));
        roomChats.add(new RoomChat("Doi 4", "lalala", "nanana", "20.00", "21.30"));
        return roomChats;

    }

    private static ArrayList<Chat> getOnlyChat() {
        ArrayList<Chat> chats = new ArrayList<>();
        chats.add(new Chat("Doi 1", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.baale ));
        chats.add(new Chat("Rony", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.rony ));
        chats.add(new Chat("Paul", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.paull ));
        chats.add(new Chat("Doi 2", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.angga ));
        chats.add(new Chat("Doi 3", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.azmi ));
        chats.add(new Chat("Doi 5", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.rizkyn ));
        chats.add(new Chat("Doi 4", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.cipung));
        chats.add(new Chat("Doi 2", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.angga ));
        chats.add(new Chat("Doi 3", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.azmi ));
        chats.add(new Chat("Doi 5", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.rizkyn ));
        chats.add(new Chat("Doi 4", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.cipung));
        chats.add(new Chat("Doi 2", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.angga ));
        chats.add(new Chat("Doi 3", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.azmi ));
        chats.add(new Chat("Doi 5", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.rizkyn ));
        chats.add(new Chat("Doi 4", "11.31",  "+62 111 123 3344","Busy","Februari 01,2023", R.drawable.cipung));

        return chats;
    }

    public static ArrayList<Chat> getChatWithRoomChat(){
        ArrayList<Chat> listChat = new ArrayList<>();

        //gabungan chat dan roomchat
        for (Chat chat: getOnlyChat()){
            for(RoomChat roomChat: getRoomChat()){
                //jika nma konntak sama dgn nama di roomchat
                if (roomChat.getName().equals(chat.getName())){
                    //dipasangkan
                    chat.addChat(roomChat);
                }
            }
            //memasukkan chat ke list chat baru
            listChat.add(chat);
        }
        return listChat;
    }
}
