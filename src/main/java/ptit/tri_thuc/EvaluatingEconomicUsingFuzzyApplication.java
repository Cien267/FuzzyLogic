package ptit.tri_thuc;

import org.springframework.boot.SpringApplication;

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
                {"SP"}, {"VL", "LO", "ME"}, {"LPC"}, UNEMPLOYMENT_RATE_VARIABLES, {"1"}
            },
            {
                {"SP"}, {"VL", "LO", "ME"}, {"MPC"}, {"HUR"}, {"1"}
            },
            {
                {"SP"}, {"HI", "VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                {"MP"}, {"VL"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                {"MP"}, {"LO", "ME", "HI"}, {"LPC"}, UNEMPLOYMENT_RATE_VARIABLES, {"1"}
            },
            {
                {"MP"}, {"LO", "ME", "HI"}, {"MPC"}, {"HUR"}, {"1"}
            },
            {
                {"MP"}, {"VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                {"LP"}, {"VL", "LO"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                {"LP"}, {"ME", "HI", "VH"}, {"LPC"}, UNEMPLOYMENT_RATE_VARIABLES, {"1"}
            },
            {
                {"LP"}, {"ME", "HI", "VH"}, {"MPC"}, {"HUR"}, {"1"}
            }
    };
    public static final String[][][] MEDIUM_ECONOMY = {
            {
                    {"SP"}, {"VL", "LO", "ME"}, {"MPC"}, {"LUR", "MUR"}, {"1"}
            },
            {
                    {"SP"}, {"VL", "LO", "ME"}, {"HPC"}, {"HUR"}, {"0.5"}
            },
            {
                    {"SP"}, {"HI", "VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                    {"MP"}, {"VL"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"MPC"}, {"LUR"}, {"1"}
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"MPC"}, {"MUR"}, {"0.5"}
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"HPC"}, {"HUR"}, {"0.5"}
            },
            {
                    {"MP"}, {"VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                    {"LP"}, {"VL", "LO"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                    {"LP"}, {"ME", "HI", "VH"}, {"MPC"}, {"LUR"}, {"1"}
            },
            {
                    {"LP"}, {"ME", "HI", "VH"}, {"MPC"}, {"MUR"}, {"0.5"}
            },
            {
                    {"LP"}, {"ME", "HI", "VH"}, {"HPC"}, {"HUR"}, {"0.5"}
            }
    };
    public static final String[][][] HIGH_ECONOMY = {
            {
                    {"SP"}, {"VL", "LO", "ME"}, {"HPC"}, {"LUR", "MUR"}, {"1"}
            },
            {
                    {"SP"}, {"HI", "VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                    {"MP"}, {"VL"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"HPC"}, {"LUR"}, {"1"}
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"HPC"}, {"MUR"}, {"0.5"}
            },
            {
                    {"MP"}, {"VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                    {"LP"}, {"VL", "LO"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"}
            },
            {
                    {"LP"}, {"ME", "HI", "VH"}, {"HPC"}, {"LUR"}, {"1"}
            },
            {
                    {"LP"}, {"ME", "HI", "VH"}, {"HPC"}, {"MUR"}, {"0.5"}
            },
    };

    public static void main(String[] args) {
        SpringApplication.run(EvaluatingEconomicUsingFuzzyApplication.class, args);
        HashMap<String, String> result = EvaluatingEconomicUsingFuzzyApplication.evaluatingEconomicLevel("SP", "HI", "HPC", "HUR");

        System.out.println(result);
    }

    public static String getPopulationLabel(int x) {
        int SPValue, MPValue, LPValue, result;

        if (x <= 0) {
            return "SP";
        } else if (x <= 20) {
            SPValue = (50 - x)/50;
            MPValue = x/20;
            LPValue = x/50;
        } else if (x <= 30) {
            SPValue = (50 - x)/50;
            MPValue = 1;
            LPValue = x/50;
        } else if (x <= 50) {
            SPValue = (50 - x)/50;
            MPValue = (50 - x)/20;
            LPValue = x/50;
        } else {
            return "LP";
        }

        result = Math.max(SPValue,Math.max(MPValue, LPValue));
        if (result == SPValue) {
            return "SP";
        } else if (result == MPValue) {
            return "MP";
        } else {
            return "LP";
        }
    }

    public static HashMap<String, String> evaluatingEconomicLevel (String population, String gdp, String gdp_per_capita, String unemployment_rate) {
        HashMap<String, String> result = new HashMap<>();
        boolean condPopulation, condGDP, condGDPPerCapita, condUnemploymentRate;

        // LOW
        for (String[][] lowListCases : LOW_ECONOMY ) {
            condPopulation = Arrays.asList(lowListCases[0]).contains(population);
            condGDP = Arrays.asList(lowListCases[1]).contains(gdp);
            condGDPPerCapita = Arrays.asList(lowListCases[2]).contains(gdp_per_capita);
            condUnemploymentRate = Arrays.asList(lowListCases[3]).contains(unemployment_rate);
            if (condPopulation && condGDP && condGDPPerCapita && condUnemploymentRate) {
                result.put("LOW", lowListCases[4][0]);
            }
        }

        // MEDIUM
        for (String[][] mediumListCases : MEDIUM_ECONOMY ) {
            condPopulation = Arrays.asList(mediumListCases[0]).contains(population);
            condGDP = Arrays.asList(mediumListCases[1]).contains(gdp);
            condGDPPerCapita = Arrays.asList(mediumListCases[2]).contains(gdp_per_capita);
            condUnemploymentRate = Arrays.asList(mediumListCases[3]).contains(unemployment_rate);
            if (condPopulation && condGDP && condGDPPerCapita && condUnemploymentRate) {
                result.put("MEDIUM", mediumListCases[4][0]);
            }
        }

        // HIGH
        for (String[][] highListCases : HIGH_ECONOMY ) {
            condPopulation = Arrays.asList(highListCases[0]).contains(population);
            condGDP = Arrays.asList(highListCases[1]).contains(gdp);
            condGDPPerCapita = Arrays.asList(highListCases[2]).contains(gdp_per_capita);
            condUnemploymentRate = Arrays.asList(highListCases[3]).contains(unemployment_rate);
            if (condPopulation && condGDP && condGDPPerCapita && condUnemploymentRate) {
                result.put("HIGH", highListCases[4][0]);
            }
        }

        return result;
    }

}
