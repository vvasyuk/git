package com.tryout.generalPuzzles.a11bst;

import java.util.*;

//Consider a server that a large number of clients connect to. Each client is identified by
//a string. Each client has a "credit", which is a nonnegative integer value. The server
//needs to maintain a data structure to which clients can be added, removed, queried,
//or updated. In addition, the server needs to be able to add a specified number of
//credits to all clients simultaneously.
//Design a data structure that implements the following methods:
//• Insert: add a client with specified credit, replacing any existing entry for the
//client.
//• Remove: delete the specified client.
//• Lookup: return the number of credits associated with the specified client.
//• Add-to-all: increment the credit count for all current clients by the specified
//amount.
//• Max: return a client with the highest number of credits.
public class n15_13_ClientsCreditsInfo {
    // BST keys are credits, and the corresponding values are the
    //clients with that credit. This makes for fast max-queries. However, to perform
    //lookups and removes by client quickly, the BST by itself is not enough (since it is
    //ordered by credit, not client id). We can solve this by maintaining an additional hash
    //table in which keys are clients, and values are credits. Lookup is trivial. Removes
    //entails a lookup in the hash to get the credit, and then a search into the BST to get the
    //set of clients with that credit, and finally a delete on that set.

    public static class ClientsCreditsInfo {
        private int offset = 0;
        private Map<String, Integer> clientToCredit = new HashMap();
        private NavigableMap<Integer, Set<String>> creditToClients = new TreeMap();

        public void insert(String clientID, int c) {
            remove(clientID);
            clientToCredit.put(clientID, c - offset);
            Set<String> set = creditToClients.get(c - offset);
            if (set == null) {
                set = new HashSet();
                creditToClients.put(c - offset, set);
            }
            set.add(clientID);
        }

        public boolean remove(String clientID) {
            Integer clientCredit = clientToCredit.get(clientID);
            if (clientCredit != null) {
                creditToClients.get(clientCredit).remove(clientID);
                if (creditToClients.get(clientCredit).isEmpty()) {
                    creditToClients.remove(clientCredit);
                }
                clientToCredit.remove(clientID);
                return true;
            }
            return false;
        }

        public int lookup(String clientID) {
            Integer clientCredit = clientToCredit.get(clientID);
            return clientCredit == null ? -1 : clientCredit + offset;
        }

        public void addAll(int C) {
            offset += C;
        }

        public String max() {
            return creditToClients.isEmpty() ? "" : creditToClients.lastEntry().getValue().iterator().next();
        }
    }
}

