package territorybattles.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import territorybattles.Bracca;
import territorybattles.Corellia;
import territorybattles.Coruscant;
import territorybattles.Dathomir;
import territorybattles.DeathStar;
import territorybattles.Felucia;
import territorybattles.Geonosis;
import territorybattles.Haven;
import territorybattles.Hoth;
import territorybattles.Kafrene;
import territorybattles.Kashyyyk;
import territorybattles.Kessel;
import territorybattles.Lothal;
import territorybattles.Malachor;
import territorybattles.Mustafar;
import territorybattles.Planet;
import territorybattles.Scarif;
import territorybattles.Tatooine;
import territorybattles.Vandor;

public class TBCalculator {
    public static List<String> calculatePhases(
        int guildGP,
        int[] platoons // 18 values, in the order: mustafar, geonosis, dathomir, haven, malachor, deathStar, corellia, felucia, tatooine, kessel, vandor, hoth, coruscant, bracca, kashyyyk, lothal, kafrene, scarif
    ) {
        // Instantiate planets
        Planet mustafar = new Mustafar();
        Planet geonosis = new Geonosis();
        Planet dathomir = new Dathomir();
        Planet haven = new Haven();
        Planet malachor = new Malachor();
        Planet deathStar = new DeathStar();
        Planet corellia = new Corellia();
        Planet felucia = new Felucia();
        Planet tatooine = new Tatooine();
        Planet kessel = new Kessel();
        Planet vandor = new Vandor();
        Planet hoth = new Hoth();
        Planet coruscant = new Coruscant();
        Planet bracca = new Bracca();
        Planet kashyyyk = new Kashyyyk();
        Planet lothal = new Lothal();
        Planet kafrene = new Kafrene();
        Planet scarif = new Scarif();

        List<Planet> allPlanets = Arrays.asList(
            mustafar, geonosis, dathomir, haven, malachor, deathStar,
            corellia, felucia, tatooine, kessel, vandor, hoth,
            coruscant, bracca, kashyyyk, lothal, kafrene, scarif
        );
        for (int i = 0; i < allPlanets.size(); i++) {
            allPlanets.get(i).setPlatoons(platoons[i]);
        }

        List<Planet> darkPlanets = Arrays.asList(mustafar, geonosis, dathomir, haven, malachor, deathStar);
        List<Planet> mixedPlanets = Arrays.asList(corellia, felucia, tatooine, kessel, vandor, hoth);
        List<Planet> lightPlanets = Arrays.asList(coruscant, bracca, kashyyyk, lothal, kafrene, scarif);

        int totalStars = 0;
        int phases = Math.max(Math.max(darkPlanets.size(), mixedPlanets.size()), lightPlanets.size());
        int darkIndex = 0, mixedIndex = 0, lightIndex = 0;
        Integer preloadDark = null, preloadMixed = null, preloadLight = null;

        List<String> phaseResults = new ArrayList<>();

        for (int phase = 1; phase <= Math.min(phases, 6); phase++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Phase ").append(phase).append(" Results:\n");
            int phaseStars = 0;
            boolean planetPreloaded = false;
            String preloadedPlanetZone = "";

            int darkStart = (preloadDark != null) ? preloadDark : darkIndex;
            int mixedStart = (preloadMixed != null) ? preloadMixed : mixedIndex;
            int lightStart = (preloadLight != null) ? preloadLight : lightIndex;

            Planet[] phasePlanets = new Planet[3];
            phasePlanets[0] = darkPlanets.get(darkStart);
            phasePlanets[1] = mixedPlanets.get(mixedStart);
            phasePlanets[2] = lightPlanets.get(lightStart);

            Arrays.sort(phasePlanets, Comparator.comparingInt(a -> a.star1 + a.star2 + a.star3));

            preloadDark = preloadMixed = preloadLight = null;

            int combatMissions = guildGP / 6;
            int remainingGP = guildGP + combatMissions;

            int nextDarkIndex = darkStart, nextMixedIndex = mixedStart, nextLightIndex = lightStart;

            for (int i = 0; i < 3; i++) {
                Planet planet = phasePlanets[i];
                int starsThisPlanet = 0;

                int zoneIdx = -1;
                if (planet == darkPlanets.get(darkStart)) zoneIdx = 0;
                else if (planet == mixedPlanets.get(mixedStart)) zoneIdx = 1;
                else if (planet == lightPlanets.get(lightStart)) zoneIdx = 2;

                if (phase == 6) {
                    int nextStarCost = -1;
                    remainingGP += planet.platoons;

                    if (remainingGP < planet.star1) {
                        nextStarCost = planet.star1 - remainingGP;
                    } else if (remainingGP < planet.star2) {
                        nextStarCost = planet.star2 - remainingGP;
                    } else if (remainingGP < planet.star3) {
                        nextStarCost = planet.star3 - remainingGP;
                    }
                    if (remainingGP >= planet.star1) {
                        starsThisPlanet++;
                        remainingGP -= planet.star1;
                    }
                    if (remainingGP >= planet.star2) {
                        starsThisPlanet++;
                        remainingGP -= planet.star2;
                    }
                    if (remainingGP >= planet.star3) {
                        starsThisPlanet++;
                        remainingGP -= planet.star3;
                    }
                    if (starsThisPlanet == 3) {
                        planet.starred = true;
                        if (zoneIdx == 0 && darkStart + 1 < darkPlanets.size()) nextDarkIndex = darkStart + 1;
                        if (zoneIdx == 1 && mixedStart + 1 < mixedPlanets.size()) nextMixedIndex = mixedStart + 1;
                        if (zoneIdx == 2 && lightStart + 1 < lightPlanets.size()) nextLightIndex = lightStart + 1;
                    }
                    phaseStars += starsThisPlanet;
                    sb.append("  ").append(planet.getClass().getSimpleName()).append(" (").append(planet.zone).append("): Stars gained: ").append(starsThisPlanet);
                    if (nextStarCost > 0) {
                        sb.append(" | GP needed for next star: ").append(nextStarCost);
                    }
                    sb.append("\n");
                    continue;
                }

                if (remainingGP < planet.star1 + planet.star2 + planet.star3) {
                    planet.preload = true;
                    planet.starred = false;
                    planetPreloaded = true;
                    preloadedPlanetZone = planet.zone;

                    if (remainingGP >= planet.star1) {
                        planet.star1 = 1;
                    } else {
                        planet.star1 = planet.star1 - remainingGP;
                    }

                    if (zoneIdx == 0) preloadDark = darkStart;
                    if (zoneIdx == 1) preloadMixed = mixedStart;
                    if (zoneIdx == 2) preloadLight = lightStart;

                    sb.append("  ").append(planet.getClass().getSimpleName()).append(" (").append(planet.zone).append("): Stars gained: 0 | Preloaded! New star1 cost: ").append(planet.star1).append("\n");
                    continue;
                }

                remainingGP += planet.platoons;

                if (remainingGP >= planet.star1) {
                    starsThisPlanet++;
                    remainingGP -= planet.star1;
                }
                if (remainingGP >= planet.star2) {
                    starsThisPlanet++;
                    remainingGP -= planet.star2;
                }
                if (remainingGP >= planet.star3) {
                    starsThisPlanet++;
                    remainingGP -= planet.star3;
                }
                if (starsThisPlanet == 3) {
                    planet.starred = true;
                    planet.preload = false;
                    if (zoneIdx == 0 && darkStart + 1 < darkPlanets.size()) nextDarkIndex = darkStart + 1;
                    if (zoneIdx == 1 && mixedStart + 1 < mixedPlanets.size()) nextMixedIndex = mixedStart + 1;
                    if (zoneIdx == 2 && lightStart + 1 < lightPlanets.size()) nextLightIndex = lightStart + 1;
                }
                phaseStars += starsThisPlanet;
                sb.append("  ").append(planet.getClass().getSimpleName()).append(" (").append(planet.zone).append("): Stars gained: ").append(starsThisPlanet);
                if (starsThisPlanet == 0) sb.append(" | Preloaded!");
                sb.append("\n");
            }

            totalStars += phaseStars;
            sb.append("Total stars after phase ").append(phase).append(": ").append(totalStars).append("\n");

            if (phase == 6) {
                sb.append("Guild GP Remaining: ").append(remainingGP).append("\n");
            }

            if (planetPreloaded) {
                sb.append("Planet preloaded this phase: ").append(preloadedPlanetZone).append("\n");
                for (Planet planet : phasePlanets) {
                    if (planet.preload) {
                        sb.append("Preloaded planet name: ").append(planet.getClass().getSimpleName()).append("\n");
                        break;
                    }
                }
            } else if (phase != 6) {
                sb.append("No planet preloaded this phase.\n");
            }

            darkIndex = nextDarkIndex;
            mixedIndex = nextMixedIndex;
            lightIndex = nextLightIndex;

            phaseResults.add(sb.toString());
        }

        phaseResults.add("FINAL_STAR_COUNT:" + totalStars);
        return phaseResults;
    }
}
