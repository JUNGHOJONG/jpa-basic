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

            entityManager.persist(member);
            entityManager.flush();
            entityManager.clear();

            Member member2 = new Member();
            member2.setId(101L);
            member2.setName("HOJONG");

            //영속 상태
            System.out.println("=== before ===");
            entityManager.persist(member2);

            //query X
            System.out.println("=== find1Start ===");
            Member findedMember1 = entityManager.find(Member.class, 101L);
            System.out.println("findedMember1.getName() = " + findedMember1.getName());
            System.out.println("=== find1End");
            //query O
            System.out.println("=== find2Start ===");
            Member findedMember2 = entityManager.find(Member.class, 100L);
            System.out.println("findedMember2.getName() = " + findedMember2.getName());
            System.out.println("=== find2End ===");

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
