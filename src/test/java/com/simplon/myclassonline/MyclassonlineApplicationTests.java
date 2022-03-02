package com.simplon.myclassonline;



import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.simplon.myclassonline.dao.LessonRepository;
import com.simplon.myclassonline.dao.UserRepository;
import com.simplon.myclassonline.model.Lesson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyclassonlineApplicationTests {

	@Autowired
	public UserRepository repository;
	@Autowired
	public LessonRepository lrepo;
	@Test
	void contextLoads() {
	}

	@Test
	void findById() {
		assertNotNull(repository.findById(1));
	}
	
	@Test
	void findByEmail() {
		assertNotNull(repository.findByEmail("pierredupont@gmail.com"));
	}
	
	
	@Test
	void testFindAll() {

		assertNotEquals(0, repository.findAll().size());
	}
	@Test
	void testAddLesson(){
		Lesson lesson = new Lesson("dfgfgfg","azazaz");
		assertTrue(lrepo.add(lesson));
		assertNotNull(lesson.getLessonId());
	}
	
}


