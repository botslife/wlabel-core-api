
USE `core`;
LOCK TABLES `hibernate_sequence` WRITE;
INSERT INTO `hibernate_sequence` VALUES (0);
UNLOCK TABLES;

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES ('GBR','GB',53.3,12.2,'Great Britian'),('IND','IN',53.3,12.2,'India'),('SGP','SG',53.3,12.2,'Singapore');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,27.47,77.68,'Aylesbury','HP219XZ','Bucks','BC','GBR'),(4,27.47,77.68,'Visakhapatnam','530001','Andhra Pradesh','AP','IND'),(6,27.47,77.68,'Singapore','NOCODE','Singapore','SG','SGP');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `venue` WRITE;
/*!40000 ALTER TABLE `venue` DISABLE KEYS */;
INSERT INTO `venue` VALUES (2,'92','Dalesford Road','Almost at the end of the road','Aylesbury TM Centre',28.52,83.59,'Aylesbury','HP219XZ',1000,1),(5,'1009','RTC Depot','Close to Supermarket','Simhachalam TM centre',28.52,83.59,'Simhachalam TM','530053',1089,4),(7,'The Councourse','Floor 12, Flat 2034','Opposite to food restaurent','GR Councourse',28.52,83.59,'Singapore Concourse','CONCOURSE',9,6);
/*!40000 ALTER TABLE `venue` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `site` WRITE;
/*!40000 ALTER TABLE `site` DISABLE KEYS */;
INSERT INTO `site` VALUES (3,'uk.tm.org','UK site','ACTIVE','GBR'),(13,'sgp.tm.org','Singapore site','ACTIVE','SGP'),(14,'in.tm.org','India site','ACTIVE','IND');
/*!40000 ALTER TABLE `site` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `site_settings` WRITE;
/*!40000 ALTER TABLE `site_settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_settings` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `centre` WRITE;
/*!40000 ALTER TABLE `centre` DISABLE KEYS */;
INSERT INTO `centre` VALUES (9,1,'Aylesbury TM centre ','raja@tm.org','fullimage.url','00447508472911','Aylesbury TM centre','00447508472922','ACTIVE','thumbimaege.url','COUNTRYPARENT','tm.org/aylesbury',1,3),(15,1,'Singapore Councourse TM centre - the only TM centre in Singapore ','raja@tm.org','fullimage.url','00447508472911','Singapore TM centre','00447508472922','ACTIVE','thumbimaege.url','COUNTRYPARENT','tm.org/singapore',6,13),(19,1,'Visakhapatnam IT hills TM centre - Medidate right on the beach ','raja@tm.org','fullimage.url','00447508472911',' Vizag TM centre','00447508472922','ACTIVE','thumbimaege.url','COUNTRYPARENT','tm.org/singapore',4,14);
/*!40000 ALTER TABLE `centre` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `centre_settings` WRITE;
/*!40000 ALTER TABLE `centre_settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `centre_settings` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `site_centre` WRITE;
/*!40000 ALTER TABLE `site_centre` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_centre` ENABLE KEYS */;
UNLOCK TABLES;
