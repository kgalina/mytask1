package com.mytask.mytask1.Steps;

import java.util.Random;

public class Steps  {
    public String inputRandString() throws InterruptedException {
        Random random = new Random();
        int rand_int = random.nextInt();
        String rand_symb = String.valueOf(rand_int);
        return rand_symb;
    }

    }