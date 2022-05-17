package it.polimi.ingsw.clientModels.Answers;


import it.polimi.ingsw.client.View;
import it.polimi.ingsw.clientModels.ClientModel;
import it.polimi.ingsw.server.ModelSerializer;

public class ErrorMessage implements ClientModel {

    private ErrorType error;

    public ErrorMessage(ErrorType error)
    {
        this.error = error;
    }
    @Override
    public void accept(View visitor)
    {
        visitor.visit(this);
    }

    public ErrorType getError() {
        return error;
    }

    public enum ErrorType {
        NotYourTurnError,
        NotEnoughCoinError,
        CloudError,
        AssistantOtherPlayerError,
        AssistantAlreadyChosenError,
        CharacterAlreadyUsedError,
        NormalModeError,
        StudentError,
        IsleError,
        TileIsEmptyError,
        TileIsFullError,
        ProhibitedError,
        MovesError,
        PlayerDisconnected
    }

    @Override
    public String serialize(){
        return ModelSerializer.serialize(this);
    }
}