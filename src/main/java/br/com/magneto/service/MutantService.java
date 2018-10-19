package br.com.magneto.service;

import org.springframework.stereotype.Service;

@Service
public class MutantService {

    public boolean isMutant(String[] dna){
        final int n = dna.length;

        String [][] matrix = this.getCharMatrix(dna, n);

        int resultHorizontalTest = this.findSequenceInLines(matrix,n);
        if(resultHorizontalTest >= 2) return  true;

        int resultVerticalTest = this.findSequenceInCols(matrix,n);
        if(resultHorizontalTest + resultVerticalTest >= 2) return  true;

        int resultPriDiagonal = this.findSequenceInPrimaryDiagonal(matrix,n);
        if(resultPriDiagonal + resultHorizontalTest + resultVerticalTest >= 2) return  true;

        int resultSecDiagonal = this.findSequenceInSecondaryDiagonal(matrix,n);
        if(resultSecDiagonal + resultPriDiagonal + resultHorizontalTest + resultVerticalTest >= 2) return  true;

        //TODO save dna in mongodb

        return false;
    }

    private String [][] getCharMatrix(String[] dna, int n) {

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


    private int findSequenceInLines(String [][] matrix, int n){
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

    private int findSequenceInCols(String [][] matrix, int n){
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

    private int findSequenceInPrimaryDiagonal(String [][] matrix, int n){
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

    private int findSequenceInSecondaryDiagonal(String [][] matrix, int n){
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
