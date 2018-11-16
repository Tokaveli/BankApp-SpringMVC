/* Hasło: haslo123 */
INSERT INTO accounts(login, password_hash, account_number, balance) VALUES
('123456789', 'a15f8ae07675bfb96e084bfb4f52fb2c22091061aae86e0eb76a55f4e52dd74e',
 '68000023673478238965342345', 1500000);

INSERT INTO addresses(street, house_number, city, zip_code) VALUES
('Piłsudskiego', '53', 'Siedlce', '08-110');

INSERT INTO users(
	id_account, id_address,
    firstname, lastname, pin,
    series_number, expiry_date,
    birth_date, birthplace, mothers_maiden_name,
    email, phone_number
) VALUES (
	1, 1,
	'Jan', 'Nowak', '94020405678',
    'AVL112477', '2020-12-30',
    '1990-02-21', 'Warszawa', 'Kowalska',
    'nowak@gmail.com', '668456223'
);

INSERT INTO deposits(id_account, period, amount, open_date, close_date) VALUES
(1, 24, 1000000, '2017-02-05', '2017-08-05');

INSERT INTO transfers(
	id_account,
    receiver_name, receiver_address,
    account_number, amount, title,
    send_date
) VALUES
(1, 'Waldemar Brzoza', 'ul. Akacjowa 53, 08-110 Siedlce', '32222223673474862965344756', 100000, 'Rozliczenie', '2017-01-01');

INSERT INTO debit_cards(id_account, card_number, pin, cvv, expiry_date) VALUES
(1, '4567124965863265', '7445', '245', '2022-08-01');