package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

            //비영속 상태
            Member member = new Member();
            member.setId(100L);
            member.setName("JUNGHOJONG");

            //영속 상태
            System.out.println("=== before ===");
            entityManager.persist(member);
//            entityManager.detach(member);
            System.out.println("=== after ===");

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        emf.close();

    }
}
