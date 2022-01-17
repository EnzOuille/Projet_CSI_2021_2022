package com.csi.CSI.repositories;

import com.csi.CSI.objets.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationRepo extends JpaRepository<Notification, String>, JpaSpecificationExecutor<Notification> {

}

