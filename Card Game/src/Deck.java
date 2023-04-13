import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards=new ArrayList<>();
        /* A faceValue=1
         * 2 faceValue=2
         * .
         * .
         * J faceValue=11
         * Q faceValue=12
         * K faceValue=13
         */
        for(int value=1;value<=13;value++) {
            for(Suit suit:Suit.values()) {
                cards.add(new Card(value,suit));
            }
        }
        shuffle();
    }
    private void shuffle() {
        int n=cards.size();
        Random random=new Random();
        for(int index=0;index<n;index++) {
            int randomPosition = index + random.nextInt(52 - index);
            Card tmpCard = cards.get(index);
            cards.set(index, cards.get(randomPosition));
            cards.set(randomPosition, tmpCard);
        }
    }
    public Card getCard(int index){
        return cards.get(index);
    }
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
