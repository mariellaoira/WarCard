package com.svi.warcard.objects;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cardsInHand;
    private int id;

    public Player(int id) {
        super();
        cardsInHand = new ArrayList<>();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public Card revealCard(){
        if(cardsInHand.isEmpty())
            return null;
        return cardsInHand.remove(0);
    }

    @Override
    public String toString() {
        if(cardsInHand.isEmpty()){
            return "";
        }
        String str = "";
        for (Card card : cardsInHand) {
            if(card == null)
                continue;
            switch (card.getType()) {
                case DIAMONDS:
                    str += "D";
                    break;
                case SPADES:
                    str += "S";
                    break;
                case CLUBS:
                    str += "C";
                    break;
                case HEARTS:
                    str += "H";
                    break;
                default:
                    break;
            }
            str += "-";
            switch (card.getRank()) {
                case ACE:
                    str += "A";
                    break;
                case KING:
                    str += "K";
                    break;
                case QUEEN:
                    str += "Q";
                    break;
                case JACK:
                    str += "J";
                    break;
                case TEN:
                    str += "10";
                    break;
                case NINE:
                    str += "9";
                    break;
                case EIGHT:
                    str += "8";
                    break;
                case SEVEN:
                    str += "7";
                    break;
                case SIX:
                    str += "6";
                    break;
                case FIVE:
                    str += "5";
                    break;
                case FOUR:
                    str += "4";
                    break;
                case THREE:
                    str += "3";
                    break;
                case TWO:
                    str += "2";
                    break;
                default:
                    break;
            }
            if(cardsInHand.indexOf(card) != cardsInHand.size() - 1)
                str += ", ";
        }

        return str;
    }
}
