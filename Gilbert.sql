-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: gilbert
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=724 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'(di)vision'),(2,'032c'),(3,'1017 ALYX 9SM'),(4,'11 by Boris Bidjan Saberi'),(5,'132 5. ISSEY MIYAKE'),(6,'16Arlington'),(7,'3.1 Phillip Lim'),(8,'424'),(9,'44 Label Group'),(10,'4SDESIGNS'),(11,'66°North'),(12,'7 DAYS Active'),(13,'99%IS-'),(14,'_J.L - A.L_'),(15,'A BETTER FEELING'),(16,'A Diciannoveventitre'),(17,'A PERSONAL NOTE 73'),(18,'A-COLD-WALL*'),(19,'A. A. Spectrum'),(20,'A.P.C.'),(21,'AAPE by A Bathing Ape'),(22,'AARON ESH'),(23,'ABRA'),(24,'Achilles Ion Gabriel'),(25,'Acne Studios'),(26,'ACRONYM®'),(27,'ADER error'),(28,'adidas Originals'),(29,'adidas x Humanrace by Pharrell Williams'),(30,'Adieu'),(31,'Adsum'),(32,'Advisory Board Crystals'),(33,'ADYAR'),(34,'ænrmòus'),(35,'AFFXWRKS'),(36,'AFTER PRAY'),(37,'AGOLDE'),(38,'AGR'),(39,'Ahluwalia'),(40,'Ahluwalia &PaulSmith'),(41,'AIREI'),(42,'AKILA'),(43,'ALAINPAUL'),(44,'Alan Crocetti'),(45,'Alec Doherty'),(46,'ALEXANDER DIGENOVA'),(47,'Alexander Wang'),(48,'Alighieri'),(49,'ALTU'),(50,'AMBUSH'),(51,'AMI Paris'),(52,'AMIRI'),(53,'AMOMENTO'),(54,'and wander'),(55,'Anderson\'s'),(56,'Andersson Bell'),(57,'ANDREJ GRONAU'),(58,'Ann Demeulemeester'),(59,'Anna Sui'),(60,'ANONYMOUS CLUB'),(61,'ANOTHER ASPECT'),(62,'Apartment 1007'),(63,'APRÈS Research'),(64,'Archival Reinvent'),(65,'Aries'),(66,'Ashley Williams'),(67,'ASICS'),(68,'ASPESI'),(69,'ATON'),(70,'ATTACHMENT'),(71,'AURALEE'),(72,'AUTRY'),(73,'Aviva Jifei Xue'),(74,'Awake NY'),(75,'Axel Arigato'),(76,'Aztech Mountain'),(77,'B1ARCHIVE'),(78,'Baffin'),(79,'Baina'),(80,'Balenciaga'),(81,'Bally'),(82,'Balmain'),(83,'BAO BAO ISSEY MIYAKE'),(84,'BAPE'),(85,'Barbour'),(86,'Barena'),(87,'BARRAGÁN'),(88,'Bather'),(89,'BEAMS PLUS'),(90,'Belstaff'),(91,'Berner Kühl'),(92,'Bianca Saunders'),(93,'Billionaire Boys Club'),(94,'Birkenstock'),(95,'Birrot'),(96,'Black Comme des Garçons'),(97,'BLÆST'),(98,'Bless'),(99,'Bleue Burnham'),(100,'BLK DNM'),(101,'BLUEMARBLE'),(102,'Bode'),(103,'BONNIE CLYDE'),(104,'Boris Bidjan Saberi'),(105,'BOSS'),(106,'both'),(107,'Bottega Veneta'),(108,'Botter'),(109,'Brain Dead'),(110,'Brioni'),(111,'Brunello Cucinelli'),(112,'Burberry'),(113,'BUTLER SVC'),(114,'C.P. Company'),(115,'C2H4'),(116,'Calvin Klein'),(117,'CALVINLUO'),(118,'Camiel Fortgens'),(119,'CAMPERLAB'),(120,'Canada Goose'),(121,'Carhartt Work In Progress'),(122,'Carlota Barrera'),(123,'Carne Bollente'),(124,'CARNET-ARCHIVE'),(125,'CARRER'),(126,'Carter Young'),(127,'Cartier'),(128,'Casablanca'),(129,'CASEY CASEY'),(130,'Castañer'),(131,'CCP'),(132,'CDLP'),(133,'Celine'),(134,'CFCL'),(135,'Chanel'),(136,'Charles Jeffrey LOVERBOY'),(137,'Charlie Constantinou'),(138,'CHENPENG'),(139,'Children of the Discordance'),(140,'CHIMI'),(141,'Chopova Lowena'),(142,'Christian Louboutin'),(143,'Clarks Originals'),(144,'CMF Outdoor Garment'),(145,'CMMAWEAR'),(146,'CMMN SWDN'),(147,'Coach 1941'),(148,'Cobra S.C.'),(149,'colbo'),(150,'COLLEGIUM'),(151,'Collina Strada'),(152,'COMMAS'),(153,'Comme des Garçons Homme'),(154,'Comme des Garçons Homme Deux'),(155,'Comme des Garçons Homme Plus'),(156,'COMME des GARÇONS PLAY'),(157,'Comme des Garçons Shirt'),(158,'COMME des GARÇONS WALLETS'),(159,'Commission'),(160,'Common Projects'),(161,'Connor McKnight'),(162,'Converse'),(163,'COOR'),(164,'Coperni'),(165,'Cordera'),(166,'Cornerstone'),(167,'Côte&Ciel'),(168,'Courrèges'),(169,'COÛT DE LA LIBERTÉ'),(170,'Cowgirl Blue Co'),(171,'Craig Green'),(172,'Crocs'),(173,'Cutler and Gross'),(174,'DAIWA PIER39'),(175,'DANCER'),(176,'Danner'),(177,'DARKPARK'),(178,'Deadwood'),(179,'DEMON'),(180,'Descente ALLTERRAIN'),(181,'DEVÁ STATES'),(182,'DEVOA'),(183,'Diemme'),(184,'Diesel'),(185,'Dime'),(186,'Dingyun Zhang'),(187,'Dion Lee'),(188,'District Vision'),(189,'Dita'),(190,'DOCUMENT'),(191,'Dolce&Gabbana'),(192,'Double Rainbouu'),(193,'doublet'),(194,'Dr. Martens'),(195,'DRAE'),(196,'Drake\'s'),(197,'drew house'),(198,'Dries Van Noten'),(199,'Drôle De Monsieur'),(200,'Dsquared2'),(201,'Dunst'),(202,'ECCO.kollektive'),(203,'Eckhaus Latta'),(204,'Edward Cuming'),(205,'EGONlab'),(206,'Eidos'),(207,'ekn'),(208,'éliou'),(209,'Ellie Mercer'),(210,'Emanuele Bicocchi'),(211,'Emporio Armani'),(212,'Enfants Riches Déprimés'),(213,'Engineered Garments'),(214,'Entire Studios'),(215,'Erdem'),(216,'ERL'),(217,'Ernest W. Baker'),(218,'Études'),(219,'EYTYS'),(220,'F-LAGSTUF-F'),(221,'F/CE.®'),(222,'Fabiana Filippi'),(223,'FACTORY900'),(224,'FARIS'),(225,'Fax Copy Express'),(226,'Fear of God'),(227,'Fear of God Athletics'),(228,'Fear of God ESSENTIALS'),(229,'Fendi'),(230,'Feng Chen Wang'),(231,'Ferragamo'),(232,'FFFPOSTALSERVICE'),(233,'Filippa K'),(234,'Fiorucci'),(235,'FLATLIST EYEWEAR'),(236,'Floyd'),(237,'FORMA'),(238,'FRAME'),(239,'Fred Perry'),(240,'Frédérique Constant'),(241,'FREI-MUT'),(242,'Frenckenberger'),(243,'FUMITO GANRYU'),(244,'Gabriela Coll Garments'),(245,'Galilee-By-Sea'),(246,'GANNI'),(247,'GANT 240 MULBERRY STREET'),(248,'Garrett Leight'),(249,'GCDS'),(250,'Gentle Fullness'),(251,'Georgia Kemball'),(252,'Gerrit Jacob'),(253,'Gimaguas'),(254,'Giorgio Armani'),(255,'Givenchy'),(256,'Glass Cypress'),(257,'GmbH'),(258,'Golden Goose'),(259,'Goldwin'),(260,'Goldwin 0'),(261,'GR10K'),(262,'Gramicci'),(263,'Greg Lauren'),(264,'GREG ROSS'),(265,'Grey Ant'),(266,'Gucci'),(267,'GUESS USA'),(268,'Guest in Residence'),(269,'Guidi'),(270,'Han Kjobenhavn'),(271,'HARAGO'),(272,'Hatton Labs'),(273,'HAULIER'),(274,'HEAD OF STATE'),(275,'Hed Mayner'),(276,'HELIOT EMIL'),(277,'Helmut Lang'),(278,'Henrik Vibskov'),(279,'HEREU'),(280,'Hermes'),(281,'Heron Preston'),(282,'HGBB STUDIO'),(283,'HH-118389225'),(284,'HODAKOVA'),(285,'Holzweiler'),(286,'HOMME PLISSÉ ISSEY MIYAKE'),(287,'HOPE'),(288,'Horizn Studios'),(289,'Howlin\''),(290,'Hugo'),(291,'HUGO KREIT'),(292,'Human Recreational Services'),(293,'HYEIN SEO'),(294,'ICECREAM'),(295,'IN GOLD WE TRUST PARIS'),(296,'Innerraum'),(297,'INSATIABLE HIGH'),(298,'International Gallery BEAMS'),(299,'Isa Boulder'),(300,'Isabel Marant'),(301,'Izzue'),(302,'J EONGL I'),(303,'JACQUEMUS'),(304,'Jan-Jan Van Essche'),(305,'Jean Paul Gaultier'),(306,'JieDa'),(307,'Jil Sander'),(308,'JiyongKim'),(309,'John Elliott'),(310,'Johnlawrencesullivan'),(311,'Joseph'),(312,'Judy Turner'),(313,'Julius'),(314,'Juntae Kim'),(315,'Junya Watanabe'),(316,'Justine Clenquet'),(317,'Juun.J'),(318,'JW Anderson'),(319,'K.NGSLEY'),(320,'KANGHYUK'),(321,'KAPTAIN SUNSHINE'),(322,'KARA'),(323,'Karmuel Young'),(324,'Kartik Research'),(325,'KEEN'),(326,'Kenzo'),(327,'Khaite'),(328,'Khoki'),(329,'KIDILL'),(330,'Kids Worldwide'),(331,'KidSuper'),(332,'Kijun'),(333,'Kiko Kostadinov'),(334,'King & Tuckfield'),(335,'KLEMAN'),(336,'kolor'),(337,'KOZABURO'),(338,'Ksubi'),(339,'Kuboraum'),(340,'Kuro'),(341,'KUSIKOHC'),(342,'Labrum'),(343,'Lacoste'),(344,'Lady White Co.'),(345,'Lanvin'),(346,'Lardini'),(347,'Late Checkout'),(348,'Le Gramme'),(349,'le PÈRE'),(350,'LE17SEPTEMBRE'),(351,'LEMAIRE'),(352,'Les Tien'),(353,'lesugiatelier'),(354,'Levi\'s'),(355,'LGN Louis Gabriel Nouchi'),(356,'Li-Ning'),(357,'Liberal Youth Ministry'),(358,'LISA YANG'),(359,'LOEWE'),(360,'Louis Vuitton'),(361,'Loro Piana'),(362,'LOW CLASSIC'),(363,'Lownn'),(364,'LU\'U DAN'),(365,'Ludovic de Saint Sernin'),(366,'Lukhanyo Mdingi'),(367,'MAAP'),(368,'MACKAGE'),(369,'Madhappy'),(370,'Magliano'),(371,'Maharishi'),(372,'Maiden Name'),(373,'Maisie Wilen'),(374,'Maison Kitsuné'),(375,'Maison Margiela'),(376,'Maison MIHARA YASUHIRO'),(377,'Manors Golf'),(378,'Manzoni 24'),(379,'MAPLE'),(380,'Marcelo Burlon County of Milan'),(381,'Marina Yee'),(382,'Marine Serre'),(383,'Mark Kenly Domino Tan Studio'),(384,'Marking Distance'),(385,'Marni'),(386,'Marsèll'),(387,'Martin Asbjørn'),(388,'Martine Ali'),(389,'Martine Rose'),(390,'Maryam Nassir Zadeh'),(391,'master-piece'),(392,'mastermind JAPAN'),(393,'MASTERMIND WORLD'),(394,'Matsuda'),(395,'MAUSTEIN'),(396,'Max Mara'),(397,'Maximilian Davis'),(398,'MCQ'),(399,'McQueen'),(400,'meanswhile'),(401,'Members of the Rage'),(402,'Merrell 1TRL'),(403,'Meryll Rogge'),(404,'Merz b. Schwanen'),(405,'Meta Campania Collective'),(406,'Metalwood Studio'),(407,'mfpen'),(408,'MICROMILSPEC'),(409,'MISBHV'),(410,'Missoni'),(411,'Missoni Sport'),(412,'Miu Miu'),(413,'MM6 Maison Margiela'),(414,'Moncler'),(415,'Moncler Genius'),(416,'Moncler Grenoble'),(417,'Monos'),(418,'Montblanc'),(419,'Moon Boot'),(420,'Moose Knuckles'),(421,'Moschino'),(422,'Mowalola'),(423,'Mr. Saturday'),(424,'MSGM'),(425,'Mugler'),(426,'Museum of Peace & Quiet'),(427,'N.Hoolywood'),(428,'Nahmias'),(429,'Naked & Famous Denim'),(430,'NAMESAKE'),(431,'nanamica'),(432,'NANGA'),(433,'Nanushka'),(434,'NEEDLES'),(435,'Neighborhood'),(436,'Neil Barrett'),(437,'New Balance'),(438,'Nike'),(439,'Nike Jordan'),(440,'Nike x OFF-White'),(441,'Nike x Travis Scott'),(442,'Nili Lotan'),(443,'NN07'),(444,'NO IDEA'),(445,'NO/FAITH STUDIOS'),(446,'Noah'),(447,'NOMA t.d.'),(448,'Noon Goons'),(449,'Norda'),(450,'NORSE PROJECTS'),(451,'Norse Projects ARKTISK'),(452,'NotSoNormal'),(453,'Nuba'),(454,'Nudie Jeans'),(455,'NULLUS'),(456,'Numbering'),(457,'NVRFRGT'),(458,'Oakley'),(459,'Oakley Factory Team'),(460,'OAS'),(461,'Objects IV Life'),(462,'octi'),(463,'Off-White'),(464,'Officine Creative'),(465,'Officine Générale'),(466,'Oliver Peoples'),(467,'Olly Shinder'),(468,'Omar Afridi'),(469,'On'),(470,'Online Ceramics'),(471,'Opening Ceremony'),(472,'Orlebar Brown'),(473,'Ottolinger'),(474,'OTTOMILA'),(475,'OUAT'),(476,'OUR LEGACY'),(477,'Outdoor Voices'),(478,'OVER OVER'),(479,'Palm Angels'),(480,'PALMER'),(481,'Palmes'),(482,'Paloma Wool'),(483,'PALY'),(484,'PANGAIA'),(485,'Parajumpers'),(486,'Paravel'),(487,'Parel Studios'),(488,'Parts of Four'),(489,'Pas Normal Studios'),(490,'Paul Smith'),(491,'PDF'),(492,'Pearl Octopuss.y'),(493,'Pearls Before Swine'),(494,'Perks and Mini'),(495,'Persol'),(496,'pet-tree-kor'),(497,'Peter Do'),(498,'Phileo'),(499,'Pilgrim Surf + Supply'),(500,'PLACES+FACES'),(501,'Polo Ralph Lauren'),(502,'PONDER.ER'),(503,'Pop Trading Company'),(504,'Port Tanger'),(505,'PORTER - Yoshida & Co'),(506,'POST ARCHIVE FACTION (PAF)'),(507,'POTTERY'),(508,'Prada Eyewear'),(509,'Praying'),(510,'Professor.E'),(511,'Prototypes'),(512,'PS by Paul Smith'),(513,'PUMA'),(514,'R13'),(515,'Rabanne'),(516,'Raf Simons'),(517,'rag & bone'),(518,'Raga Malak'),(519,'Raimundo Langlois'),(520,'RAINMAKER KYOTO'),(521,'RAINS'),(522,'Ralph Lauren Purple Label'),(523,'Random Identities'),(524,'RANRA'),(525,'Rapha'),(526,'Rassvet'),(527,'Ray-Ban'),(528,'RCOS'),(529,'Re/Done'),(530,'RECTO'),(531,'Reebok Classics'),(532,'Reese Cooper'),(533,'Reigning Champ'),(534,'Remi Relief'),(535,'Represent'),(536,'RETROSUPERFUTURE'),(537,'Rhude'),(538,'RICE NINE TEN'),(539,'Rick Owens'),(540,'Rick Owens DRKSHDW'),(541,'Rier'),(542,'RIGARDS'),(543,'Rimowa'),(544,'rito structure'),(545,'Rivington Roi Rebis'),(546,'ROA'),(547,'Robyn Lynch'),(548,'Róhe'),(549,'Rombaut'),(550,'RRL'),(551,'RTA'),(552,'S.R. STUDIO. LA. CA.'),(553,'sacai'),(554,'SAGE NATION'),(555,'Saint Laurent'),(556,'Saintwoods'),(557,'Salomon'),(558,'Samsøe Samsøe'),(559,'SAPIO'),(560,'Satisfy'),(561,'Satoshi Nakamoto'),(562,'Satta'),(563,'Saturdays NYC'),(564,'Saucony'),(565,'Saul Nash'),(566,'SC103'),(567,'Schnayderman\'s'),(568,'Sean Suen'),(569,'Sebago'),(570,'Sébline'),(571,'Second/Layer'),(572,'Secret of Manna'),(573,'Séfr'),(574,'Serapis'),(575,'Seventh'),(576,'Shamballa'),(577,'Simone Rocha'),(578,'SIR.'),(579,'Situationist'),(580,'SKIMS'),(581,'Sky High Farm Workwear'),(582,'Small Talk Studio'),(583,'Snow Peak'),(584,'Soar Running'),(585,'Solid Homme'),(586,'Song for the Mute'),(587,'Sophie Buhai'),(588,'SOPHNET.'),(589,'SOSHIOTSUKI'),(590,'Soulland'),(591,'South2 West8'),(592,'SPENCER BADU'),(593,'Spinelli Kilcollin'),(594,'Sporty & Rich'),(595,'Stanley Raffington'),(596,'S.T. Dupont'),(597,'Stefan Cooke'),(598,'stein'),(599,'Stepney Workers Club'),(600,'Steven Passaro'),(601,'Still Kelly'),(602,'Stockholm (Surfboard) Club'),(603,'Stolen Girlfriends Club'),(604,'Stone Island'),(605,'Story mfg.'),(606,'STRONGTHE'),(607,'Studio Nicholson'),(608,'Stüssy'),(609,'SUICOKE'),(610,'Sulvam'),(611,'Sunflower'),(612,'SUNNEI'),(613,'Sunspel'),(614,'Swampgod'),(615,'System'),(616,'T/SEHNE'),(617,'TAAKK'),(618,'Taiga Takahashi'),(619,'TAION'),(620,'TAKAHIROMIYASHITA TheSoloist.'),(621,'Tanaka'),(622,'Tekla'),(623,'Templa'),(624,'Teva'),(625,'Th products'),(626,'Thames MMXX.'),(627,'The Elder Statesman'),(628,'The Frankie Shop'),(629,'The Letters'),(630,'The North Face'),(631,'The Ouze'),(632,'The Row'),(633,'the Shepherd UNDERCOVER'),(634,'The Viridi-anne'),(635,'The World Is Your Oyster'),(636,'Theophilio'),(637,'Theory'),(638,'thisisneverthat'),(639,'Thom Browne'),(640,'thom/krom'),(641,'Thug Club'),(642,'Tiger of Sweden'),(643,'Timberland'),(644,'Title of Work'),(645,'TMS.SITE'),(646,'Toga Virilis'),(647,'Tods'),(648,'Tokyo James'),(649,'TOM FORD'),(650,'Tom Wood'),(651,'TOMBOGO™'),(652,'Tommy Jeans'),(653,'Tondolo'),(654,'Tonywack'),(655,'Toogood'),(656,'Tumi'),(657,'UMA WANG'),(658,'Umbro'),(659,'Uncertain Factor'),(660,'UNDERCOVER'),(661,'Uniform Bridge'),(662,'Universal Colours'),(663,'Universal Works'),(664,'untitlab®'),(665,'Valentino'),(666,'Valentino Garavani'),(667,'Van Cleef & Arpels'),(668,'Vans'),(669,'VAQUERA'),(670,'Vasiliki'),(671,'VEERT'),(672,'Veilance'),(673,'VEIN'),(674,'VEJA'),(675,'Veneda Carter'),(676,'Versace'),(677,'Versace Jeans Couture'),(678,'Versace Underwear'),(679,'VETEMENTS'),(680,'Viktor&Rolf Mister Mister'),(681,'Vince'),(682,'VINNY’s'),(683,'Virón'),(684,'visvim'),(685,'Vitaly'),(686,'Vivienne Westwood'),(687,'vowels'),(688,'VTMNTS'),(689,'WACKO MARIA'),(690,'Wales Bonner'),(691,'Walter Van Beirendonck'),(692,'We11done'),(693,'White Mountaineering®︎'),(694,'Who Decides War'),(695,'WILLY CHAVARRIA'),(696,'Won Hundred'),(697,'WOOD WOOD'),(698,'WOOYOUNGMI'),(699,'WTAPS'),(700,'WYNN HAMLYN'),(701,'Xander Zhou'),(702,'XENIA TELUNTS'),(703,'XLIM'),(704,'Y\'s For Men'),(705,'Y-3'),(706,'Y/Project'),(707,'YAKU'),(708,'YEEZY'),(709,'YLÈVE'),(710,'YMC'),(711,'YOHJI YAMAMOTO'),(712,'YOKE'),(713,'young n sang'),(714,'Youth'),(715,'Youths in Balaclava'),(716,'Yuki Hashimoto'),(717,'YULONG XIA'),(718,'YUME YUME'),(719,'Yves Saint Laurent'),(720,'Z Zegna'),(721,'ZANKOV'),(722,'ZEGNA'),(723,'ZEGNA x The Elder Statesman');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (4,'Beauty'),(3,'Home'),(1,'Men\'s'),(2,'Women\'s');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clothing_article`
--

DROP TABLE IF EXISTS `clothing_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clothing_article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `subcategory_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `subcategory_id` (`subcategory_id`),
  CONSTRAINT `clothing_article_ibfk_1` FOREIGN KEY (`subcategory_id`) REFERENCES `subcategory` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clothing_article`
