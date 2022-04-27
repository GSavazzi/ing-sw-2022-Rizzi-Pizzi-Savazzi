package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * UML CHANGES <br>
 * 1) Added function getWinner() <br>
 */

public class Game {

    private final static int MAX_N_ISLES = 12;

    private GameModel gameModel; // WHEN TO INITIALIZE???
    private ActionTurnHandler turn;
    private boolean started;
    private int playersNumber;
    private List<Integer> playersOrder;
    private int currentPlayer;
    private boolean expertMode;

    public Game(int playersNumber, boolean expertMode){
        this.playersNumber= playersNumber;
        gameModel = new GameModel(playersNumber);
        this.expertMode = expertMode;
    }

    public Player getWinner(){
        return gameModel.getPlayers().stream()
                .filter(p -> (
                        p.getBoard().getTowers() == gameModel.getPlayers().stream()
                                .map(n -> n.getBoard().getTowers())
                                .min(Integer::compareTo)
                                .get()
                        ))
                .max(Comparator.comparingInt(a -> gameModel.numberOfProfessors(a)))
                .get();
    }

    // DOESN'T SAY WHO WINS
    public boolean checkEnd(){
        // TOWER FINISHED
        for(Player p : gameModel.getPlayers()){
            if(p.getBoard().getTowers() <= 0){
                return true;
            }
        }
        // MORE THAN 3 ISLES
        if(gameModel.getIsles().size() <= 3){
            return true;
        }
        // NO STUDENTS IN BAG
        if(gameModel.checkEmptyBag()){
            return true;
        }
        // NO MORE ASSISTANTS
        for(Player p : gameModel.getPlayers()){
            if(p.getDeck().size() <= 0){
                return true;
            }
        }
        return false;
    }

    public void startActionTurn(){
       turn = new ActionTurnHandler(currentPlayer,gameModel, playersNumber);
    }

    public void giveCoin(Player p){
//        try{
//            gameModel.removeCoin();
//            gameModel.getPlayer(p.getID()).addCoin();
//        }catch(NotEnoughCoinsException e){
//            // WHAT??? TELL THE PLAYER THE COINS ARE FINISHED?
//        }
    }

    public List<Integer> getPlayersOrder() {
        return playersOrder;
    }

    // EXPECTED ALL PLAYERS CREATED
    public void setupGame(){
        ArrayList<Colour> students = new ArrayList<>();
        for(Colour c: Colour.values()){
            students.add(c);
            students.add(c);
        }
        Random rand = new Random();
        try {
            gameModel.setMotherNPos(rand.nextInt(MAX_N_ISLES));
        } catch (TileOutOfBoundsException e) {
            e.printStackTrace();
        }

        for(int i=0; i<MAX_N_ISLES && students.size()>0; i++) {
                int student = rand.nextInt(students.size());
                try {
                    if (!(gameModel.getMotherNature() == i || (gameModel.getMotherNature() + 6) % 12 == i)) {
                        gameModel.getIsle(i).addStudent(students.remove(student));
                    }
                } catch (TileOutOfBoundsException e) {
                    e.printStackTrace();
                }



        }

        for(Cloud c: gameModel.getClouds())
        {
            try {
                c.addStudents(gameModel.extractStudents((playersNumber == 3) ? 4 : 3));
            }catch(StudentsOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        for(Player p : gameModel.getPlayers()){
            p.createBoard((playersNumber == 3)?6:8);
            try {
                p.getBoard().addStudents(gameModel.extractStudents((playersNumber == 3) ? 9 : 7));
            }catch(StudentsOutOfBoundsException e){
                e.printStackTrace();
            }
        }

        currentPlayer = rand.nextInt(gameModel.getPlayers().size());
        // TURN HANDLER??? I DON'T REMEMBER...
    }

    public boolean isExpertMode(){
        return expertMode;
    }

    public GameModel getGameModel(){
        return gameModel;
    }

    public void createPlayer(String nickname){
        gameModel.addPlayer(gameModel.getPlayers().size(),nickname);
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }

    public void chooseAssistant(int assistantID, int playerID){
        String notify = null;
        try{
            gameModel.getPlayer(playerID).setChoosenAssistant(assistantID);
        }catch (IndexOutOfBoundsException e){
            notify = "Assistant chosen id must be less than 10 and more than 0";
        }
    }

    public void checkNextOrder(){
        ArrayList<Player> tempPlayers = new ArrayList<>(gameModel.getPlayers());
        tempPlayers.sort(Comparator.comparingInt(p -> p.getChosen().getValue()));
        playersOrder = tempPlayers.stream()
                .map(Player::getID)
                .collect(Collectors.toList());
    }

    public ActionTurnHandler getTurnHandler()
    {
        return turn;
    }

}
