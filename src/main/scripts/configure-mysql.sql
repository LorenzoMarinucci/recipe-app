#conectarse y ejecutar como usuario root
#crear las bases de datos
CREATE DATABASE rec_dev;
CREATE DATABASE rec_prod;

#crear las cuentas de servicio
CREATE USER 'rec_dev_user'@'localhost' IDENTIFIED BY 'lor';
CREATE USER 'rec_prod_user'@'localhost' IDENTIFIED BY 'lor';

#grants
GRANT SELECT ON rec_dev.* to 'rec_dev_user'@'localhost';
GRANT INSERT ON rec_dev.* to 'rec_dev_user'@'localhost';
GRANT DELETE ON rec_dev.* to 'rec_dev_user'@'localhost';
GRANT UPDATE ON rec_dev.* to 'rec_dev_user'@'localhost';
GRANT SELECT ON rec_prod.* to 'rec_prod_user'@'localhost';
GRANT INSERT ON rec_prod.* to 'rec_prod_user'@'localhost';
GRANT DELETE ON rec_prod.* to 'rec_prod_user'@'localhost';
GRANT UPDATE ON rec_prod.* to 'rec_prod_user'@'localhost';
GRANT SELECT ON rec_dev.* to 'rec_dev_user'@'%';
GRANT INSERT ON rec_dev.* to 'rec_dev_user'@'%';
GRANT DELETE ON rec_dev.* to 'rec_dev_user'@'%';
GRANT UPDATE ON rec_dev.* to 'rec_dev_user'@'%';
GRANT SELECT ON rec_prod.* to 'rec_prod_user'@'%';
GRANT INSERT ON rec_prod.* to 'rec_prod_user'@'%';
GRANT DELETE ON rec_prod.* to 'rec_prod_user'@'%';
GRANT UPDATE ON rec_prod.* to 'rec_prod_user'@'%';