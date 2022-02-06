package com.synechron.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synechron.notificationservice.model.Notification;
import com.synechron.notificationservice.service.NotificationApiService;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

	@Autowired
	private NotificationApiService NotificationApiService;

	@PostMapping("/notifications/customer/{customerid}")
	public ResponseEntity<Notification> createNotification(@PathVariable("customerid") long cid,
			@Valid @RequestBody Notification notification) {

		Notification t = this.NotificationApiService.createNotification(cid, notification);
		return new ResponseEntity<Notification>(t, HttpStatus.CREATED);
	}

	@GetMapping("/customer/{customerid}/notification/{id}")
	public ResponseEntity<Notification> findById(@PathVariable("customerid") long cid, @PathVariable("id") long nid) {

		Notification l1 = this.NotificationApiService.getNotificationByCustomerIdAndNotificationId(cid, nid);
		// System.out.println(l1);
		return new ResponseEntity<Notification>(l1, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/customer/{customerid}")
	public ResponseEntity<List<Notification>> findById(@PathVariable("customerid") long cid) {

		List<Notification> l1 = this.NotificationApiService.getNotificationByCustomerId(cid);
		// System.out.println(l1);
		return new ResponseEntity<List<Notification>>(l1, HttpStatus.OK);

	}
}