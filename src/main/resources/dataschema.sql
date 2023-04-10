/*DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
    `id`   tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COMMENT='contains product categories, e.g., dairy, meats, etc.';

LOCK
TABLES `category` WRITE;
INSERT INTO `category`
VALUES (1, 'dairy'),
       (2, 'meats'),
       (3, 'bakery'),
       (4, 'fruit & veg');
UNLOCK
TABLES;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `id`          int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name`        varchar(45)   NOT NULL,
    `price`       decimal(5, 2) NOT NULL,
    `description` tinytext,
    `last_update` timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `category_id` tinyint(3) unsigned NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_product_category` (`category_id`),
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1 COMMENT='contains product details';

LOCK
TABLES `product` WRITE;
INSERT INTO `product`
VALUES (1, 'milk', 1.70, 'semi skimmed (1L)', '2023-01-30 03:15:48', 1),
       (2, 'cheese', 2.39, 'mild cheddar (330g)', '2023-01-30 03:15:48', 1),
       (3, 'butter', 1.09, 'unsalted (250g)', '2023-01-30 03:15:48', 1),
       (4, 'free range eggs', 1.76, 'medium-sized (6 eggs)', '2023-01-30 03:15:48', 1),
       (5, 'organic meat patties', 2.29, 'rolled in fresh herbs<br>2 patties (250g)', '2023-01-30 03:15:48', 2),
       (6, 'parma ham', 3.49, 'matured, organic (70g)', '2023-01-30 03:15:48', 2),
       (7, 'chicken leg', 2.59, 'free range (250g)', '2023-01-30 03:15:48', 2),
       (8, 'sausages', 3.55, 'reduced fat, pork<br>3 sausages (350g)', '2023-01-30 03:15:49', 2),
       (9, 'sunflower seed loaf', 1.89, '600g', '2023-01-30 03:15:49', 3),
       (10, 'sesame seed bagel', 1.19, '4 bagels', '2023-01-30 03:15:49', 3),
       (11, 'pumpkin seed bun', 1.15, '4 buns', '2023-01-30 03:15:49', 3),
       (12, 'chocolate cookies', 2.39, 'contain peanuts<br>(3 cookies)', '2023-01-30 03:15:49', 3),
       (13, 'corn on the cob', 1.59, '2 pieces', '2023-01-30 03:15:49', 4),
       (14, 'red currants', 2.49, '150g', '2023-01-30 03:15:49', 4),
       (15, 'broccoli', 1.29, '500g', '2023-01-30 03:15:49', 4),
       (16, 'seedless watermelon', 1.49, '250g', '2023-01-30 03:15:50', 4);
UNLOCK
TABLES;

*/