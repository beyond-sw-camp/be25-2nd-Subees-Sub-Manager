INSERT INTO subscription_category (category_name)
VALUES 
('OTT'),
('AI'),
('Music'),
('Others');

UPDATE subscription_category
SET category_name = 'Etc'
WHERE category_name = 'Others';

-- 확인
SELECT * 
FROM subscription_category ;

# 카드 종류
INSERT INTO card (card_id, card_company)
VALUES 
(1, '신한카드'),
(2, '국민카드'),
(3, '농협카드'),
(4, '우리카드'),
(5, '하나카드'),
(6, '삼성카드'),
(7, '현대카드'),
(8, '롯데카드');

-- 확인
SELECT * 
FROM card 
ORDER BY 1;

