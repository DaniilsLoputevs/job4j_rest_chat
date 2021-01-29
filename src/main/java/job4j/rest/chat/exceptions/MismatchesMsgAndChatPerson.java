package job4j.rest.chat.exceptions;

public class MismatchesMsgAndChatPerson extends RuntimeException {
    
    public MismatchesMsgAndChatPerson() {
        super("(message.creator) not contains in (room.members), - check you request!");
    }
    
}
