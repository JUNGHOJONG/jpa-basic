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
            Member member = new Member();
            member.setName("Davinci");
            entityManager.persist(member);

//            Member member1 = entityManager.find(Member.class, member.getId());
//            System.out.println("name : " + member1.getName());
//            System.out.println("id : " +member1.getId());
//            member1.setName("change");

            List<Member> result = entityManager.createQuery("select m from Member m", Member.class)
                    .getResultList();
            for (Member tempMember : result) {
                System.out.println("member.name : " + tempMember.getName());
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        emf.close();

    }
}
