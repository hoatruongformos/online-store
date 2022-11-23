package com.mitsolu.store.repository;

import com.mitsolu.store.domain.OrderItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OrderItem entity.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    default Optional<OrderItem> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<OrderItem> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<OrderItem> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct orderItem from OrderItem orderItem left join fetch orderItem.product left join fetch orderItem.order",
        countQuery = "select count(distinct orderItem) from OrderItem orderItem"
    )
    Page<OrderItem> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct orderItem from OrderItem orderItem left join fetch orderItem.product left join fetch orderItem.order")
    List<OrderItem> findAllWithToOneRelationships();

    @Query(
        "select orderItem from OrderItem orderItem left join fetch orderItem.product left join fetch orderItem.order where orderItem.id =:id"
    )
    Optional<OrderItem> findOneWithToOneRelationships(@Param("id") Long id);
    
    @Query(value = "select * from order_item oi cross join product_order po cross join customer c cross join jhi_user u where oi.order_id = po.id and po.customer_id = c.id and c.user_id = u.id and u.login =:login", nativeQuery = true)
    Page<OrderItem> findAllByOrderCustomerUserLogin(@Param("login") String login, Pageable pageable);
    
    Optional<OrderItem> findOneByIdAndOrderCustomerUserLogin(@Param("id") Long id, @Param("login") String login);
}
