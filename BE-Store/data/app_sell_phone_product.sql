-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: app_sell_phone
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` varchar(255) DEFAULT NULL,
  `updated_date` varchar(255) DEFAULT NULL,
  `description` text,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `status` int NOT NULL,
  `stock` int NOT NULL,
  `brand_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs6cydsualtsrprvlf2bb3lcam` (`brand_id`),
  CONSTRAINT `FKs6cydsualtsrprvlf2bb3lcam` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (13,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','Cuối cùng thì chiếc iPhone 14 Pro Max cũng đã chính thức lộ diện tại sự kiện ra mắt thường niên vào ngày 08/09 đến từ nhà Apple, kết thúc bao lời đồn đoán bằng một bộ thông số cực kỳ ấn tượng cùng vẻ ngoài đẹp mắt sang trọng','iphone14promax.jpg','Điện thoại iPhone 14 Pro Max 128GB',28190000,1,50,1),(14,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','iPhone 14 Pro 128GB - Mẫu smartphone đến từ nhà Apple được mong đợi nhất năm 2022, lần này nhà Táo mang đến cho chúng ta một phiên bản với kiểu thiết kế hình notch mới, cấu hình mạnh mẽ nhờ con chip Apple A16 Bionic và cụm camera có độ phân giải được nâng cấp lên đến 48 MP.','iphone14pro.jpg','Điện thoại iPhone 14 Pro 128GB',26490000,1,50,1),(17,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','Apple đã chính thức trình làng bộ 3 siêu phẩm iPhone 11, trong đó phiên bản iPhone 11 64GB có mức giá rẻ nhất nhưng vẫn được nâng cấp mạnh mẽ như iPhone Xr ra mắt trước đó.','iphone11.jpg','Điện thoại iPhone 11 64GB',11990000,1,50,1),(18,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','Trong khi sức hút đến từ bộ 4 phiên bản iPhone 12 vẫn chưa nguội đi, thì hãng điện thoại Apple đã mang đến cho người dùng một siêu phẩm mới iPhone 13 với nhiều cải tiến thú vị sẽ mang lại những trải nghiệm hấp dẫn nhất cho người dùng.','iphone13.jpg','Điện thoại iPhone 13 128GB',18390000,1,50,1),(19,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','Samsung Galaxy S23 có thể xem là cái tên mở màn cho dòng điện thoại cao cấp được nhà Samsung giới thiệu vào dịp đầu năm 2023, kể từ lúc chính thức ra mắt máy nhận được khá nhiều sự quan tâm với hàng loạt thông tin cấu hình hết sức nổi bật như con chip hàng đầu Qualcomm cùng bộ camera siêu chất lượng.','galaxys235g.jpg','Điện thoại Samsung Galaxy S23 5G 128GB',18390000,1,40,2),(20,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','Samsung Galaxy S21 FE 5G (6GB/128GB) được Samsung ra mắt với dáng dấp thời thượng, cấu hình khỏe, chụp ảnh đẹp với bộ 3 camera chất lượng, thời lượng pin đủ dùng hằng ngày và còn gì nữa? Mời bạn khám phá qua nội dung sau ngay.','s21fe5g.jpg','Điện thoại Samsung Galaxy S21 FE 5G (6GB/128GB) ',10990000,1,40,2),(21,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','Tại sự kiện Samsung Unpacked thường niên, Samsung Galaxy Z Fold4 256GB chính thức được trình làng thị trường công nghệ, mang trên mình nhiều cải tiến đáng giá giúp Galaxy Z Fold trở thành dòng điện thoại gập đã tốt nay càng tốt hơn.','fold4.jpg','Điện thoại Samsung Galaxy Z Fold4 256GB',37990000,1,20,2),(22,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','Xiaomi Redmi Note 11 Pro 4G mang trong mình khá nhiều những nâng cấp cực kì sáng giá. Là chiếc điện thoại có màn hình lớn, tần số quét 120 Hz, hiệu năng ổn định cùng một viên pin siêu trâu.','node11pro.jpg','Điện thoại Xiaomi Redmi Note 11 Pro',6190000,1,60,3),(23,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','Cuối cùng Xiaomi 12T Pro 5G cũng đã chính thức lộ diện trên thị trường sau hàng loạt thông tin rò rỉ về thông số, đúng như dự đoán thì độ phân giải trên camera của phiên bản này được nhà sản xuất nâng cấp lên đến 200 MP, giúp máy trở thành thiết bị có khả năng ghi hình sắc nét thuộc top đầu giới smartphone, đi kèm với đó là màn hình chất lượng cùng bộ vi xử lý mạnh mẽ xứng tầm flagship.','12T.jpg','Điện thoại Xiaomi 12T Pro 5G',14090000,1,55,3),(24,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','OPPO Reno8 T 5G 128GB là mẫu điện thoại đầu tiên trong năm 2023 mà OPPO kinh doanh tại Việt Nam. Máy nhận được khá nhiều sự quan tâm đến từ cộng đồng công nghệ về thông số kỹ thuật hết sức ấn tượng như: Camera 108 MP, chipset nhà Qualcomm và màn hình AMOLED.','reno8.jpg','Điện thoại OPPO Reno8 T 5G 128GB',9900000,1,60,4),(25,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','OPPO Find X5 Pro 5G có lẽ là cái tên sáng giá được xướng tên trong danh sách điện thoại có thiết kế ấn tượng trong năm 2022. Máy được hãng cho ra mắt với một diện mạo độc lạ chưa từng có, cùng với đó là những nâng cấp về chất lượng ở camera nhờ sự hợp tác với nhà sản xuất máy ảnh Hasselblad. ','findx5pro.jpg','Điện thoại OPPO Find X5 Pro 5G',24990000,1,40,4),(26,'2023-02-25 20:51:15.095','2023-02-25 20:51:15.095','OPPO đã trình làng mẫu Reno7 Z 5G với thiết kế OPPO Glow độc quyền, camera mang hiệu ứng như máy DSLR chuyên nghiệp cùng viền sáng kép, máy có một cấu hình mạnh mẽ và đạt chứng nhận xếp hạng A về độ mượt.','reno7z.jpg','Điện thoại OPPO Reno7 Z 5G',7490000,1,40,4);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-07 11:27:14
