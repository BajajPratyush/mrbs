package org.mrbs.dao.intf;

import org.mrbs.entity.Meeting;
import org.mrbs.entity.User;

import java.util.List;
import java.util.Set;

public interface UserDaoIntf {
    public Set<Meeting> showAllMeetings(User user);

    public void addUsers(List<User> users) throws Exception;
}
