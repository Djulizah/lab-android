package com.example.assignment_3_2;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionList {
    String soal;
    String[] opsi;
    int jawabanBenar;
    int bobot;

    public QuestionList(String soal, String[] opsi, int jawabanBenar, int bobot) {
        this.soal = soal;
        this.opsi = opsi;
        this.jawabanBenar = jawabanBenar;
        this.bobot = bobot;
    }

    static QuestionList[] getQuizSample() {
        ArrayList questions = new ArrayList<QuestionList>();

        questions.add(new QuestionList("Siapakah bos banteng merah?", new String[]{"Mega-chan", "Puan", "Jokowi", "Amin"}, 0, 20));
        questions.add(new QuestionList("Jenis bencana alam apa yang terjadi di anime Tenki no Ko?", new String[]{"Meteor", "Gempa", "Banjir", "Longsor"}, 2, 39));
        questions.add(new QuestionList("Dari daftar nama berikut, siapakah tokoh Komunisme?", new String[]{"Benjamin Franklin", "Immanuel Kant", "Joseph Stalin", "Adolf Hitler"}, 2, 66));
        questions.add(new QuestionList("Dari daftar nama berikut, siapakah yang berasal dari Italia?", new String[]{"Adolf Hitler", "Benito Mussolini", "Idi Amin Dada", "Vladimir Lenin"}, 1, 58));
        questions.add(new QuestionList("Dari daftar nama berikut, siapakah tokoh kapitalisme?", new String[]{"Benito Mussolini", "Karl Marx", "Jean-Baptiste Say", "Adam Smith"}, 3, 66));
        questions.add(new QuestionList("Tanggal berapa Uni Soviet dibubarkan?", new String[]{"23 Juni 1996", "26 Desember 1991", "12 Februari 1982", "30 Januari 1993"}, 1, 67));
        questions.add(new QuestionList("Apa ibukota dari negara Denmark?", new String[]{"Veljle", "Odense", "Kopenhagen", "Randers"}, 2, 41));
        questions.add(new QuestionList("Siapakah pemain yang paling sering merasakan final FIFA World Cup?", new String[]{"Luis Figo", "Miroslav Klose", "Cafu", "Oliver Kahn"}, 2, 57));
        questions.add(new QuestionList("Apa ibukota dari negara Yugoslavia?", new String[]{"Udine", "Ljubljana", "Celje", "Maribor"}, 1, 41));

        Collections.shuffle(questions);
        return (QuestionList[]) questions.toArray(new QuestionList[]{});
    }
    public int getJawabanBenar() {
        return jawabanBenar;
    }

    public String getPilihanBenar(int index) {
        return opsi[index];
    }

}
