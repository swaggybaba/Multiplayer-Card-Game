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
                this.cards.add(new Card(value,suit));
            }
        }
        shuffle();
    }
    private void shuffle() {
        int n=getCards().size();
        Random random=new Random();
        for(int index=0;index<n;index++) {
            int randomPosition = index + random.nextInt(52 - index);
            Card tmpCard = getCards().get(index);
            getCards().set(index, getCards().get(randomPosition));
            getCards().set(randomPosition, tmpCard);
        }
    }
    public Card getCard(int index){
        return getCards().get(index);
    }
    public Card removeCardFromTop(){
        if(getSize()==0){
            return null;
        }
        return getCards().remove(cards.size()-1);
    }
    public int getSize(){
        return getCards().size();
    }
    public List<Card> getCards() {
        return this.cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
