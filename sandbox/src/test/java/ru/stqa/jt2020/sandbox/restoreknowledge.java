package ru.stqa.jt2020.sandbox;

import org.testng.annotations.Test;

public class restoreknowledge {




    @Test
    public void test1(){

        int num = 121;
        int kus = num;
        int buf=0;
        if(num <0 ){
//            return false;
            System.out.println(false);
        }
        while(kus>=10){
            buf = buf*10 + kus%10;
            kus = kus / 10;

        }
        buf = buf*10+kus;
        if (buf ==num)
//            return true;
            System.out.println(true);
        else
//            return false;
            System.out.println(false);

    }

}
