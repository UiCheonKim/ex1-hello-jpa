package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // Persistence 에서 설정 정보 조회
        // EntityManagerFactory 만드는 순간 데이터베이스 연결도 다 되고 왠만한건 다 됨
        // EntityManagerFactory 로딩시점에 딱 하나만 만들어야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 트랜잭션 같은거 DB connection 을 얻어서 쿼리를 날리고 종료되는 한 일괄적인 단위를 할때 EntityManager를 만들어줘야함
        EntityManager em = emf.createEntityManager();
        // code

        // JPA 에서 모든 데이터를 변경하는 모든 작업은 트랜잭션 안에서 작업이 되어야함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
/*
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
//            em.remove(findMember); // 삭제
            findMember.setName("HelloJPA");
//            em.persist(findMember); // 수정하고 persist 안써도 됨!
*/
            // JPA table 대상으로 절대 코드 짜지 않는다. 객체 대상으로 쿼리한다.
            // 방언을 바꿔도 JPQL 자체를 바꿀 필요가 없다는 장점 -> 한마디로 표현하자면 객체 지향 SQL
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1) // 1번부터 10번까지 페이지네이션
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 자원을 다 쓰면 닫아줘야한다. -> 그래야 데이터베이스 커넥션을 반환되거나 할거임
        }
        emf.close(); // 커넥션 풀링이나 리소스 릴리즈 되기 위해
    }
}
