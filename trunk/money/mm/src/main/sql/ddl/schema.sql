create schema mm;
create user mm; 
create user 'mm'identified by 'mm';
grant all privileges on mm to mm;

INSERT INTO `mm`.`category` (`category_id`, `description`, `name`) VALUES ('1', 'Grocery', 'Grocery');
INSERT INTO `mm`.`category` (`category_id`, `description`, `name`) VALUES ('2', 'Cash Withdrawal', 'Cash Withdrawl');

INSERT INTO `mm`.`transaction` (`transaction_id`, `account_name`, `account_number`, `balance`, `description`, `type`, `value`, `category_id`) VALUES ('1', 'RBS Royalties Gold', '00140213', '100', 'Tesco Shopping', 'POS', '50', '1');
INSERT INTO `mm`.`transaction` (`transaction_id`, `account_name`, `account_number`, `balance`, `description`, `type`, `value`, `category_id`) VALUES ('2', 'RBS Royalties Gold', '00140213', '100', 'Lloyds Cashline', 'ATM', '50', '2');

