import java.util.ArrayList;
import java.util.List;

public class Player {
    int playerId;
    private List<Card> playingCards;

    public Player(int playerId){
        this.playerId=playerId;
        playingCards=new ArrayList<>();
    }
    public void printCards(){
        System.out.println("Player Id: "+ this.playerId+" cards are:");
        for(Card card:playingCards){
            System.out.println(card);
        }
    }
    public List<Card> getPlayingCards() {
        return playingCards;
    }

    public void setPlayingCards(List<Card> playingCards) {
        this.playingCards = playingCards;
    }
}
