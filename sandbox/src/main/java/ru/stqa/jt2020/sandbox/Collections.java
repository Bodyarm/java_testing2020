package ru.stqa.jt2020.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main (String[] args) {
        String[] langs = {"Java","C#","Python","PHP"    };

        for (String l : langs){
            System.out.println(l + " kus!");
        }
        List<String> languages = new ArrayList<String>();
        languages.add("Java");
        languages.add("C#");
        languages.add("Python");
        languages.add("PHP");

        for (String l : languages){
            System.out.println(l + " ---------kus!");
        }

        List<String> langg = Arrays.asList("Java","C#","Python","PHP");

        for (int i = 0; i < langg.size(); i++){
            System.out.println(langg.get(i) + " ****kus!");
        }

    }

}
