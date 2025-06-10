package territorybattles;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


// Future improvements: Add individual planet CM value instead of assuming 1/4 of guildGP
// Add a way to enter average number of CMs completed per planet during runtime
// Add options for platoon completions per planet


// This is currently geared towards semi casual guilds, since we are assuming 1/4 of guildGP is used for CMs


// This is a simple program to calculate the number of stars gained in a territory battle based on guildGP and the planets available.
// It is not meant to be a full-fledged program, but rather a simple calculator for territory battles.
public class Main {
    @SuppressWarnings({ "ConvertToTryWithResources", "unused", "UnusedAssignment" })
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to TBCalculator!");
            System.out.print("Enter guildGP (int): ");
            int guildGP = scanner.nextInt();

            // Create planets using only their constructors
            Mustafar mustafar = new Mustafar();
            Geonosis geonosis = new Geonosis();
            Corellia corellia = new Corellia();
            Felucia felucia = new Felucia();
            Coruscant coruscant = new Coruscant();
            Bracca bracca = new Bracca();
            Dathomir dathomir = new Dathomir();
            Kashyyyk kashyyyk = new Kashyyyk();
            Tatooine tatooine = new Tatooine();
            Haven haven = new Haven();
            Kessel kessel = new Kessel();
            Lothal lothal = new Lothal();
            Kafrene kafrene = new Kafrene();
            Malachor malachor = new Malachor();
            Vandor vandor = new Vandor();
            Scarif scarif = new Scarif();
            DeathStar deathStar = new DeathStar();
            Hoth hoth = new Hoth();





            List<Planet> darkPlanets = Arrays.asList(mustafar, geonosis, dathomir, haven, malachor,deathStar);
            List<Planet> mixedPlanets = Arrays.asList(corellia, felucia, tatooine, kessel, vandor, hoth);
            List<Planet> lightPlanets = Arrays.asList(coruscant, bracca, kashyyyk, lothal, kafrene, scarif);

            int totalStars = 0;
            int phases = Math.max(Math.max(darkPlanets.size(), mixedPlanets.size()), lightPlanets.size());
            // Track which planet to start with in each list for each phase
            int darkIndex = 0, mixedIndex = 0, lightIndex = 0;
            // Track preloaded planets for each list
            Integer preloadDark = null, preloadMixed = null, preloadLight = null;

            List<Planet> allPlanets = Arrays.asList(
                mustafar, geonosis, dathomir, haven, malachor, deathStar,
                corellia, felucia, tatooine, kessel, vandor, hoth,
                coruscant, bracca, kashyyyk, lothal, kafrene, scarif
            );

            for (Planet planet : allPlanets) {
                int platoonsCompleted = 0;
                while (true) {
                    System.out.print("Enter number of platoons completed for " + planet.getClass().getSimpleName() + " (" + planet.zone + ") (0-6): ");
                    platoonsCompleted = scanner.nextInt();
                    if (platoonsCompleted >= 0 && platoonsCompleted <= 6) break;
                    System.out.println("Please enter a value between 0 and 6.");
                }
                planet.setPlatoons(platoonsCompleted);
            }

