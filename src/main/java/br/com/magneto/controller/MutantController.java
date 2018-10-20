package br.com.magneto.controller;

import br.com.magneto.dto.DnaRequest;
import br.com.magneto.dto.StatisticResponse;
import br.com.magneto.service.MutantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MutantController {

    private MutantService mutantService;

    public MutantController(final MutantService mutantService) {
        this.mutantService = mutantService;
    }

    String[] validStrings    = { "A", "T", "C", "G" };

    @GetMapping("/hello")
    public String showLuckyWord() {
        return "hello";
    }


    @PostMapping("/mutant")
    public ResponseEntity verifyIsMutant(@RequestBody DnaRequest dnaRequest){
        if (validPayload(dnaRequest)){
            if(mutantService.isMutant(dnaRequest.getDna())){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<StatisticResponse> getStats() {
        return new ResponseEntity<>(mutantService.getStats(), HttpStatus.OK);
    }

    //TODO Create @Validator to dna req
    boolean validPayload(DnaRequest dnaRequest){
        if(dnaRequest == null || dnaRequest.getDna() == null)
            return false;

        return isSquareMatrix(dnaRequest) && isValidString(dnaRequest);
    }

    private boolean isValidString(DnaRequest dnaRequest) {
        boolean isValid = true;
        int lenthDna = dnaRequest.getDna().length;
        String [][] matrix= new String[lenthDna][lenthDna];

        int i = 0;
        for (String dna : dnaRequest.getDna()) {
            String [] lineMatrix = dna.split("");
            int j = 0;
            for (String ch : lineMatrix){
                ch = ch.toUpperCase();
                matrix[i][j]=ch;
                if (!isValidChar(ch)) return false;
                j++;
            }
            i++;
        }

        return isValid;
    }

    private boolean isSquareMatrix(DnaRequest dnaRequest) {
        int lenthDna = dnaRequest.getDna().length;
        boolean isValid = true;
        for(String s : dnaRequest.getDna()){
           if(lenthDna != s.length()){
               isValid = false;
           }
        }
        return isValid;
    }

    private boolean isValidChar(String s){
        return  s.contains("A") || s.contains("T") ||
                s.contains("C") || s.contains("G");
    }
}