--

LOCK TABLES `clothing_article` WRITE;
/*!40000 ALTER TABLE `clothing_article` DISABLE KEYS */;
INSERT INTO `clothing_article` VALUES (1,'Sandals',1),(2,'Sneakers',1),(3,'Boots',1),(4,'Loafers',1),(5,'Slippers',1),(6,'Lace ups ',1),(7,'Flip Flops',1),(8,'Handbags',8),(9,'Crossbody bags',8),(10,'Shoulder bags',8),(11,'Tote bags',8),(12,'Beauty bags',8),(13,'Clutch bags',8),(14,'Wallets',8),(15,'Backpacks ',8),(16,'Duffle bags',8),(17,'Suitcases',8),(18,'Bracelets',10),(19,'Earrings',10),(20,'Necklaces',10),(21,'Rings',10),(22,'Brooches ',10),(23,'Bag charms',10),(24,'Sets',10),(25,'Watches',10),(26,'Other',10),(27,'Headwear',7),(28,'Belts',7),(29,'Scarfs',7),(30,'Glasses',7),(31,'Gloves',7),(32,'Pocket squares ',7),(33,'Ties',7),(34,'Cufflinks',7),(35,'Shorts',6),(36,'Trousers ',6),(37,'Jeans ',6),(38,'Knit',6),(39,'T-shirts',6),(40,'Polos ',6),(41,'Shirts',6),(42,'Sweaters & Hoodies ',6),(43,'Suits & sets',6),(44,'Blazers',6),(45,'Coats',6),(46,'Jackets ',6),(47,'Nightwear',6),(48,'Socks ',6),(49,'Beachwear',6),(50,'Sportswear',6),(51,'Skirts',2),(52,'Shorts',2),(53,'Trousers ',2),(54,'Jeans ',2),(55,'Dresses',2),(56,'Knit',2),(57,'Tops ',2),(58,'Shirts',2),(59,'Sweaters & Hoodies ',2),(60,'Jumpsuits ',2),(61,'Suits & sets',2),(62,'Blazers',2),(63,'Coats',2),(64,'Jackets ',2),(65,'Nightwear',2),(66,'Lingerie ',2),(67,'Ponchos ',2),(68,'Socks ',2),(69,'Bikinis',2),(70,'Beachwear',2),(71,'Sportswear',2),(72,'Headwear',5),(73,'Hair accessories',5),(74,'Belts',5),(75,'Scarfs',5),(76,'Glasses',5),(77,'Gloves',5),(78,'Pocket squares ',5),(79,'Sneakers',9),(80,'Heels',9),(81,'Sandals ',9),(82,'Boots',9),(83,'Loafers',9),(84,'Slippers',9),(85,'Lace ups ',9),(86,'Flip Flops',9),(87,'Flats ',9),(88,'Handbags',3),(89,'Crossbody bags',3),(90,'Shoulder bags',3),(91,'Tote bags',3),(92,'Beauty bags',3),(93,'Clutch bags',3),(94,'Wallets',3),(95,'Backpacks ',3),(96,'Duffle bags',3),(97,'Suitcases',3),(98,'Bracelets',4),(99,'Earrings',4),(100,'Necklaces',4),(101,'Rings',4),(102,'Brooches ',4),(103,'Bag charms',4),(104,'Sets',4),(105,'Watches',4),(106,'Other',4);
/*!40000 ALTER TABLE `clothing_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(150) NOT NULL,
  `VAT_number` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Henning','123');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand_id` int NOT NULL,
  `clothing_article_id` int NOT NULL,
  `seller_id` int NOT NULL,
  `status_id` int NOT NULL DEFAULT '1',
  `condition_id` int NOT NULL,
  `model_name` varchar(150) DEFAULT NULL,
  `description` varchar(2000) NOT NULL,
  `price` double NOT NULL,
  `date_uploaded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `brand_id` (`brand_id`),
  KEY `clothing_article_id` (`clothing_article_id`),
  KEY `seller_id` (`seller_id`),
  KEY `status_id` (`status_id`),
  KEY `condition_id` (`condition_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`clothing_article_id`) REFERENCES `clothing_article` (`id`),
  CONSTRAINT `product_ibfk_3` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `product_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `product_status` (`id`),
  CONSTRAINT `product_ibfk_5` FOREIGN KEY (`condition_id`) REFERENCES `product_condition` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,254,2,8,2,3,'lol','dehbzhtedhj',1000,'2025-05-21 11:03:13'),(2,28,47,8,2,2,'lolololol','oigowesjgwesgwse',200,'2025-05-23 17:43:49');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_condition`
--

DROP TABLE IF EXISTS `product_condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_condition` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_condition`
--

LOCK TABLES `product_condition` WRITE;
/*!40000 ALTER TABLE `product_condition` DISABLE KEYS */;
INSERT INTO `product_condition` VALUES (1,'new'),(2,'Very Good Condition'),(3,'Good Condition'),(4,'Average Condition'),(5,'Below Average Condition');
/*!40000 ALTER TABLE `product_condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `image_url` text NOT NULL,
  `uploaded_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `product_image_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (1,1,'/uploads/products/product_1_1026e7dd-9eae-4926-b4ec-c17f49edf5c4.webp','2025-05-21 11:03:13'),(2,2,'/uploads/products/product_2_a4c0b21c-0386-4ae1-a2c3-435b4b770916.jpg','2025-05-23 17:43:49');
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_status`
--

DROP TABLE IF EXISTS `product_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_status`
--

LOCK TABLES `product_status` WRITE;
/*!40000 ALTER TABLE `product_status` DISABLE KEYS */;
INSERT INTO `product_status` VALUES (1,'review'),(2,'listed'),(3,'sold');
/*!40000 ALTER TABLE `product_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL,
  `role` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'user'),(2,'employee'),(3,'admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `subcategory_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategory`
--

LOCK TABLES `subcategory` WRITE;
/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
INSERT INTO `subcategory` VALUES (1,'Shoes',1),(2,'Clothing',2),(3,'Bags & Luggage',2),(4,'Jewelry',2),(5,'Accessories',2),(6,'Clothing',1),(7,'Accessories',1),(8,'Bags & Luaggage',1),(9,'Shoes',2),(10,'Jewelry',1),(11,'Tech',3),(12,'Furniture',3),(13,'Decoration',3),(14,'Textiles',3);
/*!40000 ALTER TABLE `subcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fName` varchar(100) NOT NULL,
  `lName` varchar(100) NOT NULL,
  `display_name` varchar(100) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `profile_picture` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login` timestamp NULL DEFAULT NULL,
  `shipping_full_name` varchar(255) DEFAULT NULL,
  `shipping_address` text,
  `shipping_zip` varchar(20) DEFAULT NULL,
  `shipping_city` varchar(100) DEFAULT NULL,
  `shipping_country` varchar(100) DEFAULT NULL,
  `role_id` int NOT NULL DEFAULT '1',
  `company_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `role_id` (`role_id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'rfdegrs<gh','gr<sgsr<g','hr<wgs<gr','ghr<g<erws','bob@gmail.com','$2a$10$SB9c.aPgb09IdPEILLP8/OvcWODNEc9SvzEmOSbr1Q.9VKcjm2pZa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(5,'rgw<gr<sg','r<g<srgrg','sd<gfdsg<sfgf<sd','gfsd<gdf<sgs<','bob1@gmail.com','$2a$10$/NqGIma9JEEMq.UmyKmYPu..QMj0oWULpvyt3ymEA6Ze0Xs9a.gae',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(6,'erghrwesg','erhrhah','aerheadrheh','erhhedrhe','bob5@gmail.com','$2a$10$seeebhMc6s02X7OjzNs6vO0YF8aPTj01DlTlM5dVwpb7yxbcLy0mK','','2025-05-16 11:51:19','2025-05-16 11:51:19',NULL,NULL,NULL,NULL,NULL,1,NULL),(7,'rghregrg','wsrgrwegw','gwrgwrg','test','test@gmail.com','$2a$10$DeCzvEqa3mK8EHDNyp4EhuCU2DP7B7gYAwwyJwzxOy3AMo2x/5s2W','https://i.redd.it/5ahxmv9b6nfe1.jpeg','2025-05-16 12:09:47','2025-05-16 12:09:47',NULL,'',NULL,NULL,NULL,1,NULL),(8,'test1','ter','gqefg','test1','test1@gmail.com','$2a$10$v3ZhQDLII6y5Ay79i2FKI.GJhD0Dd/vkA5OaCuCzwKNjfjtV4CoUq','/uploads/profile_8_94227318-6434-4517-aa60-6844a570b229.jpg','2025-05-17 12:58:47','2025-05-24 11:34:08',NULL,NULL,NULL,NULL,NULL,1,1),(9,'bob','bobbyy','bob','bob','bob3@gmail.com','$2a$10$Wdx38hvf9rLFNal8oLHhSexByuWpmN7BCR3EV/NpzPNwZB7Ge5x/e',NULL,'2025-05-20 19:56:16','2025-05-20 20:01:46',NULL,NULL,NULL,NULL,NULL,1,NULL),(10,'mik','mik','mik','mik','mik@gmail.com','$2a$10$P8amtN03gaWAdTZBBA29T.z9X/sLKeE/7JI2SsCXGuD.shma2K8jS',NULL,'2025-05-20 20:02:15','2025-05-20 20:02:15',NULL,NULL,NULL,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-28 10:39:20
