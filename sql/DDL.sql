CREATE TABLE subscription_category (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    use_yn CHAR(1) NOT NULL DEFAULT 'Y' CHECK (use_yn IN ('Y','N'))
);

CREATE TABLE subscription_item (
    item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    use_yn CHAR(1) NOT NULL DEFAULT 'Y' CHECK (use_yn IN ('Y','N')),
    FOREIGN KEY (category_id) REFERENCES subscription_category(category_id)
);

CREATE TABLE user ( 
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    edit_id VARCHAR(255) NOT NULL,
    `log` VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL UNIQUE,
    gender CHAR(1) NULL CHECK (gender IN ('M','F')),
    birth_date DATE NULL,
    user_state VARCHAR(255) NOT NULL,
    user_image TEXT NULL,
    email_verified CHAR(1) NOT NULL DEFAULT 'N' CHECK (email_verified IN ('Y','N')),
    email_verified_at DATETIME NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL DEFAULT CURRENT_TIMESTAMP 
);

CREATE TABLE payment_method (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    card_name VARCHAR(255) NULL,
    memo TEXT NULL,
    method_type ENUM('CARD','BANK','ETC') NOT NULL,
    is_active TINYINT(1) NOT NULL DEFAULT 1,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE add_subscription (
    subscription_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    payment_id BIGINT NOT NULL,
    price INT CHECK (price >= 0),
    billing_cycle ENUM('1W','1M','6M','1Y') NOT NULL,
    payment_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    use_yn CHAR(1) NOT NULL DEFAULT 'Y' CHECK (use_yn IN ('Y','N')),
	 FOREIGN KEY (item_id) REFERENCES subscription_item(item_id),
	 FOREIGN KEY (user_id) REFERENCES user(user_id),
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
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
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

CREATE TABLE recommendations (
    report_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    input_budget INT NOT NULL DEFAULT 0 CHECK (input_budget >= 0),
    `comment` TEXT NULL,
    total_price INT NOT NULL DEFAULT 0 CHECK (total_price >= 0),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	 FOREIGN KEY (user_id) REFERENCES `user`(user_id)
);

