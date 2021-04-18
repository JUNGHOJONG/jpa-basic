package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Member member1 = new Member();
            member1.setId(150L);
            member1.setName("A");

            entityManager.persist(member1);
            entityManager.flush();
            entityManager.clear();

            System.out.println("=====================");
            Member findedMember1 = entityManager.find(Member.class, 150L);

            entityManager.close();
            System.out.println("++++++++++++++++");
            Member findedMember2 = entityManager.find(Member.class, 150L);

            System.out.println("======================");

            Member member2 = new Member();
            member2.setId(151L);
            member2.setName("B");


            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("exception!!!!!!!!!!!");
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        emf.close();

    }
}
