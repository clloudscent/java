package com.example.board;

public class Gugudan {
    public static void main(String[] args) {
        for(int i =1; i<=9; i++){
            StringBuffer sb = new StringBuffer();
            for(int j = 2; j<=9; j++){
                // %s : string
                // %d : integer
                // %f : float
                // %b : boolean
                sb.append("|\t %d X %d = %d |".formatted(j, i , i*j));
            }
            sb.append("|");
            System.out.println(sb.toString());
        }
    }
}
