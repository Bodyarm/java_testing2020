package ru.stqa.jt2020.sandbox;


import org.testng.annotations.Test;


public class restoreknowledge {


    @Test
    public void test1() {
        String s = "abcde";
        String goal = "cdeab";
        String smod = s;
        boolean rotatePosible = false;
        //Нужна проверка, что длины совпадают
        //Проверка, что все символы из goal есть в s
        //Если обе проверки проходят, то дальше уже крутишь
        for(int i =1; i <=s.length();i++) {
            smod = smod.substring(1, smod.length()) + smod.substring(0, 1);
            System.out.println(smod);

            if(smod.equals(goal)) {
                rotatePosible=true;
                break;
            }

        }

        System.out.println(rotatePosible);




    }

}
