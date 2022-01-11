package com.svi.warcard.objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Deck {
	private List<Card> cards;

    /**
     * Standard-Constructor to initialize the deck of cards
     * from the file input.txt
     */
    public Deck() {
        super();
        //Initialize List
        cards = new ArrayList<Card>();
        //String buffer to build a array that saves the content of the file
        StringBuffer buffer = new StringBuffer();
        try (Scanner scan = new Scanner(new FileInputStream("input.txt"))) {
            //Fill the string-buffer with all the data
            while (scan.hasNextLine()) {
                buffer.append(scan.nextLine());
            }
            //fill a list temporarily with Strings by splitting attributes by comma using StringTokenizer
            List<String> cards = Collections.list(new StringTokenizer(buffer.toString(), ","))
                .stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());

            //Filling the deck of cards with card Objects
            cards.forEach(str -> {
                try {
                    this.cards.add(new Card(str));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    public int deckSize(){
        return this.cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        String str = "";
        for (Card card : cards) {
            if(cards.indexOf(card) == cards.size() - 1)
                str += card;
            else 
                str += card + ", ";
        }

        return str;
    }

    /**
     * Method to shuffle deck of cards in half and then alternating swap them
     */
    public void shuffelDeck(){
        List<Card> firstHalf = new ArrayList<>();
        List<Card> secondHalf = new ArrayList<>();
        for (int i = 0; i < this.cards.size() / 2; i++) {
            firstHalf.add(this.cards.get(i));
        }
        for (int i = this.cards.size() / 2; i < this.cards.size(); i++) {
            secondHalf.add(this.cards.get(i));
        }
        int i = 0, k = 0, l = 0;
        for (int j = 0; j < this.cards.size(); j++) {
        	//check if 'i' is divisible by 2. For storing of cards in the deck alternately.
            if(i % 2 == 0)
                this.cards.set(j, firstHalf.get(k++));
            else 
                this.cards.set(j, secondHalf.get(l++));
            i++;
        }
    }
}
