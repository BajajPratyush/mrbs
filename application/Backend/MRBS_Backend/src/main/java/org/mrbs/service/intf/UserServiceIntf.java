package org.mrbs.service.intf;

import org.mrbs.model.exceptions.UserXMLProcessingException;

public interface UserServiceIntf {
    void addUsersFromXML(String xmlFilePath) throws UserXMLProcessingException;
}
