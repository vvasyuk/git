package com.example.demo.util;

import org.apache.commons.io.FileUtils;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jopa on 12/30/2017.
 */
public class Utils {

    public static MessageCreator getTestXml(String fileName) throws IOException{
        String textMsg = FileUtils.readFileToString(new File(fileName), "UTF-8");
        return new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage();
                msg.setText(textMsg);
                return msg;
            }
        };
    }
}
