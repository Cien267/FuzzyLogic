package ptit.tri_thuc;

import org.springframework.boot.SpringApplication;

import java.lang.reflect.Array;
import java.util.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class EvaluatingEconomicUsingFuzzyApplication {
    public static final String[] POPULATION_VARIABLES = {
            "SP", //small
            "MP", //medium
            "LP", //large
    };
    public static final String[] GDP_VARIABLES = {
            "VL", //very low
            "LO", //low
            "ME", //medium
            "HI", //high
            "VH" //very high
    };
    public static final String[] GDP_PER_CAPITA_VARIABLES = {
            "LPC", //low
            "MPC", //medium
            "HPC" //high
    };
    public static final String[] UNEMPLOYMENT_RATE_VARIABLES = {
            "LUR", //low
            "MUR", //medium
            "HUR" //high
    };
    public static final String[][][] LOW_ECONOMY = {
            {
                {"SP"}, {"VL", "LO", "ME"}, {"LPC"}, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                {"SP"}, {"VL", "LO", "ME"}, {"MPC"}, {"HUR"}
            },
            {
                {"SP"}, {"HI", "VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                {"MP"}, {"VL"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                {"MP"}, {"LO", "ME", "HI"}, {"LPC"}, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                {"MP"}, {"LO", "ME", "HI"}, {"MPC"}, {"HUR"}
            },
            {
                {"MP"}, {"VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                {"LP"}, {"VL", "LO"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                {"LP"}, {"ME", "HI", "VH"}, {"LPC"}, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                {"LP"}, {"ME", "HI", "VH"}, {"MPC"}, {"HUR"}
            }
    };
    public static final String[][][] MEDIUM_ECONOMY = {
            {
                    {"SP"}, {"VL", "LO", "ME"}, {"MPC"}, {"LUR", "MUR"}
            },
            {
                    {"SP"}, {"VL", "LO", "ME"}, {"HPC"}, {"HUR"}
            },
            {
                    {"SP"}, {"HI", "VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                    {"MP"}, {"VL"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"MPC"}, {"LUR"}
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"MPC"}, {"MUR"}
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"HPC"}, {"HUR"}
            },
            {
                    {"MP"}, {"VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                    {"LP"}, {"VL", "LO"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                    {"LP"}, {"ME", "HI", "VH"}, {"MPC"}, {"LUR"}
            },
            {
                    {"LP"}, {"ME", "HI", "VH"}, {"MPC"}, {"MUR"}
            },
            {
                    {"LP"}, {"ME", "HI", "VH"}, {"HPC"}, {"HUR"}
            }
    };
    public static final String[][][] HIGH_ECONOMY = {
            {
                    {"SP"}, {"VL", "LO", "ME"}, {"HPC"}, {"LUR", "MUR"}
            },
            {
                    {"SP"}, {"HI", "VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                    {"MP"}, {"VL"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"HPC"}, {"LUR"}
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"HPC"}, {"MUR"}
            },
            {
                    {"MP"}, {"VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                    {"LP"}, {"VL", "LO"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES
            },
            {
                    {"LP"}, {"ME", "HI", "VH"}, {"HPC"}, {"LUR"}
            },
            {
                    {"LP"}, {"ME", "HI", "VH"}, {"HPC"}, {"MUR"}
            },
    };

    public static void main(String[] args) {
        SpringApplication.run(EvaluatingEconomicUsingFuzzyApplication.class, args);
        String result = EvaluatingEconomicUsingFuzzyApplication.evaluatingEconomicLevel("MP", "ME", "HPC", "HUR");

        System.out.println(result);
    }

    public static String evaluatingEconomicLevel (String population, String gdp, String gdp_per_capita, String unemployment_rate) {
        // LOW
        for (String[][] lowListCases : LOW_ECONOMY ) {
                Boolean condPopulation = Arrays.asList(lowListCases[0]).contains(population);
                Boolean condGDP = Arrays.asList(lowListCases[1]).contains(gdp);
                Boolean condGDPPerCapita = Arrays.asList(lowListCases[2]).contains(gdp_per_capita);
                Boolean condUnemploymentRate = Arrays.asList(lowListCases[3]).contains(unemployment_rate);
                if (condPopulation && condGDP && condGDPPerCapita && condUnemploymentRate)
                    return "LOW";
        }

        // MEDIUM
        for (String[][] mediumListCases : MEDIUM_ECONOMY ) {
            Boolean condPopulation = Arrays.asList(mediumListCases[0]).contains(population);
            Boolean condGDP = Arrays.asList(mediumListCases[1]).contains(gdp);
            Boolean condGDPPerCapita = Arrays.asList(mediumListCases[2]).contains(gdp_per_capita);
            Boolean condUnemploymentRate = Arrays.asList(mediumListCases[3]).contains(unemployment_rate);
            if (condPopulation && condGDP && condGDPPerCapita && condUnemploymentRate)
                return "MEDIUM";
        }

        // HIGH
        for (String[][] highListCases : HIGH_ECONOMY ) {
            Boolean condPopulation = Arrays.asList(highListCases[0]).contains(population);
            Boolean condGDP = Arrays.asList(highListCases[1]).contains(gdp);
            Boolean condGDPPerCapita = Arrays.asList(highListCases[2]).contains(gdp_per_capita);
            Boolean condUnemploymentRate = Arrays.asList(highListCases[3]).contains(unemployment_rate);
            if (condPopulation && condGDP && condGDPPerCapita && condUnemploymentRate)
                return "HIGH";
        }

        return "UNKNOWN";
    }

}
