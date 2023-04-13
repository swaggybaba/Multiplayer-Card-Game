import java.util.ArrayList;
import java.util.List;

public class Player {
    private int playerId;
    private List<Card> playingCards;

    public Player(int playerId){
        this.playerId=playerId;
        playingCards=new ArrayList<>();
    }
    public void printCards(){
        System.out.println("Player Id: "+ this.playerId+" cards are:");
        for(Card card:getPlayingCards()){
            System.out.println(card);
        }
    }
    public int getIndexOfSameSuitOrFaceValue(Card lastCardOfDiscardPile){
        if(lastCardOfDiscardPile==null){
            return 0;
        }
        for(int index=0;index<getPlayingCards().size();index++){
            Card card=getPlayingCards().get(index);
            if(card.getFaceValue()==lastCardOfDiscardPile.getFaceValue()||card.getSuit()==lastCardOfDiscardPile.getSuit()){
                return index;
            }
        }
        return -1;
    }
    public void removeCard(int index){
        getPlayingCards().remove(index);
    }
    public void addCard(Card card){
        getPlayingCards().add(card);
    }

    public Card getCard(int index){
        return getPlayingCards().get(index);
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public List<Card> getPlayingCards() {
        return playingCards;
    }

    public void setPlayingCards(List<Card> playingCards) {
        this.playingCards = playingCards;
    }
}
