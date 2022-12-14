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
                {"SP"}, {"HI", "VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
            },
            {
                {"MP"}, {"VL"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
            },
            {
                {"MP"}, {"LO", "ME", "HI"}, {"LPC"}, UNEMPLOYMENT_RATE_VARIABLES, {"1"}
            },
            {
                {"MP"}, {"LO", "ME", "HI"}, {"MPC"}, {"HUR"}, {"1"}
            },
            {
                {"MP"}, {"VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
            },
            {
                {"LP"}, {"VL", "LO"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
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
                    {"SP"}, {"HI", "VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
            },
            {
                    {"MP"}, {"VL"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
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
                    {"MP"}, {"VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
            },
            {
                    {"LP"}, {"VL", "LO"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
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
                    {"SP"}, {"HI", "VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
            },
            {
                    {"MP"}, {"VL"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"HPC"}, {"LUR"}, {"1"}
            },
            {
                    {"MP"}, {"LO", "ME", "HI"}, {"HPC"}, {"MUR"}, {"0.5"}
            },
            {
                    {"MP"}, {"VH"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
            },
            {
                    {"LP"}, {"VL", "LO"}, GDP_PER_CAPITA_VARIABLES, UNEMPLOYMENT_RATE_VARIABLES, {"0"} // triplicated
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
        String populationLabel, GDPLabel, GDPPerCapitaLabel, unemploymentRateLabel;
        populationLabel = EvaluatingEconomicUsingFuzzyApplication.getPopulationLabel(1);
        GDPLabel = EvaluatingEconomicUsingFuzzyApplication.getGDPLabel(2);
        GDPPerCapitaLabel = EvaluatingEconomicUsingFuzzyApplication.getGDPPerCapitaLabel(3);
        unemploymentRateLabel = EvaluatingEconomicUsingFuzzyApplication.getUnemploymentRateLabel(4);

        HashMap<String, String> result = EvaluatingEconomicUsingFuzzyApplication.evaluatingEconomicLevel(populationLabel, GDPLabel, GDPPerCapitaLabel, unemploymentRateLabel);

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

    public static String getGDPLabel(int x) {
        int VLValue, LOValue, MEValue, HIValue, VHValue, result;

        if (x <= 50000) {
            VLValue = 1;
        } else if (x <= 100000) {
            VLValue = (1000000 - x)/50000;
        } else {
            VLValue = 0;
        }

        if (x <= 0) {
            LOValue = 0;
        } else if (x <= 100000) {
            LOValue = x/100000;
        } else if (x <= 200000) {
            LOValue = (200000 - x)/100000;
        } else {
            LOValue = 0;
        }

        if (x <= 100000) {
            MEValue = 0;
        } else if (x <= 200000) {
            MEValue = (x - 100000)/100000;
        } else if (x <= 500000) {
            MEValue = 1;
        } else if (x <= 600000) {
            MEValue = (600000 - x)/100000;
        } else {
            MEValue = 0;
        }

        if (x <= 500000) {
            HIValue = 0;
        } else if (x <= 600000) {
            HIValue = (x - 500000)/100000;
        } else if (x <= 900000) {
            HIValue = 1;
        } else if (x <= 1000000) {
            HIValue = (1000000 - x)/100000;
        } else {
            HIValue = 0;
        }

        if (x <= 900000) {
            VHValue = 0;
        } else if (x <= 1000000) {
            VHValue = (x - 900000)/100000;
        } else {
            VHValue = 1;
        }

        result = Math.max(VLValue,Math.max(LOValue, Math.max(MEValue, Math.max(HIValue, VHValue))));
        if (result == VLValue) {
            return "VL";
        } else if (result == LOValue) {
            return "LO";
        } else if (result == MEValue) {
            return "ME";
        } else if (result == HIValue) {
            return "HI";
        } else {
            return "VH";
        }
    }

    public static String getGDPPerCapitaLabel(int x) {
        int LPCValue, MPCValue, HPCValue, result;

        if (x <= 0) {
            LPCValue = 1;
        } else if (x <= 30000) {
            LPCValue = (30000 - x)/30000;
        } else {
            LPCValue = 0;
        }

        if (x <= 0) {
            MPCValue = 0;
        } else if (x <= 15000) {
            MPCValue = x/15000;
        } else if (x <= 30000) {
            MPCValue = (30000 - x)/30000;
        } else {
            MPCValue = 0;
        }

        if (x <= 0) {
            HPCValue = 0;
        } else if (x <= 30000) {
            HPCValue = x/30000;
        } else {
            HPCValue = 1;
        }

        result = Math.max(LPCValue,Math.max(MPCValue, HPCValue));
        if (result == LPCValue) {
            return "LPC";
        } else if (result == MPCValue) {
            return "MPC";
        } else {
            return "HPC";
        }
    }

    public static String getUnemploymentRateLabel(int x) {
        int LURValue, MURValue, HURValue, result;

        if (x <= 0) {
            LURValue = 1;
        } else if (x <= 20) {
            LURValue = (20 - x)/20;
        } else {
            LURValue = 0;
        }

        if (x <= 0) {
            MURValue = 0;
        } else if (x <= 10) {
            MURValue = x/10;
        } else if (x <= 20) {
            MURValue = (20 - x)/10;
        } else {
            MURValue = 0;
        }

        if (x <= 0) {
            HURValue = 0;
        } else if (x <= 20) {
            HURValue = x/20;
        } else {
            HURValue = 1;
        }

        result = Math.max(LURValue,Math.max(MURValue, HURValue));
        if (result == LURValue) {
            return "LUR";
        } else if (result == MURValue) {
            return "MUR";
        } else {
            return "HUR";
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
