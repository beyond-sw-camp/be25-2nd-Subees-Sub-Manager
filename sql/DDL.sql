CREATE TABLE subscription_category (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE subscription_item (
    item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT NOT NULL,
    item_name VARCHAR(255) NOT NULL,
	 FOREIGN KEY (category_id) REFERENCES subscription_category(category_id)
);

CREATE TABLE user ( 
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL UNIQUE,
    user_state ENUM('ACTIVE','INACTIVE','SUSPENDED') NOT NULL,
    profile_image VARCHAR(255) NULL ,
    email_verified CHAR(1) NOT NULL DEFAULT 'N' CHECK (email_verified IN ('Y','N')),
    email_verified_at DATETIME NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE card (
	 card_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	 card_company VARCHAR(50) NOT NULL UNIQUE 
);

CREATE TABLE payment_method (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    card_id BIGINT NULL ,
    user_id BIGINT NOT NULL,
    card_name VARCHAR(255) NULL,
	 is_active TINYINT(1) NOT NULL DEFAULT 1,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    FOREIGN KEY (card_id) REFERENCES `card`(card_id)
);

CREATE TABLE add_subscription (
    subscription_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    item_id BIGINT NULL,
    category_id BIGINT NOT NULL,
    payment_id BIGINT NULL,
    price INT NOT NULL,
    billing_cycle ENUM('1M', '1Y') NOT NULL,
    start_date DATETIME NULL ,
    end_date DATETIME NULL ,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    use_yn CHAR(1) DEFAULT 'Y',
    FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    FOREIGN KEY (item_id) REFERENCES subscription_item(item_id),
    FOREIGN KEY (payment_id) REFERENCES payment_method(payment_id)
);

CREATE TABLE community_posts (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    view_count INT NOT NULL DEFAULT 0,
    scrap_count INT NOT NULL DEFAULT 0,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	 updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES `user`(user_id)
);

CREATE TABLE community_scrap (
    scrap_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	 FOREIGN KEY (post_id) REFERENCES community_posts(post_id),
	 FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    UNIQUE (post_id, user_id)
);

CREATE TABLE notifications (
    notification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    subscription_id BIGINT NOT NULL,
    is_read TINYINT(1) NOT NULL DEFAULT 0,
    is_closed TINYINT(1) NOT NULL DEFAULT 0,
    notify_type ENUM('D3','D0') NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    alarm TEXT NOT NULL,
	 FOREIGN KEY (user_id) REFERENCES `user`(user_id),
	 FOREIGN KEY (subscription_id) REFERENCES add_subscription(subscription_id)
);

ALTER TABLE payment_method 
    ADD COLUMN custom_card_company VARCHAR(50) NULL;
    
ALTER TABLE add_subscription
MODIFY COLUMN use_yn CHAR(1) NOT NULL DEFAULT 'Y';

ALTER TABLE subscription_item
ADD UNIQUE (category_id, item_name);

ALTER TABLE add_subscription
DROP COLUMN category_id;

ALTER TABLE add_subscription
ADD CHECK (use_yn IN ('Y', 'N'));

ALTER TABLE payment_method
ADD CHECK (is_active IN (0, 1));

ALTER TABLE notifications
ADD CHECK (is_read IN (0, 1));

ALTER TABLE notifications
ADD CHECK (is_closed IN (0, 1));

ALTER TABLE add_subscription
MODIFY COLUMN item_id BIGINT NOT NULL;