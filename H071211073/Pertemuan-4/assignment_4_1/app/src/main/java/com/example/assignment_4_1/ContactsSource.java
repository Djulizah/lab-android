package com.example.assignment_4_1;

import java.util.ArrayList;

public class ContactsSource {
    private static ArrayList<ArrayList<Chats>> chats() {
        ArrayList<ArrayList<Chats>> chats = new ArrayList<>();

        ArrayList<Chats> chatLeon = new ArrayList<>();
        chatLeon.add(new Chats("go to the windmill", "00:00"));
        chatLeon.add(new Chats("now", "01:00"));
        chats.add(chatLeon);

        ArrayList<Chats> chatClaire = new ArrayList<>();
        chatClaire.add(new Chats("go to safe point", "03:00"));
        chatClaire.add(new Chats("mission complete", "13:00"));
        chats.add(chatClaire);

        ArrayList<Chats> chatAshley = new ArrayList<>();
        chatAshley.add(new Chats("target acquired", "00:00"));
        chats.add(chatAshley);

        ArrayList<Chats> chatSadler = new ArrayList<>();
        chatSadler.add(new Chats("no thanks, bro!", "00:00"));
        chats.add(chatSadler);

        ArrayList<Chats> chatJill = new ArrayList<>();
        chatJill.add(new Chats("get away from Mr.X", "00:00"));
        chatJill.add(new Chats("Head over to the bridge", "01:00"));
        chatJill.add(new Chats("Mission Complete", "15:00"));
        chats.add(chatJill);

        ArrayList<Chats> chatChris = new ArrayList<>();
        chatChris.add(new Chats("unidentified", "00:00"));
        chatChris.add(new Chats("reload", "01:00"));
        chatChris.add(new Chats("reload", "01:00"));
        chatChris.add(new Chats("reload", "01:00"));
        chatChris.add(new Chats("reload", "01:00"));
        chats.add(chatChris);

        ArrayList<Chats> chatLuis = new ArrayList<>();
        chatLuis.add(new Chats("baila my friend :3", "00:00"));
        chatLuis.add(new Chats("target neutralized", "01:00"));
        chats.add(chatLuis);

        ArrayList<Chats> chatAda = new ArrayList<>();
        chatAda.add(new Chats("target lost contact", "00:00"));
        chatAda.add(new Chats("unidentified", "01:00"));
        chats.add(chatAda);

        ArrayList<Chats> chatEthan = new ArrayList<>();
        chatEthan.add(new Chats("message not found", "00:00"));
        chats.add(chatEthan);

        ArrayList<Chats> chatCarlos = new ArrayList<>();
        chatCarlos.add(new Chats("aid Jill to eliminate Mr.X on the bridge", "01:15"));
        chatCarlos.add(new Chats("Mission Complete", "15:00"));
        chats.add(chatCarlos);
        return chats;
    }
    public static ArrayList<Contacts> contacts = generateContacts();
    private static ArrayList<Contacts> generateContacts() {
        ArrayList<Contacts> contacts = new ArrayList<>();
        contacts.add(new Contacts("Leon K","9987 6629", "Un infected", "March 23, 2023", R.drawable.re__2_, chats().get(0)));
        contacts.add(new Contacts("Claire Redfield","09236 984", "On board", "March 4, 2020", R.drawable.re__8_, chats().get(1)));
        contacts.add(new Contacts("Baby Eagle", "324 2385", "Home :3", "May 25, 2023", R.drawable.re__3_, chats().get(2)));
        contacts.add(new Contacts("Lord", "32 53983", "Las plagas", "March 22, 2023", R.drawable.re__6_, chats().get(3)));
        contacts.add(new Contacts("Jill V", "912843 234", "On mission", "September 9, 2020", R.drawable.re__7_, chats().get(4)));
        contacts.add(new Contacts("Chris", "234436 984", "Busy", "June 7, 1996", R.drawable.re__9_, chats().get(5)));
        contacts.add(new Contacts("L Sera", "2938 35", "mamacita", "March 4, 2020", R.drawable.re__5_, chats().get(6)));
        contacts.add(new Contacts("A.W.", "xxx xx xx", "Null", "March 25, 2023", R.drawable.re__4_, chats().get(7)));
        contacts.add(new Contacts("Ethan Winters", "0983 984", "Missing", "December 20, 2018", R.drawable.baseline_person_24, chats().get(8)));
        contacts.add(new Contacts("Carlos","09236 984", "On board", "March 4, 2020", R.drawable.re__1_, chats().get(9)));
        return contacts;
    }

//    private static ArrayList<Contacts> profileContact() {
//        ArrayList<Contacts> profileContact = new ArrayList<>();
//        profileContact.add(new Contacts("Leon K", "9987 6629", "Un infected", "March 23, 2023", R.drawable.re__2_));
//        return profileContact;
//    }
}
