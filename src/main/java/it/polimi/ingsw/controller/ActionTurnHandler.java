package it.polimi.ingsw.controller;

import it.polimi.ingsw.clientModels.Answers.ErrorMessage;
import it.polimi.ingsw.clientModels.Answers.TurnMessage;
import it.polimi.ingsw.clientModels.ClientModel;
import it.polimi.ingsw.exceptions.NotEnoughCoinsException;
import it.polimi.ingsw.exceptions.StudentsOutOfBoundsException;
import it.polimi.ingsw.exceptions.TileOutOfBoundsException;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.Observable;
import it.polimi.ingsw.server.Observer;

import java.util.List;
import java.util.Optional;

public class ActionTurnHandler extends Observable<ClientModel> {
    private int currentPlayer;
    private final GameModel gameModel;
    private int studentsToMove;
    private CheckProfessorStrategy professorStrategy;
    private CheckTowerStrategy checkTowerStrategy;
    private Phase phase;

    private boolean usedCharacter;

    public ActionTurnHandler(GameModel gameModel){
        this.gameModel = gameModel;

    }

    public void setupActionTurnHandler(int currentPlayer,int numOfPlayers){
        this.currentPlayer = currentPlayer;
        studentsToMove = (numOfPlayers == 3)?4:3;
        phase = Phase.STUDENTS;
        professorStrategy= new DefaultCheckProfessorStrategy();
        checkTowerStrategy = (numOfPlayers == 4)? new TeamCheckTowerStrategy() : new PlayerCheckTowerStrategy();
        usedCharacter = false;
    }

    public void moveMn(int moves){
        Assistant a = gameModel.getPlayers().get(currentPlayer).getChosen();

        if(moves<=a.getMn_moves()+a.getBoost() && moves>0){
            gameModel.moveMN(moves);
            checkTower(gameModel.getMotherNature());
            checkIsleJoin(gameModel.getMotherNature());
            phase=Phase.CLOUD;
            notify(new TurnMessage(currentPlayer, TurnMessage.Turn.ACTION_CLOUDS));
        }
        else{
            notify(new ErrorMessage(currentPlayer,ErrorMessage.ErrorType.MovesError));
        }
        setInfStrategy(new DefaultInfStrategy());
    }

    public void setInfStrategy(influenceStrategy strategy){
        for(Isle isle : gameModel.getIsles()){
            isle.setInfStrategy(strategy);
        }
    }

    public void moveStudentToIsle(Colour student, int isle){
        Player player = gameModel.getPlayer(currentPlayer);
        try{
            if(player.getBoard().getStudents(student)>0){
                if(isle < gameModel.getIsles().size() && isle>=0) {
                    player.getBoard().removeStudent(student);
                    gameModel.getIsle(isle).addStudent(student);
                    studentsToMove --;
                    if(studentsToMove  == 0) {
                        phase = Phase.MOTHERNATURE;
                        notify(new TurnMessage(currentPlayer, TurnMessage.Turn.ACTION_MN));
                    }
                }
                else{
                    notify(new ErrorMessage(currentPlayer,ErrorMessage.ErrorType.IsleError));
                }
            }
            else {
                notify(new ErrorMessage(currentPlayer,ErrorMessage.ErrorType.StudentError));
            }
        }catch(StudentsOutOfBoundsException e){
            e.printStackTrace();
        }catch(TileOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public void moveStudentToTable(Colour student){
        try{
            Player player = gameModel.getPlayer(currentPlayer);
            if(player.getBoard().getStudents(student)>0) {
                if(!player.getBoard().isTableFull(student)) {
                    player.getBoard().removeStudent(student);
                    player.getBoard().addToTable(student);
                    if(player.getBoard().checkCoin(student))
                        gameModel.giveCoin(player);
                    checkProfessor(student);
                    studentsToMove --;
                    if(studentsToMove  == 0) {
                        phase = Phase.MOTHERNATURE;
                        notify(new TurnMessage(currentPlayer, TurnMessage.Turn.ACTION_MN));
                    }
                }
                else{
                    notify(new ErrorMessage(currentPlayer,ErrorMessage.ErrorType.TileIsFullError));
                }
            }else{
                notify(new ErrorMessage(currentPlayer,ErrorMessage.ErrorType.StudentError));
            }


        }catch(StudentsOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public boolean moveFromCloud(int cloudId){

        try{
            if(cloudId <0 || cloudId>=gameModel.getClouds().size()) {
                notify(new ErrorMessage(currentPlayer, ErrorMessage.ErrorType.CloudError));
                return false;
            }
            else {
                if (gameModel.getCloud(cloudId).isEmpty()) {
                    notify(new ErrorMessage(currentPlayer, ErrorMessage.ErrorType.CloudError));
                    return false;
                } else {
                    try {
                        gameModel.getPlayer(currentPlayer).getBoard().addStudents(gameModel.getCloud(cloudId).empty());
                    } catch (TileOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch(TileOutOfBoundsException e){
            e.printStackTrace();
        }
        return true;
    }

    public void checkIsleJoin(int index)
    {
        try {
            gameModel.joinIsle(index);
        }catch(TileOutOfBoundsException e)
        {
            e.printStackTrace();
        }
    }

    public int getStudentsToMove() {
        return studentsToMove;
    }
    public Phase getPhase(){
        return phase;
    }

     public void setProfessorStrategy(CheckProfessorStrategy strategy)
    {
        professorStrategy = strategy;
    }
    public void checkProfessor(Colour student)
    {
        professorStrategy.checkProfessor(gameModel,student,currentPlayer);
    }

    public void checkTower(int isle){
        checkTowerStrategy.checkTower(gameModel,isle);
    }
    public void setCurrentPlayer(Player player)
    {
        this.currentPlayer = player.getID();
    }

    public boolean isUsedCharacter() {
        return usedCharacter;
    }
    public void setUsedCharacter (boolean used)
    {
        usedCharacter = used;
    }
}
