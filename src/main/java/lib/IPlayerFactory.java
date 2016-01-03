package lib;

public interface IPlayerFactory {

    IPlayer createComputerPlayer(String name);

    IPlayer createUserPlayer(String name);
}
