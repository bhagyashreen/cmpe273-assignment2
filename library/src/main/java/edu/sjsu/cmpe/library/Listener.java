/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.sjsu.cmpe.library;

import javax.jms.Connection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.fusesource.stomp.jms.StompJmsDestination;
import org.fusesource.stomp.jms.message.StompJmsMessage;

import edu.sjsu.cmpe.library.config.LibraryServiceConfiguration;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.repository.BookRepository;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

class Listener {

	String topicName = "";
	Listener(LibraryServiceConfiguration configuration){
		topicName = configuration.getStompTopicName();
	}	

	public void listenService(BookRepositoryInterface bookRepository) {
		String receivedData = "";

		try {
			String user = "admin";
			String password = "password";
			String host = "54.193.56.218";
			int port = 61613;
			String destination = topicName; //topic name from configuration

			StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
			factory.setBrokerURI("tcp://" + host + ":" + port);

			Connection connection = factory.createConnection(user, password);
			connection.start();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Destination dest = new StompJmsDestination(destination);

			MessageConsumer consumer = session.createConsumer(dest);
			System.currentTimeMillis();
			System.out.println("Waiting for messages...");
			while (true) {
				Message msg = consumer.receive();
				
				if (msg instanceof TextMessage) {
					receivedData = ((TextMessage) msg).getText();
					if ("SHUTDOWN".equals(receivedData)) {
						break;
					}
					System.out.println("Received message = " + receivedData);					
					
					
					//String data = "1:"Restful Web Services":"computer"/":/""http://goo.gl/ZGmzoJ"";
						//String receivedData = "1" + ":\"" + "Restful Web Services" + "\":\"" + "computer" + "\":\"" + "http://goo.gl/ZGmzoJ" + "\"";
						String args1[] = receivedData.split("\"");
						
						String isbn = args1[0].split(":")[0];
						String title = args1[1];
						String category = args1[3];
						String coverImage = args1[5];
						Book receivedBook = new Book();
					
					receivedBook.setIsbn(Long.parseLong(isbn));					
					receivedBook.setTitle(title);
					receivedBook.setCategory(category);
					
					try {
						receivedBook.setCoverimage(new URL(coverImage));
					} catch (MalformedURLException e) {
					    // eat the exception
					}
					//BookRepositoryInterface bookRepository = new BookRepository();
					bookRepository.updateLibraryAfterResponse(receivedBook);
					///

				} else if (msg instanceof StompJmsMessage) {
					StompJmsMessage smsg = ((StompJmsMessage) msg);
					receivedData = smsg.getFrame().contentAsString();
					if ("SHUTDOWN".equals(receivedData)) {
						break;
					}
					System.out.println("Received message = " + receivedData);

				} else {
					System.out.println("Unexpected message type: "
							+ msg.getClass());
				}
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
