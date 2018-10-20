package br.com.magneto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"countMutantDna","countHumanDna","ratio"})
public class StatisticResponse {

    @JsonProperty(value = "count_mutant_dna")
    private long countMutantDna;
    @JsonProperty(value = "count_human_dna")
    private long countHumanDna;
    private double ratio;

    public long getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(long coutMutantDna) {
        this.countMutantDna = coutMutantDna;
    }

    public long getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(long countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
