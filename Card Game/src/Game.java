import java.util.ArrayList;
import java.util.List;

public class Game {
    private int numberOfPlayers;
    private List<Player> players;

    public Game(int numberOfPlayers){
        this.numberOfPlayers=numberOfPlayers;
        players=new ArrayList<>();
        for(int index=0;index<numberOfPlayers;index++){
            players.add(new Player(index+1));
        }
        giveFiveCardsToEachPlayer();
        startGame();
    }

    public void startGame(){
        Deck deck=new Deck();
        int indTopCardOfDrawPile=deck.getCards().size()-1;
        Card lastCardOfDiscardPile=null;
        int playerTurn=0;
        while(true){
            Player player=players.get(playerTurn);
            System.out.println("Turn of player : player id "+player.getPlayerId());
            System.out.println("Last Card: "+lastCardOfDiscardPile);
            int indOfMatchingCard=player.getIndexOfSameSuitOrFaceValue(lastCardOfDiscardPile);
            if(indOfMatchingCard==-1){
                if(indTopCardOfDrawPile==-1){
                    System.out.println("DRAW: No one win");
                    break;
                }
                System.out.println("Player don't have of card of same Suit or same Face value");
                Card topCardOfDrawPile=deck.getCard(indTopCardOfDrawPile--);
                System.out.println("Card pick up from Draw pile: "+ topCardOfDrawPile);
                if(topCardOfDrawPile.getSuit()==lastCardOfDiscardPile.getSuit()||topCardOfDrawPile.getFaceValue()==lastCardOfDiscardPile.getFaceValue()){
                    //if drawed card suit or face value matches with last card of discsrd pile no need to add that card to player card list
                    lastCardOfDiscardPile=topCardOfDrawPile;
                    System.out.println("Picked up card has same suit or same face value with last card of discard pile, So the card played is: "+ topCardOfDrawPile);
                }
                else {
                    player.addCard(deck.getCard(indTopCardOfDrawPile--));
                    System.out.println("Card is added to player cards");
                }
            }
            else{
                System.out.println("Played Card: "+player.getCard(indOfMatchingCard));
                lastCardOfDiscardPile=player.getCard(indOfMatchingCard);
                player.removeCard(indOfMatchingCard);
                if(player.getPlayingCards().size()==0){
                    System.out.println("Player "+player.getPlayerId()+" wins the game");
                    break;
                }
            }
            playerTurn++;
            playerTurn%=numberOfPlayers;
        }
    }
    private void giveFiveCardsToEachPlayer(){
        Deck deck=new Deck();
        int index=deck.getCards().size()-1;
        for(int count=1;count<=5;count++){
            for(int playerNumber=0;playerNumber<numberOfPlayers;playerNumber++){
                players.get(playerNumber).addCard(deck.getCard(index--));
            }
        }
        printCardOfPlayers();
    }

    private void printCardOfPlayers(){
        for(Player player:players){
            player.printCards();
        }
    }
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
