package com.mitsolu.store.repository;

import com.mitsolu.store.domain.Shipment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Shipment entity.
 */
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    default Optional<Shipment> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Shipment> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Shipment> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct shipment from Shipment shipment left join fetch shipment.invoice",
        countQuery = "select count(distinct shipment) from Shipment shipment"
    )
    Page<Shipment> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct shipment from Shipment shipment left join fetch shipment.invoice")
    List<Shipment> findAllWithToOneRelationships();

    @Query("select shipment from Shipment shipment left join fetch shipment.invoice where shipment.id =:id")
    Optional<Shipment> findOneWithToOneRelationships(@Param("id") Long id);
    
    @Query(value = "select * from shipment s cross join invoice i cross join product_order po cross join customer c cross join jhi_user u where s.invoice_id = i.id and i.order_id = po.id and po.customer_id = c.id and c.user_id = u.id and u.login =:login", nativeQuery = true)
    Page<Shipment> findAllByInvoiceOrderCustomerUserLogin(@Param("login") String login, Pageable pageable);
    
    Optional<Shipment> findOneByIdAndInvoiceOrderCustomerUserLogin(@Param("id") Long id, @Param("login") String login);
}
