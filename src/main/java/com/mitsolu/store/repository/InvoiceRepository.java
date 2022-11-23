package com.mitsolu.store.repository;

import com.mitsolu.store.domain.Invoice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Invoice entity.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    default Optional<Invoice> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Invoice> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Invoice> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct invoice from Invoice invoice left join fetch invoice.order",
        countQuery = "select count(distinct invoice) from Invoice invoice"
    )
    Page<Invoice> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct invoice from Invoice invoice left join fetch invoice.order")
    List<Invoice> findAllWithToOneRelationships();

    @Query("select invoice from Invoice invoice left join fetch invoice.order where invoice.id =:id")
    Optional<Invoice> findOneWithToOneRelationships(@Param("id") Long id);
    
    @Query(value = "select * from invoice i cross join product_order po cross join customer c cross join jhi_user u where i.order_id = po.id and po.customer_id = c.id and c.user_id = u.id and u.login =:login", nativeQuery = true)
    Page<Invoice> findAllByOrderCustomerUserLogin(@Param("login") String login, Pageable pageable);
    
    Optional<Invoice> findOneByIdAndOrderCustomerUserLogin(@Param("id") Long id, @Param("login") String login);
}
