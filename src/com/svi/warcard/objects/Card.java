package com.svi.warcard.objects;

public class Card {
	private Suit type;
	private Rank rank;

	public Card(String str) throws Exception {
		super(); // a reference variable that is used to refer parent class constructors
		String[] arr = str.split("-");
		switch (arr[0]) {
		case "D":
			this.type = Suit.DIAMONDS;
			break;
		case "H":
			this.type = Suit.HEARTS;
			break;
		case "S":
			this.type = Suit.SPADES;
			break;
		case "C":
			this.type = Suit.CLUBS;
			break;
		default:
			throw new Exception();
		}
		switch (arr[1]) {
		case "A":
			this.rank = Rank.ACE;
			break;
		case "K":
			this.rank = Rank.KING;
			break;
		case "Q":
			this.rank = Rank.QUEEN;
			break;
		case "J":
			this.rank = Rank.JACK;
			break;
		case "10":
			this.rank = Rank.TEN;
			break;
		case "9":
			this.rank = Rank.NINE;
			break;
		case "8":
			this.rank = Rank.EIGHT;
			break;
		case "7":
			this.rank = Rank.SEVEN;
			break;
		case "6":
			this.rank = Rank.SIX;
			break;
		case "5":
			this.rank = Rank.FIVE;
			break;
		case "4":
			this.rank = Rank.FOUR;
			break;
		case "3":
			this.rank = Rank.THREE;
			break;
		case "2":
			this.rank = Rank.TWO;
			break;
		default:
			throw new Exception();
		}
	}

	public Card(Rank rank, Suit type) {
		super();
		this.rank = rank;
		this.type = type;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getType() {
		return type;
	}
	
	@Override
	public String toString() {
		String str = "";
		switch (type) {
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
		switch (rank) {
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

		return str;
	}
}
