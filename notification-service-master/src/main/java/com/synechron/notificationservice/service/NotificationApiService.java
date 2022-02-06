package com.synechron.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.synechron.notificationservice.model.Notification;
import com.synechron.notificationservice.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Service
@Slf4j
public class NotificationApiService {
	
	@Autowired
	private NotificationRepository notificationRepository;

	public Notification createNotification(long cid, Notification notification) {
		log.info("notification -->", notification);
		notification.setCustomerId(cid);
		Notification t = this.notificationRepository.save(notification);
		return t;
	}

	public Notification getNotificationByCustomerIdAndNotificationId(long cid, long nid) {

		Notification notification = this.notificationRepository.findByCustomerIdAndId(cid, nid);
		// .orElseThrow(() -> new IllegalArgumentException("Ticket with Customer id does
		// not exists"));
		return notification;
	}

	public List<Notification> getNotificationByCustomerId(long cid) {

		List<Notification> notifications = this.notificationRepository.findByCustomerId(cid);
		// .orElseThrow(() -> new IllegalArgumentException("Ticket with Customer id does
		// not exists"));
		return notifications;
	}

}
