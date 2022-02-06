package com.synechron.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synechron.notificationservice.model.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	Notification findByCustomerIdAndId(long cid, long nid);

	List<Notification> findByCustomerId(long cid);

}
