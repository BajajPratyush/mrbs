package org.mrbs.service.impl;

import org.mrbs.dao.intf.UserDaoIntf;
import org.mrbs.entity.User;
import org.mrbs.entity.UserRole;
import org.mrbs.model.exceptions.UserXMLProcessingException;
import org.mrbs.service.intf.UserServiceIntf;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceIntf {

    private final UserDaoIntf userDao;

    public UserService(UserDaoIntf userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUsersFromXML(String xmlFilePath) {
        try {
            File file = new File(xmlFilePath);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("user");
            List<User> userList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    int id = Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent());
                    String name = elem.getElementsByTagName("name").item(0).getTextContent();
                    String email = elem.getElementsByTagName("email").item(0).getTextContent();
                    long phone = Long.parseLong(elem.getElementsByTagName("phone").item(0).getTextContent());
                    UserRole role = UserRole.valueOf(elem.getElementsByTagName("role").item(0).getTextContent());

                    User user = new User(id, name, email, phone, role);
                    userList.add(user);
                }
            }

            userDao.addUsers(userList);

        } catch (Exception e) {
            try {
                throw new UserXMLProcessingException("Error processing XML file", e);
            } catch (UserXMLProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
