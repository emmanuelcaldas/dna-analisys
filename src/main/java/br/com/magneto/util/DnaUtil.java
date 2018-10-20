package br.com.magneto.util;

import org.springframework.stereotype.Component;

@Component
public class DnaUtil {

    public String [][] getCharMatrix(String[] dna, int n) {

        String [][] matrix= new String[n][n];
        int i = 0;
        for (String line : dna) {
            String [] lineMatrix = line.split("");
            int j = 0;
            for (String ch : lineMatrix){
                ch = ch.toUpperCase();
                matrix[i][j]=ch;
                j++;
            }
            i++;
        }
        return matrix;
    }


    public int findSequenceInLines(String [][] matrix, int n){
        int totalOcurrences = 0;
        for(int i = 0; i<n; i++){
            String lastStr = "", fixedStr = "";
            int totalSequence = 1;
            for(int j = 0; j<n; j++){
                if(lastStr.equals(matrix[i][j])){
                    if(totalSequence == 1) fixedStr = matrix[i][j];
                    if(lastStr.equals(fixedStr)) {
                        totalSequence++;
                        if (totalSequence >= 4) {
                            totalOcurrences++;
                        }
                    }else{
                        totalSequence = 1;
                    }
                }
                lastStr = matrix[i][j];
            }
        }
        return totalOcurrences;
    }

    public int findSequenceInCols(String [][] matrix, int n){
        int totalOcurrences = 0;
        for(int j = 0; j<n; j++){
            String lastStr = "", fixedStr = "";
            int totalSequence = 1;
            for(int i = 0; i<n; i++){
                if(lastStr.equals(matrix[i][j])){
                    if(totalSequence == 1) fixedStr = matrix[i][j];
                    if(lastStr.equals(fixedStr)) {
                        totalSequence++;
                        if (totalSequence >= 4) {
                            totalOcurrences++;
                        }
                    }else{
                        totalSequence = 1;
                    }
                }
                lastStr = matrix[i][j];
            }
        }
        return totalOcurrences;
    }

    public int findSequenceInPrimaryDiagonal(String [][] matrix, int n){
        int totalOcurrences = 0;
        String lastStr = "", fixedStr = "";
        int totalSequence = 1;
        for(int i = 0; i<n; i++){
            if(lastStr.equals(matrix[i][i])){
                if(totalSequence == 1) fixedStr = matrix[i][i];
                if(lastStr.equals(fixedStr)) {
                    totalSequence++;
                    if (totalSequence >= 4) {
                        totalOcurrences++;
                    }
                }else{
                    totalSequence = 1;
                }
            }
            lastStr = matrix[i][i];
        }
        return totalOcurrences;
    }

    public int findSequenceInSecondaryDiagonal(String [][] matrix, int n){
        int totalOcurrences = 0;
        String lastStr = "", fixedStr = "";
        int totalSequence = 1;
        for(int i = 0, j = n-1; i<n; i++ , j--){
            if(lastStr.equals(matrix[i][j])){
                if(totalSequence == 1) fixedStr = matrix[i][j];
                if(lastStr.equals(fixedStr)) {
                    totalSequence++;
                    if (totalSequence >= 4) {
                        totalOcurrences++;
                    }
                }else{
                    totalSequence = 1;
                }
            }
            lastStr = matrix[i][j];
        }
        return totalOcurrences;
    }
}
