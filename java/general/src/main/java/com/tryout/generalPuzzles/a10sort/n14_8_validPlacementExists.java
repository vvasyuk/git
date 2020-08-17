package com.tryout.generalPuzzles.a10sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// A player in the back row must be taller than the player in front of him, as illustrated in Figure 14.3. All players in a row must be from the same team.
public class n14_8_validPlacementExists {

    // place Team A behind Team B if A's tallest player is not taller than the tallest player in B, then it's not possible to place Team A behind Team B
    // second tallest player in A should be taller than the second tallest player in B, and so on
    // efficiently check whether A's tallest, second tallest, etc. players are each taller than B's tallest, second tallest, etc. players by first sorting the arrays of player heights
    class Player implements Comparable<Player> {
        public Integer height;
        public Player(Integer h) {
            height = h;
        }
        @Override
        public int compareTo(Player that) {
            return Integer.compare(height, that.height);
        }
    }

    class Team {
        public Team(List<Integer> height) {
            players = new ArrayList<Player>(height.size());
            for (int i = 0;
            i<height.size (); ++i){
                players.add(new Player(height.get(i)));
            }
        }

        // Checks if A can be placed in front of B.
        public boolean validPlacementExists(Team A, Team B) {
            List<Player> ASorted = A.sortPlayersByHeight();
            List<Player> BSorted = B.sortPlayersByHeight();
            for (int i = 0; i<ASorted.size() && i < BSorted.size(); ++i){
                if (ASorted.get(i).compareTo(BSorted.get(i)) >= 0){
                    return false;
                }
            }
            return true;
        }

        private List<Player> sortPlayersByHeight() {
            List<Player> sortedPlayers = new ArrayList<Player>(players);
            Collections.sort(sortedPlayers);
            return sortedPlayers;
        }

        private List<Player> players;
    }
}
