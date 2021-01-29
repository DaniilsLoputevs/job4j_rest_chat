package job4j.rest.chat.services;

import job4j.rest.chat.models.Message;

import java.util.function.Consumer;

public interface RoomService {
    
    /**
     * Add to correct chat and save in db.
     *
     * @param roomId -
     * @param msg    -
     */
    void acceptMsg(int roomId, Message msg);
    
    /**
     * Add to correct chat and save in db.
     *
     * @param roomId   -
     * @param personId -
     */
    void acceptPerson(int roomId, int personId);
    
    void outLastMsg(int roomId, Consumer<String> output);
    
    void outAllMsg(int roomId, Consumer<String> output);
}