            for (int phase = 1; phase <= Math.min(phases, 6); phase++) {
                System.out.println("\n--- Phase " + phase + " ---");
                int phaseStars = 0;
                boolean planetPreloaded = false;
                String preloadedPlanetZone = "";

                // Determine which planet to attempt first in each list
                int darkStart = (preloadDark != null) ? preloadDark : darkIndex;
                int mixedStart = (preloadMixed != null) ? preloadMixed : mixedIndex;
                int lightStart = (preloadLight != null) ? preloadLight : lightIndex;

                // Prepare the phase's planets
                Planet[] phasePlanets = new Planet[3];
                phasePlanets[0] = darkPlanets.get(darkStart);
                phasePlanets[1] = mixedPlanets.get(mixedStart);
                phasePlanets[2] = lightPlanets.get(lightStart);

                // Sort by total star cost (ascending)
                Arrays.sort(phasePlanets, (a, b) ->
                    Integer.compare(a.star1 + a.star2 + a.star3, b.star1 + b.star2 + b.star3)
                );

                // Reset preloads for this phase
                preloadDark = preloadMixed = preloadLight = null;

                // Calculate combat missions and remaining GP ONCE per phase
                int combatMissions = guildGP / 6;
                int remainingGP = guildGP + combatMissions;

                // Track which index to use for each zone in the next phase
                int nextDarkIndex = darkStart, nextMixedIndex = mixedStart, nextLightIndex = lightStart;

                for (int i = 0; i < 3; i++) {
                    Planet planet = phasePlanets[i];
                    int starsThisPlanet = 0;

                
                    // Find which zone this planet is in to update the correct index/preload
                    int zoneIdx = -1;
                    if (planet == darkPlanets.get(darkStart)) zoneIdx = 0;
                    else if (planet == mixedPlanets.get(mixedStart)) zoneIdx = 1;
                    else if (planet == lightPlanets.get(lightStart)) zoneIdx = 2;

                    // Special logic for phase 6: use all remaining GP, no preloading
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
                            // Only advance if 3 stars
                            if (zoneIdx == 0 && darkStart + 1 < darkPlanets.size()) nextDarkIndex = darkStart + 1;
                            if (zoneIdx == 1 && mixedStart + 1 < mixedPlanets.size()) nextMixedIndex = mixedStart + 1;
                            if (zoneIdx == 2 && lightStart + 1 < lightPlanets.size()) nextLightIndex = lightStart + 1;
                        }
                        phaseStars += starsThisPlanet;
                        System.out.print("Planet attempted: " + planet.getClass().getSimpleName() + " (" + planet.zone + ") | Stars gained: " + starsThisPlanet);
                        if (nextStarCost > 0) {
                            System.out.print(" | GP needed for next star: " + nextStarCost);
                        }
                        System.out.println();
                        continue;
                    }

                    // Preloading logic for phases 1-5
                    if (remainingGP < planet.star1 + planet.star2 + planet.star3) {
                        planet.preload = true;
                        planet.starred = false;
                        planetPreloaded = true;
                        preloadedPlanetZone = planet.zone;

                        // Update star1 cost for next phase
                        if (remainingGP >= planet.star1) {
                            planet.star1 = 1;
                        } else {
                            planet.star1 = planet.star1 - remainingGP;
                        }

                        // Mark which planet to start with next phase
                        if (zoneIdx == 0) preloadDark = darkStart;
                        if (zoneIdx == 1) preloadMixed = mixedStart;
                        if (zoneIdx == 2) preloadLight = lightStart;

                        System.out.println("Planet attempted: " + planet.getClass().getSimpleName() + " (" + planet.zone + ") | Stars gained: 0 | Preloaded! New star1 cost: " + planet.star1);
                        // Do NOT add platoon value or advance index for this zone
                        continue;
                    }

                    // Only add platoon value if not preloaded
                    remainingGP += planet.platoons;

                    // Star logic
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
                        planet.preload = false; // <-- Reset preload flag if completed
                        // Only advance if 3 stars
                        if (zoneIdx == 0 && darkStart + 1 < darkPlanets.size()) nextDarkIndex = darkStart + 1;
                        if (zoneIdx == 1 && mixedStart + 1 < mixedPlanets.size()) nextMixedIndex = mixedStart + 1;
                        if (zoneIdx == 2 && lightStart + 1 < lightPlanets.size()) nextLightIndex = lightStart + 1;
                    }
                    phaseStars += starsThisPlanet;
                    System.out.println("Planet attempted: " + planet.getClass().getSimpleName() + " (" + planet.zone + ") | Stars gained: " + starsThisPlanet + (starsThisPlanet == 0 ? " | Preloaded!" : ""));
                }

                totalStars += phaseStars;
                System.out.println("Total stars after phase " + phase + ": " + totalStars);

                if (phase == 6) {
                    System.out.println("Guild GP Remaining: " + remainingGP);
                }

                if (planetPreloaded) {
                    System.out.println("Planet preloaded this phase: " + preloadedPlanetZone);
                    for (Planet planet : phasePlanets) {
                        if (planet.preload) {
                            System.out.println("Preloaded planet name: " + planet.getClass().getSimpleName());
                            break;
                        }
                    }
                } else if (phase != 6) {
                    System.out.println("No planet preloaded this phase.");
                }

                // Update indices for next phase
                darkIndex = nextDarkIndex;
                mixedIndex = nextMixedIndex;
                lightIndex = nextLightIndex;
            }

            System.out.print("\nType 'exit' to quit or press Enter to restart: ");
            scanner.nextLine(); // consume leftover newline
            String input = scanner.nextLine();
            if (input.trim().equalsIgnoreCase("exit")) {
                break;
            }
        }
        scanner.close();
    }
}