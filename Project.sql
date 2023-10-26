use testdb;
drop table if exists User; 
CREATE TABLE if not exists User( 
    username VARCHAR(50) NOT NULL, 
    password VARCHAR(20) NOT NULL, 
    role VARCHAR(15) NOT NULL, 
    PRIMARY KEY (username) ); 
    insert into User(username, password, role)
    values ('davidsmith@gmail.com','david123', 'David Smith'),
			('susie@gmail.com','susie1234', 'Client'),
            ('sophie@gmail.com', 'sophie1234', 'Client'),
            ('angelo@gmail.com','angelo1234', 'Client'),
            ('rudy@gmail.com','rudy1234', 'Admin'),
            ('jeannette@gmail.com','jeannette123','Client'),
            ('root','pass1234', 'Default');
select * from User;