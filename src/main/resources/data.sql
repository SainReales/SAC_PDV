insert  into products (`id`,`created_at`,`enable`,`iva`,`name`,`price`,`stock`) values (1,NULL,'',0,'Varilla Hierro 3x3',25999,5),(2,NULL,'',0,'Varilla Aluminio 3x3',22555,4),(3,NULL,'',0,'Lamina Aluminio 2x2',14999,5),(4,'2022-04-17','',2,'Sony Notebook Z110',37990,0),(5,'2022-04-17','',3,'Hewlett Packard Multifuncional F2280',69990,2),(6,'2022-04-17','',4,'Bianchi Bicicleta Aro 26',69990,2),(7,'2022-04-17','',5,'Mica Comoda 5 Cajones',299990,1),(8,'2022-04-17','',4,'Panasonic Pantalla LCD',259990,2),(9,'2022-04-17','',5,'Sony Camara digital DSC-W320B',123490,2),(10,'2022-04-17','',3,'Apple iPod shuffle',1499990,2),(11,'2022-04-17','',2,'Sony Notebook Z110',37990,0),(12,'2022-04-17','',3,'Hewlett Packard Multifuncional F2280',69990,2),(13,'2022-04-17','',4,'Bianchi Bicicleta Aro 26',69990,2),(14,'2022-04-17','',5,'Mica Comoda 5 Cajones',299990,2),(15,'2022-04-17','',4,'Panasonic Pantalla LCD',259990,2),(16,'2022-04-17','',5,'Sony Camara digital DSC-W320B',123490,2),(17,'2022-04-17','',3,'Apple iPod shuffle',1499990,2),(18,'2022-04-17','',2,'Sony Notebook Z110',37990,2),(19,'2022-04-17','',3,'Hewlett Packard Multifuncional F2280',69990,2),(20,'2022-04-17','',4,'Bianchi Bicicleta Aro 26',69990,2),(21,'2022-04-17','',5,'Mica Comoda 5 Cajones',299990,2),(22,'2022-04-23','',0,'lamina lata 2ss2',12123,5),(23,NULL,'\0',0,'Bloque 2x2',1233,4);



insert  into users (`id`,`enable`,`password`,`username`) values (1,'\0',NULL,'andres'),(2,'','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','admin'),(3,'','$2a$10$DmuCBnGToz4wYmaJ5Czi3OPDKtTC1W.SMzmnLyJj0pOTHvqdxHPzG','sain'),(6,'','$2a$10$yrY4bPVtj2YboQAhsb4n4uOyIxFlIelwLNZKWYFh9rqSITfSE/Tiq','pedro'),(7,'\0',NULL,'luis'),(8,'','$2a$10$I5TR14a.dnzLATvRiB023OoWpHEvOp.vyO.V1jqp.ZFm9S3U0iyi.','pablo');

insert  into authorities (`id`,`authority`,`user_id`) values (12,'ROLE_USER',1),(2,'ROLE_ADMIN',2),(3,'ROLE_USER',2),(4,'ROLE_ADMIN',3),(8,'ROLE_USER',6),(10,'ROLE_USER',7),(11,'ROLE_USER',8);

insert  into clients (`id`,`created_at`,`direction`,`email`,`foto`,`identification`,`lastname`,`name`,`phone`) values (1,NULL,'Cra 71a #79a-82','elbananoclasico@gmail.com',NULL,'23323333223','Reales','Sain','+573007343352'),(2,NULL,'fffffffff','juan@gmail.com',NULL,'23323333223','Gonzales','James','2323223222'),(3,NULL,'fffffffff','juan@gmail.com',NULL,'23323333223','Perez','Richard','2323223222'),(4,'2022-04-17','fffffffff','juan@gmail.com',NULL,'23323333223','Perez','Janie','2323223222'),(5,'2022-04-17','fffffffff','juan@gmail.com',NULL,'23323333223','Perez','Linus','2323223222'),(6,'2022-04-17','fffffffff','juan@gmail.com',NULL,'23323333223','Perez','Johnny','2323223222'),(7,NULL,'csabcu','edgar@gmail.com',NULL,'12132132','perez','edgar','112134444'),(8,'2022-05-15','cra 45','elbicho@gmail.com',NULL,'121323','ronaldo','cristiano','2324242');



insert  into invoices (`id`,`created_at`,`description`,`observation`,`client_id`,`user_id`) values (3,'2022-04-18','xa','cac',5,2),(4,'2022-04-23','Laminas','laminas',5,2),(5,'2022-04-23','Laminas','laminas',1,2),(6,'2022-04-23','Varillas','varilas',5,2),(7,'2022-04-23','Varillas','dss',1,7),(8,'2022-04-25','bloque','bloque',1,2),(9,'2022-04-30','Varillas','<sca',1,2),(10,'2022-04-30','va alu','axa',2,2),(11,'2022-04-30','la hi','dadca',7,2),(12,'2022-05-13','aasda','daaxa',1,2);

insert  into invoice_items (`id`,`itbis`,`price`,`quatity`,`product_id`,`invoice_id`) values (4,5,299990,1,7,3),(5,0,14999,1,3,4),(6,0,25999,1,1,4),(7,0,14999,1,3,5),(8,0,12123,1,22,5),(9,0,25999,1,1,6),(10,0,22555,1,2,6),(11,2,37990,2,4,6),(12,0,22555,1,2,7),(13,0,1233,2,23,8),(14,2,37990,2,11,9),(15,0,22555,3,2,10),(16,0,25999,5,1,11),(17,0,22555,3,2,12);

-- INSERT INTO invoice_items (quatity, invoice_id, product_id) VALUES(1, 1, 5);
-- INSERT INTO invoice_items (quatity, invoice_id, product_id) VALUES(1, 1, 7);
-- INSERT INTO invoices (description, observation, client_id, created_at,user_id) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW(),1);
-- INSERT INTO invoice_items (quatity, invoice_id, product_id) VALUES(3, 2, 6);

INSERT INTO global_config(company_name, company_phone, company_address, company_nit, company_email, invoice_footer) values('xxxxx c.a','334344323','xxxxxxxxxx','d3344f3f4f43','company@gmail.com','the footer')
