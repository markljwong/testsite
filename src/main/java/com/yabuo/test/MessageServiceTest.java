package com.yabuo.test;

import com.yabuo.Application;
import com.yabuo.chatbot.Message;
import com.yabuo.chatbot.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import static org.junit.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MessageServiceTest {

	@Autowired
	private MessageService MessageService;

	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Before
	public void before() {
		esTemplate.deleteIndex(Message.class);
		esTemplate.createIndex(Message.class);
		esTemplate.putMapping(Message.class);
		esTemplate.refresh(Message.class);
	}

	@Test
	public void testSave() {

		Message Message = new Message("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
		Message testMessage = MessageService.save(Message);

		assertNotNull(testMessage.getId());
		assertEquals(testMessage.getTitle(), Message.getTitle());
		assertEquals(testMessage.getAuthor(), Message.getAuthor());
		assertEquals(testMessage.getReleaseDate(), Message.getReleaseDate());

	}

	@Test
	public void testFindOne() {

		Message Message = new Message("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
		MessageService.save(Message);

		Message testMessage = MessageService.findOne(Message.getId());

		assertNotNull(testMessage.getId());
		assertEquals(testMessage.getTitle(), Message.getTitle());
		assertEquals(testMessage.getAuthor(), Message.getAuthor());
		assertEquals(testMessage.getReleaseDate(), Message.getReleaseDate());

	}

	@Test
	public void testFindByTitle() {

		Message Message = new Message("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
		MessageService.save(Message);

		List<Message> byTitle = MessageService.findByTitle(Message.getTitle());
		assertThat(byTitle.size(), is(1));
	}

	@Test
	public void testFindByAuthor() {

		List<Message> MessageList = new ArrayList<>();

		MessageList.add(new Message("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
		MessageList.add(new Message("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
		MessageList.add(new Message("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
		MessageList.add(new Message("1007", "Spring Data + ElasticSearch", "Rambabu Posa", "01-APR-2017"));
		MessageList.add(new Message("1008", "Spring Boot + MongoDB", "Mkyong", "25-FEB-2017"));

		for (Message Message : MessageList) {
			MessageService.save(Message);
		}

		Page<Message> byAuthor = MessageService.findByAuthor("Rambabu Posa", new PageRequest(0, 10));
		assertThat(byAuthor.getTotalElements(), is(4L));

		Page<Message> byAuthor2 = MessageService.findByAuthor("Mkyong", new PageRequest(0, 10));
		assertThat(byAuthor2.getTotalElements(), is(1L));

	}

	@Test
	public void testDelete() {

		Message Message = new Message("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
		MessageService.save(Message);
		MessageService.delete(Message);
		Message testMessage = MessageService.findOne(Message.getId());
		assertNull(testMessage);
	}

}
