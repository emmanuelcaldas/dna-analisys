package br.com.magneto.service;

import br.com.magneto.domain.VerifiedDna;
import br.com.magneto.dto.StatisticResponse;
import br.com.magneto.repository.DnaRepository;
import br.com.magneto.util.DnaUtil;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    private DnaRepository dnaRepository;
    private DnaUtil dnaUtil;

    public MutantService(final DnaRepository dnaRepository, DnaUtil dnaUtil) {
        this.dnaRepository = dnaRepository;
        this.dnaUtil = dnaUtil;
    }

    public boolean isMutant(String[] dna){
        final int n = dna.length;
        int resultVerticalTest = 0, resultPriDiagonal = 0;
        boolean isMutant = false;

        String [][] matrix = dnaUtil.getCharMatrix(dna, n);

        //verificacoes somente feitas enquanto não se encontram 2 linhas de sequencias iguais de dna
        int resultHorizontalTest = dnaUtil.findSequenceInLines(matrix,n);
        if(resultHorizontalTest >= 2) isMutant =  true;

        if(!isMutant) {
            resultVerticalTest = dnaUtil.findSequenceInCols(matrix, n);
            if (resultHorizontalTest + resultVerticalTest >= 2) isMutant = true;
        }

        if(!isMutant) {
            resultPriDiagonal = dnaUtil.findSequenceInPrimaryDiagonal(matrix, n);
            if (resultPriDiagonal + resultHorizontalTest + resultVerticalTest >= 2) isMutant = true;
        }

        if(!isMutant) {
            int resultSecDiagonal = dnaUtil.findSequenceInSecondaryDiagonal(matrix, n);
            if (resultSecDiagonal + resultPriDiagonal + resultHorizontalTest + resultVerticalTest >= 2) isMutant = true;
        }

        //salvando no mongo
        saveInMongo(dna, isMutant);

        return isMutant;
    }

    private void saveInMongo(String[] dna, boolean isMutant) {
        StringBuilder dnaToRepo = new StringBuilder();
        for (String line : dna) {
            dnaToRepo.append(line);
        }

        VerifiedDna verifiedDna = new VerifiedDna();
        verifiedDna.setDna(dnaToRepo.toString());
        verifiedDna.setMutant(isMutant);
        try {
            dnaRepository.save(verifiedDna);
        }catch (org.springframework.dao.DuplicateKeyException e){
            System.out.println(e.getMessage());
        }
    }

    public StatisticResponse getStats(){
        StatisticResponse stats = new StatisticResponse();
        stats.setCountMutantDna(dnaRepository.countByIsMutant(true));
        stats.setCountHumanDna(dnaRepository.countByIsMutant(false));
        stats.setRatio(stats.getCountMutantDna() / stats.getCountHumanDna());
        return   stats;
    }
}
