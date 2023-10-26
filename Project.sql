use testdb;
drop table if exists User; 
CREATE TABLE if not exists User( 
    email VARCHAR(50) NOT NULL, 
    firstName VARCHAR(10) NOT NULL, 
    lastName VARCHAR(10) NOT NULL, 
    password VARCHAR(20) NOT NULL, 
    role VARCHAR(15) NOT NULL, 
    adress_street_num VARCHAR(4) , 
    adress_street VARCHAR(30) , 
    adress_city VARCHAR(20), 
    adress_state VARCHAR(2),
    adress_zip_code VARCHAR(5),
    credit_card VARCHAR (16),
    phone_number VARCHAR(10),
    cash_bal DECIMAL(13,2) DEFAULT 1000,
    PPS_bal DECIMAL(13,2) DEFAULT 0,
    PRIMARY KEY (email) ); 
    insert into User(email, firstName, lastName, password, role, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, credit_card, phone_number, cash_bal, PPS_bal)
    values ('davidsmith@gmail.com', 'David', 'Smith','david123', 'David Smith', '2468', 'yolos street', 'ides', 'CM', '24680', '1234123412341234', '3134635264', '1000', '0'),
			('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', 'Client', '1234', 'whatever street', 'detroit', 'MI', '48202', '2345234523452345', '2487354635', '1000', '0'),
            ('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', 'Client', '2468', 'yolos street', 'ides', 'CM', '24680', '3456345634563456', '3132548736', '1000', '0'),
            ('angelo@gmail.com', 'Angelo', 'Francis','angelo1234', 'Client', '4680', 'egypt street', 'lolas', 'DT', '13579','4567456745674567', '2487609832', '1000', '0'),
            ('rudy@gmail.com', 'Rudy', 'Smith','rudy1234', 'Admin', '1234', 'sign street', 'samo ne tu','MH', '09876', '5678567856785678', '2483658352', '1000', '0'),
            ('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', 'Client', '0981', 'snoop street', 'kojik', 'HW', '87654', '6789678967896789', '3132645375', '1000', '0'),
            ('root', 'default', 'default','pass1234', 'Default', '0000', 'Default', 'Default', '0', '00000', '0000000000000000', '0000000000', '1000','1000000000');
select * from User;