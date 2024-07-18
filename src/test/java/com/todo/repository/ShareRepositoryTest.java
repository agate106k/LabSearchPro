// package com.todo.repository;

// import static org.junit.jupiter.api.Assertions.assertNotEquals;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

// import com.todo.entity.Share;

// @DataJpaTest
// public class ShareRepositoryTest {
//   @Autowired
//   private ShareRepository shareRepository;
//   @Autowired
//   private TestEntityManager em;

//   @BeforeEach
//   void setup() {
//     var share = new Share();
//     share.setUserId(255);
//     share.setTaskId(255);
//     em.persist(share);
//   }

//   @Test
//   void testFindByUserId() {
//     var share = shareRepository.findByUserId(255);
//     assertNotEquals(0, share.size());
//   }

//   @Test
//   void testFindByTaskId() {
//     var share = shareRepository.findByTaskId(255);
//     assertNotEquals(0, share.size());
//   }

//   // @AfterTestClass
//   // void end() {
//   // var share = new Share();
//   // share.setUserId(255);
//   // share.setTaskId(255);

//   // shareService.delete(share);
//   // }
// }
