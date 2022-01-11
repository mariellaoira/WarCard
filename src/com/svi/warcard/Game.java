package com.svi.warcard;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.svi.warcard.objects.Card;
import com.svi.warcard.objects.Deck;
import com.svi.warcard.objects.Player;
import com.svi.warcard.objects.Rank;
import com.svi.warcard.objects.Suit;

import java.util.AbstractMap.SimpleEntry;

public class Game {
    private Deck deck;
    private int numOfPlayers;
    private int shuffels;
    private List<Player> players;
    private int numberOfCards;
    private List<Card> hierarchy;

    /**
     * Constructor that initialize a game and deck of cards from input.txt
     * @param players Number of Players.
     * @param shuffles Number of shuffles to be made.
     */
    public Game(int players, int shuffels) {
        super();
        deck = new Deck();
        this.numberOfCards = deck.deckSize();
        this.numOfPlayers = players;
        this.shuffels = shuffels;
        this.players = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            this.players.add(new Player(i + 1));
        }
        /**
         * Array lists that will rank 'Suits' and 'Ranks' depending on index. 0 is the highest.
         */
        this.hierarchy = new ArrayList<>();
        Suit[] suits = Suit.values();
        Rank[] ranks = Rank.values();
        int k = 0, l = 0;
        for (int i = 0; i < 52; i++) {
            if(l == 4){
                l = 0;
                k++;
            }    
            if(k == 13)
                k = 0;
            hierarchy.add(new Card(ranks[k], suits[l++]));        
        }
    }

    public int getShuffels() {
        return shuffels;
    }

    /**
     * Method that distributes that card among all players
     */
    public void distributeCards(){
        while (!deck.getCards().isEmpty()) 
            for (Player player : players) 
                if(!deck.getCards().isEmpty())
                    player.getCardsInHand().add(deck.getCards().remove(0));
    }

    /**
     * Method to check whether the game is over or not.
     * @return True if game is finished, otherwise false.
     */
    public boolean gameOver(){
        for (Player player : players) {
            if(player.getCardsInHand().size() == numberOfCards){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that gets the winning player and Card bases on the cards put on the table.
     * @param cards Map of cards on the table
     * @return Returns a simple entry with a Player-Object and a Card-Object.
     */
    public SimpleEntry<Card, Player> getRoundWinner(List<Card> cards){
        if(cards.isEmpty())
            return null;

        Player winner = null;
        int lowestIndex = Integer.MAX_VALUE;
        Card winningCard = null;

        int index = 0;
        for (Card entry : cards) {
            for (Card card2 : hierarchy) {
                if(entry != null && card2 != null){
                    if(card2.getRank().equals(entry.getRank()) && card2.getType().equals(entry.getType())){
                        if(lowestIndex > hierarchy.indexOf(card2)){
                            lowestIndex = hierarchy.indexOf(card2);
                            winner = players.get(index);
                            winningCard = entry;
                        }
                    }
                } 
            }
            index++;
        } 

        return new SimpleEntry<Card,Player>(winningCard, winner);
    }
    
    /**
     * Method that adds rounds. The loop will not stop until the start index matches the cards' size.
     * @return Returns a loop of rounds.
     */
    public void addRounds(Player player, List<Card> cards, Card c){
        int startIndex = 0;
        for (Player p : players) {
            if(p.equals(player)){
                break;
            }
            startIndex++;
        }
        int endIndex = startIndex;
        do {
            if(cards.get(startIndex) != null){
                player.getCardsInHand().add(cards.get(startIndex));
            }
            if(startIndex == cards.size() - 1)
                startIndex = 0; 
            else 
                startIndex++;
        } while (startIndex != endIndex);
    }

    /**
     * For bug fixes only. Sometimes, the program has inaccurate rounds because of the players' cards in hand.
     * @return Returns players' cards in hand.
     */
    public void fixPlayer(Player player){
        List<Card> cardss = new ArrayList<>();
        for (Card card : player.getCardsInHand()) {
            if(card != null){
                cardss.add(card);
            }
        }

        player.getCardsInHand().clear();
        player.getCardsInHand().addAll(cardss);
    }


    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Number of Player(Min: 2, Max: 52): ");
        int numOfPlayers = scan.nextInt();
        if(numOfPlayers < 2 || numOfPlayers > 52){
            System.out.println("Bad Input!");
            scan.close();
            return;
        }
        System.out.print("Enter Number of Shuffles: ");
        int shuffels = scan.nextInt();
        if(shuffels < 1){
            System.out.println("Bad Input!");
            scan.close();
            return;
        }
        Game game = new Game(numOfPlayers, shuffels);
        System.out.println(game.hierarchy);
        System.out.println("Deck size: " + game.deck.deckSize());
        System.out.println("!!!Deck Created!!!");
        System.out.println(game.deck + "\n");
        for (int i = 0; i < shuffels; i++) {
            System.out.println("Deck after " + (i + 1) + " shuffle(s)");
            game.deck.shuffelDeck();
            System.out.println(game.deck + "\n");
        }
        System.out.println("Deck after shuffling for " + shuffels + " time(s)");
        System.out.println(game.deck + "\n");
        System.out.println("Cards of players after distribution");
        game.distributeCards();
        for (Player player : game.players) {
            System.out.println("Player " + player.getId() + ":" + player + "\n");
        }

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!Game Start!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        
        int round = 1;
        while (!game.gameOver()) {
            System.out.println("Round " + round++);
            List<Card> cardsToCompare = new ArrayList<>();
            for (Player player : game.players) {
                game.fixPlayer(player);
                if(player.getCardsInHand().isEmpty()){
                    cardsToCompare.add(null);
                    continue;
                } 
                cardsToCompare.add(player.getCardsInHand().remove(0));
            }

            int index = 0;
            for (Player player : game.players) {
                Card card = null;
                if(cardsToCompare.get(index) != null)
                    card = cardsToCompare.get(index);
                //Displays: Player revealing their top cards along with remaining cards. If players has no cards, display 'Nothing'
                System.out.println("Player : " + player.getId() + " reveals: " + (card == null ? "Nothing, " : card) + " Remaining Cards Size:" + player.getCardsInHand().size() + " Remaining Cards: " + player);
                index++;
            }
            SimpleEntry<Card, Player> entry = game.getRoundWinner(cardsToCompare);
            System.out.println("Winning Card: " + entry.getKey());
            game.addRounds(entry.getValue(), cardsToCompare, entry.getKey());
            System.out.println("Round Winner is Player " + entry.getValue().getId() + "\n");
        }

        scan.close();
    }
}
